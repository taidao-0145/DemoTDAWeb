package com.example.demotda.controller;

import com.example.demotda.config.ConfigPay;
import com.example.demotda.config.PaymentConfig;
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

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

    @GetMapping("/admin/importProduct")
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
            return "redirect:/admin/importProduct?nhap so lon hon 0";
        }
    }

    @PostMapping("/addImportProduct")
    public String addImportProduct(@ModelAttribute ImportProductDto formImport,@RequestParam("supplier") Long supplier,
                                   @RequestParam("note") String note,Model model,Principal principal, @RequestParam("dateadd") String dateadd) throws ParseException {
        LocalDateTime localDateTime = LocalDateTime.parse(dateadd);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        String username=principal.getName();
        User user=userService.findUserByUsername(username);
        Supplier supp= supplierService.findById(supplier);
        List<ImportProduct> list= formImport.getImportProducts();
        long total=0;
        for (ImportProduct importPro : list) {
            total+=importPro.getPrice()*importPro.getQuantity();
        }
        for (ImportProduct importPro : list) {
            if(importPro.getQuantity()<=0){
                return "redirect:/admin/importProduct?nhap lai so luong";
            }
        }
        for (ImportProduct importPro : list) {
            if(importPro.getPrice()<=0){
                return "redirect:/admin/importProduct?nhap lai gia ";
            }
        }
        ImportMaster importMaster= new ImportMaster(date,total,total,supp,user,note);
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
        return "redirect:/admin/importProduct";
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
        return "redirect:/admin/importProduct";
    }

    @GetMapping("/cancelPayment")
    public String cancelPayment(@RequestParam("id") Long id,@RequestParam("debt") long debt){
        supplierService.updateDebtSupplier(debt,id);
        return "redirect:/admin/importProduct";

    }
    @GetMapping("/detailImport")
    public String detailImport(@RequestParam("id") Long idImportMaster,Model model){
        List<ImportProduct> listImportProduct= importProductService.findByMaster(idImportMaster);
        model.addAttribute("listImportProduct",listImportProduct);
        ImportMaster importMaster= importMasterService.findById(idImportMaster);
        model.addAttribute("importMaster",importMaster);
        return "admin/detailimport";
    }

    @GetMapping("/paymentVNPay")
    public String payment(@RequestParam("amount") double amount) throws UnsupportedEncodingException {
        Random random = new Random();

        // lấy số ngẫu nhiên trong khoảng từ 0 đến 99
        int randomNumber = random.nextInt(100000);

        DecimalFormat df = new DecimalFormat("###");
        String total_tax_f = df.format(amount*100);
        System.out.println(total_tax_f);

        Map<String,String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", PaymentConfig.VERSIONVNPAY);
        vnp_Params.put("vnp_Command", PaymentConfig.COMMAND);
        vnp_Params.put("vnp_TmnCode", PaymentConfig.TMNCODE);
        vnp_Params.put("vnp_Amount", String.valueOf(total_tax_f));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_TxnRef", randomNumber+"");
        vnp_Params.put("vnp_OrderInfo", "thanh toan ve xem phim");
        vnp_Params.put("vnp_OrderType", PaymentConfig.ORDERTYPE);
        vnp_Params.put("vnp_Locale", PaymentConfig.LOCALEDEFAULT);
        vnp_Params.put("vnp_ReturnUrl", "https://www.google.com/");
        vnp_Params.put("vnp_IpAddr", PaymentConfig.IPDEFAULT);
        Date cld = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld);
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);


        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = ConfigPay.hmacSHA512(PaymentConfig.CHECKSUM, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = ConfigPay.vnp_PayUrl + "?" + queryUrl;


        return "redirect:"+paymentUrl;

    }


}
