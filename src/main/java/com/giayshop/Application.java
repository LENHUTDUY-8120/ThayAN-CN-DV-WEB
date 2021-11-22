package com.giayshop;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.giayshop.config.FileStorageProperties;
import com.giayshop.entity.Brand;
import com.giayshop.entity.ChatLieu;
import com.giayshop.entity.Color;
import com.giayshop.repository.BrandRepo;
import com.giayshop.repository.ChatLieuRepo;
import com.giayshop.repository.ColorRepo;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class Application {

	@Autowired
	private ChatLieuRepo chatLieuRepo;
	@Autowired
	private ColorRepo colorRepo;
	@Autowired
	private BrandRepo brandRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void init() {
		List<ChatLieu> listCL = chatLieuRepo.findAll();
		List<Color> listColor = colorRepo.findAll();
		List<Brand> listBrand = brandRepo.findAll();
		
		if (listColor.isEmpty()) {
			Color color1 = new Color();
			color1.setName("Màu Vàng");
			color1.setCode("yellow");
			Color color2 = new Color();
			color2.setName("Màu Đỏ");
			color2.setCode("red");
			Color color3 = new Color();
			color3.setName("Màu Trắng");
			color3.setCode("white");
			Color color4 = new Color();
			color4.setName("Màu Đen");
			color4.setCode("black");
			Color color5 = new Color();
			color5.setName("Màu Hồng");
			color5.setCode("pink");
			Color color6 = new Color();
			color6.setName("Màu Navy");
			color6.setCode("navy");
			colorRepo.save(color1);
			colorRepo.save(color2);
			colorRepo.save(color3);
			colorRepo.save(color4);
			colorRepo.save(color5);
			colorRepo.save(color6);
		}
		
		if (listCL.isEmpty()) {
			ChatLieu chatLieu1 = new ChatLieu();
			chatLieu1.setName("Da thật");
			chatLieu1.setCode("da");
			ChatLieu chatLieu2 = new ChatLieu();
			chatLieu2.setName("vải");
			chatLieu2.setCode("vai");
			ChatLieu chatLieu3 = new ChatLieu();
			chatLieu3.setName("PU");
			chatLieu3.setCode("pu");
			ChatLieu chatLieu4 = new ChatLieu();
			chatLieu4.setName("Da bê");
			chatLieu4.setCode("dabe");
			ChatLieu chatLieu5 = new ChatLieu();
			chatLieu5.setName("Da lộn");
			chatLieu5.setCode("dalon");
			ChatLieu chatLieu6 = new ChatLieu();
			chatLieu6.setName("Da tổng hợp");
			chatLieu6.setCode("datt");
			chatLieuRepo.save(chatLieu1);
			chatLieuRepo.save(chatLieu2);
			chatLieuRepo.save(chatLieu3);
			chatLieuRepo.save(chatLieu4);
			chatLieuRepo.save(chatLieu5);
			chatLieuRepo.save(chatLieu6);
		}
		
		if (listBrand.isEmpty()) {
			Brand brand1 = new Brand();
			brand1.setName("Nike");
			brand1.setCode("nike");
			brand1.setImagePath("logo.png");
			Brand brand2 = new Brand();
			brand2.setName("Puma");
			brand2.setCode("pm");
			brand2.setImagePath("logo.png");
			Brand brand3 = new Brand();
			brand3.setName("Adidas");
			brand3.setCode("adi");
			brand3.setImagePath("logo.png");
			Brand brand4 = new Brand();
			brand4.setName("Superem");
			brand4.setCode("sup");
			brand4.setImagePath("logo.png");
			Brand brand5 = new Brand();
			brand5.setName("Thượng Đình");
			brand5.setCode("td");
			brand5.setImagePath("logo.png");
			Brand brand6 = new Brand();
			brand6.setName("Other");
			brand6.setCode("oth");
			brand6.setImagePath("logo.png");
			brandRepo.save(brand1);
			brandRepo.save(brand2);
			brandRepo.save(brand3);
			brandRepo.save(brand4);
			brandRepo.save(brand5);
			brandRepo.save(brand6);
		}
	}
}
