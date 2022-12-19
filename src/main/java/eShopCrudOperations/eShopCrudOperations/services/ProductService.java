package eShopCrudOperations.eShopCrudOperations.services;

import eShopCrudOperations.eShopCrudOperations.model.Product;
import eShopCrudOperations.eShopCrudOperations.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService implements IProductService {


    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Integer Id) {
        return productRepository.findById(Id).get();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productFromDB = productRepository.findById(product.getId()).get();
        productFromDB.setName(product.getName());
        productFromDB.setRate(product.getRate());
        productFromDB.setType(product.getType());
        productRepository.save(productFromDB);
        return productRepository.saveAndFlush(product);
    }

    @Override
    public void deleteProduct(Integer Id) {
        productRepository.deleteById(Id);
    }


    //Automatic Custom Queries
    @Override
    public List<Product> getAllProductsByName(String name) {

        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getAllProductsByNameorType(String name,String type) {

        return productRepository.findByNameOrType(name,type);
    }

    //Manual Custom Queries JPQL
    @Override
    public List<Product> getByNameEndsWith(String name) {
        return productRepository.findByNameEndsWith(name);
    }

    @Override
    public List<Product> getByTypeOrderByNameDesc(String type) {
        return productRepository.findByTypeOrderByNameDesc(type);
    }

    //Manual Custom Queries Native Query
    @Override
    public List<Product> findByNameEndsWithUsingNative(String name) {
        return productRepository.findByNameEndsWithUsingNative(name);
    }

}
