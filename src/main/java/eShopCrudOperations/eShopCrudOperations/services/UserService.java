package eShopCrudOperations.eShopCrudOperations.services;

import eShopCrudOperations.eShopCrudOperations.model.User;
import eShopCrudOperations.eShopCrudOperations.repository.UserRepository;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    public User get(Long id) throws UserNotFoundException {

        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could Not Find any User with the Id" + id);
    }

    @Override
    public void delete(Long id) throws UserNotFoundException {

        Long count = userRepository.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could Not Find any User with the Id" + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }



}
