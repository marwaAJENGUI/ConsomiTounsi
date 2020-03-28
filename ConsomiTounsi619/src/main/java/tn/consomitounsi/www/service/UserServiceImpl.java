package tn.consomitounsi.www.service;

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

}
