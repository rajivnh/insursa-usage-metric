package com.insursa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insursa.service.UsageMetricService;

@RestController
public class UsageMetricController {
	@Autowired UsageMetricService service;
	
	@PostMapping("/api/insert/{companyId}/{rateDefinitionId}")
	public ResponseEntity<Void> insert(@PathVariable String companyId, @PathVariable String rateDefinitionId) {
		service.insert(companyId, rateDefinitionId);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/api/findHitsByCompanyId/{fromMonth}/{toMonth}")
	public ResponseEntity<Long> findHitsByCompanyId(@PathVariable Integer fromMonth, @PathVariable Integer toMonth) {
		return ResponseEntity.ok(service.findHitsByCompanyId(fromMonth, toMonth));
	}
	
	@GetMapping("/api/findHitsByCompanyId/{fromMonth}/{toMonth}/{companyId}")
	public ResponseEntity<Long> findHitsByCompanyId(@PathVariable Integer fromMonth, @PathVariable Integer toMonth, @PathVariable String companyId) {
		return ResponseEntity.ok(service.findHitsByCompanyId(fromMonth, toMonth, companyId));
	}
}
