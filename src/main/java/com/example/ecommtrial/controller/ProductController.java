package com.example.ecommtrial.controller;

import com.example.ecommtrial.model.Product;
import com.example.ecommtrial.repository.ProductRepo;
import com.example.ecommtrial.service.ProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin
public class ProductController {

    @Autowired
    ProductService service;

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productRepo.findAll();
    }

    @PostMapping("/save")
    public String saveProd(@RequestBody Product product){
        productRepo.save(product);
        return "Saved...";
    }

//    @GetMapping("/custom")
//    public List<Product> fetchControll(){
//       return  service.fetchAll();
//    }

/*    @DeleteMapping
    public String deleteUser(@PathVariable long id){
        Product deleteProd = productRepo.delete(id);
    }*/

/*    @PutMapping(value = "update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody Product product){
        Product updateProducts = productRepo.findBy(id).get();
    }*/

   /* @RequestMapping("/products/{prodId}")
    public Product getProductsById(@PathVariable int prodId){
        return service.getProductById(prodId);
    }

    @PostMapping("/products/add")
    public void addProduct(@RequestBody Product prod){
        service.addProducts(prod);
    }*/

}
