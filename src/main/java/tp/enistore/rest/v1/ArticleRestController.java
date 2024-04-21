package tp.enistore.rest.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.enistore.bo.Article;
import tp.enistore.service.ArticleService;

@RestController
@RequestMapping("/api/v1/articles")
@CrossOrigin
public class ArticleRestController {

	@Autowired
	public ArticleService articleService;
	
	@GetMapping("/all")
	public List<Article> getArticles(){
		return articleService.getArticles();
	}
	
	@GetMapping("/get/{uid}")
	public Article getArticle(@PathVariable("uid") String uid){
		return articleService.getArticleById(uid);
	}
	
	@PostMapping("/save")
	public Boolean saveArticle(@RequestBody Article article){
		return articleService.save(article);
	}
	
	@DeleteMapping("/{uid}")
	public Boolean deleteArticle(@PathVariable("uid") String uid){
		return articleService.remove(uid);
	}
}
