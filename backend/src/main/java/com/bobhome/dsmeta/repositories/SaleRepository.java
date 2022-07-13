package com.bobhome.dsmeta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bobhome.dsmeta.entities.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>{

}
