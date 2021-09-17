package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;
import java.util.List;

//@Table(name="USER")
@Entity
public class UserModel implements Serializable{

	public enum Role {
		ROLE_USER, ROLE_ADMIN, ADMIN, USER
	}

	private static final long serialVersionUID = 1L;
	
	@Id//Pk
	//@Column(name="USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name="USERNAME")
	String userName;
	
	@Column(name="PASSWORD")
	String password;
	
	
//	@Enumerated(EnumType.STRING)
//	@Column(nullable = false)
//	private ROLE role;
	
	@Column(columnDefinition = "boolean default true")
	private boolean enabled;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
//	@Column(name="IS_ADMIN")
//	Boolean role;
//	
	
	@JsonBackReference
	@OneToMany( cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	List<Review> reviews;

	
	
	
	
//	@OneToMany( cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id"/*, referencedColumnName = "id"*/)
//	List<Review> reviews;
	
	
	//List<Restaurant> favorites;
	
	public UserModel() {
		this(-1,"N/A","N/A", Role.ROLE_USER /*, new ArrayList<Review>()*/);
	}

	
	


	public UserModel(Integer id, String userName, String password, Role role/*, List<Review> reviews*/) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
//		this.reviews = reviews;
	}
	
	public String toJson() {
		
		return "{\"id\" : " + id
				+ ", \"username\" : \"" + userName + "\""
				+ ", \"password\" : \"" + password + "\"" 
				+ ", \"role\" : \"" + role + "\"" +
				", \"reviews\" : \"" + reviews + "\"" +
		"}";
	}





	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<Review> getReviews() {
		return reviews;
	}


	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", reviews=" + reviews
				+  "]";
	}





	public Integer getId() {
		return id;
	}





	public void setId(Integer id) {
		this.id = id;
	}




	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	

}