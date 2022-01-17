package com.abc.vehiclerental.service;

import java.util.List;

import com.abc.vehiclerental.entity.UserDetails;

//interface for userService implementation
public interface UserService {

	public UserDetails registerUser(UserDetails userDetails);

	public UserDetails login(String emailId, String password);

	public UserDetails logout(String emailId);

	public UserDetails getUser(long userId);

	public void updateUser(UserDetails user);

	public void deleteAccount(long userId);

	public List<UserDetails> getAllUsers();

}
