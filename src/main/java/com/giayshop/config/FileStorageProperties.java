package com.giayshop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
	private String productImages;
	private String brandImages;
	
	public String getProductImages() {
		return productImages;
	}
	public void setProductImages(String productImages) {
		this.productImages = productImages;
	}
	public String getBrandImages() {
		return brandImages;
	}
	public void setBrandImages(String brandImages) {
		this.brandImages = brandImages;
	}
}
