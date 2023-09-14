package gwangju.ssafy.backend.global.component.excel;

import gwangju.ssafy.backend.global.exception.ExcelException;
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

public class SimpleExcelFile<T> {

	private SXSSFWorkbook wb;

	public SimpleExcelFile(List<T> data, Class<T> type) {
		this.wb = new SXSSFWorkbook();

		SXSSFSheet sheet = wb.createSheet();

		renderHeader(sheet, 0, 0, type);
		renderBody(sheet, 1, 0, data);
	}


	private void renderHeader(Sheet sheet, int rowIdx, int columnStartIdx, Class<T> type) {
		Row headerRow = sheet.createRow(rowIdx);
		CellStyle blueCellStyle = wb.createCellStyle();
		applyCellStyle(blueCellStyle, new Color(223, 235, 246));

		for (String headerName : ExcelColumnExtractor.extractHeaderName(type)) {
			Cell headerCell = headerRow.createCell(columnStartIdx++);
			headerCell.setCellValue(headerName);
			headerCell.setCellStyle(blueCellStyle);
		}

	}
	private void applyCellStyle(CellStyle cellStyle, Color color) {
		XSSFCellStyle xssfCellStyle = (XSSFCellStyle) cellStyle;
		xssfCellStyle.setFillForegroundColor(new XSSFColor(color, new DefaultIndexedColorMap()));
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
	}

	private void renderBody(Sheet sheet, int rowIdx, int columnStartIdx, List<T> data) {
		for (T datum : data) {
			int columnIdx = columnStartIdx;
			Row bodyRow = sheet.createRow(rowIdx++);

			for (String value : ExcelColumnExtractor.extractFieldValue(datum)) {
				Cell bodyCell = bodyRow.createCell(columnIdx++);
				bodyCell.setCellValue(value);
			}
		}
	}

	public void writeFile(OutputStream outputStream) {
		try {
			this.wb.write(outputStream);
		} catch (Exception e) {
			throw new ExcelException("엑셀 변환에 실패하였습니다.");
		}finally {
			try {
				this.wb.close();
				this.wb.close();
			} catch (IOException e) {
				throw new ExcelException("자원 해제를 실패하였습니다.");
			}
		}
	}
}