package com.giayshop.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.giayshop.converter.ProductConverter;
import com.giayshop.entity.Products;
import com.giayshop.payload.response.ProductsAdminResponse;
import com.giayshop.payload.response.ProductsResponse;
import com.giayshop.service.ProductsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/products")
public class ProductsApi {

	@Autowired
	private ProductsService productsService;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ProductsResponse addProduct(@RequestPart("product") String product,
			@RequestPart("images") MultipartFile[] images) {
		return productsService.saveProduct(images, product);
	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> getProducts(@RequestParam(required = false) String title,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int size) {
		try {
			Page<Products> pageP = productsService.findProducts(title, page, size);
			List<ProductsAdminResponse> productAd = new ArrayList<>();
			productAd = ProductConverter.toListAdminResponse(pageP.getContent());
			Map<String, Object> response = new HashMap<>();
			response.put("products", productAd);
			response.put("currentPage", pageP.getNumber());
			response.put("totalItems", pageP.getTotalElements());
			response.put("totalPages", pageP.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
