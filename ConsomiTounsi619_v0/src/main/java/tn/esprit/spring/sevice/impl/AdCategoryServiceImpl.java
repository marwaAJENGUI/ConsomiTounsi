package tn.esprit.spring.sevice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.repository.AdCategoryRepository;
import tn.esprit.spring.sevice.interfece.IAdCategoryService;

@Service
public class AdCategoryServiceImpl implements IAdCategoryService{
	@Autowired
	AdCategoryRepository adCategoryRepository;
	

}
