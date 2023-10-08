package com.sporty.shoes.SportyShoes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="category_tab")
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int Id;
@Column(name="cat_name")
private String name;
}
