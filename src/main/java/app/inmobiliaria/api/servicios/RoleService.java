package app.inmobiliaria.api.servicios;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.inmobiliaria.api.entity.Privilegios;
import app.inmobiliaria.api.entity.Role;
import app.inmobiliaria.api.entity.User;
import app.inmobiliaria.api.payload.response.RoleResponse;
import app.inmobiliaria.api.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PrivilegiosService privilegioService;
	
	@Autowired
	UserService userService;

	
	Logger logger_2 = LogManager.getLogger();
	
	List <RoleResponse> ListroleResponse = new ArrayList<>();
	
	
	public Role create(Role us) {
	  return roleRepo.save(us);
	}

	public Role update(Role us) {
	  return roleRepo.save(us);
	}

	public List<RoleResponse> getAllRole(){
		if(ListroleResponse.size() > 0) {
			ListroleResponse.clear();
		}
		
		roleRepo.getAllRole().stream().forEach((s)-> {
			RoleResponse roleResponse = new RoleResponse();
			roleResponse.setId(s.getId());
			roleResponse.setName(s.getName());
			roleResponse.setVigencia(s.getVigencia());
			Privilegios privilegio = privilegioService.findByRole(s.getId());
			roleResponse.setPrivilegios(privilegio);
			
			List<User> listaUsuario = userService.findUsersByRole(s.getId());
			
			roleResponse.setUsuarios(listaUsuario);
			
			
			ListroleResponse.add(roleResponse);
		});
			
	  return ListroleResponse;
	}

	public void delete(Role us){
	  roleRepo.delete(us);
	}

	public Optional<Role> search(Long id){
		
		return  roleRepo.findById(id);
	  
	}

	public void delete_menu(Long id) {
	  roleRepo.deleteById(id);	
	}
	
	public Set<Role> setRole(Set<String> strRoles) {
		Set<Role> roles = new HashSet<>();
		  logger_2.info("Funcion setRole, variale: strRoles ");
		logger_2.info(strRoles);
		
		
		if (strRoles == null) {
			new RuntimeException("Error: Role is not found.");
			return null;
	    } else {
	      strRoles.forEach(role -> {
	    	  Role adminRole = roleRepo.findByName(role);
	  	
		          roles.add(adminRole);
		          logger_2.info("Funcion setRole, variale: roles ");
		      	logger_2.info(roles);
	      });
	    }
		return roles;
		
	}
	
	public Role findByNameRole (String role) {
		Role adminRole = roleRepo.findByName(role);
		return adminRole;
	}
	
	public List<Role> findByMenu (Long id) {
		List<Role> adminRole = roleRepo.findByMenu(id);
		return adminRole;
	}
	
}

