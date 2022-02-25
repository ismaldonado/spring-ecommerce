package com.ecommerce.ecommerce.service.user;

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

	@Override
	public User save(User user) {
		return this.iuserRepository.save(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return this.iuserRepository.findByEmail(email);
	}
}
