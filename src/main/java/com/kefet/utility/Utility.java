package com.kefet.utility;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class Utility {
	
	private final Logger log = Logger.getLogger(this.getClass());
	  private static final String CLASS_NAME = "Utility ";
	  private String methodNM = null;
	  
	//********************************************************************************* **/
	  
	  public String getTodayDate(){
		  methodNM = CLASS_NAME+".getTodayDate()";
		  log.info("in " + methodNM);
		  
		  Date date = new Date();
		  String dateFormat = "yyyy-MM-dd";
		  SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		  
		   return sdf.format(date).toString();
	  }  
	  
	//********************************************************************************* **/
	  // MM-dd-yyyy
	  public String convertDateFormatForHuman(Date date){
		  methodNM = CLASS_NAME+".convertDateFormatForHuman()";
		  log.info("in " + methodNM);
		  
		  String dateFormat = "MM-dd-yyyy";
		  SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		  
		  return sdf.format(date);
	  }
	   
	 //********************************************************************************* **/
	  // return yyyy-MM-dd
	  public String convertDateFormatForDb(String date){
		  methodNM = CLASS_NAME+".convertDateFormatForDb()";
		  log.info("in " + methodNM);
		  //MM-dd-yyyy
		  String mm = date.substring(0, 2);
		  String dd = date.substring(3, 5);
		  String yyyy = date.substring(6, 10);
		  return yyyy+"-"+mm+"-"+dd;
	  }
	  
	//********************************************************************************* **/
	  
	 public Date fromStringToDateFormat(String dateFormat, String dateVAlue){
		 methodNM = CLASS_NAME+".fromStringToDateFormat()";
		 log.info("in " + methodNM);
		  
		 // example of dateFormat = 'yyyy-MM-dd'
		 // exmaple of dateValue = '1901-01-01'
		 DateFormat df = new SimpleDateFormat(dateFormat);
		 Date date=null;
		 try{
			date = df.parse(dateVAlue);
		 } catch (ParseException e){
			log.error("in " + methodNM +"---ParseException---", e);
		 }
		return date;
	 }
	 
	 
	 
	 public String fromStringToDateFormat(String dateFormat, Date dateVAlue){
		 methodNM = CLASS_NAME+".fromStringToDateFormat()";
		 log.info("in " + methodNM);
		  
		 // example of dateFormat = 'yyyy-MM-dd'
		 // exmaple of dateValue = '1901-01-01'
		 DateFormat df = new SimpleDateFormat(dateFormat);
	
		return df.format(dateVAlue);
	 }
	 
	//********************************************************************************* **/
	
	// this method is created to accommodate empty time stamp for mysql.
	
	 public static Timestamp emptyTimeStamp(){
		 return Timestamp.valueOf("0000-00-00 00:00:00"); 
	 }
	 
	//********************************************************************************* **/
	 
	 public Timestamp getTimeStampForDb(){
		 
		 java.util.Date today = new java.util.Date();
		    return new java.sql.Timestamp(today.getTime());
	 }
	 
	 //******************************************************************************************************************
	 
	 // this method restuns the date difference between todays date and last time the user was logged in.
	 // ex: 0 = today 1= yesterda, 3= three days a go etc... 
	 
	 public long getUsersActivityLogUserFriendly(Date date){
		  methodNM = CLASS_NAME+".getUsersActivityLogUserFriendly()";
		  log.info("in " + methodNM);
		 
		 java.util.Date today = new java.util.Date();
		 return (today.getTime() - date.getTime()) / (1000 * 60 * 60 * 24); 
		
	 }	
	 
	 
	 /**
	  * this method creates a month date with the format:
	  *  2015-1-19
	  *  2015-2-18
	  *  starting as the first date the current date 
	  *  both will be put in Date array the first array will be the beginning of the week
	  *  in this case 2015-1-19
	  *  
	  * @return String of array of date 
	  */
	 public Date[] getForwardMonthDateForDbStartingFromToday(){
		 Date[] theweek = new Date[2];
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
			
			Calendar cal = Calendar.getInstance ();
			int dayofWeek = cal.get (Calendar.DAY_OF_WEEK);

			cal.add (Calendar.DATE, -1 * (dayofWeek - Calendar.MONDAY));
			Date fdow = cal.getTime ();
			//As the cal object is now modified, just add six to get the last day of the week.
			cal.add (Calendar.DATE, 30);
			Date ldow = cal.getTime ();
			
			try {
				theweek[0] = sdf.parse(sdf.format(fdow));
				theweek[1] = sdf.parse(sdf.format(ldow));
			} catch (ParseException e) {
				log.error("###############################################");
				log.error("#   weeklyDateForDb ParseException generated  #");
				log.error("###############################################");
			}
		
		 return theweek;
	 }
	 
	 /**
	  * if today was 2015-1-19 it will return in a array format 
	  * 2014-12-20
	  * 2015-1-19
	  * which means the array 0 will hold 2014-12-20 and array 1 will hold the current date 2015-1-19
	  * it will show 
	  * @return Date array.
	  */
	 public Date[] getBackwardMonthForDbStartingFromToday(){
		 
		 Date[] theMonth = new Date[2];
		 // get today's date
		Calendar cal = Calendar.getInstance ();
		int dayofWeek = cal.get (Calendar.DAY_OF_WEEK);
		cal.add (Calendar.DATE, -1 * (dayofWeek - Calendar.MONDAY));
		Date today = cal.getTime ();
		theMonth[0]=today;
		Calendar date = new GregorianCalendar();
	    date.setTime(today);
	    date.add(Calendar.DAY_OF_MONTH, -30);
	    Date lastMonthDate = date.getTime();
	    theMonth[1]=lastMonthDate;
	    
	    return theMonth;
	 }
	 
	 /**
	  * This method return one week backward 
	  * If today was 2015-07-18 it will return in a array format 
	  * 2015-07-12
	  * 2015-07-18
	  * which means the array 0 will hold 2015-07-12 and array 1 will hold the current date 2015-07-18
	  * 
	  * @return ate array.
	  */
	 public Date[]getBackwardWeekForDbStartingFromToday(){
		 
		 Date[] theWeek = new Date[2];
		 
		// get today's date
		
		Calendar cal = Calendar.getInstance ();
		int dayofWeek = cal.get (Calendar.DAY_OF_WEEK);
		cal.add (Calendar.DATE, dayofWeek -7);
		Date today = cal.getTime ();
		
		theWeek[0]=today;
		
		Calendar date = new GregorianCalendar();
	    date.setTime(today);
	    date.add(Calendar.DAY_OF_WEEK, -7);
	    Date lastWeekDate = date.getTime();
	    
	    theWeek[1]=lastWeekDate;
		 
		 return theWeek;
	 }
	 
	 //****************************************************************************************************************
	 
	 // TODO the code belew need to be tested and does not work.
	 
	 public String convertMillisecondsToHuman(long milliseconds){
		methodNM = CLASS_NAME + ".convertMillisecondsToHuman()";
		log.info("in " + methodNM);
		
		log.debug("convertMillisecondsToHuman()--milliseconds------"+milliseconds);

		String view;

		long time = milliseconds / 1000;
		String seconds = Integer.toString((int) (time % 60));
		String minutes = Integer.toString((int) ((time % 3600) / 60));
		String hours = Integer.toString((int) (time / 3600));
		
		for (int i = 0; i < 2; i++) {
			if (seconds.length() < 2) {
				seconds = "0" + seconds;
			}
			if (minutes.length() < 2) {
				minutes = "0" + minutes;
			}
			if (hours.length() < 2) {
				hours = "0" + hours;
			}
		}
		
		if(hours.equals("00")){
			view= minutes + ":" + seconds;
		}else{
			view=hours + ":" + minutes + ":" + seconds;
		}
		
		log.debug("convertMillisecondsToHuman()--view------"+view);

		return view;
	 }
}