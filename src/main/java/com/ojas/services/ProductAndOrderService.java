package com.ojas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojas.models.Orders;
import com.ojas.models.Products;
import com.ojas.repositories.OrderDao;
import com.ojas.repositories.ProductDao;

@Service
public class ProductAndOrderService {

	@Autowired
	private ProductDao productdao;
	
	@Autowired
	private OrderDao orderDao;
	
	public Products createProduct(Products product) {
		return productdao.save(product);
	}
	
	public Orders createOrder(Orders order) {
		return orderDao.save(order);
	}
	
	public Iterable<Products> getProducts(){
		return productdao.findAll();
	}
	
	public Iterable<Orders> getOrders(){
		return orderDao.findAll();
	}
	
	public Optional<Products> getproduct(Integer productId){
		return productdao.findById(productId);
	}
	
	public Optional<Orders> getOrder(Integer orderId){
		return orderDao.findById(orderId);
	}
	
	public int placeOrder(Integer productId, Integer quantity, Orders order) {
		int result = 1;
		Products product = productdao.findById(productId).orElse(new Products());
		if(product == null)
			return result;
		order.setQuantity(quantity);
		
		order.setProducts(product);
		orderDao.save(order);
		result = 0;
		return result;
		
	}
	
	public int updateOrder(Integer id, Integer quantity) {
		int result = 1;
		Orders order = orderDao.findById(id).orElse(new Orders());
		if(order.getOrderId() == 0) {
			return result;
		}
		
		order.setQuantity(quantity);
		orderDao.save(order); 
		result = 0;
		
		return result;
	}
}
