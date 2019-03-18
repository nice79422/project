package kr.co.seoulit.common.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.nexacro.xapi.data.PlatformData;
import com.nexacro.xapi.tx.HttpPlatformRequest;

public class PlatformDataHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {

		System.out.println("----- 2 (1) 핸들러 메서드 아규먼트 리졸버의 supportsParameter 시작 -----");

		System.out.println("	컨트롤러의 해당 파라미터 이름 : " + parameter.getParameterName());
		System.out.println("	컨트롤러의 해당 파라미터 타입 : " + parameter.getParameterType());
		System.out.println();

		Boolean flag = false;

		if (PlatformData.class.isAssignableFrom(parameter.getParameterType())) {

			flag = true;

		}

		return flag;

	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		System.out.println("----- 2 (2) 핸들러 메서드 아규먼트 리졸버의 resolveArgument 시작 -----");

		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

		String userAgent = request.getHeader("User-Agent");

		String paramName = parameter.getParameterName();
		Class<?> paramType = parameter.getParameterType();

		Object returnObj = null;

		// 넥사크로에서 보낸 request 인 경우
		if (userAgent != null && userAgent.indexOf("nexacroplatform") != -1) {

			System.out.println("	PlatformDataHandlerMethodArgumentResolver : 파라미터 조작 시작 ---");

			if (paramType == PlatformData.class && paramName.equals("inData")) {

// 전처리 : handlerInterceptor 에서 처리 중임
//				HttpPlatformRequest httpPlatformRequest = new HttpPlatformRequest(request);
//				httpPlatformRequest.receiveData();
//
//				PlatformData inData = httpPlatformRequest.getData();
//				request.setAttribute("inData", inData);

				returnObj = (PlatformData) request.getAttribute("inData");

			} else if (paramType == PlatformData.class && paramName.equals("outData")) {

				PlatformData outData = new PlatformData();
				request.setAttribute("outData", outData);

				returnObj = outData;

			} else if (paramType == HttpServletRequest.class) {

				// 전처리
				HttpPlatformRequest httpPlatformRequest = new HttpPlatformRequest(request);
				httpPlatformRequest.receiveData();

				PlatformData inData = httpPlatformRequest.getData();
				PlatformData outData = new PlatformData();

				request.setAttribute("inData", inData);
				request.setAttribute("outData", outData);

				returnObj = request;

				System.out.println("	핸들러 메서드 아규먼트 리졸버에서 inData 주소 : " + inData);
				System.out.println("	핸들러 메서드 아규먼트 리졸버에서 outData 주소 : " + outData);
				System.out.println();

			}

			// 넥사크로에서 보낸 request 가 아닌 경우
		} else {

			// 위의 내용 복붙 : 나중에 수정하자
			if (paramType == PlatformData.class && paramName.equals("inData")) {

// 전처리 : handlerInterceptor 에서 처리 중임
//				HttpPlatformRequest httpPlatformRequest = new HttpPlatformRequest(request);
//				httpPlatformRequest.receiveData();
//
//				PlatformData inData = httpPlatformRequest.getData();
//				request.setAttribute("inData", inData);

				returnObj = (PlatformData) request.getAttribute("inData");

			} else if (paramType == PlatformData.class && paramName.equals("outData")) {

				PlatformData outData = new PlatformData();
				request.setAttribute("outData", outData);

				returnObj = outData;

			} else if (paramType == HttpServletRequest.class) {

				// 전처리
				HttpPlatformRequest httpPlatformRequest = new HttpPlatformRequest(request);
				httpPlatformRequest.receiveData();

				PlatformData inData = httpPlatformRequest.getData();
				PlatformData outData = new PlatformData();

				request.setAttribute("inData", inData);
				request.setAttribute("outData", outData);

				returnObj = request;

				System.out.println("	핸들러 메서드 아규먼트 리졸버에서 inData 주소 : " + inData);
				System.out.println("	핸들러 메서드 아규먼트 리졸버에서 outData 주소 : " + outData);
				System.out.println();

			}
			
			
			
		}

		System.out.println("	" + paramName + " : " + returnObj + " 객체 주입");
		System.out.println();

		return returnObj;
	}

}
