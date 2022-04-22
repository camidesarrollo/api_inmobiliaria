package app.inmobiliaria.api.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.inmobiliaria.api.entity.ERole;
import app.inmobiliaria.api.entity.Menu;
import app.inmobiliaria.api.entity.Role;
import app.inmobiliaria.api.entity.SubMenu;
import app.inmobiliaria.api.entity.Vigencia;
import app.inmobiliaria.api.payload.request.MenuRequest;
import app.inmobiliaria.api.payload.request.RoleRequest;
import app.inmobiliaria.api.payload.request.SubMenuRequest;
import app.inmobiliaria.api.payload.response.MenuRoleResponse;
import app.inmobiliaria.api.payload.response.MessageResponse;
import app.inmobiliaria.api.payload.response.UserInfoResponse;
import app.inmobiliaria.api.repository.MenuRepository;
import app.inmobiliaria.api.repository.RoleRepository;
import app.inmobiliaria.api.servicios.MenuService;
import app.inmobiliaria.api.servicios.RoleService;
import app.inmobiliaria.api.servicios.SubMenuService;
import app.inmobiliaria.api.servicios.UserDetailsImpl;
import app.inmobiliaria.api.servicios.UserDetailsServiceImpl;
import app.inmobiliaria.api.servicios.VigenciaService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/menu")
public class MenuController {
	
	 @Autowired
	  MenuRepository menuRepository;
	 
	  @Autowired
	  RoleRepository roleRepository;
	  
	  @Autowired
	  MenuService menu_ser;
	  
	  @Autowired
	  SubMenuService sub_menu_servi;
	  
	  @Autowired
	  RoleService roleService;
	  
	  
	 Logger logger = LogManager.getLogger(MenuController.class);
		
	Logger logger_2 = LogManager.getLogger();
	
	MessageResponse mensaje = new MessageResponse ();
	
	  @Autowired
	  VigenciaService vigenciaService;
	  
	List<Object> objeto = new ArrayList<Object>();
	
