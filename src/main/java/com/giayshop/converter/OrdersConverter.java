package com.giayshop.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.giayshop.config.MapperUtil;
import com.giayshop.entity.OrderItem;
import com.giayshop.entity.Orders;
import com.giayshop.payload.response.ItemResponse;
import com.giayshop.payload.response.OrderDetailResponse;

public class OrdersConverter {
	
	public static OrderDetailResponse tOrderDetailResponse(Orders orders) {
		OrderDetailResponse orderDetailResponse = MapperUtil.map(orders, OrderDetailResponse.class);
		orderDetailResponse.setOrderState(orders.getOrderState());
		return orderDetailResponse;
	}
	
	public static List<OrderDetailResponse> toListOrderDetail(List<Orders> orders) {
		return orders.stream().map(order -> tOrderDetailResponse(order)).collect(Collectors.toList());
	}
	
	public static ItemResponse toItemResponse(OrderItem orderItem) {
		ItemResponse item = new ItemResponse();
		item.setProductId(orderItem.getId());
		item.setName(orderItem.getProduct().getTitle());
		item.setImage(orderItem.getProduct().getListImage().get(0).getFileName());
		item.setPrice(orderItem.getPrice());
		item.setQuantity(orderItem.getQuantity());
		return item;
	}
	
	public static List<ItemResponse> toListItemRes(List<OrderItem> listOrderItems) {
		return listOrderItems.stream().map(orderItem -> toItemResponse(orderItem)).collect(Collectors.toList());
	}
}
