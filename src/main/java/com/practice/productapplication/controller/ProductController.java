package com.practice.productapplication.controller;

import com.practice.productapplication.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("/products")
@RestController
public class ProductController {
    List<Product> products = List.of(
            new Product("Apple", 450),
            new Product("Banana", 700),
            new Product("Carrot", 300),
            new Product("Tomato", 600),
            new Product("Orange", 800),
            new Product("Cucumber", 400),
            new Product("Grapes", 1200),
            new Product("Potato", 200),
            new Product("Beef", 4500),
            new Product("Chicken", 2000),
            new Product("Pork", 3000),
            new Product("Milk", 500),
            new Product("Cheese", 1500),
            new Product("Yogurt", 800)
    );

    @GetMapping
    public List<Product> findAll(@RequestParam (required = false) Integer from,
                                 @RequestParam (required = false) Integer to) {
        if (from == null && to == null) {
            log.debug("GET /products");
            return products;
        }
        List<Product> newProducts = new ArrayList<>();
        if (to == null) {
            log.debug("GET /products?from=" + from);
            for (Product product : products) {
                if (product.getPrice() > from) {
                    newProducts.add(product);
                }
            }
            return newProducts;
        } else if (from == null) {
            log.debug("GET /products?to=" + to);
            for (Product product : products) {
                if (product.getPrice() < to) {
                    newProducts.add(product);
                }
            }
            return newProducts;
        } else {
            log.debug("GET /products?from=" + from + "&to=" + to);
            for (Product product : products) {
                if (product.getPrice() > from && product.getPrice() < to) {
                    newProducts.add(product);
                }
            }
            return newProducts;
        }
    }
    //   /products?from=500&to=1000  - все товары от 500 до 1000
    //   /products?from=500          - все товары от 500
    //   /products?to=1000           - все товары до 1000
    //   /products                   - все товары
    //  Сервис не нужно создавать
}


