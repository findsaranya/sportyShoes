package com.sporty.shoes.SportyShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sporty.shoes.SportyShoes.entity.User;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
	User findByEmailIdAndPassword(String emailId,String password);
	User findByEmailId(String emailId);

}
