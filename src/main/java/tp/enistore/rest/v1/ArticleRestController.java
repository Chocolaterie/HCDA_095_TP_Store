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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import tp.enistore.bo.Article;
import tp.enistore.service.ArticleService;

@RestController
@RequestMapping("/api/v1/articles")
@CrossOrigin
@Tag(name = "Article API", description = "Les points d'entrée de mes API liées aux articles")
public class ArticleRestController {

	@Autowired
	public ArticleService articleService;
	
	@GetMapping("/all")
	@Operation(summary = "Récupérer tout les articles")
	public List<Article> getArticles(){
		return articleService.getArticles();
	}
	
	@GetMapping("/get/{uid}")
	@Operation(summary = "Récupérer un article pas son ID")
	public Article getArticle(
			@Parameter(description = "ID de l'article", required = true)
			@PathVariable("uid") String uid){
		return articleService.getArticleById(uid);
	}
	
	@PostMapping("/save")
	@Operation(summary = "Enregistrer un article. Si id existant alors c'est une modification sinon une création")
	public Boolean saveArticle(@RequestBody Article article){
		return articleService.save(article);
	}
	
	@DeleteMapping("/{uid}")
	@Operation(summary = "Supprimer un article via l'ID renseigner dans l'url")
	public Boolean deleteArticle(
			@Parameter(description = "ID de l'article à supprimer", required = true)
			@PathVariable("uid") String uid){
    	return false;
    	//return articleService.remove(uid);
	}
}
