package com.example.demotda.controller;

import com.example.demotda.dto.ExportProductDto;
import com.example.demotda.dto.ReturnProductDto;
import com.example.demotda.model.*;
import com.example.demotda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
public class ExportProductController {
    private ExportProductService exportProductService;
    private ProductService productService;
    private ExportMasterService exportMasterService;
    private ReturnProductService returnProductService;
    private ReturnMasterService returnMasterService;
    private UserService userService;
    @Autowired
    public ExportProductController(ExportProductService exportProductService,
                                   ProductService productService,
                                   ExportMasterService exportMasterService,
                                   UserService userService,
                                   ReturnProductService returnProductService,
                                   ReturnMasterService returnMasterService) {
        this.exportProductService = exportProductService;
        this.productService=productService;
        this.exportMasterService=exportMasterService;
        this.userService=userService;
        this.returnProductService=returnProductService;
        this.returnMasterService=returnMasterService;
    }

    @GetMapping("/exProduct")
    public String viewExportProduct(Model model){
        List<ExportMaster> listMaster=exportMasterService.listAll();
        model.addAttribute("listMaster",listMaster);
        return listByPage(model,1);
    }


    @GetMapping("/pageExportProduct")
    public String listByPage(Model model, @RequestParam("page") int currentPage){
        Page<ExportProduct> page= exportProductService.listAll(currentPage);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        if(totalPages <1){
            totalPages=1;
        }
        List<ExportProduct> listAll= page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("listExport", listAll);
        return "admin/exportproduct";
    }

    @PostMapping("/formExportProduct")
    public String formExportProduct(Model model, @RequestParam("num") int num){
        if(num>0){
            List<Product> products= productService.listProduct();
            model.addAttribute("products",products);
            ExportProductDto formExport= new ExportProductDto();
            for (int i = 1; i <= num; i++) {
                formExport.addExportProduct(new ExportProduct());
            }
            model.addAttribute("formExport",formExport);
            return "admin/formexportproduct";
        }
        else {
            return "redirect:/exProduct";
        }
    }

    @PostMapping("/addExportProduct")
    public String exportProduct(@ModelAttribute ExportProductDto formExport, @RequestParam("branch") String branch,
                                @RequestParam("note") String note, Model model, Principal principal, @RequestParam("dateadd") String dateadd){
        LocalDateTime localDateTime = LocalDateTime.parse(dateadd);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        String username=principal.getName();
        User user=userService.findUserByUsername(username);
        List<ExportProduct> list=formExport.getExportProducts();
        long total=0;
        for (ExportProduct exportPro : list) {
            total+=exportPro.getPrice()*exportPro.getQuantity();
        }
        ExportMaster exportMaster= new ExportMaster(date,total,branch,note,user);
        exportMasterService.save(exportMaster);
        for (ExportProduct exportProduct : list) {
            exportProduct.setExportMaster(exportMaster);
        }
        exportProductService.saveAll(list);
        for (ExportProduct exportProduct : list) {
            productService.updateExport(exportProduct.getQuantity(),exportProduct.getProduct().getId());
            productService.reportProduct(exportProduct.getQuantity(),exportProduct.getProduct().getId());
        }
        return "redirect:/exProduct";
    }

    @GetMapping("/viewReturnProduct")
    public String formReturnProduct(@RequestParam("id") Long idExportMaster,Model model){
        List<ExportProduct> listExport=exportProductService.findByMaster(idExportMaster);
        model.addAttribute("listExport",listExport);
        int max= listExport.size();
        model.addAttribute("max",max);
        model.addAttribute("id",idExportMaster);
        return "admin/formreturnproduct";
    }

    @PostMapping("/formReturnProduct")
    public String formReturnProduct(@RequestParam("num") int num,Model model,@RequestParam("max") int max,@RequestParam("id") Long id){
        if(0< num && num <=max){
            List<ExportProduct> products=exportProductService.findByMaster(id);
//            List<Product> products= productService.listProduct();
            model.addAttribute("products",products);
            ReturnProductDto formReturn= new ReturnProductDto();
            for (int i = 1; i <= num; i++) {
                formReturn.addReturnProduct(new ReturnProduct());
            }
            model.addAttribute("formReturn",formReturn);
            return "admin/formaddreternproduct";
        }
        else {
            return "redirect:/viewReturnProduct?id="+id;
        }
    }

    @PostMapping("/addReturnProduct")
    public String addReturnProduct(@ModelAttribute ReturnProductDto formReturn, @RequestParam("note") String note, Model model,
                                   @RequestParam("branch") String branch){

        List<ReturnProduct> list= formReturn.getReturnProducts();
        ReturnProductMaster returnProductMaster= new ReturnProductMaster(new Date(),branch,note) ;
        returnMasterService.save(returnProductMaster);
        for (ReturnProduct returnProduct : list) {
            returnProduct.setReturnProductMaster(returnProductMaster);
        }
        returnProductService.saveAll(list);
        for (ReturnProduct returnProduct : list) {
            productService.updateImport(returnProduct.getQuantity(),returnProduct.getQuantity(),returnProduct.getProduct().getId());
        }
        return "redirect:/exProduct";
    }
}
