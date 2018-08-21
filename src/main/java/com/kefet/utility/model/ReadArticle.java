/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.utility.model;

import com.kefet.model.Article;
import com.kefet.model.Users;
import com.kefet.model.Facebookdata;


/**
 *
 * @author hardddisk
 */


public class ReadArticle {
    
  
    private Users users;
    private Article article;
    private Facebookdata facebookdata;

    /**
     * @return the users
     */
    public Users getUsesr() {
        return users;
    }

    /**
     * @param user the user to set
     */
    public void setUsers(Users users) {
        this.users = users;
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
     * @return the facebookdata
     */
    public Facebookdata getFacebookdata() {
        return facebookdata;
    }

    /**
     * @param facebookdata the facebookdata to set
     */
    public void setFacebookdata(Facebookdata facebookdata) {
        this.facebookdata = facebookdata;
    }

    
}
