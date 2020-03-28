package tn.consomitounsi.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.consomitounsi.www.entity.Ad;
import tn.consomitounsi.www.entity.AdCategory;
import tn.consomitounsi.www.entity.Product;
import tn.consomitounsi.www.entity.User;
import tn.consomitounsi.www.entity.UserProductCategoryViews;
import tn.consomitounsi.www.entity.UserProductViews;
import tn.consomitounsi.www.service.IAdCategoryService;
import tn.consomitounsi.www.service.IAdService;
import tn.consomitounsi.www.service.IProductService;
import tn.consomitounsi.www.service.IUserProductCategoryViewsService;
import tn.consomitounsi.www.service.IUserProductViewsService;
import tn.consomitounsi.www.service.IUserService;


@RestController
public class AdController {
	@Autowired
	IAdService iAdService;
	@Autowired
	IAdCategoryService iAdCategoryService;
	@Autowired
	IProductService iProductService;
	@Autowired
	IUserProductViewsService iUserProductViewsService;
	@Autowired
	IUserProductCategoryViewsService iUserProductCategoryViewsService;
	@Autowired
	IUserService iUserService;
	
	@GetMapping("/view/Ads")
	@ResponseBody
	public List<Ad> getAds() {
		return iAdService.findAll();   
	}
	
	@GetMapping("/view/ad/{id}")
	@ResponseBody
    public Ad getAd(@PathVariable("id") Long id){
		Ad ad = iAdService.getAdById(id);
		Product product=ad.getProduct();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		//Get current user username
		if (principal instanceof UserDetails) {
		   username = ((UserDetails)principal).getUsername();
		} else {
		   username = principal.toString();
		}
		//get UserProductViews and UserProductCategoryViews
		User user=iUserService.getUserByUsername(username).get();
		UserProductViews UserProductViews= new UserProductViews();
		if(!iUserProductViewsService.getUserViews(user,product).isPresent()) {
			UserProductViews.setProduct(product);
			UserProductViews.setUser(user);
		}
		UserProductCategoryViews UserProductCategoryViews=new UserProductCategoryViews();
		if (!iUserProductCategoryViewsService.getUserViews(user,product.getCategory()).isPresent()) {
			UserProductCategoryViews.setProductCategory(ad.getProduct().getCategory());
			UserProductCategoryViews.setUser(user);
		}
		UserProductViews=iUserProductViewsService.setUserViews(UserProductViews);
		UserProductCategoryViews=iUserProductCategoryViewsService.setUserViews(UserProductCategoryViews);
		
		product=iProductService.getOne(product.getBarCode());
				return ad;
    }
	
	@PostMapping("/manage/addAd")
	@ResponseBody
	public Ad addAd(@RequestBody Ad ad)
	{
		ad.setCategory(validCategory(ad.getCategory()));
		ad.setProduct(validProduct(ad.getProduct()));
		Ad a =iAdService.addAd(ad);
		return a;
	}
	
	@PutMapping(value = "/manage/updateAd/{id}") 
	@ResponseBody
	public Ad updatead(@PathVariable("id") Long id,@RequestBody Ad ad) {
		Ad a = iAdService.getAdById(id);
		a.setBeginningDate(ad.getBeginningDate());
		a.setCategory(ad.getCategory());
		a.setEndDate(ad.getEndDate());
		Product product=validProduct(ad.getProduct());
		a.setProduct(product);
		a.setCategory(validCategory(ad.getCategory()));
		a.setTargetViews(ad.getTargetViews());
		return iAdService.updateAdById(ad, id);
	}
	
	@DeleteMapping("/manage/removeAd/{id}")
	@ResponseBody
    public boolean removeadCategory(@PathVariable("id") Long id){
		return iAdService.removeAd(id);
    }
	
	AdCategory validCategory(AdCategory category) {
		if (category==null) throw new IllegalArgumentException("Ad Category can not be empty");
		if((category.getId()!=null)&&(iAdCategoryService.existsById(category.getId()))) {
			return iAdCategoryService.getOne(category.getId());
		}else if ((category.getName()!=null)&&(iAdCategoryService.findCategoryByName(category.getName()).size()>0)) {
			return iAdCategoryService.findCategoryByName(category.getName()).get(0);			
		}else throw new IllegalArgumentException("Invalid Ad Category, could not find reference");
	}
	
	Product validProduct(Product product) {
		if (product==null) throw new IllegalArgumentException("Product can not be empty");
		if((product.getBarCode()!=null)&&(iProductService.existsById(product.getBarCode()))) {
			return iProductService.getOne(product.getBarCode());
		}else if ((product.getName()!=null)&&(iProductService.findProductByName(product.getName()).size()>0)) {
			return iProductService.findProductByName(product.getName()).get(0);			
		}else throw new IllegalArgumentException("Invalid Product, could not find reference");
	}
	
}
