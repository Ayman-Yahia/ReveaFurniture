package com.codingdojo.ReveaStoreProject.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.ReveaStoreProject.models.Product;
import com.codingdojo.ReveaStoreProject.models.User;
import com.codingdojo.ReveaStoreProject.services.UserService;
import com.codingdojo.ReveaStoreProject.validator.UserValidator;

@Controller
public class UserController {
	private UserService userService;
    private UserValidator userValidator;
    
    public UserController(UserService userService,UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
    
    @RequestMapping("/registration")
    public String registerForm(@Valid @ModelAttribute("user") User user) {
        return "registrationPage.jsp";
    }
    
    // ******************************************************************************
    // This method is only commented when you want to comment out the following method
//    @PostMapping("/registration")
//    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
//        userValidator.validate(user, result);
//        if (result.hasErrors()) {
//            return "registrationPage.jsp";
//        }
//        
//        userService.saveWithUserRole(user);
//        return "redirect:/login";
//
//    }
    // ******************************************************************************
    // ******************************************************************************
     //This method is only commented out when you want to add an admin, and the previous method shall be commented 
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "registrationPage.jsp";
        }
        userService.saveUserWithAdminRole(user);
        return "redirect:/login";
    }
    // ******************************************************************************
    
    @RequestMapping("/login")
    public String loginpage(@ModelAttribute("user") User user) {
        return "registrationPage.jsp";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user,@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }
        return "redirect:/home";
    }
    
    
    
    @RequestMapping("/admin")
    public String adminPage(Principal principal, Model model) {
        String username = principal.getName();
//        List<Product> allProducts=productService.allProducts();
//        model.addAttribute("products",allProducts)
        model.addAttribute("currentUser", userService.findByUsername(username));
        return "adminPage.jsp";
    }  
    @RequestMapping("/admin/users")
    public String adminUsers(Principal principal, Model model) {
        String username = principal.getName();
        List<User> allUsers=userService.allUsers();
        model.addAttribute("users",allUsers);
        model.addAttribute("currentUser", userService.findByUsername(username));
        return "adminUsers.jsp";
    }
    @GetMapping("/admin/edit/{id}")
    public String editPage(@PathVariable("id") Long id,@ModelAttribute("product") Product product,Model model) {
    	model.addAttribute("product",userService.findProductById(id));
    	return "editProduct.jsp";
    	
    }
    @GetMapping("/admin/add")
    public String addingPage(@ModelAttribute("product") Product product) {
    	return "adminAddProduct.jsp";
    	
    }
    @PostMapping("/admin/add")
    public String adminAdd(Principal principal, Model model,@Valid@ModelAttribute("product") Product product,BindingResult result) {
    	if(result.hasErrors()) {
			
			return "redirect:/admin/add";
		}else {
			userService.createProduct(product);
			return "redirect:/admin";
		}
    }
    @PutMapping("/admin/edit/{id}")
    public String editProduct(@PathVariable("id") Long id,Principal principal,BindingResult result, Model model,@Valid@ModelAttribute("product") Product product,@RequestParam(value="name") String name,@RequestParam(value="description") String description,@RequestParam(value="price") double price,@RequestParam(value="image") String image,@RequestParam(value="availableQuantity") int availableQuantity) {
    	Product mProduct=userService.findProductById(id);
			if (result.hasErrors()) {
	            return "redirect:/admin/edit/"+mProduct.getId();
	        } else {
	        	userService.updateProduct(id,name,description,price,image,availableQuantity);
	        	userService.createProduct(mProduct);
	            return "redirect:/admin";
	        }
			
		
    }
    @RequestMapping(value = {"/", "/home"})
    public String home(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
        return "homePage.jsp";
    }
    //cart
    
}
