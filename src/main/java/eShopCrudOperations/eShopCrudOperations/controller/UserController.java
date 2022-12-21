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
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra) {

        userService.save(user);
        ra.addFlashAttribute("message", "The User has been added Successfully !!");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = userService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User by ID : " + id);
            return "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }


    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
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

        Integer userId=user.getId();
        System.out.println(userId);
        User userData=userService.findByUserId(userId);
        if(user.getEmail().equals(userData.getEmail()) && user.getPassword().equals(userData.getPassword())  )
        {
            return "redirect:/index";
        }
        else
        {
            return "redirect:/login";
        }


    }


    @GetMapping("/")
    public String showLoginForm(Model model) {
        User user = new User();
        System.out.println(user);
        model.addAttribute("user",user);
        return "login";
    }

    //Code not Working


    // Using Spring Security
    @GetMapping("/loginpage")
    public String login() {
       User user=getPrincipal();
        System.out.println(user);
       if(user!=null)
       {
           return "index";
       }
       return "loginpage";
    }

    private User getPrincipal(){
        User user=null;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User)
        {
            user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        }
        return user;
    }




}
