package com.codingdojo.ReveaStoreProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.codingdojo.ReveaStoreProject.models.Product;
import com.codingdojo.ReveaStoreProject.models.User;
import com.codingdojo.ReveaStoreProject.repositories.CartRepository;
import com.codingdojo.ReveaStoreProject.repositories.ProductRepository;
import com.codingdojo.ReveaStoreProject.repositories.RoleRepository;
import com.codingdojo.ReveaStoreProject.repositories.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private CartRepository cartRepository;
	private ProductRepository productRepository;


	public UserService(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder,CartRepository cartRepository,ProductRepository productRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.cartRepository=cartRepository;
		this.productRepository=productRepository;
	}

	public void saveWithUserRole(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(roleRepository.findByName("ROLE_USER"));
		userRepository.save(user);
	}

	public void saveUserWithAdminRole(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
		userRepository.save(user);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	public List<User> allUsers(){
		return userRepository.findAll();
	}
	 public Product createProduct(Product b) {
	        return productRepository.save(b);
	    }
	 public Product findProductById(Long id) {
	    	Optional<Product> event = productRepository.findById(id);
	    	if(event.isPresent()) {
	            return event.get();
	    	}
	    	else {
	    	    return null;
	    	}
	    }
	 public void updateProduct(Long id, String name, String description, double price, String image,int availableQuantity) {
			Optional <Product> temp = productRepository.findById(id);
			if(temp != null) {
				temp.get().setName(name);
				temp.get().setDescription(description);
				temp.get().setPrice(price);
				temp.get().setImage(image);
				temp.get().setAvailableQuantity(availableQuantity);
			}  
	    }
	
//	//cart
//	public void addToCart(Long carId, Cart cartItems, User user){
//
//		Product product = productRepository.findById(carId).orElse(null);
//		    cartItems.setProduct(product);
//
//		        cartItems.setTotalPrice(product.getPrice());
//		        cartItems.setUser(user);
//
//		        cartRepository.save(cartItems);
//
//		}
//		public List<Cart> myCart(String userName){
//
//		    List<Cart> cartItems = new ArrayList<>();
//		    cartRepository.findByUsername(userName).forEach(cartItems::add);
//
//		    return cartItems;
//		}

}
