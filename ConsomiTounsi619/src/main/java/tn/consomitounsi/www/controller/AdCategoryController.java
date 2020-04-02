package tn.consomitounsi.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.consomitounsi.www.entity.AdCategory;
import tn.consomitounsi.www.service.IAdCategoryService;



@RestController
public class AdCategoryController {
	@Autowired
	IAdCategoryService iAdCategoryService;
	
	@GetMapping("/view/AdCategories")
	@ResponseBody
	public List<AdCategory>getAdCategories() {
		return iAdCategoryService.findAll();  
		}
	
	@PostMapping("/manage/addAdCategory")
	@ResponseBody
	public AdCategory addAdCategory(@RequestBody AdCategory adCategory){
		return 		iAdCategoryService.addAdCategory(adCategory);
	}
	
	@GetMapping("/view/AdCategory/{id}")
	@ResponseBody
    public AdCategory  getAdCategory(@PathVariable("id") Long id){
		AdCategory category= iAdCategoryService.getAdCategoryById(id).get();
		return category;
    }
	
	@DeleteMapping("/manage/removeAdCategory/{id}")
	@ResponseBody
    public boolean removeAdCategory(@PathVariable("id") Long id){
		return iAdCategoryService.removeAdCategory(id);
    }

	@PutMapping("/manage/updateAdCategory/{id}")
    public AdCategory  updateAdCategory(@PathVariable("id") Long id, @RequestBody AdCategory adCategory){
    	return  iAdCategoryService.updateAdCategoryById( adCategory,  id);
    }
}
