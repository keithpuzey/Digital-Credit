package io.demo.credit.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import io.demo.credit.model.Billing;

public interface BillingRepository extends CrudRepository<Billing, Long> {
	
	List<Billing> findAll();
	

}