package com.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.model.Order;

@Repository
public interface IOrdenRepository extends JpaRepository<Order, Integer> {

}
