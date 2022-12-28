package com.example.demotda.model;

public class TopSellingg {
    private Long idProduct;
    private String nameProduct;
    private Long total;

    public TopSellingg(Long idProduct, String nameProduct, Long total) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.total = total;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Long getSum() {
        return total;
    }

    public void setSum(Long sum) {
        this.total = sum;
    }
}
