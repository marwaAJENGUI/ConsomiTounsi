package tn.esprit.spring.sevice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.repository.AdRepository;
import tn.esprit.spring.sevice.interfece.IAdService;
@Service
public class AdServiceImpl implements IAdService{
	@Autowired
	AdRepository adRepository;

}
