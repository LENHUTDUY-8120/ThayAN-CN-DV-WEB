package com.giayshop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Comments")
public class Comments extends BaseEntity{

	@Column
	private String fullname;
	@Column
	private String email;
	@Column
	private String phoneNumber;
	@Column
	private String content;
	@Column
	private int rate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "Product_ID_FK"))
	private Products product;
	
	@OneToMany(mappedBy = "comments",orphanRemoval = true)
	private List<Subcomment> subcomments = new ArrayList<>();

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Subcomment> getSubcomments() {
		return subcomments;
	}

	public void setSubcomments(List<Subcomment> subcomments) {
		this.subcomments = subcomments;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}
	
}
