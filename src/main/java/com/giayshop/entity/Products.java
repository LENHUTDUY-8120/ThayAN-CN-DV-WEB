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
@Table(name = "products")
public class Products extends BaseEntity{

	@Column
	private String title;
	@Column
	private String productCode;
	@Column
	private String gioiTinh;
	@Column
	private int price;
	@Column
	private int quantity;
	@Column
	private int views = 0;
	@Column
	private String describes;
	@Column
	private String status = "active";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brand_id", foreignKey = @ForeignKey(name = "Brand_ID_FK"))
	private Brand brand;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "color_id", foreignKey = @ForeignKey(name = "Color_ID_FK"))
	private Color color;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chatlieu_id", foreignKey = @ForeignKey(name = "Chatlieu_ID_FK"))
	private ChatLieu chatLieu;
	
	@OneToMany(mappedBy = "product")
	private List<Images> listImage = new ArrayList<>();
	
	@OneToMany(mappedBy = "product")
	private List<Comments> comments = new ArrayList<>();
	
	@OneToMany(mappedBy = "product")
	private List<OrderItem> orderItems = new ArrayList<>();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public ChatLieu getChatLieu() {
		return chatLieu;
	}
	public void setChatLieu(ChatLieu chatLieu) {
		this.chatLieu = chatLieu;
	}
	public List<Comments> getComments() {
		return comments;
	}
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	public List<Images> getListImage() {
		return listImage;
	}
	public void setListImage(List<Images> listImage) {
		this.listImage = listImage;
	}
}
