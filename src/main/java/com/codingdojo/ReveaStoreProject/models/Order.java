package com.codingdojo.ReveaStoreProject.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

	@Entity
	@Table(name="orders")
	public class Order {
		@Id
	    @GeneratedValue
	    private Long id;
	    private Long quantity;
	    private String address;
	    private Long phone_unmber;
	    private float total_price;
	    @Column(updatable=false)
	    private Date createdAt;
	    private Date updatedAt;
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="user_id")
	    private User user;
	    @OneToMany(mappedBy="order", fetch = FetchType.LAZY)
	    private List<Cart> carts;
	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	        name = "orders_products", 
	        joinColumns = @JoinColumn(name = "order_id"), 
	        inverseJoinColumns = @JoinColumn(name = "product_id"))
	    private List<Product> products;
	    
	    public Order() {
	    }
		public Long getId() {
			return id;
		}


		public Long getQuantity() {
			return quantity;
		}



		public void setQuantity(Long quantity) {
			this.quantity = quantity;
		}



		public String getAddress() {
			return address;
		}



		public void setAddress(String address) {
			this.address = address;
		}



		public Long getPhone_unmber() {
			return phone_unmber;
		}



		public void setPhone_unmber(Long phone_unmber) {
			this.phone_unmber = phone_unmber;
		}



		public float getTotal_price() {
			return total_price;
		}



		public void setTotal_price(float total_price) {
			this.total_price = total_price;
		}



		public Date getCreatedAt() {
			return createdAt;
		}



		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}



		public Date getUpdatedAt() {
			return updatedAt;
		}



		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}



		@PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
	    @PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }
	    
	}


