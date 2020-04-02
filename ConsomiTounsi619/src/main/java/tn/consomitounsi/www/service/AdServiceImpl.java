package tn.consomitounsi.www.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.consomitounsi.www.entity.Ad;
import tn.consomitounsi.www.entity.ProductCategory;
import tn.consomitounsi.www.entity.User;
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
	public Optional<Ad> getAdById(Long id) {
		return 		adRepository.findById(id);
	}

	@Override
	public Ad saveAd(Ad ad) {
		return adRepository.save(ad);		
	}
	
	@Override
	public Ad updateAdById(Ad ad, Long id) {
		adRepository.flush();
		return adRepository.findById(id).get(); 
	}
	
	@Override
	public boolean removeAd(Long id) {
		adRepository.delete(adRepository.getOne(id));
		return true;
	}
	@Override
	public List<Ad> userProductAds(User user){
		Date today=new Date();
		return adRepository.findUserAds(user, today);
	}
	@Override
	public List<Ad> findAllByViews(){
		Date today=new Date();
		return adRepository.findAllByViews(today);
	}
	@Override
	public List<Ad> findByProductCategory(ProductCategory category){
	Date today=new Date();
	return adRepository.findAdsByProductCategory(category,today);
	}
	

}