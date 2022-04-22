package app.inmobiliaria.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.inmobiliaria.api.entity.Menu;
import app.inmobiliaria.api.entity.SubMenu;
import app.inmobiliaria.api.payload.response.MenuRoleResponse;

public interface MenuRepository extends JpaRepository<Menu, Long>{
	@Query(value = "SELECT * FROM menu where menu_id =  ?", nativeQuery = true)
	Menu findOneById(long menu);
	
	@Query(value = "SELECT * FROM menu where menu_title =  ?", nativeQuery = true)
	 List <Menu> findByname(String nombre_menu);

	@Query(value = "select * from menu \r\n"
			+ "left join menu_roles on menu.menu_id = menu_roles.menu_id  \r\n"
			+ "left join roles on menu_roles.role_id = roles.id \r\n"
			+ "where menu.vigencia_id = 1 and menu_roles.role_id = ?", nativeQuery = true)
	List<Menu>findMenuByRol(Long rol_id);
	

	@Query(value = "SELECT * FROM menu where tipo_menu =  ?", nativeQuery = true)
	List<Menu> findByTipoMenu(Long id);
	
	@Query(value = "SELECT * FROM menu where menu_id =  ?", nativeQuery = true)
	Menu findPadreByChild(Long id);
	
	



	
	
}
