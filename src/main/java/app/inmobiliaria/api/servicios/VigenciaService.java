package app.inmobiliaria.api.servicios;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.inmobiliaria.api.entity.Role;
import app.inmobiliaria.api.entity.Vigencia;
import app.inmobiliaria.api.repository.RoleRepository;
import app.inmobiliaria.api.repository.VigenciaRepository;

@Service
public class VigenciaService {
	
	@Autowired
	private VigenciaRepository vigenciaRepo;
	
	Logger logger_2 = LogManager.getLogger();
	
	Vigencia adminVigencia = new Vigencia();
	
	public Vigencia create(Vigencia us) {
	  return vigenciaRepo.save(us);
	}

	public Vigencia update(Vigencia us) {
	  return vigenciaRepo.save(us);
	}

	public List<Vigencia> getAllRole(){
	  return vigenciaRepo.findAll();
	}

	public void delete(Vigencia us){
		vigenciaRepo.delete(us);
	}

	public Optional<Vigencia> search(Long id){
	  return vigenciaRepo.findById(id);
	}

	public void delete_menu(Long id) {
		vigenciaRepo.deleteById(id);	
	}
	
	public Vigencia searchVigenciaByName (String name) {
		return adminVigencia = vigenciaRepo.findByName(name);
		
	}
	
	public Vigencia setVigencia(Set<String> strVigencia) {
		  logger_2.info("Funcion setRole, variale: strRoles ");
		logger_2.info(strVigencia);
		
		
		if (strVigencia == null) {
			new RuntimeException("Error: Role is not found.");
			return null;
	    } else {
	    	strVigencia.forEach(vigencias -> {
	    		adminVigencia = vigenciaRepo.findByName(vigencias);
	      });
	    }
		return adminVigencia;
		
	}
	


}
