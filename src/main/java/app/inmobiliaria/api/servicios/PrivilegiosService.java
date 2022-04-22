package app.inmobiliaria.api.servicios;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import app.inmobiliaria.api.entity.Privilegios;
import app.inmobiliaria.api.entity.Role;
import app.inmobiliaria.api.repository.PrivilegiosRepository;

@Service
public class PrivilegiosService {
	
	@Autowired
	private PrivilegiosRepository PrivilegioRepo;

	@PersistenceContext
	EntityManager entityManager;
	
	public  Privilegios findByRole(Long id) {
		return PrivilegioRepo.findbyRole(id);
	}
	
	public Privilegios create(Privilegios us) {
		  return PrivilegioRepo.save(us);
		}
	
	public Privilegios update(Privilegios us) {
		return PrivilegioRepo.save(us);
	}

	public Optional<Privilegios> search(Long id){
		return PrivilegioRepo.findById(id);
	}
	
	public Privilegios findLast() {
		return PrivilegioRepo.findLast();
	}

	public void delete(Privilegios privilegios) {
		
		 PrivilegioRepo.delete(privilegios);
		
	}
	
}
