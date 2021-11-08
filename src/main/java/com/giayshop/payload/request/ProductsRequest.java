package com.giayshop.payload.request;

public class ProductsRequest {
	private String title;
	private String productCode;
	private float price;
	private String describes;
	private String brandCode;
	private String gioiTinh;
	private String colorCode;
	private String chatLieuCode;
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
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public String getChatLieuCode() {
		return chatLieuCode;
	}
	public void setChatLieuCode(String chatLieuCode) {
		this.chatLieuCode = chatLieuCode;
	}
}
