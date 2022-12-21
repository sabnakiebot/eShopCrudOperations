package eShopCrudOperations.eShopCrudOperations.services;

import eShopCrudOperations.eShopCrudOperations.model.Product;
import eShopCrudOperations.eShopCrudOperations.model.User;

import java.util.List;

public interface IProductService {

    List<Product> getAllProducts();

    Product getProduct(Integer Id);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Integer Id);

    // Automatic Custom Queries


    List<Product> getAllProductsByName(String name);

    List<Product> getAllProductsByNameorType(String name, String type);

    // Manual Custom  Queries JPQL

    List<Product> getByNameEndsWith(String name);
    List<Product> getByTypeOrderByNameDesc(String type);

    // Manual Custom  Queries Native Query

    List<Product> findByNameEndsWithUsingNative(String name);

    Product get(Integer id) throws UserNotFoundException;

    void delete(Integer id) throws UserNotFoundException;

}
