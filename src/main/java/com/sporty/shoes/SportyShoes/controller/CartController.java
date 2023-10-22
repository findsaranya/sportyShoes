package com.sporty.shoes.SportyShoes.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sporty.shoes.SportyShoes.entity.Product;
import com.sporty.shoes.SportyShoes.model.CartItems;
import com.sporty.shoes.SportyShoes.services.IProductImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Controller
public class CartController {
	@Autowired
	IProductImpl prodService;
	
	@RequestMapping(value="/cart",method=RequestMethod.GET)
	public String cartPage(ModelMap map,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String error = null;
		 Set<CartItems> listItems = new HashSet<CartItems>();
		 BigDecimal total = BigDecimal.ZERO;
		 if (session.getAttribute("userId") == null) {
			 error = "<h4 style='color:red;'>Error, You need to login before adding items to cart</h4>";
		  }else {
			  
			  //check item list in session
				 if(session.getAttribute("cartItems") != null) {
					 listItems = ( Set<CartItems>) session.getAttribute("cartItems");
		
				 }
				total = listItems.stream().map(CartItems::getGrossTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
		  }
		 
		  map.addAttribute("cartValue", total);
		  map.addAttribute("cartItems", listItems);
		  map.addAttribute("error",error);
		  map.addAttribute("pageTitle", "SPORTY SHOES - YOUR CART");
			return "cart";
		
	}
	
	@RequestMapping(value="/addTocart",method=RequestMethod.GET)
	public String addToCart(ModelMap map,HttpServletRequest request, @RequestParam(value="id", required=true) String id) {
		
		HttpSession session = request.getSession();
		 if (session.getAttribute("userId") == null) { 
			return "redirect:cart";
		  }

		 //product id
		 int idValue = Integer.parseInt(id);
		 //list
		 Set<CartItems> listItems = new HashSet<CartItems>();
		 //check item list in session
		 if(session.getAttribute("cartItems") != null) {
			 listItems = (Set<CartItems>) session.getAttribute("cartItems"); 
		 }
		 //get the product
		 String error = null;
		 Optional<Product> product = prodService.getProductInfo(idValue);
		 if(product.isPresent()) {
			 Long findItem = listItems.stream().filter(x -> x.getProduct().getPoductId() == product.get().getPoductId()).count();
			 if(findItem >0) {
				error = "<h4 style='color:red;'>Item already in the cart!!</h4>";
			 }else {
				 CartItems cartItems = new CartItems();
				 cartItems.setProduct(product.get());
				 cartItems.setQty(1);
				 BigDecimal totalPrice = product.get().getPrice().multiply(new BigDecimal(cartItems.getQty()));
				 cartItems.setGrossTotal(totalPrice);
				 listItems.add(cartItems);
			 }	 
			 }
		 else {
			 error = "<h4 style='color:red;'>Product not available</h4>";
		 }

		
		BigDecimal total = listItems.stream().map(CartItems::getGrossTotal).reduce(BigDecimal.ZERO, BigDecimal::add); 
		 session.setAttribute("cartItems", listItems);
		 map.addAttribute("cartItems", listItems);
		 map.addAttribute("cartValue", total);
		 map.addAttribute("error",error);
		 map.addAttribute("pageTitle", "SPORTY SHOES - YOUR CART");
		 
		return "cart";
	}

}
