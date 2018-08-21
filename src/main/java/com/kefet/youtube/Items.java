/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.youtube;

public class Items
{
    private String id;

    private String etag;

    private ContentDetails contentDetails;

    private String kind;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEtag ()
    {
        return etag;
    }

    public void setEtag (String etag)
    {
        this.etag = etag;
    }

    public ContentDetails getContentDetails ()
    {
        return contentDetails;
    }

    public void setContentDetails (ContentDetails contentDetails)
    {
        this.contentDetails = contentDetails;
    }

    public String getKind ()
    {
        return kind;
    }

    public void setKind (String kind)
    {
        this.kind = kind;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", etag = "+etag+", contentDetails = "+contentDetails+", kind = "+kind+"]";
    }
}
			
			

