package io.demo.credit.repository;

import org.springframework.data.repository.CrudRepository;
import io.demo.credit.model.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Role findByName(String name);

}
