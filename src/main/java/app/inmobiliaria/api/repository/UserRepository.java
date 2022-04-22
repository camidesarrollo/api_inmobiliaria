package app.inmobiliaria.api.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.inmobiliaria.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  
	@Query(value = "SELECT * FROM  users where users.vigencia_id = 1 and users.email =?", nativeQuery = true)
	User findByEmail(String email);
	
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
  
  @Query(value = "  select * from users left join user_roles on users.id = user_roles.user_id where user_roles.role_id = ?", nativeQuery = true)
	List<User> findUsersByRole(Long rol_id);
  

  
  /*User findByName(String User);*/
}
