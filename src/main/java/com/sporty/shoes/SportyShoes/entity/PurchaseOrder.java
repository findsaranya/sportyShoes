package com.sporty.shoes.SportyShoes.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name="purchase_order_tab")
public class PurchaseOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int poId;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	@Column(name="grossTotal")
	private BigDecimal grossTotal;
	@OneToMany(mappedBy = "po")
	private Set<PurchaseItem> items;
	@Temporal(TemporalType.DATE)
	private Date purchaseDate = new Date();
	
}
