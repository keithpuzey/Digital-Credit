package io.demo.credit.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import io.demo.credit.model.security.Users;
import io.demo.credit.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
	
	List<Account> findAll ();
	
	Account findByAccountOwner (Users owner);

}
