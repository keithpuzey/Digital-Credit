package io.demo.credit.repository;

import org.springframework.data.repository.CrudRepository;
import io.demo.credit.model.AuthorizedUsers;

public interface AuthorizedUsersRepository extends CrudRepository<AuthorizedUsers, Long> {
	
	void deleteByCardId(Long id);

}
