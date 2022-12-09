package com.example.demotda.model;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class UserExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<ProductSold> soldList;
    private void writeHeaderRow(){
        Row row= sheet.createRow(0);
        Cell cell= row.createCell(0);
        cell.setCellValue("ID");
        cell= row.createCell(1);
        cell.setCellValue("ID Product");
        cell= row.createCell(2);
        cell.setCellValue("Name Product");
        cell= row.createCell(3);
        cell.setCellValue("Type Product");
        cell= row.createCell(4);
        cell.setCellValue("Supplier");
        cell= row.createCell(5);
        cell.setCellValue("Quantity");
        cell= row.createCell(6);
        cell.setCellValue("Total");
        cell= row.createCell(7);
        cell.setCellValue("Export Date");


    }

    public UserExcelExporter(List<ProductSold> soldList) {
        this.soldList = soldList;
        workbook= new XSSFWorkbook();
        sheet= workbook.createSheet("Users");
    }

    private void writeDataRows(){
        int rowCount= 1;
        for(ProductSold productSold: soldList){
            Row row= sheet.createRow(rowCount++);
            Cell cell= row.createCell(0);
            cell.setCellValue(productSold.getId());
            cell= row.createCell(1);
            cell.setCellValue(productSold.getIdproduct());
            cell= row.createCell(2);
            cell.setCellValue(productSold.getNameproduct());
            cell= row.createCell(3);
            cell.setCellValue(productSold.getTypeproduct());
            cell= row.createCell(4);
            cell.setCellValue(productSold.getSupplier());
            cell= row.createCell(5);
            cell.setCellValue(productSold.getQuantity());
            cell= row.createCell(6);
            cell.setCellValue(productSold.getTotal());
            DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String datetime= dateFormat.format(productSold.getExportdate());
            cell= row.createCell(7);

            cell.setCellValue(datetime);
        }

    }
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRows();
        ServletOutputStream outputStream= response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
