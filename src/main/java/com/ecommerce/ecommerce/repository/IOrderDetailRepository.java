package com.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.model.OrdenDetail;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrdenDetail, Integer> {

}
