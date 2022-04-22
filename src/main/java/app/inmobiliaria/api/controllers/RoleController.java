package app.inmobiliaria.api.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.inmobiliaria.api.entity.Privilegios;
import app.inmobiliaria.api.entity.Role;
import app.inmobiliaria.api.entity.User;
import app.inmobiliaria.api.entity.Vigencia;
import app.inmobiliaria.api.payload.request.PrivilegiosRequest;
import app.inmobiliaria.api.payload.request.RoleRequest;
import app.inmobiliaria.api.payload.response.MessageResponse;
import app.inmobiliaria.api.payload.response.RoleResponse;
import app.inmobiliaria.api.repository.RoleRepository;
import app.inmobiliaria.api.servicios.PrivilegiosService;
import app.inmobiliaria.api.servicios.RoleService;
import app.inmobiliaria.api.servicios.UserService;
import app.inmobiliaria.api.servicios.VigenciaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	RoleService role_service;

	@Autowired
	VigenciaService vigenciaService;

	@Autowired
	PrivilegiosService privilegio_service;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserService userService;

	Set<Vigencia> vigencia = new HashSet<>();

	Logger logger_2 = LogManager.getLogger();

	@PersistenceContext
	EntityManager entityManager;

	MessageResponse mensaje = new MessageResponse();

	@GetMapping("/getRoles")
	public ResponseEntity<?> getRoles() {

		List<RoleResponse> roleResponse = role_service.getAllRole();

		return ResponseEntity.ok()
				.body(roleResponse);

	}

	@PostMapping("createRoles")
	public ResponseEntity<?> crearRoles(@Valid @RequestBody RoleRequest roleRequest) {

		Role id_role = roleRepository.findLast();

		Role role = new Role(id_role.getId() + 1, roleRequest.getName());

		Set<String> strVigencia = roleRequest.getVigencia();

		Vigencia vigencias = vigenciaService.setVigencia(strVigencia);

		role.setVigencia(vigencias);

		logger_2.info(role);
		role_service.create(role);
		
		Privilegios id_privilegios = privilegio_service.findLast();
		
		Privilegios privilegios = new Privilegios();
		
		privilegios.setPrivilegios_id(id_privilegios.getPrivilegios_id()+1);
		privilegios.setActualizar(false);
		privilegios.setCrear(false);
		privilegios.setVer(false);
		privilegios.setRole(role);
		
		privilegio_service.create(privilegios);
		
		mensaje.setCode(200);
		mensaje.setMessage("¡Rol registrado con exito!");
		return ResponseEntity.ok(mensaje);

	}

	@PutMapping("/updateRole/{id}")
	public ResponseEntity<?> updateUsuario(@PathVariable Long id, @Valid @RequestBody RoleRequest roleRequest) {
		logger_2.info(roleRequest);
		Optional<Role> roleList = role_service.search(id);

		if (!roleList.isPresent()) {
			mensaje.setCode(404);
			mensaje.setMessage("¡Error al momento de updatear el rol, no se ha encontrado rol!");
			return ResponseEntity.ok()
					.body(mensaje);
		} else {
			Role roleupdate = new Role(roleList.get().getId(), roleRequest.getName());

			Set<String> strVigencia = roleRequest.getVigencia();

			Vigencia vigencias = vigenciaService.setVigencia(strVigencia);

			if (vigencias == null) {
				mensaje.setCode(404);
				mensaje.setMessage("¡Error al momento de updatear el rol, no se ha encontrado vigencia!");
				return ResponseEntity.ok()
						.body(mensaje);
			}

			roleupdate.setVigencia(vigencias);

			Role role = role_service.update(roleupdate);
			if (role == null) {
				mensaje.setCode(404);
				mensaje.setMessage("¡Error al momento de updatear el rol!");
				return ResponseEntity.ok()
						.body(mensaje);
			}
		}

		mensaje.setCode(200);
		mensaje.setMessage("¡Rol actualizado con exito!");
		return ResponseEntity.ok()
				.body(mensaje);

	}

	@GetMapping("/getRole/{id}")
	public ResponseEntity<?> getRole(@PathVariable Long id) {

		Optional<Role> role = role_service.search(id);

		return ResponseEntity.ok()
				.body(role);
	}

	@PutMapping("/updateRoles")

	public ResponseEntity<?> updateRoles(@Valid @RequestBody List<Object> RoleRequest) {
		logger_2.info(RoleRequest.get(0).getClass());

		RoleRequest.stream().forEach((s) -> {
			Optional<Role> possibleRole = Optional.empty();
			Map<String, Object> map = (Map<String, Object>) s;

			for (String key : map.keySet()) {

				logger_2.info("Key: " + key + " maps to value: " + map.get(key));
				if (key == "id") {
					Integer id = (Integer) map.get(key);
					Long longId = new Long(id);
					possibleRole = role_service.search(longId);
				}

				if (possibleRole.isPresent()) {

					Role role = possibleRole.get();

					if (key == "name") {
						role.setName((String) map.get(key));
					}

					if (key == "vigencia") {
						List<Object> list = (List<Object>) map.get(key);
						Set<Object> items = new HashSet<Object>(list);
						String strvigencia = "";
						for (Object object : list) {
							logger_2.info(object);
							strvigencia = (String) object;
						}

						/* Set<String> strVigencia = (Set<String>) map.get(key); */
						Vigencia vigencias = vigenciaService.searchVigenciaByName(strvigencia);
						role.setVigencia(vigencias);
					}

					role_service.update(role);
					if (key == "privilegios") {
						logger_2.info(map.get(key).getClass());

						Map<String, Object> map_2 = (Map<String, Object>) map.get(key);
						logger_2.info(map_2.get("privilegios_id"));
						Long longId = new Long((Integer) map_2.get("privilegios_id"));
						Optional<Privilegios> possiblePrivilegio = privilegio_service.search(longId);
						if (possibleRole.isPresent()) {
							Privilegios privilegios = possiblePrivilegio.get();
							privilegios.setActualizar((Boolean) map_2.get("actualizar"));
							privilegios.setCrear((Boolean) map_2.get("crear"));
							privilegios.setVer((Boolean) map_2.get("ver"));
							privilegio_service.update(privilegios);
						}

					}

				}

			}

		});

		mensaje.setCode(200);
		mensaje.setMessage("¡Se ha modificado Rol con exito!");
		return ResponseEntity.ok()
				.body(mensaje);
	}

	@DeleteMapping("/eliminarRole/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable Long id) {
		
		
		List<User> listaUsuario = userService.findUsersByRole(id);
		
		if(listaUsuario.size() > 0) {
			mensaje.setCode(404);
			mensaje.setMessage("¡Error: No se puede eliminar el rol, debido a que existen usuarios asociados al rol!");
			return ResponseEntity.ok()
					.body(mensaje);
		}
		
		
		Privilegios privilegios = privilegio_service.findByRole(id);
		
		
		if(privilegios != null) {
			privilegio_service.delete(privilegios);
		}

		
		Optional<Role> role = role_service.search(id);
		
		if(role.isPresent()) {
			Role rol = role.get();
			role_service.delete(rol);
			
		}
		
		mensaje.setCode(200);
		mensaje.setMessage("¡Rol eliminado con exito!");
		
		return ResponseEntity.ok()
				.body(mensaje);
	}

}
