package tn.consomitounsi.www.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

import tn.consomitounsi.www.entity.User;
import tn.consomitounsi.www.security.config.MyUserDetails;
import tn.consomitounsi.www.service.IUserService;


@RestController
public class UserController {
	@Autowired
	IUserService iUserService;
	@GetMapping("/view/users")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getUsers() {
		return iUserService.findAll();   
	}
	
	@GetMapping("/view/user/{id}")
	@ResponseBody
    public User getUser(@PathVariable("id") Long id){
		return  iUserService.getUserById(id).get();		
		}
	@GetMapping("/view/this")
	@ResponseBody
    public String getUserCurrent(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		   return  ((UserDetails)principal).toString();
		}
		 return principal.toString();
	}
	@PostMapping("/manage/addUser")
	@ResponseBody
	public User addUser(@RequestBody User user)
	{
		User u =iUserService.addUser(user);
		return u;
	}
	
	@PutMapping(value = "/manage/updateUser/{id}") 
	@ResponseBody
	public User updateUser(@PathVariable("id") Long id,@RequestBody User user) {
		if ( iUserService.getUserById(id).isPresent()){
			User u = iUserService.getUserById(id).get();
			u.setUserName(user.getUserName());
			u.setEmail(user.getEmail());
			u.setFirstName(user.getFirstName());
			u.setLastName(user.getLastName());
			u.setPassword(user.getPassword());
			u.setRoles(user.getRoles());
			return iUserService.updateUser(u, id);
	}else throw new  IllegalArgumentException("Invalide user ID, user do not exist");
	}
	
	@DeleteMapping("/manage/removeUser/{id}")
	@ResponseBody
    public boolean removeadCategory(@PathVariable("id") Long id){
		return iUserService.removeUser(id);
    }
	


}
