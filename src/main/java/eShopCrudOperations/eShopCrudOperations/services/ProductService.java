package eShopCrudOperations.eShopCrudOperations.services;

import eShopCrudOperations.eShopCrudOperations.model.Product;
import eShopCrudOperations.eShopCrudOperations.model.User;
import eShopCrudOperations.eShopCrudOperations.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Product get(Integer id) throws UserNotFoundException {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could Not Find any Product with the Id" + id);
    }

    @Override
    public void delete(Integer id) throws UserNotFoundException {
        Long count = productRepository.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could Not Find any User with the Id" + id);
        }
        productRepository.deleteById(id);
    }

}
