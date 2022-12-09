package com.example.demotda.controller.admin;

import com.example.demotda.model.ProductSold;
import com.example.demotda.model.UserExcelExporter;
import com.example.demotda.repositorie.ProductSoldRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller

public class ExportExcelCpntroller {
    private ProductSoldRepo productSoldRepo;
    @Autowired
    public ExportExcelCpntroller(ProductSoldRepo productSoldRepo){
        this.productSoldRepo=productSoldRepo;
    }


    @GetMapping("/exportexcelsold")
    public void exsold(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        String headerKey= "Content-Disposition";
        String headerValue="attachment; filename=Danh sách hàng bán hàng.xlsx";
        response.setHeader(headerKey,headerValue);

        List<ProductSold> listproductsold= productSoldRepo.findAll();
        UserExcelExporter excelExporter= new UserExcelExporter(listproductsold);
        excelExporter.export(response);
    }
}
