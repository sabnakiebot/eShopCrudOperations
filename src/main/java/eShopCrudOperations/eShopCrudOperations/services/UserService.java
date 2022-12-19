package eShopCrudOperations.eShopCrudOperations.services;

import eShopCrudOperations.eShopCrudOperations.model.Product;
import eShopCrudOperations.eShopCrudOperations.model.User;
import eShopCrudOperations.eShopCrudOperations.repository.ProductRepository;
import eShopCrudOperations.eShopCrudOperations.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements  IUserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return (List<User>)  userRepository.findAll();
    }
}
