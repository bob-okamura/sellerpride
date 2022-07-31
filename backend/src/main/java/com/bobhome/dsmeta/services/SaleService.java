package com.bobhome.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bobhome.dsmeta.dto.SaleDTO;
import com.bobhome.dsmeta.entities.Sale;
import com.bobhome.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository repository;

	@Transactional(readOnly = true)
	public SaleDTO findById(Long id) throws Exception {
		Optional<Sale> obj = repository.findById(id);
		Sale entity = obj.orElseThrow();
		return new SaleDTO(entity);
	}
	
	@Transactional
	public List<SaleDTO> findByName(String name) {
		List<Sale> list = repository.findBySellerName(name);
		return list.stream().map(x -> new SaleDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)	
	public Page<SaleDTO> findByDate(String minDate, String maxDate, Pageable pageable) {
		
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		LocalDate min = minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
		LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		
		Page<Sale> page = repository.findSalesByDate(min, max, pageable);
		return page.map(x -> new SaleDTO(x));
	}

}
