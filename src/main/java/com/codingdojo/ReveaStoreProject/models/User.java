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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

import com.sun.istack.NotNull;

@Entity
@Table(name="users")
public class User {
	@Id
    @GeneratedValue
    private Long id;
	@NotNull
	@Email(message="Email must be valid")
	private String email;
	private String password;
    @Transient
    private String passwordConfirmation;
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
	public User() {
    }
    public Long getId() {
        return id;
	}
    public void setId(Long id) {
        this.id = id;
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
