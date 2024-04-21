package tp.enistore.dao.jpa.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import tp.enistore.bo.Article;

public interface ArticleMongoRepository extends MongoRepository<Article, String> {

	Article findByUid(String uid);
	
	void deleteByUid(String uid);
}
