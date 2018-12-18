package com.test4th.common.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test4th.common.annotation.Column;
import com.test4th.common.annotation.Remove;
import com.tobesoft.platform.PlatformConstants;
import com.tobesoft.platform.data.Dataset;
import com.tobesoft.platform.data.DatasetList;
import com.tobesoft.platform.data.PlatformData;
import com.tobesoft.platform.data.Variant;

public class DatasetBeanMapper {

	/* 반환형 앞의 <T>는 제네릭메서드 이다 */
    private <T> String getDatasetName(Class<T> classType) {
    	/* 해당클래스의 annotation이 있으면 클래스의 annotation 을 반환 그렇지않으면 null을 반환 */
        return classType.getAnnotation(com.test4th.common.annotation.Dataset.class).name();
    }

    private String getColumnName(Method method) {
        String columnName = null;
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Column) {
                String annoValue = ((Column) annotation).name();
                columnName = annoValue;
            }
        }

        if (annotations.length == 0)
            columnName = formattingToSnake(method.getName());
        return columnName;
    }

    private String formattingToSnake(String name) {
        String regex = "([a-z])([A-Z])";
        String replacement = "$1_$2";

        name = name.substring(3);
        name = name.replaceAll(regex, replacement);
        return name.toLowerCase();
    }

    @SuppressWarnings("unused")
    private String formattingToCamel(String name) {
        String regex = "([a-z])([A-Z])";

        return name;
    }

    private void setColumnName(Dataset dataset, Method method, Map<String, String> nameMapper) {
        if(method.getName().startsWith("get")) {
            Column column = method.getAnnotation(Column.class);
            Remove remove = method.getAnnotation(Remove.class);

            if(column != null) {
                dataset.addStringColumn(column.name());
                nameMapper.put(column.name(), method.getName());
            } else if(column == null && remove == null) {
                String columnName = formattingToSnake(method.getName());

                dataset.addStringColumn(columnName);
                nameMapper.put(columnName, method.getName());
            }
        }
    }

    private <T> void setColumn(Dataset dataset, Map<String, String> nameMapper, T bean) throws Exception {
        int rowIndex = dataset.appendRow();

        for(String columnName : nameMapper.keySet()) {
            String methodName = nameMapper.get(columnName);

            Method method = bean.getClass().getDeclaredMethod(methodName);
            Variant variant = new Variant(method.invoke(bean));
            dataset.setColumn(rowIndex, columnName, variant);
        }
    }

    private <T> T setBean(Dataset dataset, Class<T> classType, int rowIndex) throws Exception {
        T bean = classType.newInstance();
        Method[] methods = classType.getDeclaredMethods();
        Method statusSetMethod = classType.getMethod("setStatus", String.class);
        statusSetMethod.invoke(bean, dataset.getRowStatus(rowIndex));

        for(Method method : methods) {
            if(method.getName().startsWith("set")) {
                String columnName = getColumnName(method);
                System.out.println("\n컬럼명::"+columnName);
                if(columnName != null) {
                    int columnIndex = dataset.getColumnIndex(columnName);
                    System.out.println("\n칼럼인덱스::"+columnIndex);
                    Variant variant = dataset.getColumn(rowIndex, columnIndex);
                    System.out.println("\n변수::"+variant);
                    if(variant != null) {
                        method.invoke(bean, variant.getString());
                        System.out.println("\n메서드실행::");
                    }
                }
            }
        }
        return bean;
    }

    private <T> T setDeleteBean(Dataset dataset, Class<T> classType, int rowIndex) throws Exception {
        T bean = classType.newInstance();
        Method[] methods = classType.getDeclaredMethods();
        Method statusSetMethod = classType.getMethod("setStatus", String.class);
        statusSetMethod.invoke(bean, "delete");

        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                String columnName = getColumnName(method);
                if (columnName != null) {
                    Variant columnValue = dataset.getDeleteColumn(rowIndex, columnName);
                    if (columnValue != null)
                        method.invoke(bean, columnValue.getString());
                }
            }
        }
        return bean;
    }

    public <T> List<T> datasetToBeans(PlatformData inData, Class<T> classType) throws Exception {
        List<T> beanList = new ArrayList<T>();
        String datasetName = getDatasetName(classType);
        Dataset dataset = inData.getDataset(datasetName);
        T bean = null;
        int rowCount = dataset.getRowCount();
        for(int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            bean = setBean(dataset, classType, rowIndex);
            beanList.add(bean);
        }
        rowCount = dataset.getDeleteRowCount();
        for(int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            bean = setDeleteBean(dataset, classType, rowIndex);
            beanList.add(bean);
        }
        return beanList;
    }

    public <T> void beansToDataset(PlatformData outData, List<T> beanList, Class<T> classType) throws Exception {
        Map<String, String> nameMapper = new HashMap<String, String>();
        DatasetList datasetList = outData.getDatasetList();

        String datasetName = getDatasetName(classType);
        System.out.println(datasetName);
        Dataset dataset = new Dataset(datasetName, PlatformConstants.CHARSET_UTF8, false, false);
        datasetList.addDataset(dataset);

        Method[] methods = classType.getDeclaredMethods();
        for(Method method : methods){
            setColumnName(dataset, method, nameMapper);
        }
        for(T bean : beanList)
            setColumn(dataset, nameMapper, bean);

    }

    public <T> T datasetToBean(PlatformData inData, Class<T> classType) throws Exception {
        T bean = null;

        String datasetName = getDatasetName(classType);
        Dataset dataset = inData.getDataset(datasetName);

        System.out.println(dataset+"\nbeanMapper입니다!");
        
        System.out.println(dataset.getDeleteRowCount()+"Bean이 있으면 뭐가 나오는지??0인지 다른 숫자인지?");

        if(dataset.getDeleteRowCount() == 0)
            bean = setBean(dataset, classType, 0);
        else
            bean = setDeleteBean(dataset, classType, 0);

        return bean;
    }

    public <T> void beanToDataset(PlatformData outData, T bean, Class<T> classType) throws Exception {
        Map<String, String> nameMapper = new HashMap<String, String>();
        DatasetList datasetList = outData.getDatasetList();

        String datasetName = getDatasetName(classType);
        Dataset dataset = new Dataset(datasetName, PlatformConstants.CHARSET_UTF8, false, false);

        datasetList.addDataset(dataset);

        if(bean != null) {
            Method[] methods = classType.getDeclaredMethods();


            for(Method method : methods)
                setColumnName(dataset, method, nameMapper);
        }


        setColumn(dataset, nameMapper, bean);
    }

    public void mapToDataset(PlatformData outData, String datasetName, List<Map<String, Object>> mapList) throws Exception {
        DatasetList datasetList = outData.getDatasetList();
        Dataset dataset = new Dataset(datasetName, PlatformConstants.CHARSET_UTF8, false, false);
        datasetList.addDataset(dataset);

        int rowIndex = 0;
        for(Map<String, Object> map : mapList) {
            rowIndex = dataset.appendRow();
            for(String key : map.keySet()) {
                String columnName = key; // formattingToSnake(key);

                dataset.addStringColumn(columnName);
                Variant variant = new Variant(map.get(key));
                dataset.setColumn(rowIndex, columnName, variant);
            }
        }
        dataset.printDataset();
    }

    public List<Map<String, Object>> datasetToMap(PlatformData inData, String datasetName) throws Exception {


        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

        Dataset dataset = inData.getDataset(datasetName);
        int rowCount = dataset.getRowCount();

        for(int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            Map<String, Object> map = new HashMap<>();
            map.put("status", dataset.getRowStatus(rowIndex));

            for(int colIndex = 0; colIndex < dataset.getColumnCount(); colIndex++) {
                String key = dataset.getColumnId(colIndex);
                Variant variant = new Variant(dataset.getColumn(rowIndex, colIndex));
                variant.setType(Variant.VT_OBJECT);
                Object value = variant.getObject();
                map.put(key, value);
            }
            mapList.add(map);
        }

        return mapList;
    }
}
