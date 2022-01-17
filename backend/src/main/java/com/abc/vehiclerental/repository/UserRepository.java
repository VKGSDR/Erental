package com.abc.vehiclerental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abc.vehiclerental.entity.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Long> {

	@Query("Select count(*) from UserDetails u where u.role = 'user'")
	public int findByRole();

	@Query("Select count(*) from UserDetails u where u.isSubscriber = true")
	public int findByIsSubscriber();

	
	@Query("SELECT u FROM UserDetails u WHERE u.emailId= :email and u.password = :pwd")
	public UserDetails login(@Param("email") String emailId, @Param("pwd") String password);

	
	public UserDetails findByEmailId(String emailId);

}
