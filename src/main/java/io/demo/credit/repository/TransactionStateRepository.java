package io.demo.credit.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.demo.credit.model.TransactionState;


public interface TransactionStateRepository extends CrudRepository<TransactionState, Long> {
	
	TransactionState findByName (String name);
	TransactionState findByCode (String code);
	
	List<TransactionState> findAll();

}
