package com.bobhome.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bobhome.dsmeta.entities.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>{
	
	@Query("SELECT obj FROM Sale obj WHERE obj.date BETWEEN :min AND :max ORDER BY obj.amount DESC")
	Page<Sale> findSalesByDate(LocalDate min, LocalDate max, Pageable pageable);

	@Query("SELECT obj FROM Sale obj WHERE UPPER(obj.sellerName) LIKE UPPER(CONCAT(:name,'%'))")
	List<Sale> findBySellerName(String name); 

}
