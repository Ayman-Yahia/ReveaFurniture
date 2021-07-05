package com.codingdojo.ReveaStoreProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.codingdojo.ReveaStoreProject.models.Cart;
import com.codingdojo.ReveaStoreProject.models.Category;
import com.codingdojo.ReveaStoreProject.models.Product;
import com.codingdojo.ReveaStoreProject.models.User;
import com.codingdojo.ReveaStoreProject.repositories.CartRepository;
import com.codingdojo.ReveaStoreProject.repositories.CategoryRepository;
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
	private CategoryRepository categoryRepository;


	public UserService(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder,CartRepository cartRepository,ProductRepository productRepository,CategoryRepository categoryRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.cartRepository=cartRepository;
		this.productRepository=productRepository;
		this.categoryRepository=categoryRepository;
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
	 public Category createCategory(Category b) {
	        return categoryRepository.save(b);
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
	 public Category findCategoryById(Long id) {
	    	Optional<Category> event = categoryRepository.findById(id);
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
	public void addToCart( Long user,Long product){
		Product mProduct=productRepository.findById(product).orElse(null);
		if((mProduct.getAvailableQuantity()-1)>=0) {
			Cart m=cartRepository.getCartWhereIdAndUserAndNotOrederd(product,user,true);
			if (m!=null) {
				int s=m.getQuantity();
				m.setQuantity(s+1);
				cartRepository.save(m);

			}else {
				User mUser=userRepository.findById(user).orElse(null);
				mProduct.setAvailableQuantity(mProduct.getAvailableQuantity()-1);
				productRepository.save(mProduct);
				Cart addCart=new Cart(mProduct.getPrice(),1,true,mUser,mProduct);
				cartRepository.save(addCart);
			}
		}
		
		
	}
	public void addToCartWithQuantity( Long user,Long product,int quantity){
		Product mProduct=productRepository.findById(product).orElse(null);
		if((mProduct.getAvailableQuantity()-quantity)>=0) {
			Cart m=cartRepository.getCartWhereIdAndUserAndNotOrederd(product,user,true);
			if (m!=null) {
				int s=m.getQuantity();
				m.setQuantity(s+quantity);
				cartRepository.save(m);

			}else {
				User mUser=userRepository.findById(user).orElse(null);
				mProduct.setAvailableQuantity(mProduct.getAvailableQuantity()-quantity);
				productRepository.save(mProduct);
				Cart addCart=new Cart(mProduct.getPrice(),quantity,true,mUser,mProduct);
				cartRepository.save(addCart);
			}
		}
		
	}
	public void removeProduuctFromCart(Long user,Long product) {
		Cart m=cartRepository.getCartWhereIdAndUserAndNotOrederd(product,user,true);
		cartRepository.deleteById(m.getId());
		
	}
	public List<Product> allProducts(){
		return productRepository.findAll();
	}
	public List<Category> allCategories(){
		return categoryRepository.findAll();
	}
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	public List<Product> productsOrderdByPrice(){
		return productRepository.findByOrderByPriceAsc();
	}
}
