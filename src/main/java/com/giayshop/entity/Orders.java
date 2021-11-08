package com.giayshop.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders extends BaseEntity{

	@Column
	private String fulname;
	@Column
	private String phoneNumber;
	@Column
	private String email;
	@Column
	private String address;
	@Column
	private String note;
	@Column
	private Date date;
	@Column
	private int total;
	@Column
	@Enumerated(EnumType.STRING)
	private OrderState orderSate;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> listOrderDetails = new ArrayList<>();

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public OrderState getOrderSate() {
		return orderSate;
	}

	public void setOrderSate(OrderState orderSate) {
		this.orderSate = orderSate;
	}

	public List<OrderItem> getListOrderDetails() {
		return listOrderDetails;
	}

	public void setListOrderDetails(List<OrderItem> listOrderDetails) {
		this.listOrderDetails = listOrderDetails;
	}

	public String getFulname() {
		return fulname;
	}

	public void setFulname(String fulname) {
		this.fulname = fulname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
