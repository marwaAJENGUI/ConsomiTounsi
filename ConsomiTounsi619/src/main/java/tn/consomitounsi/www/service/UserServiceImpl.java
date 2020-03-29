package tn.consomitounsi.www.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.consomitounsi.www.entity.User;
import tn.consomitounsi.www.repository.UserRepository;


@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public Optional<User> getUserByUsername(String username) { 
        return userRepository.findByUserName(username);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User addUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User updateUser(User user, Long id) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public boolean removeUser(Long id) {
		 userRepository.deleteById(id);
		 return true;
	}

}
