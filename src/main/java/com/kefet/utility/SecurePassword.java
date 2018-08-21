/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.utility;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author hardddisk
 */
public class SecurePassword {
     
    private String userPassword;

    /**
     * @return the userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword the userPassword to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    /**
     * 
     * @param userPassword
     * @return encoding  byte array into base 64
     */
    public byte[] encodeBase64(String userPassword){
        return  Base64.encodeBase64(userPassword.getBytes());  
    }
    
    /**
     * 
     * @param encoded
     * @return decoding byte array into base64
     */
    
    public byte[] decodeBase64(byte[] encoded){
        return Base64.decodeBase64(encoded);
    }
    
}
