package tn.consomitounsi.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.consomitounsi.www.entity.Ad;

public interface AdRepository extends JpaRepository<Ad,Long>{
	
	@Query("Select DISTINCT a From Ad  a "
			+ "JOIN  a.category c"
			+ " where c.id=:id") 
	List <Ad> findAdsByCategory(@Param("id") Long id);
	

}
