package eShopCrudOperations.eShopCrudOperations.controller;

import eShopCrudOperations.eShopCrudOperations.model.User;
import eShopCrudOperations.eShopCrudOperations.services.UserNotFoundException;
import eShopCrudOperations.eShopCrudOperations.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> lstUser = userService.getAllUsers();
        model.addAttribute("lstUser", lstUser);
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "userform";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra) {

        System.out.println(user.getUsername());
        userService.save(user);
        ra.addFlashAttribute("message", "The User has been added Successfully !!");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            User user = userService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User by ID : " + id);
            return "userform";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }


    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            userService.delete(id);
            ra.addFlashAttribute("message", "The User Id : " + id + " has been Deleted Successfully !!");

        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/users";
    }


    //Code not Working
    @PostMapping("/userLogin")
    public String login(@ModelAttribute("user") User user) {

        String username=user.getUsername();
        Long idd=user.getId();
        System.out.println(idd);
        System.out.println(username);
        User userData=userService.findByUsername(username);
        if(user.getUsername().equals(userData.getUsername()) && user.getPassword().equals(userData.getPassword())  )
        {
            return "index";
        }
        else
        {
            return "login";
        }


    }


    @GetMapping("/")
    public String showLoginForm(Model model) {
        User user = new User();
        System.out.println(user);
        model.addAttribute("user",user);
        return "login";
    }








}
