package com.xyz.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users", schema = "parcel_xyz")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "userid")
	private Integer userId;

	@Column(name = "username")
	private String username;

	@Column(name = "name")
	private String name;

	@Column(name = "email_id")
	private String emailId;

	private String address;

	private String password;

	@Column(name = "role")
	private String role;

	@Column(name = "availablity")
	private String availablity;

}
