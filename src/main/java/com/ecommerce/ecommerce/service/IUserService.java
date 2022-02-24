package com.ecommerce.ecommerce.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.model.User;

@Service
public interface IUserService {

	Optional<User> findById(Integer id);
}
