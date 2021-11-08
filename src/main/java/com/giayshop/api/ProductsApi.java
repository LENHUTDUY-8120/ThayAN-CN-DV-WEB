package com.giayshop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.giayshop.payload.response.ProductsResponse;
import com.giayshop.service.ProductsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/products")
public class ProductsApi {

	@Autowired
	private ProductsService productsService;
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
						MediaType.MULTIPART_FORM_DATA_VALUE})
	public ProductsResponse addProduct(@RequestPart("product") String product,
									   @RequestPart("images") MultipartFile[] images) {
		return productsService.saveProduct(images, product);
	}
}
