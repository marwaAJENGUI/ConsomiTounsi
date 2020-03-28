package tn.consomitounsi.www.service;

import java.util.List;

import tn.consomitounsi.www.entity.Ad;

public interface IAdService {

	List<Ad> findAll();

	Ad getAdById(Long id);

	Ad addAd(Ad ad);

	Ad updateAdById(Ad ad, Long id);

	boolean removeAd(Long id);

}
