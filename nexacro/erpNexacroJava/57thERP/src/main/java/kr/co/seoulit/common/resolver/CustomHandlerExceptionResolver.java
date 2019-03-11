package kr.co.seoulit.common.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.nexacro.xapi.data.PlatformData;
import com.nexacro.xapi.data.VariableList;

import kr.co.seoulit.common.exception.CustomException;

public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		System.out.println();
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("	" + request.getRequestURI() + "resolveException 호출됨");
		System.out.println("------------------------------------------------------------------------------");

		ModelAndView mav = null;

		String userAgent = request.getHeader("User-Agent");
		ex.printStackTrace();

		if (ex instanceof CustomException) {

			// 넥사크로에서 보낸 request 인 경우
			if (userAgent != null && userAgent.indexOf("nexacroplatform") != -1) {

				mav = customException(request, response, handler, (CustomException) ex);

			}

		} else {

			mav = exception(request, response, handler, ex);

		}

		return mav;
	}

	public ModelAndView customException(HttpServletRequest request, HttpServletResponse response, Object handler,
			CustomException customEx) {

		PlatformData outData = (PlatformData) request.getAttribute("outData");
		VariableList variableList = outData.getVariableList();

		variableList.add("ErrorCode", customEx.getErrCode());
		variableList.add("ErrorMsg", customEx.getMessage());

		/*
		 * 여기서 예외가 catch 되면 인터셉터의 afterCompletion 메서드의 exception 은 null 값이 넘어감
		 *
		 * 예외 발생 여부를 구분할 수 있도록 ErrorStatus 속성을 request 에 부여
		 */
		request.setAttribute("ErrorStatus", customEx.getErrCode() + " " + customEx.getMessage());

		System.out.println("----- HandlerExceptionResolver 에서 customException 예외 처리됨");

		return new ModelAndView("dataSetView");

	}

	public ModelAndView exception(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		PlatformData outData = null;

		if (request.getAttribute("outData") != null) {

			outData = (PlatformData) request.getAttribute("outData");

		} else {

			outData = new PlatformData();
			request.setAttribute("outData", outData);

		}

		VariableList variableList = outData.getVariableList();

		variableList.add("ErrorCode", -100);
		variableList.add("ErrorMsg", ex.getMessage());

		/*
		 * 여기서 예외가 catch 되면 인터셉터의 afterCompletion 메서드의 exception 은 null 값이 넘어감
		 *
		 * 예외 발생 여부를 구분할 수 있도록 ErrorStatus 속성을 request 에 부여
		 */
		request.setAttribute("ErrorStatus", "-100 " + ex.getMessage());
		request.setAttribute("viewName","dataSetView");
		System.out.println("----- HandlerExceptionResolver 에서 exception 예외 처리됨");
		System.out.println("exception 예외 처리됨" + ex.getMessage());

		return new ModelAndView("dataSetView");

	}

}
