package com.codingdojo.ReveaStoreProject.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.ReveaStoreProject.models.Category;
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
        List<Product> allProducts=userService.allProducts();
        model.addAttribute("products",allProducts);
        model.addAttribute("currentUser", userService.findByUsername(username));
        return "adminPage.jsp";
    }  
    @RequestMapping("/admin/users")
    public String adminUsers(Principal principal, Model model) {
        List<User> allUsers=userService.allUsers();
        model.addAttribute("users",allUsers);
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
        return "adminUsers.jsp";
    }
    @GetMapping("/admin/edit/{id}")
    public String editPage(@PathVariable("id") Long id,@ModelAttribute("product") Product product,Model model) {
    	model.addAttribute("product",userService.findProductById(id));
    	return "editProduct.jsp";
    	
    }
    @GetMapping("/admin/add")
    public String addingPage(Principal principal,@ModelAttribute("product") Product product,Model model) {
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
        model.addAttribute("categories",userService.allCategories());
    	return "adminAddProduct.jsp";
    	
    }
    @PostMapping("/admin/add")
    public String adminAdd(Principal principal, Model model,@Valid@ModelAttribute("product") Product product,BindingResult result,@RequestParam("cat") Long cat) {
    	if(result.hasErrors()) {
			
			return "redirect:/admin/add";
		}else {
			Product myProduct=userService.createProduct(product);
			Category cato=userService.findCategoryById(cat);
			myProduct.setCategory(cato);
			userService.createProduct(myProduct);
			return "redirect:/admin";
		}
    }
    @GetMapping("admin/addcategory")
    public String addCategory(Principal principal,@ModelAttribute("category") Category category,Model model) {
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
    	return "addCategory.jsp";
    }
    @PostMapping("admin/addcategory")
    public String addCategoryMethode(Principal principal,@Valid@ModelAttribute("category") Category category,BindingResult result) {
    	if(result.hasErrors()) {
			
			return "redirect:/admin/addcategory";
		}else {
			userService.createCategory(category);
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
    public String home(Principal principal, Model model,HttpSession session) {
    	if(principal==null ) {
            return "homePage.jsp";

    	}
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
        return "homePage.jsp";
    }
    @GetMapping("/admin/{id}/delete")
    public String deletProduct(Principal principal,@PathVariable("id")Long id) {
    	userService.deleteProduct(id);
    	return "redirect:/admin";
    }
    //cart route
    @RequestMapping("/cart")
    public String cart() {
        return "cartPage.jsp";
    }
    //checkout
    @RequestMapping("/checkout")
    public String checkout() {
        return "checkout.jsp";
    }
    //chart
  
    @GetMapping("admin/charts")
    public String chart(Principal principal,Model model) {
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
    	return "adminChart.jsp";}
      
    }

	

