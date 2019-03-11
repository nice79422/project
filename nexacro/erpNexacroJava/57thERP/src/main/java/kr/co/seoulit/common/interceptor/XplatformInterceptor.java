package kr.co.seoulit.common.interceptor;

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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpPlatformRequest httpPlatformRequest = new HttpPlatformRequest(request);
        httpPlatformRequest.receiveData();

        PlatformData inData = httpPlatformRequest.getData();
        PlatformData outData = new PlatformData();

        debug(inData.getDataSetList(), inData.getVariableList());

        request.setAttribute("inData", inData);
        request.setAttribute("outData", outData);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        PlatformData outData = (PlatformData) request.getAttribute("outData");

        if(outData==null){
        	return;
        }

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