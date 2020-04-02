package tn.consomitounsi.www.service;

import java.util.List;
import java.util.Optional;

import tn.consomitounsi.www.entity.Ad;
import tn.consomitounsi.www.entity.ProductCategory;
import tn.consomitounsi.www.entity.User;

public interface IAdService {

	List<Ad> findAll();

	Optional<Ad> getAdById(Long id);

	Ad updateAdById(Ad ad, Long id);

	boolean removeAd(Long id);

	Ad saveAd(Ad ad);

	List<Ad> userProductAds(User user);

	List<Ad> findAllByViews();

	List<Ad> findByProductCategory(ProductCategory category);

}
