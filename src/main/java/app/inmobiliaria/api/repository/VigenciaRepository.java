package app.inmobiliaria.api.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import app.inmobiliaria.api.entity.Vigencia;


public interface VigenciaRepository extends JpaRepository<Vigencia, Long> {

	  /*Vigencia findByName(Set<String> strVigencia);*/
	   Vigencia findByName(String strVigencia);
	   

}
