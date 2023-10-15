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
@Table(name="user_tab")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="userId")
private int userId;
@Column(name="firstName")
private String fname;
@Column(name="lastName")
private String lname;
@Column(name="emailId")
private String emailId;
@Column(name="password")
private String password;
@Column(name="age")
private int age;
@Column(name="address")
private String address;
@Temporal(TemporalType.DATE)
@Column(name="createOn")
private Date createdOn = new Date();

}
