package com.abc.vehiclerental.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "CONTACT_US_DETAILS")
public class ContactUsDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "Name is required")
	@Column(name = "name", nullable = false)
	private String name;

	@Pattern(regexp = "^(.+)@(.+)$", message = "Invalid email address")
	private String email;

	@Size(min = 10, max = 10, message = "Fax number should be of 10 digits")
	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Invalid fax number")
	private String fax;

	@NotNull
	@Size(min = 10, max = 10, message = "Mobile number should be of 10 digits")
	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Invalid mobile number")
	private String mobile;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdTimestamp;

	@UpdateTimestamp
	private LocalDateTime updatedTimestamp;

	@NotEmpty(message = "CreatedBy is required")
	private String createdBy;

	private String modifiedBy;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

}
