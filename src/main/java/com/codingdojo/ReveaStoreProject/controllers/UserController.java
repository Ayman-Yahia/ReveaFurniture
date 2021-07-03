package com.codingdojo.ReveaStoreProject.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "registrationPage.jsp";
        }
        
        userService.saveWithUserRole(user);
		return "homePage.jsp";

    }
    // ******************************************************************************
    // ******************************************************************************
//     //This method is only commented out when you want to add an admin, and the previous method shall be commented 
//    @PostMapping("/registration")
//    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
//        userValidator.validate(user, result);
//        if (result.hasErrors()) {
//            return "registrationPage.jsp";
//        }
//        userService.saveUserWithAdminRole(user);
//        return "redirect:/login";
//    }
    // ******************************************************************************
    
    @RequestMapping("/login")
    public String loginpage(@ModelAttribute("user") User user) {
        System.out.println("11");
        return "registrationPage.jsp";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user,@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
        if(error != null) {
        	System.out.println("noooooo");
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
        	System.out.println("yessssss");
            model.addAttribute("logoutMessage", "Logout Successful!");
        }
        System.out.println("skipppppp");
        return "redirect:/home";
    }
    
    
    
    @RequestMapping("/admin")
    public String adminPage(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
        return "adminPage.jsp";
    }    
    
    @RequestMapping(value = {"/", "/home"})
    public String home(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
        return "homePage.jsp";
    }
}
