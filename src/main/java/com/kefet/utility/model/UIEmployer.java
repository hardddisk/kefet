/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.utility.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author hardddisk
 */

public class UIEmployer implements java.io.Serializable{

    @Email
    @NotEmpty
    private String empEmail;
    
    @NotEmpty
    private String empPass;
    @NotEmpty
    private String confPassword;
    @NotEmpty
    private String empCompanyName;
    @NotEmpty
    private String empPrimaryPhone;
    @NotEmpty
    private String empContanctName;
    @NotEmpty
    private String captchaval;

  

    /**
     * @return the empContanctName
     */
    public String getEmpContanctName() {
        return empContanctName;
    }

    /**
     * @param empContanctName the empContanctName to set
     */
    public void setEmpContanctName(String empContanctName) {
        this.empContanctName = empContanctName;
    }

    /**
     * @return the empEmail
     */
    public String getEmpEmail() {
        return empEmail;
    }

    /**
     * @param empEmail the empEmail to set
     */
    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    /**
     * @return the empPass
     */
    public String getEmpPass() {
        return empPass;
    }

    /**
     * @param empPass the empPass to set
     */
    public void setEmpPass(String empPass) {
        this.empPass = empPass;
    }

    /**
     * @return the confPassword
     */
    public String getConfPassword() {
        return confPassword;
    }

    /**
     * @param confPassword the confPassword to set
     */
    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    /**
     * @return the empCompanyName
     */
    public String getEmpCompanyName() {
        return empCompanyName;
    }

    /**
     * @param empCompanyName the empCompanyName to set
     */
    public void setEmpCompanyName(String empCompanyName) {
        this.empCompanyName = empCompanyName;
    }

    /**
     * @return the empPrimaryPhone
     */
    public String getEmpPrimaryPhone() {
        return empPrimaryPhone;
    }

    /**
     * @param empPrimaryPhone the empPrimaryPhone to set
     */
    public void setEmpPrimaryPhone(String empPrimaryPhone) {
        this.empPrimaryPhone = empPrimaryPhone;
    }

    /**
     * @return the captchaval
     */
    public String getCaptchaval() {
        return captchaval;
    }

    /**
     * @param captchaval the captchaval to set
     */
    public void setCaptchaval(String captchaval) {
        this.captchaval = captchaval;
    }

}
