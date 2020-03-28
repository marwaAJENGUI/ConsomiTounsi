package tn.consomitounsi.www.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.consomitounsi.www.entity.Product;
import tn.consomitounsi.www.entity.UserProductViews;
import tn.consomitounsi.www.repository.UserProductViewsRepository;


@Service
public class UserProductViewsServiceImpl implements IUserProductViewsService {
	@Autowired
	UserProductViewsRepository userProductViewsRepository;
	@Override
	public Optional<UserProductViews> getUserViews(String userName,Product product) {
				return  userProductViewsRepository.getUserViews(userName, product);
	}
	@Override
	public 	UserProductViews setUserViews(UserProductViews userProductViews){
		return userProductViewsRepository.saveAndFlush(userProductViews);
		}
		
}
