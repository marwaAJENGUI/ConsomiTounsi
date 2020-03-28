package tn.consomitounsi.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import tn.consomitounsi.www.service.IUserProductCategoryViewsService;


@RestController
public class UserProductCategoryViewsController  {
	@Autowired
	IUserProductCategoryViewsService iUserProductCategoryViewsService;
}
