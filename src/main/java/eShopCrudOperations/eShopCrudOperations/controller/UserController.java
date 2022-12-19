package eShopCrudOperations.eShopCrudOperations.controller;

import eShopCrudOperations.eShopCrudOperations.model.User;
import eShopCrudOperations.eShopCrudOperations.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/users")
    public String showUserList (Model model)
    {
        List<User> lstUser=userService.getAllUsers();
        model.addAttribute("lstUser",lstUser);
        return "users";
    }
    @GetMapping("/users/new")
    public String showNewForm (Model model)
    {
        model.addAttribute("user",new User());
        return "user_form";
    }
}
