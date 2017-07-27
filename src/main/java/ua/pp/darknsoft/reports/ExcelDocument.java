package ua.pp.darknsoft.reports;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import ua.pp.darknsoft.entity.CheckEventSupplemented;
import ua.pp.darknsoft.entity.Ufop;
import static ua.pp.darknsoft.support.StaticMethod.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 03.05.2017.
 */
public class ExcelDocument extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook hssfWorkbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        //New Excel sheet
        HSSFSheet excelSheet = hssfWorkbook.createSheet("Simple excel report");
        excelSheet.setColumnWidth(1,20 * 256);
        excelSheet.setColumnWidth(2,20 * 256);
        excelSheet.setColumnWidth(3,11 * 256);
        excelSheet.setColumnWidth(7,20 * 256);
        excelSheet.setColumnWidth(8,20 * 256);
        CellStyle styleCell = hssfWorkbook.createCellStyle();
        styleCell.setWrapText(true);
        styleCell.setBorderBottom((short) 1);
        styleCell.setBorderLeft((short) 1);
        styleCell.setBorderTop((short) 1);
        styleCell.setBorderRight((short) 1);
        //Excel file name change
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=excelDocument.xls");

        Font font = hssfWorkbook.createFont();
        font.setFontName("Arial");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);

        //Create Style for header
        CellStyle styleHeader = hssfWorkbook.createCellStyle();
        styleHeader.setFillForegroundColor(HSSFColor.BLUE.index);
        styleHeader.setFillPattern(CellStyle.SOLID_FOREGROUND);
        styleHeader.setFont(font);
        styleHeader.setWrapText(true);
        //Set Borders
        styleHeader.setBorderBottom((short) 2);
        styleHeader.setBorderLeft((short) 2);
        styleHeader.setBorderTop((short) 2);
        styleHeader.setBorderRight((short) 2);
        //Set excel header
        setExcelHeader(excelSheet, styleHeader);

        //Get data from model
        List<Ufop> ufops_list = (List<Ufop>) model.get("modelObject");
        int rowCount = 1;
        for (Ufop ufop : ufops_list) {
            HSSFRow row = excelSheet.createRow(rowCount++);
            row.createCell(0).setCellValue(rowCount-1);
            row.getCell(0).setCellStyle(styleCell);
            row.createCell(1).setCellValue(repairHtml(ufop.getUfop_name()));
            row.getCell(1).setCellStyle(styleCell);
            row.createCell(2).setCellValue("x");
            row.getCell(2).setCellStyle(styleCell);
            row.createCell(3).setCellValue(ufop.getUfop_code());
            row.getCell(3).setCellStyle(styleCell);
            row.createCell(4).setCellValue("x");
            row.getCell(4).setCellStyle(styleCell);
            row.createCell(5).setCellValue("x");
            row.getCell(5).setCellStyle(styleCell);
            row.createCell(6).setCellValue("x");
            row.getCell(6).setCellStyle(styleCell);
            row.createCell(7).setCellValue("Відповідно до встановлених вимог");
            row.getCell(7).setCellStyle(styleCell);
            row.createCell(8).setCellValue("ГУ Держпродспоживслужби в Одеській області");
            row.getCell(8).setCellStyle(styleCell);


        }
    }

    public void setExcelHeader(HSSFSheet excelSheet, CellStyle styleHeader) {
        //set Excel Header names

        HSSFRow header = excelSheet.createRow(0);
        header.createCell(0).setCellValue("№");
        header.getCell(0).setCellStyle(styleHeader);
        header.createCell(1).setCellValue("Найменування суб'єкта господарювання");
        header.getCell(1).setCellStyle(styleHeader);
        header.createCell(2).setCellValue("Місцезнаходження  суб’єкта господарювання та/або його відокремлених підрозділів");
        header.getCell(2).setCellStyle(styleHeader);
        header.createCell(3).setCellValue("Ідентифікаційний код юр. особи або ідентифікаційний номер фОП");
        header.getCell(3).setCellStyle(styleHeader);
        header.createCell(4).setCellValue("Вид господарської діяльності");
        header.getCell(4).setCellStyle(styleHeader);
        header.createCell(5).setCellValue("Ступінь ризику");
        header.getCell(5).setCellStyle(styleHeader);
        header.createCell(6).setCellValue("Дата початку проведення заходу");
        header.getCell(6).setCellStyle(styleHeader);
        header.createCell(7).setCellValue("Строки проведення заходу");
        header.getCell(7).setCellStyle(styleHeader);
        header.createCell(8).setCellValue("Найменування органу державного нагляду  (контролю)");
        header.getCell(8).setCellStyle(styleHeader);
    }

}
