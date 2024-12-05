package com.insursa.service;

public interface UsageMetricService {
	void insert(String companyId, String rateDefinitionId);
	
	Long findHitsByCompanyId(int fromMonth, int toMonth);
	
	Long findHitsByCompanyId(int fromMonth, int toMonth, String companyId);
}
