package tn.consomitounsi.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.consomitounsi.www.entity.Ad;
import tn.consomitounsi.www.repository.AdCategoryRepository;
import tn.consomitounsi.www.repository.AdRepository;
import tn.consomitounsi.www.repository.ProductRepository;

@Service
public class AdServiceImpl implements IAdService{
	@Autowired
	AdRepository adRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	AdCategoryRepository adCategoryRepository;

	@Override
	public List<Ad> findAll() {
		return adRepository.findAll();
	}

	@Override
	public Ad getAdById(Long id) {
		return adRepository.getOne(id);
	}

	@Override
	public Ad addAd(Ad ad) {
		adRepository.save(ad);
		return ad;		
	}
	
	@Override
	public Ad updateAdById(Ad ad, Long id) {
		adRepository.save(ad);
		return ad; 
	}
	
	@Override
	public boolean removeAd(Long id) {
		adRepository.delete(adRepository.getOne(id));
		return true;
	}

}