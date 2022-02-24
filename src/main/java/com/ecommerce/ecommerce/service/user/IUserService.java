package com.ecommerce.ecommerce.service.user;

import java.util.Optional;

import com.ecommerce.ecommerce.model.User;

public interface IUserService {

	Optional<User> findById(Integer id);
}
