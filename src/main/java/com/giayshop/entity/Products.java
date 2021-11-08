package com.giayshop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	private float price;
	@Column
	private int views = 0;
	@Column
	private String describes;
	
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
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "product_store",
				joinColumns = @JoinColumn(name = "product_id"),
				inverseJoinColumns = @JoinColumn(name = "store_id"))
	private List<Stores> stores = new ArrayList<>();
	
	@OneToOne(mappedBy = "product")
	private OrderItem orderItem;
	
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public List<Stores> getStores() {
		return stores;
	}
	public void setStores(List<Stores> stores) {
		this.stores = stores;
	}
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
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
	public OrderItem getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
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
