package eShopCrudOperations.eShopCrudOperations.repository;

import eShopCrudOperations.eShopCrudOperations.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    public Long countById(Integer id);
}
