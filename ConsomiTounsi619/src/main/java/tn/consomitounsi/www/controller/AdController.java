package tn.consomitounsi.www.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import tn.consomitounsi.www.entity.ProductCategory;
import tn.consomitounsi.www.entity.User;
import tn.consomitounsi.www.entity.UserProductCategoryViews;
import tn.consomitounsi.www.entity.UserProductViews;
import tn.consomitounsi.www.service.IAdCategoryService;
import tn.consomitounsi.www.service.IAdService;
import tn.consomitounsi.www.service.IProductCategoryService;
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
	IProductCategoryService iProductCategoryService;
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
	
	@GetMapping("/view/userAds")
	@ResponseBody
	public List<Ad> getuserProdutAds() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		//Get current user username
		if (principal instanceof UserDetails) {
		   username = ((UserDetails)principal).getUsername();
		} else {
		   username = principal.toString();
		}
		//get User
		User user=iUserService.getUserByUsername(username).get();
		List<Ad> list =iAdService.userProductAds(user);
		if (list.size()>2)
			list =iAdService.userProductAds(user).subList(0, 2);
		else 		
			if (list.size()==0) 
				if (iAdService.findAllByViews().size()>0) list=iAdService.findAllByViews().subList(0, 2);
		 return list;
	}

	@GetMapping("/view/productCategoryAds/{id}")
	@ResponseBody
	public List<Ad> getCategoryProductsAds(@PathVariable("id")Long id) {
		//////////////////////////////////////////get connected user
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		//Get current user username
		if (principal instanceof UserDetails) {
		   username = ((UserDetails)principal).getUsername();
		} else {
		   username = principal.toString();
		}
		//get User
		User user=iUserService.getUserByUsername(username).get();
		
		//////////////////// get Product List for a category
		List<Ad> list=new ArrayList<>();
		if (iProductCategoryService.findById(id).isPresent()) {
			ProductCategory prodCategory=iProductCategoryService.findById(id).get();
			list= iAdService.findByProductCategory(prodCategory);
			List <UserProductCategoryViews> listViews= prodCategory.getProductCategoryUsersViews();
			UserProductCategoryViews views=new UserProductCategoryViews();
			if (listViews.size()>0) {
				for (UserProductCategoryViews view:listViews) {
					if (view.getUser()==user) {
						view.setViews();
						views=view;
						break;
					}
				}
			}else views.setViews();
			views.setUser(user);
			views.setProductCategory(prodCategory);
			iUserProductCategoryViewsService.setUserViews(views);
		}
		return list;
	}

	@GetMapping("/view/ad/{id}")
	@ResponseBody
    public Ad getAd(@PathVariable("id") Long id){
		Ad ad = iAdService.getAdById(id).get();
		ad.setViews();
		System.out.println(ad.getViews());
		 ad=iAdService.saveAd(ad);
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
		UserProductViews userProductViews= new UserProductViews();
		if(!iUserProductViewsService.getUserViews(user,product).isPresent()) {
			userProductViews.setProduct(product);
			userProductViews.setUser(user);
		} else userProductViews= iUserProductViewsService.getUserViews(user,product).get();
		UserProductCategoryViews userProductCategoryViews=new UserProductCategoryViews();
		if (!iUserProductCategoryViewsService.getUserViews(user,product.getCategory()).isPresent()) {
			userProductCategoryViews.setProductCategory(product.getCategory());
			userProductCategoryViews.setUser(user);
		} else userProductCategoryViews= iUserProductCategoryViewsService.getUserViews(user,product.getCategory()).get();
		userProductViews=iUserProductViewsService.setUserViews(userProductViews);
		userProductCategoryViews=iUserProductCategoryViewsService.setUserViews(userProductCategoryViews);	
			return iAdService.getAdById(id).get();
    }
	
	@PostMapping("/manage/addAd")
	@ResponseBody
	public Ad addAd(@RequestBody Ad ad){
		if(! isValidBegginningDateOnAdd( ad.getBeginningDate()))  
			throw new IllegalArgumentException("Beginning Date can not be before more than 3 days  than today's date");
		if (!isValidEndingDate(ad.getEndDate(), ad.getBeginningDate()))  
			throw new IllegalArgumentException("Ending Date can not be before Beginning Date");
		ad.setCategory(validCategory(ad.getCategory()));
		ad.setProduct(validProduct(ad.getProduct()));
		isValidBegginningDate(ad.getBeginningDate(),ad);
		Ad a =iAdService.saveAd(ad);
		return a;
	}
	
	@PutMapping(value = "/manage/updateAd/{id}") 
	@ResponseBody
	public Ad updatead(@PathVariable("id") Long id,@RequestBody Ad ad) {
		Ad a = iAdService.getAdById(id).get();
		if (ad.getBeginningDate()!=null) {
			if (isValidBegginningDateOnUpdate(a.getBeginningDate(), ad.getBeginningDate())) {
				if(isValidBegginningDate(ad.getBeginningDate(),a)) 
					a.setBeginningDate(ad.getBeginningDate());
			}else throw new IllegalArgumentException("Beginning Date can not be before more than 3 days than the old value");
		}
		if (ad.getEndDate()!=null) {
			if (isValidEndingDate(ad.getEndDate(), ad.getBeginningDate())) {
				a.setEndDate(ad.getEndDate());
			}else throw new IllegalArgumentException("Ending Date can not be before Beginning Date");
		}
		if (ad.getProduct()!=null) {
			Product product=validProduct(ad.getProduct());
			a.setProduct(product);
		}
		if (ad.getCategory()!=null) {
			a.setCategory(validCategory(ad.getCategory()));
		}
		if (ad.getTargetViews()!=0) {
			a.setTargetViews(ad.getTargetViews());
		}
		return iAdService.updateAdById(ad, id);
	}
	
	@DeleteMapping("/manage/removeAd/{id}")
	@ResponseBody
    public boolean removeadCategory(@PathVariable("id") Long id){
		return iAdService.removeAd(id);
    }
	
	public static boolean isValidBegginningDate(Date startingDate,Ad ad)  {
		List <Ad> ads=ad.getProduct().getAds();
		for (Ad a:ads) {
			if (a.getEndDate().after(startingDate)) throw  new IllegalArgumentException("starting date can not be before the end of the past ad"); 
		}
		return true;
	}
	
	public static boolean isValidBegginningDateOnAdd(Date startingDate)  {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -3);
		Date date = cal.getTime();             
        return  startingDate.after(date);
	}

	
	public static boolean isValidBegginningDateOnUpdate(Date oldDate,Date newDate)  {
		Calendar cal = Calendar.getInstance();
		cal.setTime(oldDate);
		cal.add(Calendar.DATE, -3);
		Date date = cal.getTime();             
        return  newDate.after(date);
	}

	public static boolean isValidEndingDate(Date endingDate, Date beginingDate) {
        return (endingDate.after(beginingDate)||(beginingDate.equals(endingDate)))&& (new Date().before(endingDate));
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
