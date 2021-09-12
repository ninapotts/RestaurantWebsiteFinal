 package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//@Table(name="RESTAURANTS")
@Entity

public class Restaurant implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	

	@Id
	//@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	

	@Column(name = "NAME")
	private String restaurantName;
	
	@Column(name = "ADDRESS")
	private String restaurantAddress;
	
	@Column(name = "DESCRIPTION")
	private String restaurantDescription;
	
	@Column(name = "RATING")
	private Double restaurantRating;
	
	
	//@Transient
	@JsonBackReference
	@OneToMany( cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurant_id")
	List<Review> reviews;
	
//	//@Column(name="")
////	@JsonManagedReference
//	@OneToMany(mappedBy="restaurant", cascade = CascadeType.ALL)
//	private List<Review> reviews;

//	public Restaurant() {
//		this(-1,"N/A","N/A", "N/A" , 0.0, new ArrayList<Review>());
//	}
//	
//	
//	
//	public Restaurant(Integer id, String restaurantName, String restaurantAddress, String restaurantDescription,
//				Double restaurantRating, List<Review> reviews) {
//			super();
//			this.id = id;
//			this.restaurantName = restaurantName;
//			this.restaurantAddress = restaurantAddress;
//			this.restaurantDescription = restaurantDescription;
//			this.restaurantRating = restaurantRating;
//			this.reviews = reviews;
//	}
	public Restaurant() {
		this(-1,"N/A","N/A", "N/A" , 0.0);
	}
	
	

	public Restaurant(Integer id, String restaurantName, String restaurantAddress, String restaurantDescription,
		Double restaurantRating) {
	super();
	this.id = id;
	this.restaurantName = restaurantName;
	this.restaurantAddress = restaurantAddress;
	this.restaurantDescription = restaurantDescription;
	this.restaurantRating = restaurantRating;
}



	public String toJson() {
		
		return "{\"id\" : " + id
				+ ", \"restaurantName\" : \"" + restaurantName + "\""
				+ ", \"restaurantAddress\" : \"" + restaurantAddress + "\"" 
				+ ", \"restaurantDescription\" : \"" + restaurantDescription + "\"" +
				", \"restaurantRating\" : \"" + restaurantRating + "\"" +
				", \"reviews\" : \"" + reviews + "\"" +
		"}";
	}
	

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

	public String getRestaurantDescription() {
		return restaurantDescription;
	}

	public Integer getRestaurantId() {
		return id;
	}


	public void setRestaurantId(Integer id) {
		this.id = id;
	}


//	public List<User> getUsers() {
//		return users;
//	}
//
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}


	public void setRestaurantDescription(String restaurantDescription) {
		this.restaurantDescription = restaurantDescription;
	}

	public List<Review> getReviews() {
		return reviews;
	}




	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}




	public Double getRestaurantRating() {
		return restaurantRating;
	}

	public void setRestaurantRating(Double restaurantRating) {
		this.restaurantRating = restaurantRating;
	}



	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", restaurantName=" + restaurantName + ", restaurantAddress="
				+ restaurantAddress + ", restaurantDescription=" + restaurantDescription + ", restaurantRating="
				+ restaurantRating + "]";
	}
}
