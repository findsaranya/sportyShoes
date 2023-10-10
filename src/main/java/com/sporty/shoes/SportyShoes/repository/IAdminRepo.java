package com.sporty.shoes.SportyShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sporty.shoes.SportyShoes.entity.Admin;

@Repository
public interface IAdminRepo extends JpaRepository<Admin, Integer> {
   Admin findByNameAndPassword(String name ,String password);
   Admin findByAdminId(int adminId);
}
