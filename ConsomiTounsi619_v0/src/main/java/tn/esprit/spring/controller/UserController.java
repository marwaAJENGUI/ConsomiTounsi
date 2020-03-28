package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.sevice.interfece.IUserService;

@RestController
public class UserController {
	@Autowired
	IUserService iUserService;
}
