package app.inmobiliaria.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.inmobiliaria.api.entity.Privilegios;
import app.inmobiliaria.api.entity.Role;

public interface PrivilegiosRepository extends JpaRepository<Privilegios, Long> {

	@Query(value = "SELECT * FROM privilegios where role_id =  ?", nativeQuery = true)
	Privilegios findbyRole(Long id);
	
	@Query(value = "SELECT * FROM privilegios  order by privilegios_id desc LIMIT 1", nativeQuery = true)
	Privilegios findLast();
}
