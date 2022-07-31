package com.bobhome.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bobhome.dsmeta.dto.SaleDTO;
import com.bobhome.dsmeta.services.SaleService;
import com.bobhome.dsmeta.services.SmsService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {
	
	@Autowired
	private SaleService service;
	
	@Autowired
	private SmsService smsService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleDTO> findById(@PathVariable Long id) throws Exception{
		SaleDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping(value = "/names")
	public ResponseEntity<List<SaleDTO>> findByName(@PathVariable
		@RequestParam(value = "name", defaultValue = "") String name){
		List<SaleDTO> list = service.findByName(name);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping
	public ResponseEntity<Page<SaleDTO>> findByDate(
			@RequestParam(value = "minDate", defaultValue = "") String minDate, 
			@RequestParam(value = "maxDate", defaultValue = "")String maxDate, 
			Pageable pageable){
		Page<SaleDTO> page = service.findByDate(minDate, maxDate, pageable);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping(value = "/{id}/notification")
	public void notifySms(@PathVariable Long id) {
		smsService.sendSms(id);
	}

}
