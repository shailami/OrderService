package com.cg.annapurna.OrderService.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.annapurna.OrderService.entity.Order;
import com.cg.annapurna.OrderService.exception.InvalidOrderException;
import com.cg.annapurna.OrderService.exception.OrderNotFoundException;
import com.cg.annapurna.OrderService.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResource {
	@Autowired
	OrderService service;

	@GetMapping
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> orders =service.getAllOrders();
		//System.out.println(orders);
		if(orders.isEmpty())
		{
			return new ResponseEntity<List<Order>>(orders,HttpStatus.NO_CONTENT);
		}
		System.out.println(orders.get(0).getModeOfPayment());
		return new ResponseEntity<List<Order>>(orders,HttpStatus.OK);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable Integer orderId) {
		Optional<Order> order= service.getOrderById(orderId);
		System.out.println("in resourse "+order);
		if(!order.isPresent())
		{
			return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Order>(order.get(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<Order> cancelOrder(@PathVariable Integer orderId) throws OrderNotFoundException {
		Optional<Order> order=service.getOrderById(orderId);
		if(order.isPresent()) {
			service.cancelOrder(order.get());
			return new ResponseEntity<Order>(HttpStatus.OK);
		}
		return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Order> placeOrder(@RequestBody Order order) throws InvalidOrderException {
		if(order.getOrderId()==null||order.getModeOfPayment()==null||order.getStatus()==null||order.getProducts()==null||
				order.getCartId()==null||order.getDeliveryAddress()==null||order.getRestaurantName()==null||order.getTotalAmount()==null) {
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
		}
		service.placeOrder(order);
		return new ResponseEntity<Order>(order,HttpStatus.CREATED);
	}
	
	@PutMapping("/{orderId}")
	public ResponseEntity<Order> updateOrderStatus(@PathVariable Integer orderId,@RequestParam String status) throws OrderNotFoundException {
		service.updateOrderStatus(orderId,status);
		return new ResponseEntity<Order>(HttpStatus.OK);
	}
	
	@GetMapping("/{orderId}/status")
	public String getCurrentStatus(@PathVariable Integer orderId) {
		Optional<Order> order=service.getOrderById(orderId);
		return order.get().getStatus();	
	}
	
}
