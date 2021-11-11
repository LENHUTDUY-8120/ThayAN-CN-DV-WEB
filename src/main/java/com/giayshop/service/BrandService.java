package com.giayshop.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giayshop.config.FileStorageException;
import com.giayshop.config.FileStorageProperties;
import com.giayshop.config.MapperUtil;
import com.giayshop.converter.BrandConverter;
import com.giayshop.entity.Brand;
import com.giayshop.payload.request.BrandRequest;
import com.giayshop.payload.response.BrandResponse;
import com.giayshop.repository.BrandRepo;

@Service
public class BrandService {

	private final Path fileStorageLocation;
	
	@Autowired
	private BrandRepo brandRepo;
	
	@Autowired
    public BrandService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getBrandImages())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
	
	public BrandRequest jsonToBrand(String Brand) {
		BrandRequest brandRequest = new BrandRequest();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			brandRequest = objectMapper.readValue(Brand, BrandRequest.class);
		} catch (Exception e) {
			System.out.println(e);
		}
		return brandRequest;
	}
	
	public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
	
	public List<BrandResponse> getAllBrand() {
		List<Brand> listBrand = brandRepo.findAll();
		return BrandConverter.toListRs(listBrand);
	}
	
	public BrandResponse saveBrand(String brand, MultipartFile image) {
		BrandRequest brandRequest = jsonToBrand(brand);
		Brand brandEntity = new Brand();
		brandEntity.setName(brandRequest.getName());
		brandEntity.setCode(brandRequest.getCode());
		brandEntity.setImagePath(storeFile(image));
		return MapperUtil.map(brandRepo.save(brandEntity), BrandResponse.class);
	}
	
}
