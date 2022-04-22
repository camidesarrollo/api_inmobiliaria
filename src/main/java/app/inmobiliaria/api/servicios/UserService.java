package app.inmobiliaria.api.servicios;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.inmobiliaria.api.entity.User;
import app.inmobiliaria.api.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository usRepo;
	
	
	public User create(User us) {
		return usRepo.save(us);
	}
	
	public User update(User us) {
		return usRepo.save(us);
	}
	
	public List<User> getAllUsuarios(){
		return usRepo.findAll();
	}
	
	public void delete(User us){
		 usRepo.delete(us);
	}
	
	public Optional<User> search(Long id){
		return usRepo.findById(id);
	}
	
	public void delete_usuario(Long id) {
		usRepo.deleteById(id);	
	}
	
	public List<User> findUsersByRole(Long id) {
		return usRepo.findUsersByRole(id);
	}
	
}
