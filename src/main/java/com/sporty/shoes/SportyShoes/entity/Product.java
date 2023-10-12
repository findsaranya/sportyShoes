package com.sporty.shoes.SportyShoes.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Entity
@Table(name="product_tab")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int poductId;
	@Column(name="product_name")
	private String name;
	@Temporal(TemporalType.DATE)
	@Column(name="addedOn")
	private Date createdAt= new Date();
	@Column(name="product_price")
	private BigDecimal price;
	@ManyToOne
	@JoinColumn(name="product_category")
	private Category category;

}
