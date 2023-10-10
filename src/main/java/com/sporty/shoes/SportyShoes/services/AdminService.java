package com.sporty.shoes.SportyShoes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sporty.shoes.SportyShoes.entity.Admin;
import com.sporty.shoes.SportyShoes.repository.IAdminRepo;
@Service
public class AdminService implements IAdminServiceImpl {
	
	@Autowired
	private IAdminRepo adminRepo;

	@Override
	public Admin checkNamePassword(String name, String password) {
		Admin admin = adminRepo.findByNameAndPassword(name, password);
		return admin;
	}

	@Override
	public Admin updateAdmin(Admin admin) {
	     Admin result = adminRepo.save(admin);
		return result;
	}

	@Override
	public Admin getAdminById(int Id) {
		Admin result = adminRepo.findByAdminId(Id);
		return result;
		
	}

	
}
