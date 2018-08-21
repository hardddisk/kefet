/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.utility.article;

import com.kefet.model.Article;
import com.kefet.model.Articleimage;

/**
 *
 * @author hardddisk
 */
public class Article_ArticleArticleimage {
    
    
    private Article article;
    private Articleimage articleimage;
    
    
    
    public Article_ArticleArticleimage(Article article, Articleimage articleimage){
        this.article=article;
        this.articleimage=articleimage;
    }
    
    /**
     * @return the article
     */
    public Article getArticle() {
        return article;
    }

    /**
     * @param article the article to set
     */
    public void setArticle(Article article) {
        this.article = article;
    }
        
    

    /**
     * @return the articleimage
     */
    public Articleimage getArticleimage() {
        return articleimage;
    }

    /**
     * @param articleimage the articleimage to set
     */
    public void setArticleimage(Articleimage articleimage) {
        this.articleimage = articleimage;
    }
}
