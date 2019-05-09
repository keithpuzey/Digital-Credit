package io.demo.credit.repository;

import org.springframework.data.repository.CrudRepository;
import io.demo.credit.model.security.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

}
