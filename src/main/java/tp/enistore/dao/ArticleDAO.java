package tp.enistore.dao;

import java.util.List;

import tp.enistore.bo.Article;

public interface ArticleDAO {

	public List<Article> findAll();

	public Article findById(String uid);

	public boolean save(Article article);
	
	public boolean delete(String uid);
}
