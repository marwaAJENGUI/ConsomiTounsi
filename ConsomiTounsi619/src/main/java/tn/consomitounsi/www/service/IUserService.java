package tn.consomitounsi.www.service;

import java.util.List;
import java.util.Optional;

import tn.consomitounsi.www.entity.User;

public interface IUserService {

	public Optional<User> getUserByUsername(String username);

	public List<User> findAll();

	public Optional<User> getUserById(Long id);

	public User addUser(User user);

	public User updateUser(User user, Long id);

	public boolean removeUser(Long id);

}
