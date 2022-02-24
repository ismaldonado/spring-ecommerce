package com.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

}
