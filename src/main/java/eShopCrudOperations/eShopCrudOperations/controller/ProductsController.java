package eShopCrudOperations.eShopCrudOperations.controller;

import eShopCrudOperations.eShopCrudOperations.model.Product;
import eShopCrudOperations.eShopCrudOperations.services.ProductService;
import eShopCrudOperations.eShopCrudOperations.services.UserNotFoundException;
import eShopCrudOperations.eShopCrudOperations.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class ProductsController {
    @Autowired
    ProductService productService;

    // With FrontEnd


    @GetMapping("/products")
    public String showUserList(Model model) {
        List<Product> lstProduct = productService.getAllProducts();
        model.addAttribute("lstProduct", lstProduct);
        return "products";
    }

    @GetMapping("/products/new")
    public String showNewForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("pageTitle","Add New Product");
        return "product_form";
    }

    @PostMapping("/products/save")
    public String saveProduct(Product product, RedirectAttributes ra) {

        productService.createProduct(product);
        ra.addFlashAttribute("message", "The Product has been added Successfully !!");
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Product product=   productService.get(id);
            model.addAttribute("product",product);
            model.addAttribute("pageTitle","Edit Product by ID : "+id);
            return "product_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/products";
        }


    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            productService.delete(id);
            ra.addFlashAttribute("message", "The Product Id : " + id + " has been Deleted Successfully !!");

        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/products";
    }

}
