package com.simpreserv.util;

import com.simpreserv.model.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UserEXCELExporter {

  private XSSFWorkbook workbook;
  private XSSFSheet sheet;

  private List<User> userList;

  public UserEXCELExporter(List<User> userList) {
    this.userList = userList;
    workbook = new XSSFWorkbook();
    sheet = workbook.createSheet("Users");
  }

  private void writeHeaderRow() {
    Row row = sheet.createRow(0);
    CellStyle style = workbook.createCellStyle();
    XSSFFont font = workbook.createFont();
    font.setBold(true);
    font.setFontHeight(16);
    style.setFont(font);

    createCell(row, 0,"User ID", style);
    createCell(row, 1,"E-mail", style);
    createCell(row, 2,"Enabled", style);
    createCell(row, 3,"Type", style);
  }

  private void createCell(Row row, int columnCount, Object value, CellStyle style) {
    sheet.autoSizeColumn(columnCount);
    Cell cell = row.createCell(columnCount);
    if (value instanceof Integer) {
      cell.setCellValue((Integer) value);
    } else if (value instanceof Boolean) {
      cell.setCellValue((Boolean) value);
    }else {
      cell.setCellValue((String) value);
    }
    cell.setCellStyle(style);
  }
  private void writeDataRows() {
    int rowCount = 1;
    CellStyle style = workbook.createCellStyle();
    XSSFFont font = workbook.createFont();
    font.setFontHeight(14);
    style.setFont(font);

    for (User user : userList) {
      Row row = sheet.createRow(rowCount++);
      int columnCount = 0;

      createCell(row, columnCount++, user.getUserId(), style);
      createCell(row, columnCount++, user.getEmail(), style);
      createCell(row, columnCount++, user.isEnabled(), style);
      createCell(row, columnCount++, user.getUserTypeId().toString(), style);

    }
  }


  public void export(HttpServletResponse response) throws IOException {
    writeHeaderRow();
    writeDataRows();

    ServletOutputStream outputStream = response.getOutputStream();
    workbook.write(outputStream);
    workbook.close();
    outputStream.close();
  }
}
