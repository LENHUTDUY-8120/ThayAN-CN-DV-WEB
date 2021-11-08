package com.giayshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giayshop.config.MapperUtil;
import com.giayshop.entity.TinhThanhPho;
import com.giayshop.payload.response.QuanHuyenResponse;
import com.giayshop.payload.response.TinhThanhPhoResponse;
import com.giayshop.payload.response.XaPhuongThiTranResponse;
import com.giayshop.repository.QuanHuyenRepo;
import com.giayshop.repository.TinhThanhPhoRepo;
import com.giayshop.repository.XaPhuongThiTranRepo;

@Service
public class AddressService {

	@Autowired
	private TinhThanhPhoRepo tinhThanhPhoRepo;
	@Autowired
	private QuanHuyenRepo quanHuyenRepo;
	@Autowired
	private XaPhuongThiTranRepo xaPhuongThiTranRepo;
	
	public List<TinhThanhPhoResponse> getAllTinhThanhPho() {
		List<TinhThanhPho> tinhThanhPho = tinhThanhPhoRepo.findAll();
		return MapperUtil.mapAll(tinhThanhPho, TinhThanhPhoResponse.class);
	}
	
	public List<QuanHuyenResponse> getQH(String matp){
		return MapperUtil.mapAll(quanHuyenRepo.findByMatp(matp), QuanHuyenResponse.class);
	}
	
	public List<XaPhuongThiTranResponse> getXPTT(String maqh){
		return MapperUtil.mapAll(xaPhuongThiTranRepo.findByMaqh(maqh), XaPhuongThiTranResponse.class);
	}
}
