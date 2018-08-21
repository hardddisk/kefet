package com.kefet.utility.captcha;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

import org.apache.log4j.Logger;

//import com.kefet.model.videoServer.VideoServerServletInUse;

import java.awt.geom.*;

public class CaptchaServlet extends HttpServlet{
	
	private static Logger log = Logger.getLogger(CaptchaServlet.class);
	private static final String CLASS_NAME = "CaptchaServlet ";
	private String methodNM = null;
	
	private static final long serialVersionUID = 1L;
	private int height=0;
	private int width=0;
	private int circlesToDraw = 0;
	private int charsToPrint=0;
		
	 //float horizMargin = 20.0f;
	 //float imageQuality = 0.95f; // max is 1.0 (this is for jpeg)
	 //private double rotationRange = 0.7; // this is radians	
	
	
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException
	{
		methodNM = CLASS_NAME+".doGet()";
		log.info("in " + methodNM);
		
		 this.height = Integer.parseInt(getServletConfig().getInitParameter("height"));
		 this.width=Integer.parseInt(getServletConfig().getInitParameter("width"));
		 this.circlesToDraw=Integer.parseInt(getServletConfig().getInitParameter("circlesToDraw"));
		 this.charsToPrint=Integer.parseInt(getServletConfig().getInitParameter("charsToPrint"));
		    
		
		 HttpSession session = request.getSession();
		
		try{
			   Color textColor = new Color(000,000,100);
			   Color circleColor = new Color(160,160,160);
			   Font textFont = new Font("Arial", Font.PLAIN, 24);
			 
			   float horizMargin = 20.0f;
			   //float imageQuality = 0.95f; // max is 1.0 (this is for jpeg)
			   double rotationRange = 0.7; // this is radians			
					
			   ServletOutputStream sos = response.getOutputStream();
			   BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
			   
			   Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
			   //Draw an oval
			   g.setColor(new Color(150,100,160));
			   g.fillRect(0, 0, width, height);

			   // lets make some noisey circles
			   g.setColor(circleColor);
			   for ( int i = 0; i < circlesToDraw; i++ ) {
			     int circleRadius = (int) (Math.random() * height / 2.0);
			     int circleX = (int) (Math.random() * width - circleRadius);
			     int circleY = (int) (Math.random() * height - circleRadius);
			     g.drawOval(circleX, circleY, circleRadius * 2, circleRadius * 2);
			     int x = (int) (Math.random() * width /2.0);
			     int y = (int) (Math.random() * height /2.0);
			     int startAngle = 50;
			     int arcAngle= 500;
			     g.drawArc(x,y,width,height,startAngle,arcAngle);
			   }

			   g.setColor(textColor);
			   g.setFont(textFont);
			   
			   FontMetrics fontMetrics = g.getFontMetrics();
			   int maxAdvance = fontMetrics.getMaxAdvance();
			   int fontHeight = fontMetrics.getHeight();

			   // i removed 1 and l and i because there are confusing to users...
			   // Z, z, and N also get confusing when rotated
			   // 0, O, and o are also confusing...
			   // lowercase G looks a lot like a 9 so i killed it
			   // this should ideally be done for every language...
			   // i like controlling the characters though because it helps prevent confusion
			   String elegibleChars = "ABCDEFGHJKLMPQRSTUVWXYabcdefhjkmnpqrstuvwxy23456789";
			   char[] chars = elegibleChars.toCharArray();

			   float spaceForLetters = -horizMargin * 2 + width;
			   float spacePerChar = spaceForLetters / (charsToPrint - 1.0f);

			 //  AffineTransform transform = g.getTransform();

			   StringBuffer finalString = new StringBuffer();

			   
			   
			   for ( int i = 0; i < charsToPrint; i++ ) {
				     double randomValue = Math.random();
				     int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
				     char characterToShow = chars[randomIndex];
				     finalString.append(characterToShow);
				     
				     session.setAttribute("captchaString", finalString.toString());
				     

				     // this is a separate canvas used for the character so that
				     // we can rotate it independently
				    // int charImageWidth = maxAdvance * 2;
				    // int charImageHeight = fontHeight * 2;
				     int charWidth = fontMetrics.charWidth(characterToShow);
				     int charDim = Math.max(maxAdvance, fontHeight);
				     int halfCharDim = (int) (charDim / 2);

				     BufferedImage charImage = new BufferedImage(charDim, charDim, BufferedImage.TYPE_INT_ARGB);
				     Graphics2D charGraphics = charImage.createGraphics();
				     charGraphics.translate(halfCharDim, halfCharDim);
				     double angle = (Math.random() - 0.5) * rotationRange;
				     charGraphics.transform(AffineTransform.getRotateInstance(angle));
				     charGraphics.translate(-halfCharDim,-halfCharDim);
				     charGraphics.setColor(textColor);
				     charGraphics.setFont(textFont);

				     int charX = (int) (0.5 * charDim - 0.5 * charWidth);
				     charGraphics.drawString("" + characterToShow, charX, 
				                            (int) ((charDim - fontMetrics.getAscent()) 
				                                   / 2 + fontMetrics.getAscent()));

				     float x = horizMargin + spacePerChar * (i) - charDim / 2.0f;
				     int y = (int) ((height - charDim) / 2);
				//System.out.println("x=" + x + " height=" + height + " charDim=" + charDim + " y=" + y + " advance=" + maxAdvance + " fontHeight=" + fontHeight + " ascent=" + fontMetrics.getAscent());
				     g.drawImage(charImage, (int) x, y, charDim, charDim, null, null);

				     charGraphics.dispose();
				   }
			   
			   response.setContentType("Image/png");
			   ImageIO.write(bufferedImage,"png",sos);
		}catch (IOException ioe) {
			log.error("in..."+methodNM+"---IOException---"+ ioe);
		 }
	}
}


