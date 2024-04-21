package tp.enistore.dao.jpa.sql;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tp.enistore.bo.Article;

public interface ArticleSQLRepository extends CrudRepository<Article, String>{

	Optional<Article> findByUid(String uid);
	
	void deleteByUid(String uid);
}
