package tn.consomitounsi.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import tn.consomitounsi.www.service.IUserProductViewsService;


@RestController
public class UserProductViewsContorller{
	@Autowired
	IUserProductViewsService iUserProductViewsService;
}
