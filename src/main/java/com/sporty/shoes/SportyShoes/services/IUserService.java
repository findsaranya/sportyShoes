package com.sporty.shoes.SportyShoes.services;

import java.util.List;
import java.util.Optional;

import com.sporty.shoes.SportyShoes.entity.User;

public interface IUserService {
	User createNewUser(User User);
	User updateUser(User User);
	List<User> getAllUsers();
	Optional<User> getUserInfo(int UserId);
	String deleteUser(int UserId);
	User checkUser(String emailId,String password);
	User checkEmailExists(String emailId);
	List<User> searchUserByName(String fname,String lname);
}
