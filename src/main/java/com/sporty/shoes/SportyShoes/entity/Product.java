package com.sporty.shoes.SportyShoes.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="product_tab")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int poductId;
	@Column(name="product_name")
	private String name;
	@Column(name="addedOn")
	private Date createdAt;
	@Column(name="product_price")
	private BigDecimal price;
	@OneToOne
	@JoinColumn(name="product_category")
	private Category category;

}
