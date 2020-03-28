package tn.consomitounsi.www.service;

import java.util.Optional;

import tn.consomitounsi.www.entity.User;

public interface IUserService {

	public Optional<User> getUserByUsername(String username);

}
