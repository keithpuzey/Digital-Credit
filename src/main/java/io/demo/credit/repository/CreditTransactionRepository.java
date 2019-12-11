package io.demo.credit.repository;

import org.springframework.data.repository.CrudRepository;

import io.demo.credit.model.CreditTransaction;

public interface CreditTransactionRepository extends CrudRepository<CreditTransaction, Long>{
	

}
