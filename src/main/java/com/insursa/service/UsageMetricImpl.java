package com.insursa.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.insursa.persistence.model.Identity;
import com.insursa.persistence.model.UsageMetric;
import com.insursa.persistence.repository.IdentityRepository;
import com.insursa.persistence.repository.UsageMetricRepository;

@Service
public class UsageMetricImpl implements UsageMetricService {
	@Autowired
	UsageMetricRepository repository;
	
	@Autowired
	IdentityRepository identityRepository;
	
	@Override
	public void insert(String companyId, String rateDefinitionId) {
		UsageMetric entity = new UsageMetric();
		
		entity.setCompanyId(companyId);
		entity.setRateDefinitionId(rateDefinitionId);
		entity.setCreatedDate(LocalDateTime.now());
		
		repository.save(entity);
	}
	
	@Override
	public Long findHitsByCompanyId(int fromMonth, int toMonth) {
		String emailId = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Identity identity = identityRepository.findById(emailId).get();
		
		return repository.findHitsByCompanyId(fromMonth, toMonth, identity.getCompanyId());
	}
	
	@Override
	public Long findHitsByCompanyId(int fromMonth, int toMonth, String companyId) {
		return repository.findHitsByCompanyId(fromMonth, toMonth, companyId);
	}
}
