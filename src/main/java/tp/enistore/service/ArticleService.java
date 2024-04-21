package tp.enistore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tp.enistore.bo.Article;
import tp.enistore.dao.ArticleDAO;

@Service
public class ArticleService {

	@Autowired
	public ArticleDAO articleDAO;
	
	public List<Article> getArticles(){
		return articleDAO.findAll();
	}
	
	public Article getArticleById(String uid){
		return articleDAO.findById(uid);
	}

	public boolean save(Article article) {
		return articleDAO.save(article);
	}

	public Boolean remove(String uid) {
		return articleDAO.delete(uid);
	}
}
