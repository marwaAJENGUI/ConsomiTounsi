package tn.consomitounsi.www.service;

import java.util.List;
import java.util.Optional;

import tn.consomitounsi.www.entity.Ad;

public interface IAdService {

	List<Ad> findAll();

	Optional<Ad> getAdById(Long id);

	Ad updateAdById(Ad ad, Long id);

	boolean removeAd(Long id);

	Ad saveAd(Ad ad);

}
