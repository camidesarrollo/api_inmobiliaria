package app.inmobiliaria.api.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.inmobiliaria.api.entity.SubMenu;
import app.inmobiliaria.api.repository.SubMenuRepository;

@Service
public class SubMenuService {

	@Autowired
	private SubMenuRepository subMenuRepo;
	
	
	public SubMenu create(SubMenu us) {
		return subMenuRepo.save(us);
	}
	
	public SubMenu update(SubMenu us) {
		return subMenuRepo.save(us);
	}
	
	public List<SubMenu> getAllUsuarios(){
		return subMenuRepo.findAll();
	}
	
	public void delete(SubMenu us){
		subMenuRepo.delete(us);
	}
	
	public Optional<SubMenu> search(Long id){
		return subMenuRepo.findById(id);
	}
	
	public void delete_usuario(Long id) {
		subMenuRepo.deleteById(id);	
	}
}
