package com.giayshop.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giayshop.config.FileStorageException;
import com.giayshop.config.FileStorageProperties;
import com.giayshop.config.MapperUtil;
import com.giayshop.converter.ProductConverter;
import com.giayshop.entity.Images;
import com.giayshop.entity.Products;
import com.giayshop.payload.request.ProductsRequest;
import com.giayshop.payload.response.ProductsCusResponse;
import com.giayshop.payload.response.ProductsResponse;
import com.giayshop.repository.BrandRepo;
import com.giayshop.repository.ChatLieuRepo;
import com.giayshop.repository.ColorRepo;
import com.giayshop.repository.ImagesRepo;
import com.giayshop.repository.ProductsRepo;

@Service
public class ProductsService {

	private final Path fileStorageLocation;

	@Autowired
	private ProductsRepo productsRepo;
	@Autowired
	private ColorRepo colorRepo;
	@Autowired
	private BrandRepo brandRepo;
	@Autowired
	private ChatLieuRepo chatLieuRepo;
	@Autowired
	private ImagesRepo imagesRepo;

	@Autowired
	public ProductsService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getProductImages()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public ProductsRequest jsonToProduct(String P) {
		ProductsRequest productsRequest = new ProductsRequest();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			productsRequest = objectMapper.readValue(P, ProductsRequest.class);
		} catch (Exception e) {
			System.out.println(e);
		}
		return productsRequest;
	}

	public String storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public ProductsResponse saveProduct(MultipartFile[] files, String P) {
		ProductsRequest productsRequest = jsonToProduct(P);
		Products products = new Products();
		products.setTitle(productsRequest.getTitle());
		products.setProductCode(productsRequest.getProductCode());
		products.setPrice(productsRequest.getPrice());
		products.setQuantity(productsRequest.getQuantity());
		products.setDescribes(productsRequest.getDescribes());
		products.setGioiTinh(productsRequest.getGioiTinh());
		products.setBrand(brandRepo.findOneByCode(productsRequest.getBrandCode()));
		products.setChatLieu(chatLieuRepo.findOneByCode(productsRequest.getChatLieuCode()));
		products.setColor(colorRepo.findOneByCode(productsRequest.getColorCode()));
		Products product = productsRepo.save(products);
		List<Images> listImage = new ArrayList<>();
		for (MultipartFile file : files) {
			String fileName = storeFile(file);
			Images image = new Images();
			image.setFileName(fileName);
			image.setProduct(product);
			listImage.add(imagesRepo.save(image));
		}
		product.setListImage(listImage);
		ProductsResponse productsResponse = MapperUtil.map(product, ProductsResponse.class);
		productsResponse.setBrandCode(product.getBrand().getCode());
		productsResponse.setColorCode(product.getColor().getCode());
		productsResponse.setChatLieuCode(product.getChatLieu().getCode());
		productsResponse.setViews(product.getViews());
		productsResponse.setImages(
				product.getListImage().stream().map(image -> image.getFileName()).collect(Collectors.toList()));
		return productsResponse;
	}

	public Page<Products> findProducts(String title, int page, int size, String stt) {
		Pageable paging = PageRequest.of(page, size);
		Page<Products> pageP;
		if (title == null) {
			pageP = productsRepo.findByStatus(stt, paging);
		} else {
			pageP = productsRepo.findByTitleContainingAndStatus(title, paging, stt);
		}
		return pageP;
	}

	public ProductsResponse update(ProductsRequest productsRequest, Long id) {
		Products products = productsRepo.findById(id).orElseThrow();
		products.setTitle(productsRequest.getTitle());
		products.setProductCode(productsRequest.getProductCode());
		products.setPrice(productsRequest.getPrice());
		products.setQuantity(productsRequest.getQuantity());
		products.setDescribes(productsRequest.getDescribes());
		products.setGioiTinh(productsRequest.getGioiTinh());
		products.setBrand(brandRepo.findOneByCode(productsRequest.getBrandCode()));
		products.setChatLieu(chatLieuRepo.findOneByCode(productsRequest.getChatLieuCode()));
		products.setColor(colorRepo.findOneByCode(productsRequest.getColorCode()));
		Products product = productsRepo.save(products);
		ProductsResponse productsResponse = MapperUtil.map(product, ProductsResponse.class);
		productsResponse.setBrandCode(product.getBrand().getCode());
		productsResponse.setColorCode(product.getColor().getCode());
		productsResponse.setChatLieuCode(product.getChatLieu().getCode());
		productsResponse.setImages(
				product.getListImage().stream().map(image -> image.getFileName()).collect(Collectors.toList()));
		return productsResponse;
	}

	public ProductsResponse getProductByIdAdmin(Long id) {
		Products product = productsRepo.findByIdAndStatus(id, "active");
		ProductsResponse productsResponse = MapperUtil.map(product, ProductsResponse.class);
		productsResponse.setBrandCode(product.getBrand().getCode());
		productsResponse.setColorCode(product.getColor().getCode());
		productsResponse.setChatLieuCode(product.getChatLieu().getCode());
		productsResponse.setImages(
				product.getListImage().stream().map(image -> image.getFileName()).collect(Collectors.toList()));
		productsResponse.setViews(product.getViews());
		return productsResponse;
	}

	public void delete(Long id) {
		Products products = productsRepo.findById(id).orElseThrow();
		products.setStatus("deactive");
		productsRepo.save(products);
	}

	public Page<Products> getAllProducts(String title, int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Products> pageP;
		if (title == null) {
			pageP = productsRepo.findByStatus("active", paging);
		} else {
			pageP = productsRepo.findByTitleContainingAndStatus(title, paging, "active");
		}
		return pageP;
	}

	public ProductsCusResponse getProductById(Long id) {
		Products product = productsRepo.findByIdAndStatus(id, "active");
		product.setViews(product.getViews() + 1);
		ProductsCusResponse productsCusResponse = ProductConverter.toCusResponse(productsRepo.save(product));
		return productsCusResponse;
	}
}
