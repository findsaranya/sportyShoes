package com.sporty.shoes.SportyShoes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

	 @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String login(ModelMap map, HttpServletRequest request) 
	    {
		  map.addAttribute("pageTitle", "ADMIN LOGIN");
	        return "admin/login"; 
	    }
}
