package tp.enistore.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfigMemory {
	protected final Log logger = LogFactory.getLog(getClass());

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Faux users (Sans BDD)
	 * @return
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withUsername("user").password(passwordEncoder().encode("password")).roles("USER")
				.build();

		UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("admin"))
				.roles("USER", "ADMIN").build();

		return new InMemoryUserDetailsManager(user, admin);
	}

	/**
	 * Restriction des URLs selon la connexion utilisateur et leurs rôles
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// Autorisation/habilité sur les url/routes
		http.authorizeHttpRequests((authorize) -> authorize
			.requestMatchers("/api/v1/auth/check-role-admin").hasRole("ADMIN")
			.anyRequest().authenticated()
		);

		// Configurer l'authentification de base
		http.httpBasic(Customizer.withDefaults());

		// Désactivé Cross Site Request Forgery
		// Non préconisé pour les API REST en stateless.
		// Sauf pour POST, PUT, PATCH et DELETE
		http.csrf(csrf -> {
			csrf.disable();
		});

		return http.build();
	}
}
