package eShopCrudOperations.eShopCrudOperations.controller;


import eShopCrudOperations.eShopCrudOperations.model.Product;
import eShopCrudOperations.eShopCrudOperations.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductControllerForAPI {
    @Autowired
    IProductService productService;

    @GetMapping("all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping
    public Product getProduct(@Param("product_id") String product_id) {
        return productService.getProduct(Integer.parseInt(product_id));

    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }


    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping
    public void deleteProduct(String product_id) {
        productService.deleteProduct(Integer.parseInt(product_id));
    }

    //Automatic Custom Queries

    @GetMapping("/getAllProductsByName/{name}")
    public List<Product> getAllProductsByName(@PathVariable String name) {

        return productService.getAllProductsByName(name);
    }

    @GetMapping("/getAllProductsByNameOrType/{name}/{type}")
    public List<Product> getAllProductsByNameOrType(@PathVariable String name, @PathVariable String type) {

        return productService.getAllProductsByNameorType(name, type);
    }

    //Manual Custom Queries JPQL
    @GetMapping("/getByNameEndsWith/{name}")
    public List<Product> getByNameEndsWith(@PathVariable String name) {

        return productService.getByNameEndsWith(name);
    }

    @GetMapping("/getByTypeOrderByNameDesc/{type}")
    public List<Product> getByTypeOrderByNameDesc(@PathVariable String type) {

        return productService.getByTypeOrderByNameDesc(type);
    }

    //Manual Custom Queries Native Query
    @GetMapping("/findByNameEndsWithUsingNative/{name}")
    public List<Product> findByNameEndsWithUsingNative(@PathVariable String name) {

        return productService.findByNameEndsWithUsingNative(name);
    }
}
