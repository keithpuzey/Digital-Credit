package io.demo.credit.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import io.demo.credit.model.UserProfile;

public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {
	
	UserProfile findByEmailAddress(String paramString);
	  
	UserProfile findBySsn(String paramString);
	  
	List<UserProfile> findAll();

}