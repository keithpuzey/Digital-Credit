package io.demo.credit.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import io.demo.credit.model.security.Users;
import io.demo.credit.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
	
	@Query ("select coalesce(max(accountNumber), 10000000) from Account")
	Long findMaxAccountNumber();
	
	List<Account> findAll ();
	
	List<Account> findByCustomer (Users customer);

}
