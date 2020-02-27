package ma.cadi.ecole.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.cadi.ecole.dao.RoleRepository;
import ma.cadi.ecole.dao.UserRepository;
import ma.cadi.ecole.entities.Role;
import ma.cadi.ecole.entities.User;

@RestController
@Secured(value = {"ROLE_ADMIN"})
public class UserRestService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@GetMapping(value="/ajouterUser")
	public User ajouterUser(User user) {
		String pwd=user.getPassword();
		String epwd=passwordEncoder.encode(pwd);
		user.setPassword(epwd);
		return userRepository.save(user);
	}
	@GetMapping(value="/ListUser")
	public List<User> ListUser() {
		return userRepository.findAll();
	}
	@GetMapping(value="/ajouterRole")
	public Role ajouterRole(Role role) {
		return roleRepository.save(role);
	}
	@GetMapping(value="/ListRole")
	public List<Role> ListRole() {
		return roleRepository.findAll();
	}
	@GetMapping(value="/affecterRoleUser")
	public User affecterRoleUser(String username,String roleUser) {
		User user=userRepository.getOne(username);
		Role role=roleRepository.getOne(roleUser);
		user.getRoles().add(role);
		userRepository.save(user);
		return user;
		
	}

}
