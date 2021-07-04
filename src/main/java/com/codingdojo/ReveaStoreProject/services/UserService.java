package com.codingdojo.ReveaStoreProject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.codingdojo.ReveaStoreProject.models.Cart;
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
