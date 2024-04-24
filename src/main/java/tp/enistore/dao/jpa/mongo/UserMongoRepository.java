package tp.enistore.dao.jpa.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import tp.enistore.bo.User;

public interface UserMongoRepository extends MongoRepository<User, String> {

	User findByEmailAndPassword(String email, String password);
	User findByEmail(String email);
}
