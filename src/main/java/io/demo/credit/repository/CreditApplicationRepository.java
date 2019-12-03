package io.demo.credit.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import io.demo.credit.model.CreditApplication;


public interface CreditApplicationRepository extends CrudRepository<CreditApplication, Long> {
	
	List<CreditApplication> findAll ();

}
