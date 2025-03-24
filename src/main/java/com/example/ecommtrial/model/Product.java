package com.example.ecommtrial.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Products_tbl" )
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private  int prodId;
    @Column
    private String prodName;
    @Column
    private int price;

/*    public Product(int prodId, String prodName, int price) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.price = price;
    }

    public int getProdId() {
        return prodId;
    }*/
}
