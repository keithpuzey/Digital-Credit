package io.demo.credit.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.demo.credit.model.CreditCard;

public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {
	
	List<CreditCard> findAll();

}
