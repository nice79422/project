package kr.co.seoulit.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nexacro.xapi.data.DataSetList;
import com.nexacro.xapi.data.Debugger;
import com.nexacro.xapi.data.PlatformData;
import com.nexacro.xapi.data.VariableList;
import com.nexacro.xapi.tx.HttpPlatformRequest;
import com.nexacro.xapi.tx.HttpPlatformResponse;
import com.nexacro.xapi.tx.PlatformException;
import com.nexacro.xapi.tx.PlatformType;

public class NexacroInterceptor extends HandlerInterceptorAdapter {

	// 요청을 가로채다 IN -> request, response -> OUT 변경 시킨다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println();
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("	" + request.getRequestURI() + " 호출됨");
		System.out.println("------------------------------------------------------------------------------");

		System.out
				.println("----- 1. 인터셉터에서 preHandle 호출, request 타입 : " + request.getDispatcherType().name() + " -----");
		System.out.println("	HttpServletRequest 주소 : " + request);
		System.out.println("	HttpServletResponse 주소 : " + response);
		System.out.println("	HttpServletResponse status : " + response.getStatus());
		System.out.println("	HttpServletResponse 커밋 여부 : " + response.isCommitted());
		System.out.println();

		String userAgent = request.getHeader("User-Agent");
		System.out.println("	userAgent : " + userAgent);
		System.out.println("	contentType : " + request.getContentType());
		System.out.println("	method : " + request.getMethod());

		System.out.println("	이후 수행될 컨트롤러 객체의 메서드 : " + handler.toString());

