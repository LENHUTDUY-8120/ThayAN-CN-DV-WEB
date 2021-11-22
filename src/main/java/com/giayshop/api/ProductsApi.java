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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.giayshop.converter.ProductConverter;
import com.giayshop.entity.Products;
import com.giayshop.payload.request.ProductsRequest;
import com.giayshop.payload.response.ProductsAdminResponse;
import com.giayshop.payload.response.ProductsCusResponse;
import com.giayshop.payload.response.ProductsResponse;
import com.giayshop.service.ProductsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ProductsApi {

	@Autowired
	private ProductsService productsService;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE },
			value = "/admin/products")
	public ProductsResponse addProduct(@RequestPart("product") String product,
			@RequestPart(value = "images") MultipartFile[] images) {
		return productsService.saveProduct(images, product);
	}

	@GetMapping(value = "/admin/products")
	public ResponseEntity<Map<String, Object>> getAllProducts(@RequestParam(required = false) String title,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "6") int size,
			@RequestParam(defaultValue = "active") String status) {
		try {
			Page<Products> pageP = productsService.findProducts(title, page, size,status);
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
	
	@PutMapping("/admin/products/{id}")
	public ProductsResponse update(@RequestBody ProductsRequest productsRequest, @PathVariable Long id) {
		return productsService.update(productsRequest, id);
	}
	
	@GetMapping("/admin/products/{id}")
	public ProductsResponse getbyidadmin(@PathVariable(value = "id") Long id) {
		return productsService.getProductByIdAdmin(id);
	}
	
	@DeleteMapping("/admin/products/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		productsService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/products/{id}")
	public ProductsCusResponse getbyidcus(@PathVariable(value = "id") Long id) {
		return productsService.getProductById(id);
	}
	
	@GetMapping(value = "/products")
	public ResponseEntity<Map<String, Object>> getAllProductsCus(@RequestParam(required = false) String title,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		try {
			Page<Products> pageP = productsService.getAllProducts(title, page, size);
			List<ProductsCusResponse> productcus = new ArrayList<>();
			productcus = ProductConverter.toListCusResponse(pageP.getContent());
			Map<String, Object> response = new HashMap<>();
			response.put("products", productcus);
			response.put("currentPage", pageP.getNumber());
			response.put("totalItems", pageP.getTotalElements());
			response.put("totalPages", pageP.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
