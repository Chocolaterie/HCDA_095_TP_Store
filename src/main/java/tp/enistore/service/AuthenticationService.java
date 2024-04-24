package tp.enistore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import tp.enistore.bo.User;
import tp.enistore.dao.jpa.mongo.UserMongoRepository;
import tp.enistore.security.jwt.JwtService;

@Service
public class AuthenticationService {
	
	@Autowired
	private UserMongoRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;

	public String authenticate(String email, String password) {
		// --
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		
		// Récupérer l'user en base
		User user = userRepository.findByEmail(email);
				
		// Générer le token avec toute les infos du user à l'interieur
		String jwtToken = jwtService.generateToken(user);
		
		return jwtToken;
	}
}
