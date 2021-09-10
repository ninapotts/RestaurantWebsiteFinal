package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;
import java.util.Arrays;

//@Table(name="USER")
@Entity
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id//Pk
	//@Column(name="USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name="USERNAME")
	String userName;
	
	@Column(name="PASSWORD")
	String password;
	
	@Column(name="IS_ADMIN")
	Boolean isAdmin;
	
	
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	List<Review> reviews;
	
	
	//List<Restaurant> favorites;
	
	public User() {
		
		this(-1,"N/A","N/A",false, Arrays.asList(new Review()));
	}

	
	


	public User(Integer id, String userName, String password, Boolean isAdmin, List<Review> reviews) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.isAdmin = isAdmin;
		this.reviews = reviews;
	}
	
	public String toJson() {
		
		return "{\"id\" : " + id
				+ ", \"username\" : \"" + userName + "\""
				+ ", \"password\" : \"" + password + "\"" 
				+ ", \"isAdmin\" : \"" + isAdmin + "\"" +
				", \"reviews\" : \"" + reviews + "\"" +
		"}";
	}





	public Integer getUserId() {
		return id;
	}


	public void setUserId(Integer id) {
		this.id = id;
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


//	public List<Restaurant> getFavorites() {
//		return favorites;
//	}
//
//
//	public void setFavorites(List<Restaurant> favorites) {
//		this.favorites = favorites;
//	}


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





	public Boolean getIsAdmin() {
		return isAdmin;
	}





	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	
	
	

}
