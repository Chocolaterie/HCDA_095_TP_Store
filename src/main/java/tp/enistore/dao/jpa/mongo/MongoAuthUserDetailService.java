package tp.enistore.dao.jpa.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tp.enistore.bo.User;

@Service
public class MongoAuthUserDetailService implements UserDetailsService {

	@Autowired
	private UserMongoRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User foundUser = userRepository.findByEmail(username);
		if (foundUser == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return foundUser;
	}

}
