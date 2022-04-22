package app.inmobiliaria.api.servicios;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import app.inmobiliaria.api.entity.Menu;
import app.inmobiliaria.api.entity.Role;
import app.inmobiliaria.api.entity.SubMenu;
import app.inmobiliaria.api.entity.Vigencia;
import app.inmobiliaria.api.payload.response.MenuRoleResponse;
import app.inmobiliaria.api.payload.response.MessageResponse;
import app.inmobiliaria.api.repository.MenuRepository;
import app.inmobiliaria.api.repository.SubMenuRepository;

@Service
public class MenuService {
	
	List<Role> listRole = new ArrayList<>();

	List<SubMenu> listsubMenu = new ArrayList<>();

	List<MenuRoleResponse> lista_menuRole = new ArrayList<>();

	MenuRoleResponse maObj = new MenuRoleResponse();

	List<MenuRoleResponse> myList = new ArrayList<>();

	Logger logger_2 = LogManager.getLogger();
	
	@Autowired
	private MenuRepository MenuRepo;

	@Autowired
	private RoleService roleService;

	@Autowired
	private SubMenuRepository sub_menuRepo;

	@PersistenceContext
	EntityManager entityManager;

	private List<Menu> menuList = new ArrayList<Menu>();

	public Menu create(Menu us) {
		return MenuRepo.save(us);
	}

	public Menu update(Menu us) {
		return MenuRepo.save(us);
	}

	public List<Menu> getAllMenu() {
		return MenuRepo.findAll();
	}

	public void delete(Menu us) {
		MenuRepo.delete(us);
	}

	public MenuRoleResponse search(Long id) {
		MenuRoleResponse menurole = new MenuRoleResponse();
		MenuRoleResponse padreMenu = new MenuRoleResponse();
		List<MenuRoleResponse> padreMenumyList = new ArrayList<>();
		List<Role> listRoles = new ArrayList<>();
		Role roles = new Role();
		
		Optional<Menu> menu = MenuRepo.findById(id);
	
		List<Menu> childMenu = MenuRepo.findByTipoMenu(id);
		
		List<MenuRoleResponse> childMenuList = new ArrayList<>();
		MenuRoleResponse menurolechild = new MenuRoleResponse();
		
		
		menu.stream().forEach((s)-> {
            
			menurole.setMenu_id(s.getMenu_id());
			menurole.setTipo_menu(s.getTipo_menu());
			menurole.setArgumentos(s.getArgumentos());
			menurole.setDescripcion(s.getDescripcion());
			menurole.setMenu_icon(s.getMenu_icon());
			menurole.setMenu_path(s.getMenu_path());
			menurole.setMenu_title(s.getMenu_title());
			s.getRoles().stream().forEach((r)->{
				roles.setId(r.getId());
				roles.setName(r.getName());
				listRoles.add(roles);
			});
			/*Set<Role> strRoles = menu.get(i).getRoles();*/
			menurole.setRoles(listRoles);  	
			menurole.setVigencia(s.getVigencia());
			
			Menu padremenuService = MenuRepo.findPadreByChild(s.getTipo_menu());
			
			if(padremenuService != null) {
				padreMenu.setMenu_id(padremenuService.getMenu_id());
				padreMenu.setTipo_menu(padremenuService.getTipo_menu());
				padreMenu.setArgumentos(padremenuService.getArgumentos());
				padreMenu.setDescripcion(padremenuService.getDescripcion());
				padreMenu.setMenu_icon(padremenuService.getMenu_icon());
				padreMenu.setMenu_path(padremenuService.getMenu_path());
				padreMenu.setMenu_title(padremenuService.getMenu_title());
				menurole.setPadre(padreMenu);
			}
			

			padreMenumyList.add(menurole);
        });
		
		childMenu.stream().forEach((s)-> {
			
			menurolechild.setMenu_id(s.getMenu_id());
			menurolechild.setTipo_menu(s.getTipo_menu());
			menurolechild.setArgumentos(s.getArgumentos());
			menurolechild.setDescripcion(s.getDescripcion());
			menurolechild.setMenu_icon(s.getMenu_icon());
			menurolechild.setMenu_path(s.getMenu_path());
			menurolechild.setMenu_title(s.getMenu_title());
			childMenuList.add(menurolechild);
		 });
		
		menurole.setSubmenu(childMenuList);
		
		return menurole;
	}
	
	public Optional<Menu> deleteSearch(Long id) {
		return MenuRepo.findById(id);
	}

	public void delete_menu(Long id) {
		MenuRepo.deleteById(id);
	}

	public Menu buscarMenu(Long id) {
		return MenuRepo.findOneById(id);
	}

