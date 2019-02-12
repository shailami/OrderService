package com.cg.annapurna.OrderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	/*
	 * @Bean public CommandLineRunner loadData(OrderRepository repository) {
	 * Map<String, Integer> map = new HashMap<String, Integer>();
	 * map.put("product1", 3); return (args) -> { { repository.save(new Order(101,
	 * "COD","Pending", map)); repository.save(new Order(102,"COD", "Delivered",
	 * map)); repository.save(new Order(103, "COD","Pending", map)); }
	 * 
	 * }; }
	 */

}
