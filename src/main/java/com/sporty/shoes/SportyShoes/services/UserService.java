package com.sporty.shoes.SportyShoes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.SportyShoes.entity.User;
import com.sporty.shoes.SportyShoes.repository.IUserRepo;

@Service
public class UserService implements IUserService {
	@Autowired
	IUserRepo userRepo;

	@Override
	public User createNewUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public Optional<User> getUserInfo(int userId) {
		return userRepo.findById(userId);
	}

	@Override
	public String deleteUser(int userId) {
		 userRepo.deleteById(userId);
		 return "User Id "+userId+" deleted Successfully";	 
	}

	@Override
	public User checkUser(String emailId, String password) {
		return userRepo.findByEmailIdAndPassword(emailId, password);
	}

	@Override
	public User checkEmailExists(String emailId) {
		return userRepo.findByEmailId(emailId);
	}

	@Override
	public List<User> searchUserByName(String fname, String lname) {
		return userRepo.searchByFnameOrLname(fname, lname);
	}

}
