/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.utility.article;

import com.kefet.model.Article;
import com.kefet.model.ArticleArticleimage;
import com.kefet.model.Articleimage;
import com.kefet.utility.UrlGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
/**
 *
 * @author hardddisk
 */

public class ArticleAndImageProcessing implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(ArticleAndImageProcessing.class);
    
    
    // this method will separete the article from the image 
    // and will return the images under List<String>
    
    public List<Articleimage> getArticleImageList(Article article){
        
        UrlGenerator urlGen = new UrlGenerator();
        String imageKeyIdTemp = urlGen.getNewURL();
        
        List<Articleimage> articleimageList =  new ArrayList<>();
        Document doc = Jsoup.parse(article.getArtContent());
        Elements elements = doc.getElementsByTag("img");
       
                          
        for(int i=0; i< elements.size(); i++){ 
            String artImagekeyid = String.valueOf(i)+imageKeyIdTemp;
                   
            String artImgsrc=elements.get(i).attr("src");
            String artImgalt=elements.get(i).attr("alt");
            String artImgcssclass=elements.get(i).attr("class");
            String artImgcssstyle=elements.get(i).attr("style");
          
            Articleimage articleimage = new Articleimage( artImgsrc,  artImgcssclass,
        			 artImgcssstyle,  artImgalt,  artImagekeyid);
            articleimageList.add(articleimage);
        }
        return articleimageList;
    }
    
    public Article replaceArticleWithArtImagekeyid(Article article, List<Articleimage> articleimageList){
        for(int i=0; i<articleimageList.size(); i++){
        	String replace = "<img src=\""+articleimageList.get(i).getArtImgsrc()+"\""+
         			  "  alt=\""+articleimageList.get(i).getArtImgalt()+"\""+
         			  "  style=\""+articleimageList.get(i).getArtImgcssstyle()+"\""+
         			  "  class=\""+articleimageList.get(i).getArtImgcssclass()+"\""+
         			  " ></img>";
        	
            article.setArtContent(article.getArtContent().replace(replace, articleimageList.get(i).getArtImagekeyid()));  
        }   
        return article;
    } 
    
    // this method will return by combining the article with apropriate image
    public Article getArticleWithImage(Article article, List<String> imageList){       
        if(!article.getArticleArticleimages().isEmpty()){
        List<ArticleArticleimage> artartimage = new ArrayList<ArticleArticleimage>(article.getArticleArticleimages());    
           
            for(int i=0; i<artartimage.size(); i++){       	
            	 String replace = "<img src=\""+artartimage.get(i).getArticleimage().getArtImgsrc()+"\""+
           			  "  alt=\""+artartimage.get(i).getArticleimage().getArtImgalt()+"\""+
           			  "  style=\""+artartimage.get(i).getArticleimage().getArtImgcssstyle()+"\""+
           			  "  class=\""+artartimage.get(i).getArticleimage().getArtImgcssclass()+"\""+
           			  " ></img>";      	 
                article.setArtContent(article.getArtContent().replace(artartimage.get(i).getArticleimage().getArtImagekeyid(),replace));                         
            }
        }
        return article;
    }
}