	  @PostMapping("/menu")
	  public ResponseEntity<?> insertMenu(@Valid @RequestBody MenuRequest menuRequest) {
		  
		  List<Menu> menuResponse = menu_ser.getAllMenu();

		if (menuRepository.findByname(menuRequest.getMenu_title()).size() > 0) {
				mensaje.setCode(404);
				mensaje.setMessage("Error: Menu ya existe!");
		      return ResponseEntity.ok(mensaje);
		    }
		  
		 	logger_2.info("menuREques");
		  	logger_2.info(menuRequest.getMenu_title());
		  	logger_2.info(menuRequest.getVigencia());
		  	
		  	long idmenu  = menuResponse.size()+1;
		  	Menu menu = new Menu(idmenu, menuRequest.getMenu_title(), menuRequest.getMenu_path(), menuRequest.getMenu_icon(), menuRequest.getDescripcion(), menuRequest.getArgumentos(), menuRequest.getTipo_menu());
		    Set<String> strRoles = menuRequest.getRole();
		    
		    Set<Role> roles = new HashSet<>();		

		  	
		  	roles = roleService.setRole(strRoles);
		  	Set<String> strVigencia = menuRequest.getVigencia();
		  	menu.setVigencia(vigenciaService.setVigencia(strVigencia));
		  	
		  	if(roles == null) {
				mensaje.setCode(404);
				mensaje.setMessage("Error: Rol no existe, por favor intentelo de nuevo!");
		  		 return ResponseEntity.ok(mensaje);
		  	}
		  	
		    menu.setRoles(roles);

		    menu_ser.create(menu);
			mensaje.setCode(200);
			mensaje.setMessage("Se ha creado menu con exito!");
		    return ResponseEntity.ok(mensaje);
	  }
	  
		
	  @PostMapping("/menu_update")
	  public ResponseEntity<?> updateMenu(@Valid @RequestBody MenuRequest menuRequest) {
		
		  	Menu menuFind =  menu_ser.buscarMenu(menuRequest.getMenu_id());
		  	if(menuFind == null) {
				mensaje.setCode(404);
				mensaje.setMessage("Error: No se ha encontrado menu en la base de datos!");
		  	}
		  	Menu menu = new Menu(menuRequest.getMenu_id(), menuRequest.getMenu_title(), menuRequest.getMenu_path(), menuRequest.getMenu_icon(), menuRequest.getDescripcion(), menuRequest.getArgumentos(), menuRequest.getTipo_menu());
		    Set<String> strRoles = menuRequest.getRole();
		    
		    Set<Role> roles = new HashSet<>();		

		  	
		  	roles = roleService.setRole(strRoles);
		  	Set<String> strVigencia = menuRequest.getVigencia();
		  	menu.setVigencia(vigenciaService.setVigencia(strVigencia));
		  	
		  	if(roles == null) {
				mensaje.setCode(404);
				mensaje.setMessage("Error: Rol no existe, por favor intentelo de nuevo!");
		  		 return ResponseEntity.ok(mensaje);
		  	}
		  	
		    menu.setRoles(roles);

		    menu_ser.update(menu);
		    
			mensaje.setCode(200);
			mensaje.setMessage("Se ha actualizado el  menu con exito!");
			
		    return ResponseEntity.ok(mensaje);
	  }
	  
	  
	  @PostMapping("/submenu")
	  public ResponseEntity<?> insertSubMenu(@Valid @RequestBody SubMenuRequest sub_menuRequest) {
		/*SubMenu sub_menu = new SubMenu(sub_menuRequest.getSub_menu_icon(),sub_menuRequest.getSub_menu_path(), sub_menuRequest.getSub_menu_title());
		Menu Opt_menu =  menuRepository.findOneById((long) sub_menuRequest.getMenu());
		sub_menu.setMenu(Opt_menu);
		sub_menu_servi.create(sub_menu);*/
		return null;
		  
	  }
	  @PostMapping("/getMenu")
	  public ResponseEntity<?> getMenu(@RequestBody RoleRequest role) {
		  logger_2.info(role);
		  Role rol = roleService.findByNameRole(role.getName());
		  
		   if(rol.getId() == null) {
				mensaje.setCode(404);
				mensaje.setMessage("Error: Rol no existe, por favor intentelo de nuevo!");
		  		 return ResponseEntity.ok(mensaje); 
		   }
		  List<MenuRoleResponse> menuResponse = menu_ser.obtenerMenuRol(rol.getId());
		    return ResponseEntity.ok()
			        .body(menuResponse);
		  
	  }
	  
	  
	  @GetMapping("/getAllMenu")
	  public ResponseEntity<?> getAllMenu() {
		  List<MenuRoleResponse> menuResponse = menu_ser.obtenerMenu();
		    return ResponseEntity.ok()
			        .body(menuResponse);
	  }
	  
	  @GetMapping("/getAllMenuSinFormato")
	  public ResponseEntity<?> getAllMenuSinFormato() {
		  List<Menu> menuResponse = menu_ser.getAllMenu();
		    return ResponseEntity.ok()
			        .body(menuResponse);
	  }
	  

	  
	  @GetMapping("/getMenuId/{id}")
	  public ResponseEntity<?> getMenuId(@PathVariable Long id){
		  MenuRoleResponse menuResponseRole =  menu_ser.search(id);	
		  return ResponseEntity.ok()
					        .body(menuResponseRole);
		  
	  }
	  
	  @GetMapping("/getSubMenuByMenu/{id}")
	  public ResponseEntity<?> getSubMenuByMenu(@PathVariable Long id){
		  List<Menu> menus = menu_ser.getSubMenuByMenu(id);	   
		  return ResponseEntity.ok()
					        .body(menus);
		  
	  }
	  
	  @PostMapping("/eliminarMenu/{id}")
	  public ResponseEntity<?> getMenu(@PathVariable Long id) {
		  
		  Optional<Menu> menusoption = menu_ser.deleteSearch(id);	
		  List<Menu> menus = menu_ser.getSubMenuByMenu(menusoption.get().getMenu_id());
		  logger_2.info(menus.size());
		  if(menus.size() > 0) {
				mensaje.setCode(404);
				mensaje.setMessage("No es posible eliminar menu, debido a que contiene SUBMENUS ASOCIADOS");
			  return ResponseEntity.status(404)
				        .body(mensaje);
		  }else {
			  menu_ser.delete_menu(id);
		  }
		  
			mensaje.setCode(200);
			mensaje.setMessage("¡Menu eliminado con exito!");
			
		  return ResponseEntity.status(200)
			        .body(mensaje);

		  
	  }
	  

}
