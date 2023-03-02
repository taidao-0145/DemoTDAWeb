package com.example.demotda.util;

import com.example.demotda.model.Product;
import com.example.demotda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
@Component
public class InitData implements CommandLineRunner {
    @Autowired
    private ProductService productService;


    private Long id;
    private String nameproduct;
    private int soluong;
    private String img;
    private int price;
    private int sale;
    private Date dateadd;
    private int cansell;




    public void addProduct(int n){
        for (int i = 0; i < n; i++) {
            String name= "name"+i;
            int sl= 1;
            String img= "img/np.jpg";
            int price= 2;
            int sale= 3;
            Date date= new Date();
            int canSell= 1;
            productService.saveProduct(new Product(name,sl,img,price,sale,date,canSell));
        }
    }

    @Override
    public void run(String... args) throws Exception {
        addProduct(1000);
    }
}
