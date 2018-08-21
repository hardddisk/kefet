/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.youtube;

public class ContentDetails
{
    private String dimension;

    private String duration;

    private String licensedContent;

    private String definition;

    private String projection;

    private String caption;

    public String getDimension ()
    {
        return dimension;
    }

    public void setDimension (String dimension)
    {
        this.dimension = dimension;
    }

    public String getDuration ()
    {
        return duration;
    }

    public void setDuration (String duration)
    {
        this.duration = duration;
    }

    public String getLicensedContent ()
    {
        return licensedContent;
    }

    public void setLicensedContent (String licensedContent)
    {
        this.licensedContent = licensedContent;
    }

    public String getDefinition ()
    {
        return definition;
    }

    public void setDefinition (String definition)
    {
        this.definition = definition;
    }

    public String getProjection ()
    {
        return projection;
    }

    public void setProjection (String projection)
    {
        this.projection = projection;
    }

    public String getCaption ()
    {
        return caption;
    }

    public void setCaption (String caption)
    {
        this.caption = caption;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dimension = "+dimension+", duration = "+duration+", licensedContent = "+licensedContent+", definition = "+definition+", projection = "+projection+", caption = "+caption+"]";
    }
}
			
			
