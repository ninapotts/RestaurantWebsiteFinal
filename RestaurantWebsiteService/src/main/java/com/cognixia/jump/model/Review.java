package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Table(name="REVIEWS")
@Entity

public class Review implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id//Pk
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="REVIEWID")
	Integer id;
	
	@Column(name="REVIEWCONTENT")
	String reviewContent;
	
	@Column(name="RATING")
	Double rating;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	User user;
	
	@ManyToOne
	@JoinColumn(name="restaurant_id", referencedColumnName="id")
	Restaurant restaurant;
	
//	@Column(name="USER_ID")
//	Integer userId;
	
	
	public Review() {
		super();
	}

	

	public Review(Integer id, String reviewContent, Double rating, User user, Restaurant restaurant) {
	super();
	this.id = id;
	this.reviewContent = reviewContent;
	this.rating = rating;
	this.user = user;
	this.restaurant = restaurant;
}

	public String toJson() {
		
		return "{\"id\" : " + id
				+ ", \"reviewContent\" : \"" + reviewContent + "\""
				+ ", \"rating\" : \"" + rating + "\"" 
				+ ", \"user\" : \"" + user + "\"" +
				", \"restaurant\" : \"" + restaurant + "\"" +
		"}";
	}


	public Integer getId() {
		return id;
	}

	public void setReviewId(Integer reviewId) {
		this.id = reviewId;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", reviewContent=" + reviewContent + ", rating=" + rating + ", user="
				+ user + ", restaurantId=" + restaurant + "]";
	}

	
}
