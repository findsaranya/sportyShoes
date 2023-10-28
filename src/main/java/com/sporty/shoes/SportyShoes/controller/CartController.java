package com.sporty.shoes.SportyShoes.controller;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sporty.shoes.SportyShoes.entity.Product;
import com.sporty.shoes.SportyShoes.entity.PurchaseItem;
import com.sporty.shoes.SportyShoes.entity.PurchaseOrder;
import com.sporty.shoes.SportyShoes.entity.User;
import com.sporty.shoes.SportyShoes.model.CartItems;
import com.sporty.shoes.SportyShoes.services.IPOService;
import com.sporty.shoes.SportyShoes.services.IProductImpl;
import com.sporty.shoes.SportyShoes.services.IPurchaseItemService;
import com.sporty.shoes.SportyShoes.services.IUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	@Autowired
	IProductImpl prodService;

	@Autowired
	IUserService userService;

	@Autowired
	IPOService poService;

	@Autowired
	IPurchaseItemService itemService;

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cartPage(ModelMap map, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String error = null;
		Set<CartItems> listItems = new HashSet<CartItems>();
		BigDecimal total = BigDecimal.ZERO;
		if (session.getAttribute("userId") == null) {
			error = "<h4 style='color:red;'>Error, You need to login before adding items to cart</h4>";
		} else {

			// check item list in session
			if (session.getAttribute("cartItems") != null) {
				listItems = (Set<CartItems>) session.getAttribute("cartItems");

			}
			total = listItems.stream().map(CartItems::getGrossTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
		}

		map.addAttribute("cartValue", total);
		map.addAttribute("cartItems", listItems);
		map.addAttribute("error", error);
		map.addAttribute("pageTitle", "SPORTY SHOES - YOUR CART");
		return "cart";

	}

	@RequestMapping(value = "/addTocart", method = RequestMethod.GET)
	public String addToCart(ModelMap map, HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id) {

		HttpSession session = request.getSession();
		if (session.getAttribute("userId") == null) {
			return "redirect:cart";
		}

		// product id
		int idValue = Integer.parseInt(id);
		// list
		Set<CartItems> listItems = new HashSet<CartItems>();
		// check item list in session
		if (session.getAttribute("cartItems") != null) {
			listItems = (Set<CartItems>) session.getAttribute("cartItems");
		}
		// get the product
		String error = null;
		Optional<Product> product = prodService.getProductInfo(idValue);
		if (product.isPresent()) {
			Long findItem = listItems.stream().filter(x -> x.getProduct().getPoductId() == product.get().getPoductId())
					.count();
			if (findItem > 0) {
				error = "<h4 style='color:red;'>Item already in the cart!!</h4>";
			} else {
				CartItems cartItems = new CartItems();
				cartItems.setProduct(product.get());
				cartItems.setQty(1);
				BigDecimal totalPrice = product.get().getPrice().multiply(new BigDecimal(cartItems.getQty()));
				cartItems.setGrossTotal(totalPrice);
				listItems.add(cartItems);
			}
		} else {
			error = "<h4 style='color:red;'>Product not available</h4>";
		}

		BigDecimal total = listItems.stream().map(CartItems::getGrossTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
		session.setAttribute("cartItems", listItems);
		map.addAttribute("cartItems", listItems);
		map.addAttribute("cartValue", total);
		map.addAttribute("error", error);
		map.addAttribute("pageTitle", "SPORTY SHOES - YOUR CART");

		return "cart";
	}

	@RequestMapping(value = "/addQty", method = RequestMethod.GET)
	public String incrementCartItem(ModelMap map, HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id) {
		HttpSession session = request.getSession();
		Set<CartItems> listItems = new HashSet<CartItems>();
		if (session.getAttribute("userId") == null) {
			return "redirect:cart";
		} else {
			int idValue = Integer.parseInt(id);
			if (session.getAttribute("cartItems") != null) {
				listItems = (Set<CartItems>) session.getAttribute("cartItems");
			}
			Set<CartItems> modifiedItems = listItems.stream().map(x -> {
				if (x.getProduct().getPoductId() == idValue) {
					CartItems cart = x;
					int qty = cart.getQty() + 1;
					BigDecimal total = cart.getProduct().getPrice().multiply(BigDecimal.valueOf(qty));
					cart.setQty(qty);
					cart.setGrossTotal(total);
					return cart;
				}
				return x;
			}).collect(Collectors.toSet());
			session.setAttribute("cartItems", modifiedItems);
		}
		return "redirect:cart";
	}

	@RequestMapping(value = "/deductQty", method = RequestMethod.GET)
	public String deductCartItem(ModelMap map, HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id) {
		HttpSession session = request.getSession();
		Set<CartItems> listItems = new HashSet<CartItems>();
		if (session.getAttribute("userId") == null) {
			return "redirect:cart";
		} else {
			int idValue = Integer.parseInt(id);
			if (session.getAttribute("cartItems") != null) {
				listItems = (Set<CartItems>) session.getAttribute("cartItems");
			}
			Optional<CartItems> itemFound = listItems.stream()
					.filter(item -> item.getProduct().getPoductId() == idValue).findFirst();
			if (itemFound.isPresent() && itemFound.get().getQty() > 1) {
				Set<CartItems> modifiedItems = listItems.stream().map(x -> {
					if (x.getProduct().getPoductId() == idValue) {
						CartItems cart = x;
						int qty = cart.getQty() - 1;
						BigDecimal total = cart.getProduct().getPrice().multiply(BigDecimal.valueOf(qty));
						cart.setQty(qty);
						cart.setGrossTotal(total);
						return cart;
					}
					return x;
				}).collect(Collectors.toSet());
				session.setAttribute("cartItems", modifiedItems);
			} else {
				return "redirect:cartdeleteitem?id=" + id;
//					Set<CartItems> filteredItems = listItems.stream().filter(x -> x.getProduct().getPoductId() != idValue).collect(Collectors.toSet());
//				  	session.setAttribute("cartItems", filteredItems);
			}

		}
		return "redirect:cart";
	}

	@RequestMapping(value = "/cartdeleteitem", method = RequestMethod.GET)
	public String cartDeleteItem(ModelMap map, HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id) {
		HttpSession session = request.getSession();
		Set<CartItems> listItems = new HashSet<CartItems>();
		if (session.getAttribute("userId") == null) {
			return "redirect:cart";
		} else {
			int idValue = Integer.parseInt(id);
			if (session.getAttribute("cartItems") != null) {
				listItems = (Set<CartItems>) session.getAttribute("cartItems");
			}
			Set<CartItems> filteredItems = listItems.stream().filter(x -> x.getProduct().getPoductId() != idValue)
					.collect(Collectors.toSet());
			session.setAttribute("cartItems", filteredItems);
		}
		return "redirect:cart";
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String checkout(ModelMap map, HttpServletRequest request) {
		String error = null;
		Set<CartItems> listItems = new HashSet<CartItems>();
		BigDecimal total = BigDecimal.ZERO;

		HttpSession session = request.getSession();
		if (session.getAttribute("userId") == null) {
			error = "<h4>You need to login before checking out\" </h4>";
		} else {

			if (session.getAttribute("cartItems") != null) {
				listItems = (Set<CartItems>) session.getAttribute("cartItems");
				total = listItems.stream().map(CartItems::getGrossTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

			}
		}
		map.addAttribute("cartValue", total);
		map.addAttribute("pageTitle", "SPORTY SHOES - CHECKOUT");
		return "checkout";
	}

	@RequestMapping(value = "/gateway", method = RequestMethod.GET)
	public String gateway(ModelMap map, HttpServletRequest request) {
		String error = null;
		Set<CartItems> listItems = new HashSet<CartItems>();
		BigDecimal total = BigDecimal.ZERO;

		HttpSession session = request.getSession();
		if (session.getAttribute("userId") == null) {
			error = "<h4>You need to login before checking out\" </h4>";
		} else {

			if (session.getAttribute("cartItems") != null) {
				listItems = (Set<CartItems>) session.getAttribute("cartItems");
				total = listItems.stream().map(CartItems::getGrossTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

			}
		}
		map.addAttribute("cartValue", total);
		map.addAttribute("cartItems", listItems);
		map.addAttribute("pageTitle", "SPORTY SHOES - CHECKOUT");
		return "gateway";
	}

	@RequestMapping(value = "/completepurchase", method = RequestMethod.GET)
	public String completePurchase(ModelMap map, HttpServletRequest request) {
		String error = null;
		Set<CartItems> listItems = new HashSet<CartItems>();
		BigDecimal total = BigDecimal.ZERO;

		HttpSession session = request.getSession();
		if (session.getAttribute("userId") == null) {
			error = "<h4>You need to login before checking out\" </h4>";
		} else {
			if (session.getAttribute("cartItems") != null) {
				listItems = (Set<CartItems>) session.getAttribute("cartItems");
				total = listItems.stream().map(CartItems::getGrossTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

			}

			int userId = (int) session.getAttribute("userId");
			Optional<User> user = userService.getUserInfo(userId);

			if (!listItems.isEmpty()) {
				//inistantiate po object
				PurchaseOrder po = new PurchaseOrder();
				//set items
				Set<PurchaseItem> purchaseItems = listItems.stream().map(x -> {
					PurchaseItem item = new PurchaseItem();
					item.setProduct(x.getProduct());
					item.setQty(x.getQty());
					item.setPo(po);
					item.setTotalPrice(x.getGrossTotal());
					return item;
				}).collect(Collectors.toSet());
				
				// insert into purchase order
				po.setUser(user.get());
				po.setGrossTotal(total);
				po.setItems(purchaseItems);
				PurchaseOrder poId = poService.createNewPurchaseOrder(po);
	
				// insert to purchaseItems
			    itemService.saveAll(purchaseItems);			
			}

		}

		return "redirect:confirm";
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String confirm(ModelMap map, HttpServletRequest request) {
		String error = null;
		Set<CartItems> listItems = new HashSet<CartItems>();
		BigDecimal total = BigDecimal.ZERO;

		HttpSession session = request.getSession();
		if (session.getAttribute("userId") == null) {
			error = "<h4>You need to login before checking out\" </h4>";
		} else {

			if (session.getAttribute("cartItems") != null) {
				listItems = (Set<CartItems>) session.getAttribute("cartItems");
				total = listItems.stream().map(CartItems::getGrossTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
				listItems.clear();
				session.setAttribute("cartItems", null);
			}
		}
		map.addAttribute("cartValue", total);
		map.addAttribute("pageTitle", "SPORTY SHOES - PURCHASE CONFIRMATION");
		return "confirm";
	}

}
