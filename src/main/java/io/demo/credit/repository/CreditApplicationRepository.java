package io.demo.credit.repository;

import org.springframework.data.repository.CrudRepository;

import io.demo.credit.model.CreditApplication;


public interface CreditApplicationRepository extends CrudRepository<CreditApplication, Long>{

}
