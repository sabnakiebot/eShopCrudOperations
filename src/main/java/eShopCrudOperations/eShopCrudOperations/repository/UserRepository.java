package eShopCrudOperations.eShopCrudOperations.repository;

import eShopCrudOperations.eShopCrudOperations.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    public Long countById(Integer id);

    @Query("select u from User  u where u.email = ?1 ")
    public User findByEmail(String email);
}
