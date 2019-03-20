package kr.co.seoulit.common.view;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
@Component
public class ExcelDownloadView extends AbstractView {

	private static final String CUSTOM_VALUE_KEY = "_custom";
	private static final String CUSTOM_FILE_NAME_KEY = "fileName";

	private static final String DEFAULT_CONTENT_TYPE = "ms-vnd/excel";
	private static final String DEFAULT_FILE_NAME = "test";

	private static final int HEADER_INDEX = 0;
	private static final int COLUMN_INDEX = 1;

	private Workbook workBook;
	private Sheet sheet;
	private Row row;
	private Cell cell;

	private CellStyle headStyle;
	private CellStyle bodyStyle;

	private int columnSize;
	private int rowSize;

	private List<String[]> customValueList;

	private String fileName;

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		initValue(model);

		List<Map<String, Object>> list = null;

		for (String key : model.keySet()) {

			if (key.endsWith(CUSTOM_VALUE_KEY)) {

				continue;

			}

			if (model.get(key) instanceof List<?>) {

				list = (List<Map<String, Object>>) model.get(key);

			} else {

				continue;

			}

			sheet = workBook.createSheet(key);
			customValueList = null;

			if (model.containsKey(key + CUSTOM_VALUE_KEY)) {

				customValueList = (List<String[]>) model.get(key + CUSTOM_VALUE_KEY);

			} else {

				customValueList = new ArrayList<>();

				for (String column : list.get(0).keySet()) {

					customValueList.add(new String[] { JdbcUtils.convertUnderscoreNameToPropertyName(column), column });

				}

			}

			columnSize = customValueList.size();

			/*
			 * 테이블 컬럼명 세팅
			 *
			 */
			row = sheet.createRow(0);

			for (int columnNo = 0; columnNo < columnSize; columnNo++) {

				cell = row.createCell(columnNo);
				cell.setCellStyle(headStyle);
				cell.setCellValue((String) customValueList.get(columnNo)[HEADER_INDEX]);

			}

			/*
			 * 테이블 본문 데이터 세팅
			 *
			 */
			rowSize = list.size();

			for (int rowNo = 1; rowNo <= rowSize; rowNo++) {

				row = sheet.createRow(rowNo);

				Map<String, Object> vo = list.get(rowNo - 1);

				for (int columnNo = 0; columnNo < columnSize; columnNo++) {

					cell = row.createCell(columnNo);
					cell.setCellStyle(bodyStyle);

					cell.setCellValue((String) vo.get((String) customValueList.get(columnNo)[COLUMN_INDEX]));

				}
			}

		} // end of for

		// 컨텐츠 타입과 파일명 지정
		response.setContentType(DEFAULT_CONTENT_TYPE);

		// http://jong138.blogspot.com/2017/04/blog-post_20.html
		// 한글 파일명 깨짐 해결 방안
		String userAgent = request.getHeader("User-Agent");

		boolean ie = (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1);

		String encodedFileName = null;

		if (ie) {
			encodedFileName = URLEncoder.encode(fileName, "utf-8").replaceAll("\\+", "%20");
		} else {
			encodedFileName = new String(String.valueOf(fileName).getBytes("utf-8"), "iso-8859-1");
		}

		response.setContentType("application/octet-stream");
		response.setHeader("Accept-Ranges", "bytes");

		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName);

		// String encodedFileName = new String(fileName.getBytes(), "iso-8859-1");
		// response.setHeader("Content-Disposition", "attachment;filename=" +
		// encodedFileName);

		// 엑셀 출력
		workBook.write(response.getOutputStream());
		workBook.close();

	}

	private void initValue(Map<String, Object> model) {

		workBook = new HSSFWorkbook();
		sheet = null;
		row = null;
		cell = null;
		customValueList = null;

		columnSize = 0;
		rowSize = 0;

		/*
		 * 테이블 헤더용 스타일
		 *
		 */
		headStyle = workBook.createCellStyle();

		// 가는 경계선을 가집니다.
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);

		// 배경색은 노란색입니다.
		headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// 데이터는 가운데 정렬합니다.
		headStyle.setAlignment(HorizontalAlignment.CENTER);

		/*
		 * 테이블 바디 스타일
		 *
		 */

		// 데이터용 경계 스타일 테두리만 지정
		CellStyle bodyStyle = workBook.createCellStyle();

		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);

		// model 에 "fileName" 값이 있으면 해당 값을 엑셀 파일명으로 지정
		if (model.containsKey(CUSTOM_FILE_NAME_KEY)) {

			fileName = (String) model.get(CUSTOM_FILE_NAME_KEY) + ".xls";

		} else {

			fileName = DEFAULT_FILE_NAME + ".xls";

		}

	}

}
