package com.sporty.shoes.SportyShoes.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sporty.shoes.SportyShoes.entity.PurchaseOrder;
import com.sporty.shoes.SportyShoes.entity.User;
import com.sporty.shoes.SportyShoes.services.IPOService;
import com.sporty.shoes.SportyShoes.services.IUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PurchaseOrderController {
	@Autowired
	IPOService poService;
		
	@Autowired
	IUserService userService;
	
	  @RequestMapping(value = "/memberpurchases", method = RequestMethod.GET)
	    public String memberpurchases(ModelMap map, HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  BigDecimal total = BigDecimal.ZERO;
		  if (session.getAttribute("userId") == null) {
			  return "redirect:userLogin";
		  }
		  int userId = (int) session.getAttribute("userId");
		  Optional<User> userDetail = userService.getUserInfo(userId);
		  List<PurchaseOrder> poList = poService.getPOByUser(userDetail.get());
		  total = poList.stream().map(PurchaseOrder::getGrossTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
		  map.addAttribute("list", poList);
		  map.addAttribute("totalAmount",total);
		  map.addAttribute("pageTitle", "SPORTY SHOES - YOUR ORDERS");
		  return "purchases"; 
	
		  
	    }		

}
