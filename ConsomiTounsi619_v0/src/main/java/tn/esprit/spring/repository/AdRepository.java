package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entity.Ad;
public interface AdRepository extends JpaRepository<Ad,Long>{

}
