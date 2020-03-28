package tn.consomitounsi.www.service;

import java.util.Optional;

import tn.consomitounsi.www.entity.ProductCategory;
import tn.consomitounsi.www.entity.UserProductCategoryViews;

public interface IUserProductCategoryViewsService {

	public Optional<UserProductCategoryViews> getUserViews(String userName, ProductCategory category);

	public UserProductCategoryViews setUserViews(UserProductCategoryViews userProductCategoryViews);

}
