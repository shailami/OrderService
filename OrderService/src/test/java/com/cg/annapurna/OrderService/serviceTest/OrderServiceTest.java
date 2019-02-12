package com.cg.annapurna.OrderService.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.annapurna.OrderService.entity.Address;
import com.cg.annapurna.OrderService.entity.FoodProducts;
import com.cg.annapurna.OrderService.entity.Order;
import com.cg.annapurna.OrderService.exception.InvalidOrderException;
import com.cg.annapurna.OrderService.exception.OrderNotFoundException;
import com.cg.annapurna.OrderService.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
	@Autowired
	private OrderService service;

	/*
	 * @Before public void setUp() { System.out.println("Before"); }
	 */
	@Ignore
	@Test
	public void getAllOrderTest() {
		List<Order> orders = service.getAllOrders();
		System.out.println(orders);
		assertEquals(3, orders.size());
	}

	@Test
	public void getOrderByValidIdTest() {
		Optional<Order> order = service.getOrderById(102);
		assertTrue(order.isPresent());
	}

	@Test
	public void getOrderByInvalidIdTest() {
		Optional<Order> order = service.getOrderById(109);
		// assertEquals("delivered", order.getStatus());
		assertFalse(order.isPresent());
	}



	@Test
	public void placeOrderWithValidDetailsTest() throws InvalidOrderException {
		Set<FoodProducts> products = new HashSet<FoodProducts>();
		products.add(new FoodProducts("Brinjal", 234, 12));
		Address address=new Address(65, "rosa villa", "Mumbai", 65423, "Maharashtra", "India");
		Order order = new Order(102, "COD", "Delivered", products, 1000.00, "grandmama",address, 543);
		service.placeOrder(order);
		Optional<Order> result = service.getOrderById(102);
		assertTrue(result.isPresent());
	}
	

	@Test(expected = InvalidOrderException.class)
	public void placeOrderWithInvalidDetailsTest() throws InvalidOrderException {
		Order order = new Order(102, "COD", null, null,null, null, null, null);
		service.placeOrder(order);
		Optional<Order> result = service.getOrderById(106);
		assertFalse(result.isPresent());
	}

	@Test
	public void updateOrderStatusWithValidIdTest() throws OrderNotFoundException {
		String expectedStatus = "delivered";
		service.updateOrderStatus(102, expectedStatus);
		String actualStatus = service.getOrderById(102).get().getStatus();
		assertEquals(expectedStatus, actualStatus);
	}

	@Test(expected = OrderNotFoundException.class)
	public void updateOrderStatusWithInvalidIdTest() throws OrderNotFoundException {
		String expectedStatus = "delivered";
		service.updateOrderStatus(1044, expectedStatus);
		// String actualStatus = service.getOrderById(1044).get().getStatus();

	}

	@Ignore
	@Test
	public void cancelOrderWithValidIdTest() throws OrderNotFoundException {
		Order order = service.getOrderById(101).get();
		service.cancelOrder(order);
		Optional<Order> cancelledOrder = service.getOrderById(101);
		assertFalse(cancelledOrder.isPresent());
	}

}
