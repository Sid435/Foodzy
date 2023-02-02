package com.example.foodzy;

public class DeliveryitemModel {
    private String name;
    private String price;
    private String quantity;
    private String fprice;


    DeliveryitemModel(String name, String price, String quantity, String fprice){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.fprice = fprice;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }


    public String getQuantity() {
        return quantity;
    }

    public String getFprice() {
        return fprice;
    }
}
