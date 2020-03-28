package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entity.AdCategory;

public interface AdCategoryRepository extends JpaRepository< AdCategory, Long> {

}
