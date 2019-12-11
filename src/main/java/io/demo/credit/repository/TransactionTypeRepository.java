package io.demo.credit.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.demo.credit.model.TransactionType;



public interface TransactionTypeRepository extends CrudRepository<TransactionType, Long> {
	
	TransactionType findByName (String name);
	TransactionType findByCode (String code);
	
	List<TransactionType> findAll();

}
