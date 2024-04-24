package tp.enistore.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.enistore.service.AuthenticationService;
import tp.enistore.service.dto.AuthenticationRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class UserRestController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/login")
	public String register(@RequestBody AuthenticationRequest request) {
		return authenticationService.authenticate(request.email, request.password);
	}

	@GetMapping("/check-role")
	public String checkRole() {
		return "A le droit !";
	}

	@GetMapping("/check-role-admin")
	public String checkRoleAdmin() {
		return "Only admin !";
	}

}
