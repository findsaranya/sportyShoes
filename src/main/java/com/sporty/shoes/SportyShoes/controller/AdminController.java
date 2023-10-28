package com.sporty.shoes.SportyShoes.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sporty.shoes.SportyShoes.entity.Admin;
import com.sporty.shoes.SportyShoes.entity.Category;
import com.sporty.shoes.SportyShoes.entity.Product;
import com.sporty.shoes.SportyShoes.entity.PurchaseOrder;
import com.sporty.shoes.SportyShoes.entity.User;
import com.sporty.shoes.SportyShoes.services.IAdminServiceImpl;
import com.sporty.shoes.SportyShoes.services.ICategoryImpl;
import com.sporty.shoes.SportyShoes.services.IPOService;
import com.sporty.shoes.SportyShoes.services.IProductImpl;
import com.sporty.shoes.SportyShoes.services.IUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	
	@Autowired
	private IAdminServiceImpl adminService;
	
	@Autowired
	private ICategoryImpl categoryService;
	
	@Autowired
	private IProductImpl prodService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IPOService poService;
	

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
	 
	 @RequestMapping(value = "/admincategories", method = RequestMethod.GET)
	    public String categories(ModelMap map, HttpServletRequest request) 
	    {
		 
		 HttpSession session = request.getSession();
		 if (session.getAttribute("adminId") == null) {
			  return "admin/login";
		  }
		  
		  List<Category> list = categoryService.getAllCategorys();
		  map.addAttribute("list", list);
		  map.addAttribute("pageTitle", "ADMIN SETUP PRODUCT CATEGORIES");
	        return "admin/categories"; 
	    }
	 
	  @RequestMapping(value = "/admineditcat",  method = RequestMethod.GET)
	    public String editCategory(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    	HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
			 if (session.getAttribute("adminId") == null) {
				  return "admin/login";
			  }
			  
		  int idValue = Integer.parseInt(id);
		  Category category = new Category();
		  if (idValue > 0) {
			  Optional<Category> result = categoryService.getCategoryInfo(idValue);
			 if(result.isPresent()) {
				 category = result.get();
			 }		
		  }
		  map.addAttribute("category", category);
		  map.addAttribute("pageTitle", "ADMIN EDIT PRODUCT CATEGORY");
	        return "admin/edit-category"; 
	    }		  

	  @RequestMapping(value = "/admineditcataction", method = RequestMethod.POST)
	    public String updateCategory(ModelMap map,HttpServletRequest request)
	    {
		  HttpSession session = request.getSession();
			 if (session.getAttribute("adminId") == null) {
				  return "admin/login";
			  }
			  
		  String id = request.getParameter("id");
		  String name = request.getParameter("name");
		  
		  int idValue = Integer.parseInt(id); 
		  
		  if (name == null || name.equals("") ) { 
			  map.addAttribute("error", "Error, A category name must be specified");
			  return "redirect:admineditcat?id="+id;
		  }
		  	Category category = new Category();
		  	category.setCatId(idValue); 
		  	category.setName(name);
		  	
		  	categoryService.updateCategory(category); 
		  	
	        return "redirect:admincategories";  
	    }
	  
	  
	  @RequestMapping(value = "/admindeletecat",  method = RequestMethod.GET)
	    public String deleteCategory(ModelMap map,  @RequestParam(value="id", required=true) String id,HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
			 if (session.getAttribute("adminId") == null) {
				  return "admin/login";
			  }
			  
		  int idValue = Integer.parseInt(id);
		  if (idValue > 0) {
			  categoryService.deleteCategory(idValue);
		  }
		  return "redirect:admincategories";
	    }	
	 
	  @RequestMapping(value = "/adminproducts", method = RequestMethod.GET)
	    public String products(ModelMap map,HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
			 if (session.getAttribute("adminId") == null) {
				  return "admin/login";
			  }
			  
		  List<Product> list = prodService.getAllProducts(); 
		  map.addAttribute("list", list);
		  map.addAttribute("pageTitle", "ADMIN SETUP PRODUCTS");
	        return "admin/products"; 
	    }
	  
	  @RequestMapping(value = "/admineditproduct",  method = RequestMethod.GET)
	    public String editProduct(ModelMap map, @RequestParam(value="id", required=true) String id,HttpServletRequest request) 
	    {
		  
		  HttpSession session = request.getSession();
			 if (session.getAttribute("adminId") == null) {
				  return "admin/login";
			  }
			   
			  int idValue = Integer.parseInt(id);

			  
		  Product product = new Product();
		  List<Category> catList = categoryService.getAllCategorys();
		  
		 
		  if (idValue > 0) {
			  Optional<Product> result  = prodService.getProductInfo(idValue);
			  if(result.isPresent()) {
				  product = result.get();
			  }
		  } 

		  map.addAttribute("product", product);
		  map.addAttribute("catList", catList);
		  map.addAttribute("pageTitle", "ADMIN EDIT PRODUCT");
	        return "admin/edit-product"; 
	    }		  
	  
	  @RequestMapping(value = "/admineditproductaction", method = RequestMethod.POST)
	    public String updateProduct(ModelMap map,HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
			 if (session.getAttribute("adminId") == null) {
				  return "admin/login";
			  }
			   
		  String name = request.getParameter("name");
		
		  String price = request.getParameter("price");
		  int catId = Integer.parseInt(request.getParameter("category"));
		  int id = Integer.parseInt(request.getParameter("id"));
		 
		  BigDecimal priceValue = new BigDecimal(0.0);
		  Category cat = new Category();
		 
		  if (name == null || name.equals("") || catId == 0 ) { 
			  map.addAttribute("error", "Error, A product name must be specified or category must be selected");
			  return "redirect:admineditproduct?id="+id;
		  }else {
			  Optional<Category> catDetail  = categoryService.getCategoryInfo(catId);
			  if(catDetail.isPresent()) {
				  cat = catDetail.get();
			  }
		  }
	
		  try {
			  priceValue = new BigDecimal(price);
			  
		  } catch (Exception ex) {
			  map.addAttribute("error", "Error, Price is invalid");
			  return "redirect:admineditproduct?id="+id;
		  }
		  
		  	Product product = new Product();
		  	product.setPoductId(id);
		  	product.setCategory(cat);
		  	product.setName(name);
		  	product.setPrice(priceValue);
		  	
		  	prodService.updateProduct(product); 
		  	
	        return "redirect:adminproducts"; 
	    }	
	   
	  @RequestMapping(value = "/admindeleteproduct",  method = RequestMethod.GET)
	    public String deleteProduct(@RequestParam(value="id", required=true) String id,
	    	HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
			 if (session.getAttribute("adminId") == null) {
				  return "admin/login";
			  }
			   
		  int idValue = Integer.parseInt(id);
		  if (idValue > 0) {
			  prodService.deleteProduct(idValue);
		  }
		  return "redirect:adminproducts";
	    }	
	  
	  @RequestMapping(value = "/adminmembers", method = RequestMethod.GET)
	    public String members(ModelMap map,HttpServletRequest request) 
	    {
		 
		  HttpSession session = request.getSession();
			 if (session.getAttribute("adminId") == null) {
				  return "admin/login";
			  }
		  List<User> list = userService.getAllUsers();
		  
		  map.addAttribute("list", list);
		  map.addAttribute("pageTitle", "ADMIN BROWSE MEMBERS");
	        return "admin/members"; 
	    }
	  
	  @RequestMapping(value = "/searchUser", method = RequestMethod.GET)
	    public String searchMembers(ModelMap map,HttpServletRequest request) 
	    {
		 
		  HttpSession session = request.getSession();
			 if (session.getAttribute("adminId") == null) {
				  return "admin/login";
			  }
			 String fname = request.getParameter("fname");
			 String lname= request.getParameter("lname");
		  List<User> list = userService.searchUserByName(fname, lname);
		  
		  map.addAttribute("list", list);
		  map.addAttribute("pageTitle", "ADMIN BROWSE MEMBERS");
	        return "admin/members"; 
	    }
	  
	  @RequestMapping(value = "/searchPO", method = RequestMethod.GET)
	    public String searchPO(ModelMap map,HttpServletRequest request) 
	    {
		 
		  HttpSession session = request.getSession();
			 if (session.getAttribute("adminId") == null) {
				  return "admin/login";
			  }
			 String poDate = request.getParameter("poDate");
			 String catId= request.getParameter("category");
			 System.out.println("date "+poDate);
			 Date purchaseDate = null;
			 java.sql.Date dateSql = null;
			  List<Category> catList = categoryService.getAllCategorys();
			 if(poDate != null && catId != null && !catId.equals("")) {
				  try {
					purchaseDate =new SimpleDateFormat("dd-MM-yyy").parse(poDate);
					 dateSql = new java.sql.Date(purchaseDate.getTime());
					 List<Object> list = poService.searchPOBasedOnDateAndCategory(dateSql, Integer.parseInt(catId));
					 list.stream().forEach(x -> System.out.println(x)); 
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					purchaseDate =  null;
					e.printStackTrace();
					
				}  
				 
			 }
			
		  map.addAttribute("list", null);
		  map.addAttribute("catList",catList);
		  map.addAttribute("pageTitle", "ADMIN BROWSE MEMBERS");
	        return "admin/members"; 
	    }
	  
	  @RequestMapping(value = "/adminpurchases", method = RequestMethod.GET)
	    public String purchases(ModelMap map,HttpServletRequest request) 
	    {
		  
		  HttpSession session = request.getSession();
		  BigDecimal total = BigDecimal.ZERO;
			 if (session.getAttribute("adminId") == null) {
				  return "admin/login";
			  }
		  List<PurchaseOrder> poList = poService.getAllPurchaseOrders();
		  total = poList.stream().map(PurchaseOrder::getGrossTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
		  List<Category> catList = categoryService.getAllCategorys();
		  map.addAttribute("list", poList);
		  map.addAttribute("totalAmount",total);
		  map.addAttribute("catList",catList);
		  map.addAttribute("pageTitle", "ADMIN PURCHASES REPORT");
	        return "admin/purchases"; 
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
