package com.kefet.utility;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kefet.model.Article;
import com.kefet.model.ArticleArticleimage;
import com.kefet.model.Articleshortdescription;
import com.kefet.model.Video;
import com.kefet.service.ArticleshortdescriptionService;
import org.apache.log4j.Logger;

@Component
public class SocialNetworkUtil {

    private final Logger log = Logger.getLogger(this.getClass());



    @Autowired
    ArticleshortdescriptionService articleshortdescriptionService;



    public String generateTwitterOpenGraph(Article article){
            String url = "http://www.kefet.com/readarticle?artid="+article.getId();
            String imgsrc = getFirstImageFromArticleForSocialMediaThumbnail(article);

            String twOpenGraph =
                "<meta name=\"twitter:site\" content=\"@kefetdotcom\" /> "+
                "<meta name=\"twitter:card\" content=\"article\" />"+
                "<meta name=\"twitter:creator\" content=\"@kefetdotcom\" />"+
                "<meta name=\"twitter:image\" content=\""+imgsrc+"\" />"+
                "<meta name=\"twitter:title\" content=\""+article.getArtTitle()+"\" />"+
                "<meta name=\"twitter:url\" content=\""+url+"\" />"+
                "<title>"+article.getArtTitle() +"</title>";

            return twOpenGraph;
    }

    public String generateTwitterOpenGraph(Video video, String vidcat){
            String url = "http://www.kefet.com/videoview?vidurl="+video.getVideoUrlname()+"&vidcat="+vidcat;
            String imgsrc = "../users/"+video.getVideoThumbnail();
            if(video.getVideoLocation().equals("youtube")){
                    imgsrc = video.getVideoThumbnail();
            }


            String twOpenGraph =
                "<meta name=\"twitter:site\" content=\"@kefetdotcom\" /> "+
                "<meta property=\"og:type\" content=\"video.other\" /> "+
                "<meta name=\"twitter:creator\" content=\"@kefetdotcom\" />"+
                "<meta name=\"twitter:image\" content=\""+imgsrc+"0.jpg\" />"+
                "<meta name=\"twitter:title\" content=\""+video.getVideoTitle()+"\" />"+
                "<meta name=\"twitter:url\" content=\""+url+"\" />"+
                "<title>"+video.getVideoTitle()+"</title>";

            return twOpenGraph;
    }



    public String generateFacebookOpenGraph(Article article){

            String url = "www.kefet.com/readarticle?artid="+article.getId();
            String description = getArticleshortdescriptionByArticleId(article);
            String imgsrc = getFirstImageFromArticleForSocialMediaThumbnail(article);


            /*
             * http://stackoverflow.com/questions/12567874/how-do-i-get-my-app-id
             */

            String fbOpenGraph = "<meta name=\"application-name\" content=\"kefet\"/> " +
                "<meta property=\"fb:app_id\" content=\"303123373221789\" /> "+
                "<meta property=\"fb:admins\" content=\"kefetcom\"/> "+
                "<meta property=\"og:title\" content=\""+article.getArtTitle()+"\"/> "+
                "<meta property=\"og:type\" content=\"article\"/> "+
                "<meta property=\"og:url\" content=\""+url+"\"/> "+
                "<meta property=\"og:image\" content=\""+imgsrc+"\"/>"+
                "<meta name=\"keywords\" content=\""+article.getArtTitle()+"\" />"+
                "<meta property=\"og:description\" content=\""+description+"\" />";
            return fbOpenGraph;
    }


    public String generateFacebookOpenGraph(Video video, String vidcat){

            String url = "http://www.kefet.com/videoview?vidurl="+video.getVideoUrlname()+"&vidcat="+vidcat;
            String description = video.getVideoDescrip();
            String imgsrc = "../users/"+video.getVideoThumbnail();
            if(video.getVideoLocation().equals("youtube")){
                    imgsrc = video.getVideoThumbnail();
            }


            String fbOpenGraph = "<meta name=\"application-name\" content=\"kefet\"/> " +
                            "<meta property=\"fb:app_id\" content=\"303123373221789\" /> "+
                            "<meta property=\"fb:admins\" content=\"kefetcom\"/> "+
                            "<meta property=\"og:title\" content=\""+video.getVideoTitle()+"\"/> "+
                            "<meta property=\"og:type\" content=\"video.other\" /> "+
                            "<meta property=\"og:url\" content=\""+url+"\"/> "+
                            "<meta property=\"og:site_name\" content=\"Kefet\"/> "+
                            "<meta property=\"og:image\" content=\""+imgsrc+"0.jpg\"/>"+
                            "<meta name=\"keywords\" content=\""+video.getVideoTitle()+"\" />"+
                            "<meta property=\"og:description\" content=\""+description+"\" />";
            return fbOpenGraph;
    }

    /**
     * This method returns the first image of an article to display for social networking thumbnail.
     * @param article
     * @return image source in String format.
     */
    protected String getFirstImageFromArticleForSocialMediaThumbnail(Article article){
            Iterator<?> iter =  article.getArticleArticleimages().iterator();
            String imagesrc=null;

        if(iter.hasNext()){
           ArticleArticleimage articleArticleimages = (ArticleArticleimage)iter.next();

           System.out.println(articleArticleimages.getArticleimage().getArtImagekeyid());
            imagesrc=articleArticleimages.getArticleimage().getArtImgsrc();
        }

        log.debug("======================================");
        log.debug("================imagesrc=============="+imagesrc);
        log.debug("======================================");
        return imagesrc;
    }

    /**
     * This method will return the description of an article that can be displayed on the specific social networking site.
     * @param article
     * @return article short description. 
     */
    protected String getArticleshortdescriptionByArticleId(Article article){
        Articleshortdescription description = articleshortdescriptionService.getArticleshortdescriptionByArticleId(article);

        if(description != null){
            return description.getArtShortDescrp();
        }else{
            return null;
        }

    }
}
