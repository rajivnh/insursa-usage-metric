package com.insursa.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insursa.persistence.model.UsageMetric;

@Repository
public interface UsageMetricRepository extends JpaRepository<UsageMetric, Long> {
	@Query(nativeQuery = true, value = "select count(*) from usage_matric\r\n"
			+ "       where MONTH(created_date) >= MONTH(now()) + ?1 and MONTH(created_date) <= MONTH(now()) + ?2 \r\n"
			+ "       and YEAR(created_date) = YEAR(now()) and company_id = ?3 group by company_id")
	Long findHitsByCompanyId(Integer fromMonth, Integer toMonth, String companyId);
}