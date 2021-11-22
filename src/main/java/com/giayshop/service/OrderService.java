package com.giayshop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.giayshop.config.MapperUtil;
import com.giayshop.converter.OrdersConverter;
import com.giayshop.entity.OrderItem;
import com.giayshop.entity.OrderState;
import com.giayshop.entity.Orders;
import com.giayshop.payload.request.ItemRequest;
import com.giayshop.payload.request.OrderRequest;
import com.giayshop.payload.response.OrderDetailResponse;
import com.giayshop.payload.response.OrderItemResponse;
import com.giayshop.payload.response.OrderResponse;
import com.giayshop.repository.OrderItemRepo;
import com.giayshop.repository.OrdersRepo;
import com.giayshop.repository.ProductsRepo;

@Service
public class OrderService {

	@Autowired
	private OrdersRepo ordersRepo;
	@Autowired
	private OrderItemRepo orderItemRepo;
	@Autowired
	private ProductsRepo productsRepo;
	
	@Transactional
	public OrderResponse createOrder(OrderRequest orderRequest) {
		
		List<ItemRequest> items = orderRequest.getItems();
		Orders order = new Orders();
		order.setFullname(orderRequest.getFullname());
		order.setPhoneNumber(orderRequest.getPhoneNumber());
		order.setEmail(orderRequest.getEmail());
		order.setAddress(orderRequest.getAddress());
		order.setNote(orderRequest.getNote());
		order.setTotal(orderRequest.getTotal());
		order.setOrderState(OrderState.Delivery);
		Orders order1 = ordersRepo.save(order);
		for (ItemRequest itemRequest : items) {
			OrderItem orderItem = new OrderItem();
			orderItem.setPrice(itemRequest.getPrice());
			orderItem.setQuantiy(itemRequest.getQuantiy());
			orderItem.setProduct(productsRepo.findById(itemRequest.getProductId()).orElseThrow());
			orderItem.setOrder(order1);
			orderItemRepo.save(orderItem);
		}

		return new OrderResponse(order1.getId());
	}
	
	public List<OrderDetailResponse> getListOrderAd(){
		List<Orders> orders = ordersRepo.findAll(Sort.by("createdDate"));
		return OrdersConverter.toListOrderDetail(orders);
	}
	
	public OrderItemResponse getOrderItemResponse(Long orderId) {
		Orders orders = ordersRepo.findById(orderId).orElseThrow();
		OrderItemResponse orderItemRes = MapperUtil.map(orders, OrderItemResponse.class);
		orderItemRes.setOrderState(orders.getOrderState());
		orderItemRes.setListItem(OrdersConverter.toListItemRes(orders.getListOrderItems()));
		return orderItemRes;
	}
}
