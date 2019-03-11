package kr.co.seoulit.common.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
@Component
public class DataSetView extends AbstractView {

	/*
	 * private static final Gson gson = new
	 * GsonBuilder().serializeNulls().setPrettyPrinting().create(); private static
	 * final String encoding = "utf-8"; private static final String client =
	 * "nexacro";
	 *
	 */

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

	} // renderMergedOutputModel 메소드 끝

}
