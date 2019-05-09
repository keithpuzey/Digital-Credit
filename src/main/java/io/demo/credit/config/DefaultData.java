package io.demo.credit.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import io.demo.credit.model.UserProfile;
import io.demo.credit.model.security.Role;
import io.demo.credit.model.security.Users;
import io.demo.credit.repository.RoleRepository;
import io.demo.credit.service.UserService;


@Component
public class DefaultData implements CommandLineRunner, Ordered {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultData.class);

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserService userService;
	
	@Override
	public int getOrder() {
		return 1;
	}

	@Override
	public void run(String... args) throws Exception {
		
		LOG.info("*********************************");
		LOG.info("***** Checking Default Data *****");
		
		// Load Roles if they do not exist
		if (roleRepository.findByName(Role.ROLE_USER) == null) {
			
			LOG.info("** Loading Roles...");
			
			List<Role> roles = new ArrayList<Role>();
			
			roles.add(new Role(Role.ROLE_USER));
			roles.add(new Role(Role.ROLE_ADMIN));
			roles.add(new Role(Role.ROLE_API));
			
			roleRepository.saveAll(roles);
			
		}
		
		// If the sample user data does not exist, then create it.
		if (!userService.checkEmailAdressExists("admin@demo.io") && !userService.checkSsnExists("000-00-0000")) {
			
			LOG.info("** Loading Default User...");
			
			Users user = new Users("admin@demo.io", "Demo123!");
			UserProfile userProfile = new UserProfile();
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
			
			userProfile.setEmailAddress("admin@demo.io");
			userProfile.setFirstName("Admin");
			userProfile.setLastName("User");
			userProfile.setTitle("Mr.");
			userProfile.setGender("M");
			userProfile.setDob(dateFormat.parse("1985-02-15"));
			userProfile.setSsn("000-00-0000");
			userProfile.setAddress("123 Digital Lane");
			userProfile.setCountry("United States");
			userProfile.setLocality("Internet City");
			userProfile.setPostalCode("94302");
			userProfile.setRegion("CA");
			userProfile.setHomePhone("123-456-7890");
			userProfile.setMobilePhone("123-456-7890");
			userProfile.setWorkPhone("123-456-7890");
			
			user.setUserProfile(userProfile);
			userService.createUser(user, Role.ROLE_API);
			userService.addRole(user, Role.ROLE_ADMIN);
			
		}
		
		LOG.info("*********************************");
		
	}
}
