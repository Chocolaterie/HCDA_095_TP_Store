package tp.enistore.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import tp.enistore.dao.jpa.mongo.MongoAuthUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private MongoAuthUserDetailService userDetailsService;
    
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
    public void configAuthBuilder(AuthenticationManagerBuilder builder) throws Exception {
        System.out.println(passwordEncoder().encode("admin"));
		builder.userDetailsService(userDetailsService);
    }
	  

	/**
	 * Restriction des URLs selon la connexion utilisateur et leurs rôles
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// Autorisation/habilité sur les url/routes
		http.authorizeHttpRequests((authorize) -> authorize
			.requestMatchers(HttpMethod.DELETE, "/api/v1/articles/**").hasRole("ADMIN")
			.anyRequest().authenticated()
		);

		// Configurer l'authentification de base
		http.httpBasic(Customizer.withDefaults());

		// Désactivé Cross Site Request Forgery
		http.csrf(csrf -> {
			csrf.disable();
		});

		return http.build();
	}
}
