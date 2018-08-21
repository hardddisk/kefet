/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.utility.model;

import javax.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author hardddisk
 */
public class UIUserArt {

    @Valid
    private UIArticle article;
    
    private MultipartFile file;
    
    public UIUserArt(){
        this.article = new UIArticle();  
    }

    public UIArticle getArticle() {
        return article;
    }

    public void setArticle(UIArticle article) {
        this.article = article;
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }


}
