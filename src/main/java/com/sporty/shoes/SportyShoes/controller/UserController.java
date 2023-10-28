package com.sporty.shoes.SportyShoes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sporty.shoes.SportyShoes.entity.Product;
import com.sporty.shoes.SportyShoes.entity.User;
import com.sporty.shoes.SportyShoes.services.IProductImpl;
import com.sporty.shoes.SportyShoes.services.IUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IProductImpl prodService;

	 @RequestMapping(value = "/loginaction", method = RequestMethod.POST)
	    public String loginAction(HttpServletRequest req,ModelMap map) 
	    {
		 
		 String emailId = req.getParameter("emailId");
		 String password = req.getParameter("pwd");
		
		 if(emailId != null && !emailId.equals("") && password!=null && !password.equals("")){
			 User result = userService.checkUser(emailId, password);
				if(result == null) {
					 map.addAttribute("error","Invalid password/emailId");
					 return "login";
				}
				 HttpSession session = req.getSession();
				 session.setAttribute("userLoggedIn", true);
				 session.setAttribute("userId", result.getUserId());
			    return "redirect:user-dashboard"; 
		 }else {
			 map.addAttribute("error","EmailId or password can't be empty");
			 return "login";
		 }
	    }
	 
	 @RequestMapping(value = "/user-dashboard", method = RequestMethod.GET)
	    public String adminDashboard(ModelMap map,HttpServletRequest request) {
		 HttpSession session = request.getSession();
		 if (session.getAttribute("userId") == null) {
			  return "redirect:userLogin";
		  }
		 List<Product> list = prodService.getAllProducts(); 
		 map.addAttribute("list", list);
		 return "dashboard";
	 }
	 
	 @RequestMapping(value = "/signup", method = RequestMethod.GET)
	    public String signup(ModelMap map) 
	    {
		 User user = new User();
		 map.addAttribute("user",user);
		  map.addAttribute("pageTitle", "SPORTY SHOES - MEMBER REGISTRATION");
	        return "register"; 
	    }
	 
	 @RequestMapping(value = "/signupaction", method = RequestMethod.POST)
	    public String signupAction(ModelMap map,@RequestParam(value="id", required=true) String id,HttpServletRequest request) {
		 String fname = request.getParameter("fname");
		 String lname = request.getParameter("lname");
		 String pwd = request.getParameter("pwd");
		 String pwd2 = request.getParameter("pwd2");
		 String emailId = request.getParameter("emailId");
		 int age = Integer.parseInt(request.getParameter("age"));
		 String addr = request.getParameter("address");
		 
		 User user = new User();
		 
		 int idValue = Integer.parseInt(id);
		 
		  if (idValue > 0) {
			  Optional<User> result  = userService.getUserInfo(idValue);
			  if(result.isPresent()) {
				  user = result.get();
			  }
		  } 
		 
		 if (emailId == null || emailId.equals("")) {
			 map.addAttribute("user",user);
			  map.addAttribute("error", "Email id is required.");
			  return "register";
		  }else {
			  User emailIdExists = userService.checkEmailExists(emailId);
			  if(emailIdExists != null) {
				  map.addAttribute("user",user);
				  map.addAttribute("error", "EmailId already Exists.");
				  return "register";
			  }
		  }

		  if (pwd == null || pwd2 == null || pwd.equals("") || pwd2.equals("")) {
			  map.addAttribute("user",user);
			  map.addAttribute("error", "Error , Incomplete passwords submitted.");
			  return "register";
		  }
		  
		  if (!pwd.equals(pwd2)) {
			  map.addAttribute("user",user);
			  map.addAttribute("error", "Error , Passwords do not match.");
			  return "register";
		  }
		  
		  if (fname == null || fname.equals("")) {
			  map.addAttribute("user",user);
			  map.addAttribute("error", "First name is required.");
			  return "register";
		  }
		  

		  if (lname == null || lname.equals("")) {
			  map.addAttribute("user",user);
			  map.addAttribute("error", "Last name is required.");
			  return "register";
		  }

		 
          
		  user.setUserId(idValue);
		  user.setAddress(addr);
		  user.setAge(age);
		  user.setEmailId(emailId);
		  user.setFname(fname);
		  user.setLname(lname);
		  user.setPassword(pwd);
		  
		  userService.createNewUser(user);
		 
		 return "redirect:userLogin";
	 }
	 

	  @RequestMapping(value = "/editprofile", method = RequestMethod.GET)
	    public String editProfileAction(ModelMap map,HttpServletRequest request)
	  {
		  HttpSession session = request.getSession();
			 if (session.getAttribute("userId") == null) {
				  return "redirect:userLogin";
			  }
			 User user = new User();
			 int userId = (int) session.getAttribute("userId");
			  Optional<User> result  = userService.getUserInfo(userId);
			  if(result.isPresent()) {
				  user = result.get();
			  }
	
			  map.addAttribute("user",user);
			  map.addAttribute("pageTitle", "SPORTY SHOES - PROFILE UPDATE");
		        return "register"; 
	  }
	 	 
	 @RequestMapping(value = "/userlogout", method = RequestMethod.GET)
	    public String adminLogout(ModelMap map,HttpServletRequest request) 
	    {
		  	HttpSession session = request.getSession();
		  	session.invalidate();
		  
	        return "redirect:home"; 
	    }
	 
	 
}
