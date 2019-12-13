package io.demo.credit.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import io.demo.credit.model.BillingAddress;


public interface BillingAddressRepository extends CrudRepository<BillingAddress, Long> {
	
	List<BillingAddress> findAll();
	BillingAddress findByCardId(Long id);
	

}
