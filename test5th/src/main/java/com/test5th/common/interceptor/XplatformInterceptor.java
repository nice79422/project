package com.test5th.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tobesoft.xplatform.data.DataSetList;
import com.tobesoft.xplatform.data.Debugger;
import com.tobesoft.xplatform.data.PlatformData;
import com.tobesoft.xplatform.data.VariableList;
import com.tobesoft.xplatform.tx.HttpPlatformRequest;
import com.tobesoft.xplatform.tx.HttpPlatformResponse;
import com.tobesoft.xplatform.tx.PlatformType;

public class XplatformInterceptor extends HandlerInterceptorAdapter  {

	//요청을 가로채다 IN -> request,  response -> OUT 변경 시킨다.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	//전처리 
        HttpPlatformRequest httpPlatformRequest = new HttpPlatformRequest(request);
        httpPlatformRequest.receiveData();

        PlatformData inData = httpPlatformRequest.getData();
        PlatformData outData = new PlatformData();
        // 호출 된 것을 받는다. 
        
        //debug(inData.getDataSetList(), inData.getVariableList());
        
        //jsp 담는다.
        request.setAttribute("inData", inData);
        request.setAttribute("outData", outData);
        // controller에서 request.getAttribute("outData"); 이런 형식으로 꺼내어 쓴다. 
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //후처리(Exception 발생하면 실행 안됨.)
    	super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
       //후처리 Exception 발생하나 안하나 상관 없음 
    	PlatformData outData = (PlatformData) request.getAttribute("outData"); //형변환 request에 담긴걸 꺼낸다. 
        VariableList variableList = outData.getVariableList();

        if (exception != null) {
            exception.printStackTrace();
            variableList.add("ErrorCode", -1);
            variableList.add("ErrorMsg", exception.getMessage());
        } else {
            variableList.add("ErrorCode", 0);
            variableList.add("ErrorMsg", "success");
        }

        HttpPlatformResponse httpPlatformResponse = new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML, "UTF-8");

        httpPlatformResponse.setData(outData);
        httpPlatformResponse.sendData();

        debug(outData.getDataSetList(), outData.getVariableList());

        outData = null;
        super.afterCompletion(request, response, handler, exception);
    }

    private void debug(DataSetList dataSetList, VariableList variableList) {
        Debugger debugger = new Debugger();
        // DEBUG - DataSet
        for (int n = 0; n < dataSetList.size(); n++) {
            System.out.println(debugger.detail(dataSetList.get(n)));
        }
        // DEBUG - VariableList
        for (int n = 0; n < variableList.size(); n++) {
            System.out.println(debugger.detail(variableList.get(n)));
        }
    }
}
