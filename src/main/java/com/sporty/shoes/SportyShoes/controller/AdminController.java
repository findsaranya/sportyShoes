package com.sporty.shoes.SportyShoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sporty.shoes.SportyShoes.entity.Admin;
import com.sporty.shoes.SportyShoes.services.IAdminServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	IAdminServiceImpl adminService;

	 @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String login(ModelMap map, HttpServletRequest request) 
	    {
		 HttpSession session = request.getSession();
		 if (session.getAttribute("adminId") != null) {
			  return "admin/dashboard";
		  }
		 
		  map.addAttribute("pageTitle", "ADMIN LOGIN");
	        return "admin/login"; 
	    }
	 
	 @RequestMapping(value = "/loginAction", method = RequestMethod.POST)
	    public String loginAction(HttpServletRequest req,ModelMap map) 
	    {
		 String name = req.getParameter("admin_name");
		 String password = req.getParameter("password");
		
		 if(name != null && !name.equals("") && password!=null && !password.equals("")){
			 Admin result = adminService.checkNamePassword(name,password);
				if(result == null) {
					 map.addAttribute("error","Invalid password/username");
					 return "admin/login";
				}
				 HttpSession session = req.getSession();
				 session.setAttribute("adminLoggedIn", true);
				 session.setAttribute("adminId", result.getAdminId());
			        return "admin/dashboard"; 
		 }else {
			 map.addAttribute("error","Username or password can't be empty");
			 return "admin/login";
		 }
	    }
	 
	 @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	    public String adminDashboard(ModelMap map,HttpServletRequest request) {
		 HttpSession session = request.getSession();
		 if (session.getAttribute("adminId") == null) {
			  return "admin/login";
		  }
		 
		 return "admin/dashboard";
	 }
	 
	 @RequestMapping(value = "/adminchangepassword", method = RequestMethod.GET)
	    public String changePassword(ModelMap map,HttpServletRequest request) {
		 HttpSession session = request.getSession();
		 if (session.getAttribute("adminId") == null) {
			  return "admin/login";
		  }
		 
		 int adminId = (int) session.getAttribute("adminId");
		  map.addAttribute("adminId", adminId);
		  map.addAttribute("pageTitle", "ADMIN CHANGE PASSWORD");
		 
		 return "admin/change-password";
	 }
	 
	 @RequestMapping(value = "/adminchangepwdaction", method = RequestMethod.POST)
	    public String changePasswordAction(ModelMap map,HttpServletRequest request) {
		 HttpSession session = request.getSession();
		 if (session.getAttribute("adminId") == null) {
			  return "admin/login";
		  }
		 
		  String newPassword = request.getParameter("pwd");
		     String confirmPassword = request.getParameter("confirm");
		     if(newPassword != null && !newPassword.isBlank() && !newPassword.isEmpty() && confirmPassword != null 
		    		 && !confirmPassword.isBlank() && !confirmPassword.isEmpty() ){
		    	 if(newPassword.equals(confirmPassword)){
		    		 int adminId = (int) session.getAttribute("adminId");
		    		Admin adminResult = adminService.getAdminById(adminId); 
		    		adminResult.setPassword(newPassword);
		    		adminService.updateAdmin(adminResult);
		    		session.invalidate();
		    		return "admin/login";
		    	 }else {
		    		 map.addAttribute("error", "<h4 style='color:red;'>password not matching</h4>"); 
			    	 return "admin/change-password";
		    	 }
		     }else {
		    	 map.addAttribute("error", "Invalid Password!!"); 
		    	 return "admin/change-password";
		     }

	 }
	 
	 @RequestMapping(value = "/adminlogout", method = RequestMethod.GET)
	    public String adminLogout(ModelMap map,HttpServletRequest request) 
	    {
		  	HttpSession session = request.getSession();
		  	session.invalidate();
		  	map.addAttribute("pageTitle", "ADMIN LOGIN");
	        return "admin/login"; 
	    }
}
