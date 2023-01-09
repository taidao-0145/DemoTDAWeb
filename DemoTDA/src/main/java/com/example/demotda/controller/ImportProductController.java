package com.example.demotda.controller;

import com.example.demotda.dto.ImportProductDto;
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
import java.util.Date;
import java.util.List;

@Controller
public class ImportProductController {
    private  ImportProductService importProductService;
    private ProductService productService;
    private ImportMasterService importMasterService;
    private SupplierService supplierService;
    private UserService userService;
    @Autowired
    public ImportProductController(ImportProductService importProductService,
                                   ProductService productService,
                                   ImportMasterService importMasterService,
                                   SupplierService supplierService,
                                   UserService userService) {
        this.importProductService = importProductService;
        this.productService=productService;
        this.importMasterService=importMasterService;
        this.supplierService=supplierService;
        this.userService=userService;
    }

    @GetMapping("/importProduct")
    public String viewImportProduct(Model model){
        List<ImportMaster> listMaster=importMasterService.listAll();
        model.addAttribute("listMaster",listMaster);
        return listByPage(model,1);
    }

    @GetMapping("/pageImportProduct")
    public String listByPage(Model model, @RequestParam("page") int currentPage){
        Page<ImportProduct> page= importProductService.listAll(currentPage);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<ImportProduct> listAll= page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("listImport", listAll);
        return "admin/importproduct";
    }

    @PostMapping("/formImportProduct")
    public String formImportProduct(Model model, @RequestParam("num") int num){
        if(num>0){
            List<Supplier> suppliers=supplierService.listAll();
            model.addAttribute("suppliers",suppliers);
            List<Product> products= productService.listProduct();
            model.addAttribute("products",products);
            ImportProductDto formImport= new ImportProductDto();
            for (int i = 1; i <= num; i++) {
                formImport.addImportProduct(new ImportProduct());
            }
            model.addAttribute("formImport",formImport);
            return "admin/formimportproduct";
        }
        else {
            return "redirect:/importProduct";
        }
    }

    @PostMapping("/addImportProduct")
    public String addImportProduct(@ModelAttribute ImportProductDto formImport,@RequestParam("supplier") Long supplier,
                                   @RequestParam("note") String note,Model model,Principal principal){
        String username=principal.getName();
        User user=userService.findUserByUsername(username);
        Supplier supp= supplierService.findById(supplier);
        List<ImportProduct> list= formImport.getImportProducts();
        long total=0;
        for (ImportProduct importPro : list) {
            total+=importPro.getPrice()*importPro.getQuantity();
        }
        ImportMaster importMaster= new ImportMaster(new Date(),total,total,supp,user,note);
        importMasterService.save(importMaster);
        for (ImportProduct importPro : list) {
            importPro.setImportMaster(importMaster);
        }
        importProductService.saveAll(list);
        for (ImportProduct importPro : list) {
            productService.updateImport(importPro.getQuantity(),importPro.getQuantity(),importPro.getProduct().getId());
        }
        model.addAttribute("importMaster",importMaster);
        model.addAttribute("total",total);
        return "admin/importpayment";

    }

    @PostMapping("/paymentImport")
    public String paymentImport(@RequestParam("id") Long id,@RequestParam("money") Long money,
                                @RequestParam("idSupplier") Long idSupplier,@RequestParam("total") Long total){
        if(money > total){
            money=total;
        }
        importMasterService.paymentImport(money,id);
        Long debt= total-money;
        supplierService.updateDebtSupplier(debt,idSupplier);
        return "redirect:/importProduct";
    }
    @GetMapping("continuePayment")
    public String viewContinuedPayment(@RequestParam("id") Long id, Model model){
        ImportMaster importMaster= importMasterService.findById(id);
        model.addAttribute("importMaster",importMaster);
        return "admin/continuepayment";
    }
    @PostMapping("/continuePayment")
    public String continuePaymentImport(@RequestParam("id") Long id,@RequestParam("money") Long money,
                                        @RequestParam("idSupplier") Long idSupplier,@RequestParam("total") Long total){
        if(money > total){
            money=total;
        }
        importMasterService.paymentImport(money,id);
        supplierService.updateDebtSupplierContinuePayment(money,idSupplier);
        return "redirect:/importProduct";
    }

    @GetMapping("/cancelPayment")
    public String cancelPayment(@RequestParam("id") Long id,@RequestParam("debt") long debt){
        supplierService.updateDebtSupplier(debt,id);
        return "redirect:/importProduct";

    }
    @GetMapping("/detailImport")
    public String detailImport(@RequestParam("id") Long idImportMaster,Model model){
        List<ImportProduct> listImportProduct= importProductService.findByMaster(idImportMaster);
        model.addAttribute("listImportProduct",listImportProduct);
        ImportMaster importMaster= importMasterService.findById(idImportMaster);
        model.addAttribute("importMaster",importMaster);
        return "admin/detailimport";
    }


}
