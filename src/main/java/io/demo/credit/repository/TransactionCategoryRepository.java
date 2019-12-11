package io.demo.credit.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import io.demo.credit.model.TransactionCategory;

public interface TransactionCategoryRepository extends CrudRepository<TransactionCategory, Long>{
	
	TransactionCategory findByName (String name);
	TransactionCategory findByCode (String code);
	
	List<TransactionCategory> findAll();

}
