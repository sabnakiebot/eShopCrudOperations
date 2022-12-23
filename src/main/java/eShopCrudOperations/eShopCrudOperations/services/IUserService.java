package eShopCrudOperations.eShopCrudOperations.services;

import eShopCrudOperations.eShopCrudOperations.model.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();
    void save(User user);

    User get(Long id) throws UserNotFoundException;

    void delete(Long id) throws UserNotFoundException;

    User findByUsername(String username);
}
