package com.cg.annapurna.OrderService.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.annapurna.OrderService.entity.Order;
import com.cg.annapurna.OrderService.exception.InvalidOrderException;
import com.cg.annapurna.OrderService.exception.OrderNotFoundException;
import com.cg.annapurna.OrderService.repository.OrderRepository;
@Service
public class OrderService {
	
	@Autowired 
	OrderRepository repository;

	public List<Order> getAllOrders() {
		System.out.println(repository.findAll().size());
		return repository.findAll();
	}

	public Optional<Order> getOrderById(Integer orderId) {
		return  repository.findById(orderId);
	}

	public void cancelOrder(Order order) throws OrderNotFoundException {
		if(order.getOrderId()==null) {
			throw new OrderNotFoundException("Invalid Data Order Not Found");
		}
		repository.delete(order);
	}

	public void placeOrder(Order order) throws InvalidOrderException {
		order.setDate(LocalDateTime.now());
		if(order.getOrderId()==null||order.getModeOfPayment()==null ||order.getStatus()==null||
				order.getProducts()==null||order.getDeliveryAddress()==null||order.getTotalAmount()==null||
				order.getCartId()==null||order.getRestaurantName()==null) {
			throw new InvalidOrderException("Please provide valid details for placing order");
		}
		repository.save(order);
	}

	public Order updateOrderStatus(Integer orderId, String status) throws OrderNotFoundException {
		Optional<Order> order=repository.findById(orderId);
		if(!order.isPresent()) {
			
			throw new OrderNotFoundException("Invalid Data Order Not Found"); 
		}
		order.get().setStatus(status);
		return repository.save(order.get());
	}
	
	
	

}
