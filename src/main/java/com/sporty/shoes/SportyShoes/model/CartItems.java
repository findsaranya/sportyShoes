package com.sporty.shoes.SportyShoes.model;

import java.math.BigDecimal;

import com.sporty.shoes.SportyShoes.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartItems {
	private Product product;
	private int qty;
	private BigDecimal grossTotal;
}
