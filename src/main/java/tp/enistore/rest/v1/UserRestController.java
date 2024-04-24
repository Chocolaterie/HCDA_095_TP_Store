package tp.enistore.rest.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UserRestController {

	@GetMapping("/check-role")
	public String checkRole(){
		return "A le droit !";
	}
	
	@GetMapping("/check-role-admin")
	public String checkRoleAdmin(){
		return "Only admin !";
	}
	
}
