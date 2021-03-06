package com.codingdojo.ReveaStoreProject.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
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

import com.codingdojo.ReveaStoreProject.models.Cart;
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
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,HttpSession session) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "registrationPage.jsp";
        }
        
        userService.saveWithUserRole(user);
        return "redirect:/login";

    }
    // ******************************************************************************
    // ******************************************************************************
     //This method is only commented out when you want to add an admin, and the previous method shall be commented 
//    @PostMapping("/registration")
//    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,HttpSession session) {
//        userValidator.validate(user, result);
//        if (result.hasErrors()) {
//            return "registrationPage.jsp";
//        }
//        userService.saveUserWithAdminRole(user);
//        return "redirect:/login";
//    }
    // ******************************************************************************
    
    
    @RequestMapping("/login")
    public String login(Principal principal,HttpSession session,@ModelAttribute("user") User user,@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
    	if (principal != null) {
    		session.setAttribute("user_id",userService.findByUsername(principal.getName()).getId());
    		System.out.println();
			return "redirect:/home";
		}
    	if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }
        
		
        return "registrationPage.jsp";
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
    @GetMapping("/logout")
    public String logoutS(HttpSession session) {
		SecurityContextHolder.clearContext();
		session.invalidate();
    	return "redirect:/login";
    }
    @RequestMapping(value = {"/", "/home"})
    public String home(Principal principal, Model model,HttpSession session,HttpServletRequest request) {
    	if (principal==null) {
    		model.addAttribute("myUserId",false);
    		model.addAttribute("category1", userService.findCategoryById((long) 1));
            model.addAttribute("category2", userService.findCategoryById((long) 2));
            model.addAttribute("category3", userService.findCategoryById((long) 3));
    		return "homePage.jsp";
    	}
    	if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        String username = principal.getName();
    	session.setAttribute("user_id",userService.findByUsername(username).getId());
    		model.addAttribute("user",userService.findByUsername(username));
    		model.addAttribute("myUserId",true);
    		User l=userService.findUserById(((Long)session.getAttribute("user_id")));
        	model.addAttribute("categories", userService.allCategories());
        	List<Cart> myCarts=userService.cartProducts(l.getId(),false);
            model.addAttribute("cartProducts", myCarts);
            model.addAttribute("category1", userService.findCategoryById((long) 1));
            model.addAttribute("category2", userService.findCategoryById((long) 2));
            model.addAttribute("category3", userService.findCategoryById((long) 3));
            model.addAttribute("size", myCarts.size());            

        return "homePage.jsp";
    }
    @GetMapping("/admin/{id}/delete")
    public String deletProduct(Principal principal,@PathVariable("id")Long id) {
    	userService.deleteProduct(id);
    	return "redirect:/admin";
    }
    @GetMapping("/admin/charts")
    public String showCharts(Model model,Principal principal) {
    	String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
    	Product p1=userService.findProductById((long) 27);
    	Product p2=userService.findProductById((long) 25);
    	Product p3=userService.findProductById((long) 10);
    	model.addAttribute("q1",p1.getAvailableQuantity());
    	model.addAttribute("q2",p2.getAvailableQuantity());
    	model.addAttribute("q3",p3.getAvailableQuantity());
    	

    	return "adminChart.jsp";
    }
    //cart route
    @RequestMapping("/cart")
    public String cart(Principal principal,HttpSession session,Model model,HttpServletRequest request) {
    	if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/products";
        }
    	
    	List<Cart> hisCarts=userService.cartProducts((Long) session.getAttribute("user_id"),false);
    	if (principal!=null) {
    		model.addAttribute("size", hisCarts.size());
    		model.addAttribute("myUserId",true);
    	}
    	model.addAttribute("carts",hisCarts);
    	double tp=userService.cartTotalPrice(hisCarts);
    	model.addAttribute("totalprice", tp);
        return "cartPage.jsp";
    }
    //checkout
    @RequestMapping("/checkout")
    public String checkout(Model model,Principal principal,HttpSession session,HttpServletRequest request) {
    	if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/products";
        }
    	
    	List<Cart> hisCarts=userService.cartProducts((Long) session.getAttribute("user_id"),false);

    	if (principal!=null) {
    		model.addAttribute("size", hisCarts.size());
    		model.addAttribute("myUserId",true);
    	}
        return "checkout.jsp";
    }
    @GetMapping("/products")
    public String productsPage(Principal principal,Model model,HttpSession session) {
    	model.addAttribute("products",userService.allProducts());
    	model.addAttribute("categories",userService.allCategories());
    	List<Cart> hisCarts=userService.cartProducts((Long) session.getAttribute("user_id"),false);
    	if (principal!=null) {
    		model.addAttribute("size", hisCarts.size());
    		model.addAttribute("myUserId",true);
    	}
    	return "ShopGrid.jsp";
    }
    @GetMapping("/products/sort")
    public String sortProducts(Principal principal,Model model,HttpSession session) {
    	model.addAttribute("products",userService.productsOrderdByPrice());
    	model.addAttribute("categories",userService.allCategories());
    	List<Cart> hisCarts=userService.cartProducts((Long) session.getAttribute("user_id"),false);
    	if (principal!=null) {
    		model.addAttribute("size", hisCarts.size());
    		model.addAttribute("myUserId",true);
    	}
    	return "ShopGrid.jsp";
    }
    @GetMapping("/categories/{id}")
    public String categoriesPage(Principal principal,Model model,HttpSession session,@PathVariable("id") Long id) {
    	Category cat=userService.findCategoryById(id);
    	List<Product> myProducts=cat.getProducts();
    	model.addAttribute("categories",userService.allCategories());
    	model.addAttribute("products",myProducts);
    	model.addAttribute("category",cat);
    	List<Cart> hisCarts=userService.cartProducts((Long) session.getAttribute("user_id"),false);
    	if (principal!=null) {
    		model.addAttribute("size", hisCarts.size());
    		model.addAttribute("myUserId",true);
    	}
    	return "categoryPage.jsp";
    }
    @GetMapping("/products/{id}")
    public String productPage(Principal principal,@PathVariable("id") Long id,HttpSession session,Model model) {
    	model.addAttribute("product",userService.findProductById(id));
    	List<Cart> hisCarts=userService.cartProducts((Long) session.getAttribute("user_id"),false);
    	if (principal!=null) {
    		model.addAttribute("size", hisCarts.size());
    		model.addAttribute("myUserId",true);
    	}
    	return "productPage.jsp";
    }
    @GetMapping("/cart/{id}")
    public String addToCartWithQuantity(HttpServletRequest request,@PathVariable("id") Long id,HttpSession session,@RequestParam("quantity") int quantity) {
    	if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/";
        }
    	Product addedProduct=userService.findProductById(id);
    	if(addedProduct.getAvailableQuantity()-quantity>=0) {
    		userService.addToCartWithQuantity((Long) session.getAttribute("user_id"), id,quantity);
    		return "redirect:/products";
    	}
    	return "redirect:/products"+id;
    }
    @GetMapping("/cart1/{id}")
    public String addToCartWithQuantity(HttpServletRequest request,@PathVariable("id") Long id,HttpSession session) {
    	if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/";
        }
    	Product addedProduct=userService.findProductById(id);
    	System.out.println(session.getAttribute("user_id"));    	
    	if(addedProduct.getAvailableQuantity()-1>=0) {
    		userService.addToCart((Long) session.getAttribute("user_id"), id);
    	}
    	return "redirect:/products";
    }
    @GetMapping("/search")
    public String searchFunctionality(Model model,@RequestParam("q") String search) {
    	List<Product> mProducts=userService.searchProduct(search);
    	model.addAttribute("products", mProducts);
    	model.addAttribute("categories",userService.allCategories());
		return "ShopGrid.jsp";
    }
    @GetMapping("/cart/remove/{id}")
    public String removeProductFromCart(HttpServletRequest request,@PathVariable("id") Long id,HttpSession session) {
    	if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/";
        }
    	System.out.println("inside the controller");
    	userService.removeProduuctFromCart(id);
    	return "redirect:/cart";
    }
    
    @GetMapping("/thankyou")
    public String thanks(HttpSession session) {
    	List<Cart> hisCarts=userService.cartProducts((Long) session.getAttribute("user_id"),false);

    	userService.checkoutCart(hisCarts);
    	return"thankyou.jsp";
    }
    
    @GetMapping("/contact")
    public String contact() {

    	return"contact.jsp";
    }
    
    @GetMapping("/about")
    public String about() {

    	return"about.jsp";
    }
}
