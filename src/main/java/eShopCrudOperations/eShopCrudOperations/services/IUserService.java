package eShopCrudOperations.eShopCrudOperations.services;

import eShopCrudOperations.eShopCrudOperations.model.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();
    void save(User user);

    User get(Integer id) throws UserNotFoundException;

    void delete(Integer id) throws UserNotFoundException;

    User findByEmail(String email);
}
