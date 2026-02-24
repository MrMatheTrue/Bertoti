package com.thehecklers.sburrestdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sportsarticles")
public class SportsArticleController {
    private final List<SportsArticle> articles = new ArrayList<>();

    public SportsArticleController() {
        articles.addAll(List.of(
                new SportsArticle("Bola de Futebol", "Futebol", 89.90),
                new SportsArticle("Raquete de Tênis", "Tênis", 299.90),
                new SportsArticle("Tênis de Corrida", "Running", 349.90),
                new SportsArticle("Luvas de Boxe", "Boxe", 199.90),
                new SportsArticle("Bicicleta Mountain Bike", "Ciclismo", 1899.90)
        ));
    }

    @GetMapping
    public Iterable<SportsArticle> getArticles() {
        return articles;
    }

    @GetMapping("/{id}")
    public Optional<SportsArticle> getArticleById(@PathVariable String id) {
        for (SportsArticle article : articles) {
            if (article.getId().equals(id)) {
                return Optional.of(article);
            }
        }
        return Optional.empty();
    }

    @GetMapping("/category/{category}")
    public List<SportsArticle> getArticlesByCategory(@PathVariable String category) {
        List<SportsArticle> result = new ArrayList<>();
        for (SportsArticle article : articles) {
            if (article.getCategory().equalsIgnoreCase(category)) {
                result.add(article);
            }
        }
        return result;
    }

    @PostMapping
    public SportsArticle postArticle(@RequestBody SportsArticle article) {
        articles.add(article);
        return article;
    }

    @PutMapping("/{id}")
    public ResponseEntity<SportsArticle> putArticle(@PathVariable String id,
                                                    @RequestBody SportsArticle article) {
        int articleIndex = -1;

        for (SportsArticle a : articles) {
            if (a.getId().equals(id)) {
                articleIndex = articles.indexOf(a);
                articles.set(articleIndex, article);
            }
        }

        return (articleIndex == -1) ?
                new ResponseEntity<>(postArticle(article), HttpStatus.CREATED) :
                new ResponseEntity<>(article, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable String id) {
        articles.removeIf(a -> a.getId().equals(id));
    }
}