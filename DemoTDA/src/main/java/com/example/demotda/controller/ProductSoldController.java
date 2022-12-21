package com.example.demotda.controller;
import com.example.demotda.model.Oder;
import com.example.demotda.model.ProductSold;
import com.example.demotda.util.SoldDayExcel;
import com.example.demotda.util.UserExcelExporter;
import com.example.demotda.service.OderService;
import com.example.demotda.service.ProductService;
import com.example.demotda.service.ProductSoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller

public class ProductSoldController {
    private OderService oderService;
    private ProductService productService;
    private ProductSoldService productSoldService;
    @Autowired
    public ProductSoldController(OderService oderService,ProductSoldService productSoldService,
                                   ProductService productService){
        this.oderService=oderService;
        this.productService=productService;
        this.productSoldService=productSoldService;
    }
    @GetMapping("/productSold")
    public String viewSold(Model model){
        return listByPage(model,1);
    }
    @GetMapping("/pageSold")
    public String listByPage(Model model,@RequestParam("page") int currentPage){
        Page<ProductSold> page= productSoldService.listAll(currentPage);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<ProductSold> listAll= page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("listSold", listAll);
        return "admin/productsold";
    }
    @GetMapping("/exportProduct")
    public String ExportProduct(@RequestParam("idOder") Long idOder){
        Oder oder= oderService.findOderById(idOder);
        String category= oder.getCart().getProduct().getCategory().getName();
        Long idProduct= oder.getCart().getProduct().getId();
        String nameProduct= oder.getCart().getProduct().getNameproduct();
        String supplier=oder.getCart().getProduct().getSupplier().getSupplier();
        int quantity= oder.getCart().getQuantity();
        int toTal=oder.getTotal();
        ProductSold productSold= new ProductSold(idProduct,nameProduct,category,quantity,supplier,toTal,new Date());
        productSoldService.save(productSold);
        oderService.deleteOder(idOder);
        productService.UpdateExport(quantity,idProduct);
        return "redirect:/productSold";
    }

    @GetMapping("/productSoldDay")
    public String ProductSolDay(Model model){
        Date date = new Date();
        String today = new SimpleDateFormat("dd/MM/yyyy").format(date.getTime());
        String today1 = new SimpleDateFormat("yyyy/MM/dd").format(date.getTime());
        model.addAttribute("today",today);
        List<ProductSold> listSoldDay=productSoldService.listSoldDay(today1);
        model.addAttribute("listSoldDay", listSoldDay);
        return "admin/productsoldday";
    }

    @GetMapping("/productSoldWeek")
    public String ProductSoldWeek(Model model){
        List<ProductSold> listSoldWeek= productSoldService.listSoldWeek();
        model.addAttribute("listSoldWeek",listSoldWeek);
        return "admin/productsoldweek";
    }

    @GetMapping("/exportExcelSold")
    public void exSold(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        String headerKey= "Content-Disposition";
        String headerValue="attachment; filename=Danh sách bán hàng.xlsx";
        response.setHeader(headerKey,headerValue);
        List<ProductSold> listProductSold= productSoldService.findAll();
        UserExcelExporter excelExporter= new UserExcelExporter(listProductSold);
        excelExporter.export(response);
    }

    @GetMapping("exportExcelSoldDay")
    public void exSoldDay(HttpServletResponse response) throws IOException{
        Date date= new Date();
        String today = new SimpleDateFormat("dd/MM/yyyy").format(date.getTime());
        String today1 = new SimpleDateFormat("yyyy/MM/dd").format(date.getTime());
        response.setContentType("application/octet-stream.");
        String headerKey= "Content-Disposition";
        String fileName= "DS bán hàng ngày"+today+".xlsx";
        String headerValue="attachment; filename="+fileName;
        response.setHeader(headerKey,headerValue);
        List<ProductSold> listProductSold= productSoldService.listSoldDay(today1);
        SoldDayExcel soldDayExcel= new SoldDayExcel(listProductSold);
        soldDayExcel.export(response);
    }

    @PostMapping("/searchDateProductSold")
    public String SearchProductSold(@RequestParam("startDate") String startDate,
                                    @RequestParam("endDate") String endDate,Model model){

        List<ProductSold> searchProductSold;
        if(startDate.equals(endDate)){
            searchProductSold = productSoldService.listSoldDay(startDate);
        }
        else {
            searchProductSold = productSoldService.searchDateProductSold(startDate, endDate);
        }
        model.addAttribute("searchProductSold",searchProductSold);
        return "redirect:/productSold";
    }







    @GetMapping("/sellingProduct")
    public String SellingProduct(Model model){
        return "admin/sellingProduct";
    }


}
