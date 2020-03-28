package tn.consomitounsi.www.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.consomitounsi.www.entity.ProductCategory;
import tn.consomitounsi.www.entity.User;
import tn.consomitounsi.www.entity.UserProductCategoryViews;
import tn.consomitounsi.www.repository.UserProductCategoryViewsRepository;


@Service
public class UserProductCategoryViewsServiceImpl implements IUserProductCategoryViewsService {
	@Autowired
	UserProductCategoryViewsRepository userProductCategoryViewsRepository;
	@Override
	public Optional <UserProductCategoryViews >getUserViews(User user,ProductCategory category){
		return userProductCategoryViewsRepository.getUserViews(user, category);
	}
	@Override
	public 	UserProductCategoryViews setUserViews(UserProductCategoryViews userProductCategoryViews){
		userProductCategoryViews.setViews();
		return  userProductCategoryViewsRepository.saveAndFlush(userProductCategoryViews);
		}
}
