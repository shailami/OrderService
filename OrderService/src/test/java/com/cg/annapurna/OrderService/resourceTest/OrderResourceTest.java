package com.cg.annapurna.OrderService.resourceTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.annapurna.OrderService.entity.Address;
import com.cg.annapurna.OrderService.entity.FoodProducts;
import com.cg.annapurna.OrderService.entity.Order;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderResourceTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Ignore
	@Test
	public void getAllOrdersTestForEmptyList() {
		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:9090/orders",
				String.class);
		System.out.println(responseEntity);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	@Test
	public void getAllOrdersTestForNonEmptyList() {
		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:9090/orders",
				String.class);
		System.out.println(responseEntity);
	}

	@Test
	public void getOrdersByIdThatExistsTest() {
		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:9090/orders/103",
				String.class);
		System.out.println("Response entity " + responseEntity);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void getOrdersByIdThatDoesNotExistsTest() {
		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:9090/orders/109",
				String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void getOrdersByIdWithWrongURLTest() {
		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:9090/orders/onezeroone",
				String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void incorrectURLTest() {
		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:9090/incorrectURL",
				String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void placeOrderWithCorrectCredentialsTest() {
		Set<FoodProducts> products = new HashSet<FoodProducts>();
		products.add(new FoodProducts("Brinjal", 234, 12));
		
		Address address=new Address(65, "rosa villa", "Mumbai", 65423, "Maharashtra", "India");
		Order order = new Order(103, "COD", "Delivered", products, 1000.00, "grandmama", address, 543);
		ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("http://localhost:9090/orders", order,
				String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	public void placeOrderWithWrongCredentialsTest() {
		/*
		 * Set<FoodProducts> products = new HashSet<FoodProducts>(); products.add(new
		 * FoodProducts("Brinjal", 234, 12));
		 */
		Order orders = new Order(103, null, null, null, null,null, null, null);
		ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("http://localhost:9090/orders", orders,
				String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void UpdatingStatusTest() {
		String status = "delivered";
		testRestTemplate.put("http://localhost:9090/orders/101?status=" + status + "", null);
		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:9090/orders/101/status",
				String.class);
		assertThat(responseEntity.getBody().equalsIgnoreCase(status));

	}

	@Ignore
	@Test
	public void cancelOrderForValidIdThatExistsTest() {
		testRestTemplate.delete("http://localhost:9090/orders/103");
		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:9090/orders/103",
				String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void cancelOrderForInvalidIdThatDoesNotExistsTest() {
		testRestTemplate.delete("http://localhost:9090/orders/109");
		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:9090/orders/109",
				String.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

}
