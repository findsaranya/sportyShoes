package com.sporty.shoes.SportyShoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sporty.shoes.SportyShoes.entity.Product;
import com.sporty.shoes.SportyShoes.services.IProductImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	IProductImpl prodService;
	
	@RequestMapping(value = "/home")
	public String home(ModelMap map) {
		  List<Product> list = prodService.getAllProducts(); 
		  map.addAttribute("list", list);
		    map.addAttribute("pageTitle", "SPORTY SHOES - HOMEPAGE"); 
		return "index";
	}
	
	  @RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	    public String login(ModelMap map,HttpServletRequest request) 
	    {
			 HttpSession session = request.getSession();
			 if (session.getAttribute("userId") != null) {
				  return "redirect:user-dashboard";
			  }
		  map.addAttribute("pageTitle", "SPORTY SHOES - MEMBER LOGIN");
	        return "login"; 
	    }	
	  
	
}
