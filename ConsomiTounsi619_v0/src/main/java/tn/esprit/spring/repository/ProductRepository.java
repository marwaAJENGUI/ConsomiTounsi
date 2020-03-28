package tn.esprit.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

}
