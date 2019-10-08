package com.example.abhi.homymarket.Models;

import java.util.ArrayList;

public class CartFetch {

    public static ArrayList<Float> priceList = new ArrayList<>();
    String Product_ID;

    public CartFetch() {
    }

    public CartFetch(String product_ID) {
        Product_ID = product_ID;
    }

    public String getProduct_ID() {
        return Product_ID;
    }
}
