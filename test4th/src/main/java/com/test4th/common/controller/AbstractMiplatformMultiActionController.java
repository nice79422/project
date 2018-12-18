package com.test4th.common.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.test4th.common.mapper.DatasetBeanMapper;
import com.tobesoft.platform.PlatformConstants;
import com.tobesoft.platform.PlatformRequest;
import com.tobesoft.platform.PlatformResponse;
import com.tobesoft.platform.data.DatasetList;
import com.tobesoft.platform.data.PlatformData;
import com.tobesoft.platform.data.VariableList;


public class AbstractMiplatformMultiActionController extends AbstractController{
    protected DatasetBeanMapper datasetBeanMapper;
    public void setDatasetBeanMapper(DatasetBeanMapper datasetBeanMapper){
    	this.datasetBeanMapper = datasetBeanMapper;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PlatformRequest platformRequest = new PlatformRequest(request, "UTF-8");
        platformRequest.receiveData();

        PlatformData inData = platformRequest.getPlatformData();
        PlatformData outData = new PlatformData(new VariableList(), new DatasetList());

        String methodName = request.getParameter("method");
        Class<? extends AbstractMiplatformMultiActionController> controllerClass = getClass();

        Method method = null;
        try {
            method = controllerClass.getDeclaredMethod(methodName, PlatformData.class, PlatformData.class);
            method.invoke(this, inData, outData);                     
            
            outData.getVariableList().addStr("ErrorCode", "0");
            outData.getVariableList().addStr("ErrorMsg", "success");
        } catch(InvocationTargetException invocationTargetException) {
            Throwable mainException = invocationTargetException.getCause();
            /*
             * 		위의 try{ }안에 예외 발생할 여지가 있는 부분이 있는데 예외가 controller에서 발생하고
             * 		reflection에서는 발생하지 않는경우, 예외가 reflection에 관한 예외가 장황하게 나온뒤
             * 		controller에 관한 예외가 나오는데 이때, controller관련된 예외만 출력할때 유용하다
             * */
            mainException.printStackTrace();
            System.out.println("AbstractMiplatformMultiActionController오류");
            outData.getVariableList().addStr("ErrorCode", "-1");
            outData.getVariableList().addStr("ErrorMsg", mainException.getMessage());
        } catch(Exception exception) {
        	
            exception.printStackTrace();
            System.out.println("AbstractMiplatformMultiActionController 이외 오류");
            outData.getVariableList().addStr("ErrorCode", "-1");
            outData.getVariableList().addStr("ErrorMsg", exception.getMessage());
        }

        PlatformResponse platformResponse = new PlatformResponse(response, PlatformConstants.XML, "UTF-8");
        platformResponse.sendData(outData);

        /* outData.getVariableList().printVariables(); */
        outData.clear();
        outData = null;

        return null;
    }
}