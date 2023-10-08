package com.sporty.shoes.SportyShoes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/sportyShoes")
public class HomeController {

	@RequestMapping(value = "/")
	public String home() {
		return "index";
	}
}
