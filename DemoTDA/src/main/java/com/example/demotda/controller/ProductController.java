package com.example.demotda.controller;

import com.example.demotda.constant.Constants;
import com.example.demotda.constant.Enum;
import com.example.demotda.dto.ProductDto;
import com.example.demotda.model.Category;
import com.example.demotda.model.Comment;
import com.example.demotda.model.Product;
import com.example.demotda.model.Supplier;
import com.example.demotda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class ProductController {
    private  ProductService productService;
    private CategoryService categoryService;
    private SupplierService supplierService;
    private ImportProductService importProductService;
    private ExportProductService exportProductService;
    private ReturnProductService returnProductService;
    private ProductSoldService productSoldService;
    private CommentService commentService;

    @Autowired
    public ProductController(ProductService productService,CategoryService categoryService,
                             SupplierService supplierService,ImportProductService importProductService,
                             ExportProductService exportProductService,ReturnProductService returnProductService,
                             ProductSoldService productSoldService,CommentService commentService) {
        this.categoryService=categoryService;
        this.productService = productService;
        this.supplierService=supplierService;
        this.importProductService=importProductService;
        this.exportProductService=exportProductService;
        this.returnProductService=returnProductService;
        this.productSoldService=productSoldService;
        this.commentService=commentService;
    }
    @GetMapping(value = Constants.UrlPath.URL_PRODUCT_ADMIN)
    public String productAdmin(Model model){
        return listByPage(model,1);
    }
    @GetMapping("/page")
    public String listByPage(Model model,@RequestParam("page") int currentPage){
        Page<Product> page= productService.listAll(currentPage);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        if(totalPages >0){
            List<Product> listAllProduct= page.getContent();
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("listAllProduct", listAllProduct);
        }
        return "admin/product";
    }
    @GetMapping(value = Constants.UrlPath.URL_DELETE_PRODUCT_ADMIN)
    public String viewDelete(@RequestParam("id") Long id, Model model){
        Product product= productService.getProduct(id);
        model.addAttribute("product", product);
        return "admin/deleteproduct";
    }

    @GetMapping(value = Constants.UrlPath.URL_CONFIRM_DELETE_ADMIN)
    public String confirmDelete(@RequestParam("id") Long id){
        productService.delete(id);
        return "redirect:/productAdmin";
    }

    @GetMapping(value = Constants.UrlPath.URL_VIEW_ADD_PRODUCT_ADMIN)
    public String viewAddProduct(Model model){
        List<Category> categories= categoryService.getListAll();
        model.addAttribute("categories", categories);
        List<Supplier> suppliers= supplierService.listAll();
        model.addAttribute("suppliers", suppliers);
        String a= String.valueOf(Enum.TEST);
        System.err.println(a);
        return "admin/addproduct";
    }

    @PostMapping(value = Constants.UrlPath.URL_ADD_PRODUCT_ADMIN)
    public String addProduct(@ModelAttribute ProductDto productDto){
        productService.save(productDto);
        return "redirect:/admin/productAdmin";
    }
    @GetMapping(value = Constants.UrlPath.URL_VIEW_UPDATE_PRODUCT_ADMIN)
    public String viewUpdate(@RequestParam("id") Long id, Model model){
        Product product=  productService.getProduct(id);
        model.addAttribute("product", product);
        List<Category> categories= categoryService.getListAll();
        model.addAttribute("categories", categories);
        List<Supplier> suppliers= supplierService.listAll();
        model.addAttribute("suppliers", suppliers);
        return "admin/updateproduct";
    }
    @PostMapping(value = Constants.UrlPath.URL_UPDATE_PRODUCT_ADMIN)
    public String update(@ModelAttribute ProductDto productDto){
        productService.updateProduct(productDto);
        return "redirect:/admin/productAdmin";
    }
    @GetMapping(value = Constants.UrlPath.URL_VIEW_PRODUCT)
    public String viewProduct(@RequestParam("id") Long id, Model model){
        Product product= productService.getProduct(id);
        List<Comment> list= commentService.findCommentByProduct(product);
        model.addAttribute("listComment",list);
        model.addAttribute("product",product);
        model.addAttribute("size",list.size());
        return "user/viewproduct";
    }
    @GetMapping(value = Constants.UrlPath.URL_VIEW_STORE)
    public String viewStoreUser(Model model){
        return listByPageStore(model,1);
    }
    @GetMapping("/pageStore")
    public String listByPageStore(Model model, @RequestParam("page") int currentPage){
        Page<Product> pageStore= productService.listStoreAll(currentPage);
        long totalItems= pageStore.getTotalElements();
        int totalPages= pageStore.getTotalPages();
        List<Product> listAll= pageStore.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("listAll", listAll);
        return "user/store";
    }
    @PostMapping("/searchProduct")
    public String searchProduct(@RequestParam("keyword") String keyword, Model model){
        List<Product> listAllProduct= productService.searchProduct(keyword);
        model.addAttribute("listAllProduct",listAllProduct);
        return "admin/product";
    }

    @GetMapping("/pageCheck")
    public String listByPageCheck(Model model,@RequestParam("page") int currentPage){
        Page<Product> page= productService.listAll(currentPage);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Product> listAllProduct= page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("listAllProduct", listAllProduct);
        return "admin/checkproduct";
    }
    @GetMapping(value = Constants.UrlPath.URL_CHECK_PRODUCT)
    public String viewCheckProduct(Model model){
        return listByPageCheck(model,1);
    }

    @GetMapping("/checkGood")
    public String checkGood(Model model,@RequestParam("id") Long idProduct){
        Product product= productService.getProduct(idProduct);
        model.addAttribute("product",product);
        Long countImport= importProductService.countImport(idProduct);
        if(countImport==null){
            countImport= Long.valueOf(0);
        }
        Long countExport= exportProductService.countExport(idProduct);
        if(countExport==null){
            countExport= Long.valueOf(0);
        }
        Long countReturn= returnProductService.countReturn(idProduct);
        if(countReturn==null){
            countReturn= Long.valueOf(0);
        }
        Long countSold=productSoldService.countSold(idProduct);
        if(countSold==null){
            countSold= Long.valueOf(0);
        }
        model.addAttribute("countImport",countImport);
        model.addAttribute("countExport",countExport);
        model.addAttribute("countReturn",countReturn);
        model.addAttribute("countSold",countSold);
        return "admin/checkgoods";
    }
    @GetMapping(value = Constants.UrlPath.URL_VIEW_PRODUCT_ADMIN)
    public String viewProductAdmin(@RequestParam("id") Long id, Model model){
        Product product= productService.getProduct(id);
        model.addAttribute("product",product);
        List<Comment> listComment= commentService.findCommentByProduct(product);
        model.addAttribute("listComment",listComment);
        return "admin/viewproduct";
    }

}
