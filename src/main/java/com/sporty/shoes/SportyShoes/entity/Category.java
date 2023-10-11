package com.sporty.shoes.SportyShoes.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="category_tab")
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int catId;
@Column(name="cat_name")
private String name;
@Temporal(TemporalType.DATE)
@Column(name="createdOn")
private Date createdOn = new Date();
}
