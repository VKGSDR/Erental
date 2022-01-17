package com.abc.vehiclerental.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

//This class is for declaring properties of the user and generating getter and setters for the variable //

@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	@NotEmpty(message = "username is required")
	private String userName;

	@NotEmpty(message = "password is required")
	@Size(min = 8, max = 25, message = "password length should be between 8 and 25")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z0-9 *@#]+", message = "For password, first letter must be capital. Number alphabets and special characters are to be included")
	private String password;

	@NotEmpty(message = "address is required")
	private String address;

	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "invalid mobile number")
	private String mobileNo;

	@Email(message = "incorrect email")
	@Column(unique = true, nullable = false)
	private String emailId;

	// flag for whether the user is logged in or not i.e, true if user logged in
	@JsonProperty
	private boolean loggedIn;

	// flag for subscriber, true if user is a subscriber
	@JsonProperty
	private boolean isSubscriber;

	@NotEmpty(message = "role is required")
	private String role;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdTimestamp;

	@UpdateTimestamp
	private LocalDateTime updatedTimestamp;

	@OneToMany(mappedBy = "user")
	@JsonManagedReference(value="user_booking")
	private List<BookingDetails> bookings;
	
	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private List<RegisteredFeedback> registeredFeedback;

	
	public List<RegisteredFeedback> getRegisteredFeedback() {
		return registeredFeedback;
	}

	public void setRegisteredFeedback(List<RegisteredFeedback> registeredFeedback) {
		this.registeredFeedback = registeredFeedback;
	}

	public LocalDateTime getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public LocalDateTime getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(LocalDateTime updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public boolean isSubscriber() {
		return isSubscriber;
	}

	public void setSubscriber(boolean isSubscriber) {
		this.isSubscriber = isSubscriber;
	}

	public List<BookingDetails> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookingDetails> bookings) {
		this.bookings = bookings;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}
