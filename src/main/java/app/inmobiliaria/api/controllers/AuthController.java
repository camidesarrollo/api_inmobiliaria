package app.inmobiliaria.api.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import app.inmobiliaria.api.payload.request.LoginRequest;
import app.inmobiliaria.api.payload.request.SignupRequest;
import app.inmobiliaria.api.payload.response.MessageResponse;
import app.inmobiliaria.api.payload.response.UserInfoDataTableResponse;
import app.inmobiliaria.api.payload.response.UserInfoResponse;
import app.inmobiliaria.api.repository.RoleRepository;
import app.inmobiliaria.api.repository.UserRepository;
import app.inmobiliaria.api.repository.VigenciaRepository;
import app.inmobiliaria.api.seguridad.jwt.JwtUtils;
import app.inmobiliaria.api.servicios.PrivilegiosService;
import app.inmobiliaria.api.servicios.RoleService;
import app.inmobiliaria.api.servicios.UserDetailsImpl;
import app.inmobiliaria.api.servicios.UserDetailsServiceImpl;
import app.inmobiliaria.api.servicios.UserService;
import app.inmobiliaria.api.servicios.VigenciaService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	  @Autowired
	  AuthenticationManager authenticationManager;

	  @Autowired
	  UserRepository userRepository;

	  @Autowired
	  RoleRepository roleRepository;
	  
	  @Autowired 
	  VigenciaRepository vigenciaRepository;

		@Autowired
		private PrivilegiosService privilegioService;
		
	  @Autowired
	  PasswordEncoder encoder;

	  @Autowired
	  JwtUtils jwtUtils;
	  
	
	  @Autowired
	  RoleService roleService;
	  
	  @Autowired
	  VigenciaService vigenciaService;
	  
	  @Autowired
	  UserService userService; 
	  
	  @Autowired
	  UserDetailsServiceImpl userDetailsService;
	  
	  Logger logger_2 = LogManager.getLogger();
	  
	  @PersistenceContext
		EntityManager entityManager;
	  
	   ResponseCookie jwtCookie;
	
		MessageResponse mensaje = new MessageResponse ();
		
	  @PostMapping("/signin")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		 User user = userRepository.findByEmail(loginRequest.getEmail());
		 
		 if(user == null) {
				mensaje.setCode(404);
				mensaje.setMessage("Error: Usuario no existe!");
				
			  return ResponseEntity.ok()
				        .body(mensaje);
		 }
		 logger_2.info(user);
		 
	    Authentication authentication = authenticationManager
	        .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);

	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

	   jwtCookie = jwtUtils.generateJwtCookie(userDetails);

	    List<String> roles = userDetails.getAuthorities().stream()
	        .map(item -> item.getAuthority())
	        .collect(Collectors.toList());
	    
	    Role role = roleService.findByNameRole(roles.get(0));
	    
	    Privilegios privilegio = privilegioService.findByRole(role.getId());
	    
	    
	    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
	        .body(new UserInfoResponse(userDetails.getId(),
	                                   userDetails.getUsername(),
	                                   userDetails.getEmail(),
	                                   roles,
	                                   jwtCookie.toString(),
	                                   privilegio));
	  }

	  @PostMapping("/signup")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	    	mensaje.setCode(404);
			mensaje.setMessage("Error: El nombre de usuario ya está ocupado!");
	      return ResponseEntity.ok().body(mensaje);
	    }

	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	    	mensaje.setCode(404);
			mensaje.setMessage("Error: ¡El correo electrónico ya está en uso!");
	      return ResponseEntity.ok().body(mensaje);
	    }

	    // Create new user's account
	    User user = new User(signUpRequest.getUsername(),
	                         signUpRequest.getEmail(),
	                         encoder.encode(signUpRequest.getPassword()),
	                         signUpRequest.getTelefono(),
	                         signUpRequest.getRut(),
	                         signUpRequest.getDv(),
	                         signUpRequest.getAp_paterno(),
	                         signUpRequest.getAp_materno(),
	                         signUpRequest.getNombres());

	    Set<String> strRoles = signUpRequest.getRole();
	    Set<String> strVigencia = signUpRequest.getVigencia();
	    
	    Set<Vigencia> vigencia = new HashSet<>();
	    
	  	Set<Role> roles = roleService.setRole(strRoles);
	  	Vigencia  vigencias = vigenciaService.setVigencia(strVigencia);
	  	
	    logger_2.info("Vigencia");
	    logger_2.info(vigencias);
	  	
	  	if(roles == null) {
	    	mensaje.setCode(404);
			mensaje.setMessage("Error: ¡Rol no existe, por favor intentelo de nuevo!");
	  		 return ResponseEntity.ok().body(mensaje);
	  	}
	  	logger_2.info(roles);
	    user.setRoles(roles);
	    user.setVigencia((Vigencia) vigencias);
	    logger_2.info("USER");
	    logger_2.info(user);
	    userRepository.save(user);
	    
		mensaje.setCode(200);
		mensaje.setMessage("¡Usuario registrado con éxito!");

	    return ResponseEntity.ok(mensaje);
	  }

	  @PostMapping("/signout")
	  public ResponseEntity<?> logoutUser() {
	    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		mensaje.setCode(200);
		mensaje.setMessage("¡Ha cerrado sesión con éxito!");
	    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
	        .body(mensaje);
	  }

	  @GetMapping("/getUsuarios")
	  public ResponseEntity<?> getUsuarios(){
		  List<User> getUsuarios = userRepository.findAll(); 
		 return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
			        .body(getUsuarios);
		  
	  }
	  
	  @GetMapping("/getUsuario/{id}")
	  public ResponseEntity<?>GetUsuario(@PathVariable Long id){
		  Optional<User> usuario = userRepository.findById(id);
		  String rutFor = usuario.get().getRut() + "-" + usuario.get().getDv();
		  UserInfoDataTableResponse usuariosData = new UserInfoDataTableResponse(id, usuario.get().getUsername(), usuario.get().getAp_paterno(), usuario.get().getAp_materno(), usuario.get().getEmail(),
				  usuario.get().getPassword(), usuario.get().getRut(), usuario.get().getDv(), usuario.get().getTelefono(), usuario.get().getVigencia(), usuario.get().getRoles(), rutFor, usuario.get().getNombres());
		  logger_2.info("INFORMACION DE USUARIO");
		  logger_2.info(usuariosData);
		  return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,  jwtCookie.toString())
			        .body(usuariosData);
		  
	  }


	  @PutMapping("/updateUsuario/{id}")
	  public ResponseEntity<?>updateUsuario(@PathVariable Long id, @Valid @RequestBody  SignupRequest signUpRequest){
		  Optional<User> usList = userService.search(id);
			
	        if (!usList.isPresent()) {
		    	mensaje.setCode(404);
				mensaje.setMessage("¡Error al momento de updatear el usuario!");
	        	 return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,  jwtCookie.toString())
	 			        .body(mensaje);
	        }else {
	        	 logger_2.info(signUpRequest);
	        	  User user = new User();
	        	  user.setAp_paterno(signUpRequest.getAp_paterno());
	        	  user.setAp_materno(signUpRequest.getAp_materno());
	        	  user.setDv(signUpRequest.getDv());
	        	  user.setRut(signUpRequest.getRut());
	        	  user.setUsername(signUpRequest.getUsername());
	        	  user.setEmail(signUpRequest.getEmail());
	        	  user.setTelefono(signUpRequest.getTelefono());
	        	  user.setId(id);
		    	 
	        	  if(signUpRequest.getUsername() != null) {
	        		  user.setUsername(signUpRequest.getUsername());
	        	  }else {
	        		  user.setUsername(usList.get().getUsername());
	        	  }
	        	Set<String> strRoles = signUpRequest.getRole();
	       	    Set<String> strVigencia = signUpRequest.getVigencia();
	       	    
	       	  	Set<Role> roles = roleService.setRole(strRoles);
	       	  	Vigencia  vigencias = vigenciaService.setVigencia(strVigencia);
	       	 
	       	  	user.setRoles(roles);
	    	    user.setVigencia((Vigencia) vigencias);
	    	    user.setPassword(usList.get().getPassword());
	    	    user.setNombres(usList.get().getNombres());
	       	  	User usuario = userService.update(user);
	        	
	             if(usuario == null) {
	 		    	mensaje.setCode(404);
					mensaje.setMessage("¡Error al momento de updatear el usuario!");
	            	 return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,  jwtCookie.toString())
	 	 			        .body(mensaje);
	             }
	        }
			mensaje.setCode(200);
			mensaje.setMessage("¡Usuario actualizado con exito!");
		  return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,  jwtCookie.toString())
			        .body(mensaje);
		  
	  }
	  
	  @DeleteMapping("/eliminarUsuario/{id}")
	  public ResponseEntity<?>EliminarUsuario(@Valid @PathVariable Long id){
		  User usuario = userRepository.getById(id);
		  userService.delete(usuario);
			mensaje.setCode(200);
			mensaje.setMessage("¡Usuario eliminado con exito!");
		  return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,  jwtCookie.toString())
			        .body(mensaje);
		  
	  }
	  
	  
}
