package app.inmobiliaria.api.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.inmobiliaria.api.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String role);
  
	@Query(value = "SELECT * FROM roles inner join menu_roles on roles.id = menu_roles.role_id where menu_roles.menu_id =  ?", nativeQuery = true)
	List<Role>findByMenu(Long id);

	/*@Query(value = "SELECT min(id) FROM roles")
	BigInteger min();

	@Query(value = "SELECT max(id) FROM roles")
	BigInteger max();*/
	
	@Query(value = "SELECT  id, name, vigencia_id FROM roles  order by id desc LIMIT 1", nativeQuery = true)
	Role findLast();
	
	@Query(value = "SELECT  * FROM roles left join privilegios on roles.id = privilegios.role_id", nativeQuery = true)
	List<Role> getAllRole();
	
}
