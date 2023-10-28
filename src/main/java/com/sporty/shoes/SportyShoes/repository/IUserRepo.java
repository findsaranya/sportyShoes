package com.sporty.shoes.SportyShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sporty.shoes.SportyShoes.entity.User;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
	User findByEmailIdAndPassword(String emailId,String password);
	User findByEmailId(String emailId);
	@Query("select u from User u where u.fname=?1 or u.lname=?2")
	List<User> searchByFnameOrLname(String fname,String lname);

}
