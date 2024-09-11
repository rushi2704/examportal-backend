package com.examportal.portal;

import com.examportal.portal.helper.UserFoundException;
import com.examportal.portal.model.Role;
import com.examportal.portal.model.User;
import com.examportal.portal.model.UserRole;
import com.examportal.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class PortalApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args)  {
		SpringApplication.run(PortalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			System.out.println("starting");


			User user = new User();
			user.setUserName("rushi2704");
			user.setFirstName("rushikesh");
			user.setLastName("patil");
			user.setPassword(this.bCryptPasswordEncoder.encode("2704"));
			user.setEmail("rushi@gmail.com");
			user.setProfile("default.png");
			user.setPhone("9420203007");

			Role role1 = new Role();
			role1.setRoleID(28L);
			role1.setRoleName("ADMIN");

			Set<UserRole> userRoleSet = new HashSet<>();
			UserRole userRole = new UserRole();
			userRole.setRole(role1);
			userRole.setUser(user);
			userRoleSet.add(userRole);

			User user1 = this.userService.creatUser(user, userRoleSet);
			System.out.print(user1.getUserName());


		}catch(UserFoundException e){
			e.printStackTrace();
		}

	}
}
