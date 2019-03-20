package kr.co.seoulit.common.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.WordUtils;
import org.springframework.stereotype.Component;

import com.nexacro.xapi.data.DataSet;
import com.nexacro.xapi.data.DataSetList;
import com.nexacro.xapi.data.DataTypes;
import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.annotation.Column;
import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.annotation.Remove;


@Component
public class DatasetBeanMapper {

	public <T> List<T> datasetToBeans(PlatformData inData, Class<T> classType) throws Exception {
		String datasetName = getDataSetName(classType);
		DataSet dataset = inData.getDataSet(datasetName);

		List<T> beanList = new ArrayList<T>();
		T bean = null;
		int rowCount = dataset.getRowCount();
		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
			bean = getBean(dataset, classType, rowIndex);
			beanList.add(bean);
		}

		rowCount = dataset.getRemovedRowCount();
		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
			bean = getDeletedBean(dataset, classType, rowIndex);
			beanList.add(bean);
		}

		System.out.println("----- 3. 매퍼 : datasetToBeans 실행 -----");
		System.out.println("	매퍼에서 inData 주소 : " + inData);
		System.out.println("	" + datasetName + " : " + dataset.getRowCount() + "개의 레코드를 변환 ");
		System.out.println();

		return beanList;
	}

	public <T> T datasetToBean(PlatformData inData, Class<T> classType) throws Exception {
		T bean = null;
		String datasetName = getDataSetName(classType);
		DataSet dataset = inData.getDataSet(datasetName);

		if (dataset.getRemovedRowCount() == 0)
			bean = getBean(dataset, classType, 0);
		else
			bean = getDeletedBean(dataset, classType, 0);

		System.out.println("----- 3. 매퍼 : datasetToBean 실행 -----");
		System.out.println("	매퍼에서 inData 주소 : " + inData);
		System.out.println("	" + datasetName + " : 자바빈 " + bean + " 으로 변환 ");
		System.out.println();

		return bean;

	}

	public <T> void beansToDataset(PlatformData outData, List<T> beanList, Class<T> classType) throws Exception {

		Map<String, String> nameMap = new HashMap<String, String>();

		DataSetList datasetList = outData.getDataSetList();
		String datasetName = getDataSetName(classType);
		DataSet dataset = new DataSet(datasetName);
		datasetList.add(dataset);

		//Method[] methods = classType.getDeclaredMethods();
		Method[] methods = classType.getMethods();
		for (Method method : methods)
			setColumnName(dataset, nameMap, method);
		for (T bean : beanList)
			setColumnValue(dataset, nameMap, bean);

		System.out.println("----- 3. 매퍼 : beansToDataset 실행 -----");
		System.out.println("	매퍼에서 outData 주소 : " + outData);
		System.out.println("	" + datasetName + " : " + dataset.getRowCount() + " 개의 레코드 생성 ");
		System.out.println();
	}

	public <T> void beanToDataset(PlatformData outData, T bean, Class<T> classType) throws Exception {
		Map<String, String> nameMap = new HashMap<String, String>();
		DataSetList datasetList = outData.getDataSetList();

		String datasetName = getDataSetName(classType);
		DataSet dataset = new DataSet(datasetName);

		datasetList.add(dataset);

		if (bean != null) {
			Method[] methods = classType.getDeclaredMethods();
			for (Method method : methods)
				setColumnName(dataset, nameMap, method);
			setColumnValue(dataset, nameMap, bean);
		}

		System.out.println("----- 3. 매퍼 : beanToDataset 실행 -----");
		System.out.println("	매퍼에서 outData 주소 : " + outData);
		System.out.println("	자바빈 " + bean + " 을 데이터셋" + datasetName + " 으로 변환");
		System.out.println();

	}

	public void mapToDataset(PlatformData outData, List<Map<String, Object>> mapList, String datasetName)
			throws Exception {
		DataSetList datasetList = outData.getDataSetList();
		DataSet dataset = new DataSet(datasetName);
		datasetList.add(dataset);

		for (String key : mapList.get(0).keySet()) {
			// String columnName = key.toLowerCase();
			dataset.addColumn(key, DataTypes.STRING, 256); // columnName 을 key 로 수정
		}

		int rowIndex = 0;
		for (Map<String, Object> map : mapList) {
			rowIndex = dataset.newRow();
			for (String key : map.keySet()) {
				Object columnValue = map.get(key);
				dataset.set(rowIndex, key, columnValue); // key.toLowerCase() 을 key 로 수정
			}
		}

		System.out.println("----- 3. 매퍼 : mapToDataset 실행 -----");
		System.out.println("	매퍼에서 outData 주소 : " + outData);
		System.out.println("	맵 " + mapList);
		System.out.println("	을 데이터셋 " + datasetName + " 으로 변환");
		System.out.println("	생성된 레코드 : " + dataset.getRowCount());

		System.out.println();
	}

	public List<Map<String, Object>> datasetToMap(PlatformData inData, String datasetName) throws Exception {
		List<Map<String, Object>> mapList = new ArrayList<>();

		DataSet dataset = inData.getDataSet(datasetName);
		int rowCount = dataset.getRowCount();
		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
			Map<String, Object> map = new HashMap<>();
			map.put("status", dataset.getRowTypeName(rowIndex));

			for (int colIndex = 0; colIndex < dataset.getColumnCount(); colIndex++) {
				String key = dataset.getColumn(colIndex).getName();
				Object value = dataset.getObject(rowIndex, key);
				map.put(formattingToCamel(key), value);
			}
			mapList.add(map);
		}

		rowCount = dataset.getRemovedRowCount();
		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
			Map<String, Object> map = new HashMap<>();
			map.put("status", dataset.getRowTypeName(rowIndex));

			for (int colIndex = 0; colIndex < dataset.getColumnCount(); colIndex++) {
				String key = dataset.getColumn(colIndex).getName();
				Object value = dataset.getObject(rowIndex, key);
				map.put(formattingToCamel(key), value);
			}
			mapList.add(map);
		}

		System.out.println("----- 3. 매퍼 : datasetToMap 실행 -----");
		System.out.println("	매퍼에서 inData 주소 : " + inData);
		System.out.println("	데이터셋 " + datasetName + " 을 맵으로 변환");
		System.out.println("	생성된 레코드 : " + dataset.getRowCount());

		System.out.println();

		return mapList;
	}

	private <T> String getDataSetName(Class<T> classType) {
		if (classType.isAnnotationPresent(Dataset.class))
			return classType.getAnnotation(Dataset.class).name();
		else
			return "ds" + classType.getName().replace("Bean", "");
	}

	private String getColumnName(Method method) {
		String columnName = null;
		Annotation[] annotations = method.getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation instanceof Column) {
				String annotaionName = ((Column) annotation).name();
				columnName = annotaionName;
			}
		}

		if (annotations.length == 0)
			columnName = formattingToSnake(method.getName());
		return columnName;
	}

	private void setColumnName(DataSet dataset, Map<String, String> nameMap, Method method) {

		if (method.getName().startsWith("get")) {
			Column column = method.getAnnotation(Column.class);
			Remove remove = method.getAnnotation(Remove.class);

			if (column != null) {
				dataset.addColumn(column.name(), getDataType(method));
				nameMap.put(column.name(), method.getName());
			} else if (column == null && remove == null) {
				String columnName = formattingToSnake(method.getName());

				System.out.println("	컬럼명 : " + columnName);
				dataset.addColumn(columnName, getDataType(method));
				nameMap.put(columnName, method.getName());
			}
		}

	}

	private <T> void setColumnValue(DataSet dataset, Map<String, String> nameMap, T bean) throws Exception {
		int rowIndex = dataset.newRow();

		for (String columnName : nameMap.keySet()) {
			String methodName = nameMap.get(columnName);
		//	if(methodName!="CLASS"){
//			Method method = bean.getClass().getDeclaredMethod(methodName);
			Method method = bean.getClass().getMethod(methodName);
			Object value = method.invoke(bean);
			dataset.set(rowIndex, columnName, value);

		//	}
	//		Object value = method.invoke(bean);
		//	dataset.set(rowIndex, columnName, value);
		}
	}

	private <T> T getBean(DataSet dataset, Class<T> classType, int rowIndex) throws Exception {
		T bean = classType.newInstance();
		Method[] methods = classType.getDeclaredMethods();
		Method statusMethod = classType.getMethod("setStatus", String.class);
		statusMethod.invoke(bean, dataset.getRowTypeName(rowIndex));

		for (Method method : methods) {
			if (method.getName().startsWith("set")) {
				String columnName = getColumnName(method);
				if (columnName != null) {
					Object columnValue = dataset.getObject(rowIndex, columnName);
					if (columnValue != null)
						method.invoke(bean, columnValue);
				}
			}
		}
		return bean;
	}

	private <T> T getDeletedBean(DataSet dataset, Class<T> classType, int rowIndex) throws Exception {
		T bean = classType.newInstance();
		Method[] methods = classType.getDeclaredMethods();
		Method statusMethod = classType.getMethod("setStatus", String.class);
		statusMethod.invoke(bean, DataSet.ROW_TYPE_NAME_DELETED);

		for (Method method : methods) {
			if (method.getName().startsWith("set")) {
				String columnName = getColumnName(method);
				if (columnName != null) {
					Object columnValue = dataset.getRemovedData(rowIndex, columnName);
					if (columnValue != null)
						method.invoke(bean, columnValue);
				}
			}
		}
		return bean;
	}

	private int getDataType(Method method) {
		Class<?> returnType = method.getReturnType();
		if (returnType == Date.class)
			return DataTypes.DATE;
		else if (returnType == String.class)
			return DataTypes.STRING;
		else if (returnType == int.class || returnType == boolean.class)
			return DataTypes.INT;
		else if (returnType == BigDecimal.class)
			return DataTypes.BIG_DECIMAL;
		else if (returnType == double.class)
			return DataTypes.DOUBLE;
		else if (returnType == byte[].class)
			return DataTypes.BLOB;
		else
			return DataTypes.NULL;
	}

	private String formattingToSnake(String name) {
		String regex = "([a-z])([A-Z])";
		String replacement = "$1_$2";

		if (name.startsWith("set") || name.startsWith("get"))
			name = name.substring(3);
		// aA -> a_A
		name = name.replaceAll(regex, replacement);
		// return A_A
		return name.toUpperCase();

	}

	private String formattingToCamel(String name) {

		if (name.startsWith("set") || name.startsWith("get"))
			name = name.substring(3);
		String camel = WordUtils.capitalizeFully(name, new char[] { '_' }).replaceAll("_", "");
		return camel.substring(0, 1).toLowerCase() + camel.substring(1);
	}

}