		// 1. 최초로 보낸 요청인 "REQUEST" 이고 "multipart" 아닌 경우 처리
		if (request.getDispatcherType().name() == "REQUEST" && request.getContentType() != null
				&& !request.getContentType().contains("multipart")) {

			// 전처리
			HttpPlatformRequest httpPlatformRequest = new HttpPlatformRequest(request);
			httpPlatformRequest.receiveData();

			PlatformData inData = httpPlatformRequest.getData();
			PlatformData outData = new PlatformData();

			System.out.println("	인터셉터에서 inData 주소 : " + inData);
			System.out.println("	인터셉터에서 outData 주소 : " + outData);
			System.out.println();

			// debug(inData.getDataSetList(), inData.getVariableList());

			request.setAttribute("inData", inData);
			request.setAttribute("outData", outData);

			return true;

			// 2. 최초로 보낸 요청인 "REQUEST" 이고 "multipart" 인 경우 처리
		} else if (request.getDispatcherType().name() == "REQUEST" && request.getContentType() != null
				&& request.getContentType().contains("multipart")) {

			PlatformData outData = new PlatformData();
			request.setAttribute("outData", outData);

			System.out.println("	multipart 파일 업로드 : outData 만 세팅 ");
			System.out.println("	인터셉터에서 outData 주소 : " + outData);
			System.out.println();
		} else if (request.getDispatcherType().name() != "REQUEST") {

			System.out.println("------------ 갈 길을 잃은 요청이 있습니다!!!! ---------------");

		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println(
				"----- 4. 인터셉터에서 postHandle 호출, request 타입 : " + request.getDispatcherType().name() + " -----");
		System.out.println("	HttpServletResponse status : " + response.getStatus());
		System.out.println("	HttpServletResponse 커밋 여부 : " + response.isCommitted());
		System.out.println("	수행된 컨트롤러의 메서드 : " + handler.toString());

		String viewName = null;

		if (modelAndView != null) {
			viewName = modelAndView.getViewName();
		}

		System.out.println("	컨트롤러에서 보낸 modelAndView : " + modelAndView);
		System.out.println("	컨트롤러에서 지정한 뷰 이름 : " + viewName);

		String userAgent = request.getHeader("User-Agent");
		System.out.println("	userAgent : " + userAgent);

		// 1. 최초로 보낸 요청인 "REQUEST" 인 경우만 처리, "FORWARD" 인 경우에는 처리하지 않음
		if (request.getDispatcherType().name() == "REQUEST") {

			// viewName 이 null 인 경우, response 는 갈 길을 잃음 : 아무 내용이 없는 dataSetView 를 지정
			// 다음 afterCompletion 메서드에서 구분할 수 있도록 viewName 속성을 지정
			if (viewName == null) {

				if (modelAndView != null) {
					modelAndView.setViewName("dataSetView");
				}

				request.setAttribute("viewName", "dataSetView");

			} else {
				request.setAttribute("viewName", viewName);
			}
		}

		System.out.println();
		// super.postHandle(request, response, handler, modelAndView);

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {

		System.out.println(
				"----- 5. 인터셉터에서 afterCompletion 호출, request 타입 : " + request.getDispatcherType().name() + " -----");

		System.out.println("	HttpServletRequest 주소 : " + request);
		System.out.println("	HttpServletResponse 주소 : " + response);
		System.out.println("	HttpServletResponse status : " + response.getStatus());
		System.out.println("	HttpServletResponse 커밋 여부 : " + response.isCommitted());
		System.out.println("	Exception : " + exception);

		// 넥사크로에서 보낸 Request 인지 확인 => 넥사크로에서 보낸 경우
		String userAgent = request.getHeader("User-Agent");
		System.out.println("	userAgent : " + userAgent);

		String viewName = (String) request.getAttribute("viewName");
		System.out.println("	요청 전달 완료된 뷰 이름 : " + viewName);

		// 1. 최초로 보낸 요청인 "REQUEST" 이고, 넥사크로에서 보낸 request 인 경우
		if (request.getDispatcherType().name() == "REQUEST" && userAgent != null
				&& userAgent.indexOf("nexacroplatform") != -1) {

			System.out.println(viewName+"	뷰 이름으로 switch 문 시작");

			switch (viewName) {

			/*
			 * 뷰가 dataSetView, uploadResultHandlingView
			 *
			 * => PlatformData 세팅 ,httpPlatformResponse 의 sendData() 호출
			 */
			case "dataSetView":
			case "uploadResultHandlingView":

				setHttpPlatformResponse(request, response);
				break;

			// 뷰가 dataSetView 가 아님 : jsonView 또는 jsp 파일 경로 등등.. => 그냥 두면 됨
			default:

			}

			// 2. 최초로 보낸 요청인 "REQUEST" 이고, 넥사크로에서 보낸 request 가 아닌 경우 => ex. 웹브라우저에서 호출한 경우
		} else if (request.getDispatcherType().name() == "REQUEST" && userAgent != null
				&& userAgent.indexOf("nexacroplatform") == -1) {

			// 여기는 사실 위의 if 문 내용과 동일함
			System.out.println("	넥사크로에서 보낸 요청 아님");

			switch (viewName) {

			/*
			 * 뷰가 dataSetView, uploadResultHandlingView
			 *
			 * => PlatformData 세팅 ,httpPlatformResponse 의 sendData() 호출
			 */
			case "dataSetView":
			case "uploadResultHandlingView":
				setHttpPlatformResponse(request, response);
				break;

			// 뷰가 dataSetView 가 아님 : jsonView 또는 jsp 파일 경로 등등.. => 그냥 두면 됨
			default:

			}

		}

	}

	private void setHttpPlatformResponse(HttpServletRequest request, HttpServletResponse response) {

		PlatformData outData = (PlatformData) request.getAttribute("outData");

		System.out.println("	outData 주소  : " + outData);
		System.out.println();

		VariableList variableList = outData.getVariableList();
		System.out.println("	variableList 변수명 : " + variableList.keyList());
		System.out.println("	variableList 값 : " + variableList.valueList());

		/*
		 * 예외 발생시 afterCompletion 호출 전에 CustomHandlerExceptionResolver 에서 미리 처리하고
		 *
		 * request 에 ErrorStatus 속성 지정 : 이 속성이 없는 경우, 정상 실행된 것임
		 */
		if (request.getAttribute("ErrorStatus") == null) {

			variableList.add("ErrorCode", 0);
			variableList.add("ErrorMsg", "success");

		}

		if (outData.getDataSetList().contains("dsUploadResult")) {

			System.out.println("	multipart 파일 업로드 결과 ( 데이터셋 dsUploadResult ) : "
					+ outData.getDataSetList().get("dsUploadResult"));

		}

		HttpPlatformResponse httpPlatformResponse = new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML,
				"UTF-8");

		// debug(outData.getDataSetList(), outData.getVariableList());

		httpPlatformResponse.setData(outData);

		try {

			httpPlatformResponse.sendData();

		} catch (PlatformException e) {

			System.out
					.println("----- 인터셉터의 afterCompletion : httpPlatformResponse.sendData() 에서 PlatformException 발생됨");
			e.printStackTrace();
		}

		outData = null;

	}

	@SuppressWarnings("unused")
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
