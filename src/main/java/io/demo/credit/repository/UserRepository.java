package io.demo.credit.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import io.demo.credit.model.security.Users;

public interface UserRepository extends CrudRepository<Users, Long> {

	Users findByUsername(String paramString);
	  
	List<Users> findAll();
}