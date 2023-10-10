package com.sporty.shoes.SportyShoes.services;


import com.sporty.shoes.SportyShoes.entity.Admin;

public interface IAdminServiceImpl {
	 Admin checkNamePassword(String name,String password);
	 Admin updateAdmin(Admin admin);
	 Admin getAdminById(int Id);
}
