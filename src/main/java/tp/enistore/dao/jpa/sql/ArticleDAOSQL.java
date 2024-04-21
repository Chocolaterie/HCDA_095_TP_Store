package tp.enistore.dao.jpa.sql;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tp.enistore.bo.Article;
import tp.enistore.dao.ArticleDAO;

@Component
@Profile("sql")
public class ArticleDAOSQL implements ArticleDAO {

	@Autowired
	private ArticleSQLRepository repository;

	@Override
	public List<Article> findAll() {
		return (List<Article>) repository.findAll();
	}

	@Override
	public Article findById(String uid) {
		return repository.findByUid(uid).get();
	}

	@Override
	public boolean save(Article article) {
		// Si devrait exister
		if (!article.getUid().isEmpty()) {
			Article foundArticle = findById(article.getUid());
			if (foundArticle != null) {
				// Alimente le vrai id mongo interne pour tout remplacer sur le meme document
				article.setId(foundArticle.getId());
			}
		} else {
			// Création
			article.setUid(UUID.randomUUID().toString());
		}

		// Dans tout les cas on sauvegarde
		if (repository.save(article) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String uid) {
		try {
			repository.deleteByUid(uid);
			return true; // Suppression réussie
		} catch (Exception e) {
			return false; // Échec de la suppression
		}
	}
}