	public List<Menu> getAllMenuRol(Long long1) {
		List<Menu> listaMenu = MenuRepo.findMenuByRol(long1);
		return listaMenu;
	}

	public List<Menu> getSubMenuByMenu (Long id){
		List<Menu> listaMenu = MenuRepo.findByTipoMenu(id);
		return listaMenu;
		
	}
	public List<MenuRoleResponse> obtenerMenu() {

		return MenuTree();

	}
	
	List<Menu> menu = new ArrayList<>();

	public List<MenuRoleResponse> obtenerMenuRol(Long rol_id) {

		return MenuTreeRol(rol_id);

	}
	
	

	public List<MenuRoleResponse> MenuTreeRol(Long rol_id) {
		
		if(lista_menuRole.size() > 0) {
			lista_menuRole.clear();
		}
		
		List<Menu> menu = MenuRepo.findMenuByRol(rol_id);

		for (int i = 0; i < menu.size(); i++) {
			MenuRoleResponse menurole = new MenuRoleResponse();
			menurole.setMenu_id(menu.get(i).getMenu_id());
			menurole.setTipo_menu(menu.get(i).getTipo_menu());
			menurole.setArgumentos(menu.get(i).getArgumentos());
			menurole.setDescripcion(menu.get(i).getDescripcion());
			menurole.setMenu_icon(menu.get(i).getMenu_icon());
			menurole.setMenu_path(menu.get(i).getMenu_path());
			menurole.setMenu_title(menu.get(i).getMenu_title());
			/*Set<Role> strRoles = menu.get(i).getRoles();
			menurole.setRoles((List<Role>) strRoles);*/  	
			menurole.setVigencia(menu.get(i).getVigencia());
			
			lista_menuRole.add(menurole);
		}

		return builTree();
	}

	public List<MenuRoleResponse> MenuTree() {
		
		if(lista_menuRole.size() > 0) {
			lista_menuRole.clear();
		}
		
		List<Menu> menu = MenuRepo.findAll();

		for (int i = 0; i < menu.size(); i++) {
			MenuRoleResponse menurole = new MenuRoleResponse();
			menurole.setMenu_id(menu.get(i).getMenu_id());
			menurole.setTipo_menu(menu.get(i).getTipo_menu());
			menurole.setArgumentos(menu.get(i).getArgumentos());
			menurole.setDescripcion(menu.get(i).getDescripcion());
			menurole.setMenu_icon(menu.get(i).getMenu_icon());
			menurole.setMenu_path(menu.get(i).getMenu_path());
			menurole.setMenu_title(menu.get(i).getMenu_title());
			menurole.setVigencia(menu.get(i).getVigencia());
			lista_menuRole.add(menurole);
		}
		return builTree();
	}

	// Establish tree structure
	public List<MenuRoleResponse> builTree() {
		List<MenuRoleResponse> treeMenus = new ArrayList<MenuRoleResponse>();
		logger_2.info("obteniendo los Nodos desde builTree");
		logger_2.info(getRootNode());
		for (MenuRoleResponse menuNode : getRootNode()) {
			menuNode = buildChilTree(menuNode);
			treeMenus.add(menuNode);
		}

		return treeMenus;
	}

	private List<MenuRoleResponse> getRootNode() {

		List<MenuRoleResponse> rootMenuLists = new ArrayList<MenuRoleResponse>();

		for (MenuRoleResponse menuNode : lista_menuRole) {
			
			if (menuNode.getTipo_menu() == 0) {
				logger_2.info("obteniendo el tipoMenu");	
				logger_2.info(menuNode.getTipo_menu());
				
				menuNode.setTipo("menu");
				menuNode.setMenu_path_response(menuNode.getMenu_path());
				rootMenuLists.add(menuNode);

			}
		}
		logger_2.info("obteniendo los Nodos");
		logger_2.info(rootMenuLists);
		return rootMenuLists;
	}

	// Recursion, building subtree structure
	private MenuRoleResponse buildChilTree(MenuRoleResponse pNode) {
		List<MenuRoleResponse> chilMenus = new ArrayList<MenuRoleResponse>();
		logger_2.info(pNode);
		for (MenuRoleResponse menuNode : lista_menuRole) {
		
			if (menuNode.getTipo_menu().equals(pNode.getMenu_id())) {
				menuNode.setMenu_path_response(pNode.getMenu_path() + menuNode.getMenu_path());
				menuNode.setTipo("submenu");
				chilMenus.add(buildChilTree(menuNode));
			}
		}
		
		pNode.setSubmenu(chilMenus);
		return pNode;
	}

}
