package com.example.ecommtrial.service;

import com.example.ecommtrial.model.Product;
import com.example.ecommtrial.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    @Autowired
            private ProductRepo productRepo;


//    public List<Product> fetchAll(){
//        return productRepo.fetchSustom();
//    }

/*    List<Product> products = Arrays.asList(
            new Product(101,"infinix hot 50", 20000),
            new Product(102,"infinix hot 50", 20000),
            new Product(103,"infinix hot 50", 20000)
    );*/

/*    public List<Product> getProducts(){
        return products;
    }*/


  /*  public Product getProductById(int prodId){
        return products.stream()
                .filter(p -> p.getProdId() == prodId)
                .findFirst().orElse(
                        new Product(0,"No Item", 0)
                );
    }

    public Product addProducts(Product prod){
        return prod;
    }*/


}
