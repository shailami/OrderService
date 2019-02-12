package com.cg.annapurna.OrderService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.annapurna.OrderService.entity.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, Integer>{

}
