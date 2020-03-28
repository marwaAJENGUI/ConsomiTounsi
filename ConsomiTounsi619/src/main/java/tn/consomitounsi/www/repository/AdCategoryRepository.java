package tn.consomitounsi.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.consomitounsi.www.entity.AdCategory;


public interface AdCategoryRepository extends JpaRepository< AdCategory, Long> {
	@Transactional
    @Modifying
    @Query("update AdCategory c set c.name =:name where c.id = :id")
	void updateProductCategoryById(@Param("name")String name,@Param("id") Long id);
	@Query("Select c from AdCategory c where c.name=:name") 
	List <AdCategory> findCategoryByName(@Param("name")String name);
	

}
