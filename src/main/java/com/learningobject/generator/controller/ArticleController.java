package com.learningobject.generator.controller;


import com.google.gson.Gson;
import com.learningobject.generator.controller.model.ArticleResponse;
import com.learningobject.generator.controller.model.ArticlesResponse;
import com.learningobject.generator.controller.model.LightArticleResponse;
import com.learningobject.generator.service.ArticleService;
import com.learningobject.generator.service.model.ArticleEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Path("/article")
public class ArticleController {

    private final Gson gsonParser;
    @Inject
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
        this.gsonParser = new Gson();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllArticles(@QueryParam("page") int page) {

        ArticlesResponse articlesResponse = new ArticlesResponse(
                articleService.getAllArticles().stream().map(articleEntity ->
                        new LightArticleResponse(articleEntity.getArticleId(),
                                articleEntity.getTitle(),
                                articleEntity.getKeywords()))
                        .collect(Collectors.toList()),
                10, 1);

        return Response.ok(gsonParser.toJson(articlesResponse)).build();
    }

    @GET
    @Path("/{articleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticleById(@PathParam("articleId") int articleId) {
        ArticleEntity articleEntity = articleService.getArticleById(articleId);
        ArticleResponse articleResponse = new ArticleResponse(articleEntity.getArticleId(),
                articleEntity.getTitle(),
                articleEntity.getKeywords(),
                articleEntity.getScope(),
                articleEntity.getComplexity(),
               articleEntity.getContent());

    return Response.ok(gsonParser.toJson(articleResponse)).build();
    }



}
