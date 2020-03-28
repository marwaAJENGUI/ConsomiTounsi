package tn.consomitounsi.www.service;

import java.util.Optional;

import tn.consomitounsi.www.entity.Product;
import tn.consomitounsi.www.entity.UserProductViews;

public interface IUserProductViewsService {

	public Optional<UserProductViews> getUserViews(String userName,Product product);

	public UserProductViews setUserViews(UserProductViews userProductViews);

}
