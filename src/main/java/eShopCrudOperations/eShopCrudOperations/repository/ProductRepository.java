package eShopCrudOperations.eShopCrudOperations.repository;

import eShopCrudOperations.eShopCrudOperations.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    //Automatic Custom Queries

    public List<Product> findByName(String name);

    public List<Product> findByNameOrType(String name, String type);


    //Manual Custom Queries JPQL
    @Query("select p from Product  p where p.name like %?1")
    public List<Product> findByNameEndsWith(String name);

    @Query("select p from Product  p where p.type like %?1 order by p.name desc")
    public List<Product> findByTypeOrderByNameDesc(String type);


    //Manual Custom Queries Native Query
    @Query(value="select * from Product  p where p.name like %?1" , nativeQuery = true)
    default List<Product> findByNameEndsWithUsingNative() {
        return findByNameEndsWithUsingNative(null);
    }

    //Manual Custom Queries Native Query
    @Query(value="select * from tbl_product   where product_name like %?1" , nativeQuery = true)
    public List<Product> findByNameEndsWithUsingNative(String name);
}
