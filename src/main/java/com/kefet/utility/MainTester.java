package com.kefet.utility;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

import com.kefet.utility.YouTubeAPIV3Reader;
import java.sql.Timestamp;
import java.util.Calendar;



public class MainTester {
    /*
        public static void main(String[] args){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, 30);
        
        
        long now = System.currentTimeMillis();
        long adayOld = now - (40L *60L * 60L * 1000L);
        Timestamp adayOldTimestamp = new Timestamp(adayOld);         
    }
	
	
	//public static void main(String[] args) throws IOException, SQLException{
		/*
		
		StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
		standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
		standardPBEStringEncryptor.setPassword("jwrbhx3EWxCvKyRp9xaTewnNVXLfZB7dcn7Q4b4t");
		String encodedPass = standardPBEStringEncryptor.encrypt("jwrbhx3EWxCvKyRp");
		System.out.println("Encrypted Password for admin is : "+encodedPass);
		String decPass= standardPBEStringEncryptor.decrypt(encodedPass);
		System.out.println("Decrypted Password for admin is : "+decPass);

		
		CREATE USER 'kefetResponsive'@'localhost' IDENTIFIED BY 'jwrbhx3EWxCvKyRp';
		 
            CREATE USER 'kefetResponsive'@'localhost' IDENTIFIED BY 'jwrbhx3EWxCvKyRp9xaTewnNVXLfZB7dcn7Q4b4t';
            
		GRANT SELECT,INSERT,UPDATE,DELETE ON kefet3.* TO 'kefetResponsive'@'localhost';

		UPDATE mysql.user SET Password=PASSWORD('Dischetto16111!') WHERE User='root';
    
		FLUSH PRIVILEGES;
		

    CREATE USER 'kefetResponsive'@'localhost' IDENTIFIED BY 'kuJob_12341!';
    
    SET PASSWORD FOR 'kefetResponsive'@'localhost' = PASSWORD('jwrbhx3EWxCvKyRp');
    

		int joke =2;
		int poet = 1;
		int essay =3;
		
		int[] idValue = {joke, poet, essay};
		Arrays.sort(idValue);
		
		System.out.println(idValue[0]);
		
		
		
                
            String azad = "http://localhost:8080/imgs?imgval=articleImages/6/567_6zYSdiS.jpg";

            System.out.println(azad.substring(azad.lastIndexOf("/"), azad.length()));
        

	     	String url = "jdbc:mysql://localhost:3306/kefet3?useUnicode=true&characterEncoding=utf8";
String username = "root";
String password = "Dischetto16111!";

System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
            
            
            
            String selectionQuery="SELECT ID, VIDEO_TITLE, VIDEO_DESCRIP FROM VIDEO";
            
            
            PreparedStatement preparedStatementSelect = connection.prepareStatement(selectionQuery);
            
            // execute select SQL stetement
	    ResultSet rs = preparedStatementSelect.executeQuery();
            
            while (rs.next()) {

                Integer ID = rs.getInt("ID");
                String VIDEO_TITLE = rs.getString("VIDEO_TITLE");
                VIDEO_TITLE = VIDEO_TITLE.replaceAll("\"","\'");
                
                String VIDEO_DESCRIP = rs.getString("VIDEO_DESCRIP");
                VIDEO_DESCRIP = VIDEO_DESCRIP.replaceAll("\"","\'");

                System.out.println("ID : " + ID);
                System.out.println("VIDEO_DESCRIP : " + VIDEO_DESCRIP);
                
                String query="INSERT INTO VIDEOTITLEANDDESCRIP(VIDEO_ID, VIDEO_TITLE,VIDEO_DESCRIP, VIDEO_LANG) VALUES(\""+ID+"\", \""+VIDEO_TITLE+"\", \""+VIDEO_DESCRIP+"\", \"A\")";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
            
                preparedStatement.executeUpdate();
            }
  
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
	
	   }

    
    
    
    
    
    
    
    */
    
    
    public static void main(String[] args){
    
        
                    
            String azad = "http://localhost:8080/imgs?imgval=articleImages/6/567_6zYSdiS.jpg";

            System.out.println(azad.substring(azad.lastIndexOf("/"), azad.length()));

	     	String url = "jdbc:mysql://localhost:3306/kefet3?useUnicode=true&characterEncoding=utf8";
String username = "root";
String password = "Azad1611@";

System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
            
            
            
            String selectionQuery="SELECT ID, VIDEO_THUMBNAIL FROM VIDEO";
            
            
            PreparedStatement preparedStatementSelect = connection.prepareStatement(selectionQuery);
            
            // execute select SQL stetement
	    ResultSet rs = preparedStatementSelect.executeQuery();
            
            
            
            while (rs.next()) {

                Integer ID = rs.getInt("ID");
                
                
                String VIDEO_THUMBNAIL = rs.getString("VIDEO_THUMBNAIL");
                
                
                if(VIDEO_THUMBNAIL.contains("youtube")){
                    System.out.println(ID+"=="+VIDEO_THUMBNAIL);
                    VIDEO_THUMBNAIL = VIDEO_THUMBNAIL.replace("0.jpg", "");
                    
                    String query = "update VIDEO SET VIDEO_THUMBNAIL= '"+VIDEO_THUMBNAIL+"' WHERE ID = "+ID;
                    System.out.println(query);
                
               
               PreparedStatement preparedStatement = connection.prepareStatement(query);
            
                preparedStatement.executeUpdate();
                }
                
            }
  
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        
        
    }
    
    
    
    
    

}


