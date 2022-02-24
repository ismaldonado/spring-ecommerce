package com.ecommerce.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserRepository iuserRepository;

	@Override
	public Optional<User> findById(Integer id) {
		return this.iuserRepository.findById(id);

	}
}
