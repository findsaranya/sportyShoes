package com.sporty.shoes.SportyShoes.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name="purchase_items_tab")
public class PurchaseItem {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int itemId;
@OneToOne
@JoinColumn(name = "productId")
private Product product;
@Column(name="qty")
private int qty;
@OneToOne
@JoinColumn(name="purchase_order_Id")
private PurchaseOrder purchaseOrder;
@Column(name="price")
private BigDecimal totalPrice;

}
