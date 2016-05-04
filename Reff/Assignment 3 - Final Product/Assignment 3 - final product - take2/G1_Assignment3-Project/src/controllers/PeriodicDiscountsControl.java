package controllers;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;

import entities.Car;
import entities.Order;
import entities.PeriodicDiscount;
import entities.PeriodicDiscountTemplate;

/**
 * This Class provides methods to manage PeriodicDiscounts and their templates.
 * 
 * Manages: {@code PeriodicDiscountTemplate}, {@code PeriodicDiscount}
 * Active:  {@code PeriodicDiscountTemplate}
 * Active is used to efficiently access a specific object that is being edited.
 */
public class PeriodicDiscountsControl {
	
	static List<PeriodicDiscountTemplate> periodicDiscountTemplates;
	static List<PeriodicDiscount> periodicDiscounts;
	
	static PeriodicDiscountTemplate active;
	
	/**
	 * signifies that this {@code PeriodicDiscountTemplate} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static PeriodicDiscountTemplate dummyPeriodicDiscountTemplate = null;
	/**
	 * signifies that this {@code PeriodicDiscount} reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static PeriodicDiscount dummyPeriodicDiscount = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl ()
	{
		periodicDiscountTemplates = new LinkedList<PeriodicDiscountTemplate>();
		periodicDiscounts = new LinkedList <PeriodicDiscount> ();
		
		//dummyPeriodicDiscountTemplate = new PeriodicDiscountTemplate();
		//dummyPeriodicDiscount = new PeriodicDiscount();
	}

	/**
	 * Turns a date into a numeric value, numeric values bigger than the others are dates later than the others.
	 * The date must be of the following format: "yyyy-mm-dd"
	 * 
	 * @param date The date to be converted.
	 * @return {@code long} numericVal after conversion.
	 */
	public static long getDateNumericValue (String date)
	{
		String year,month,day;
		
		year = date.substring(0,4);
		month = date.substring(5,7);
		day = date.substring(8,10);
		
		return (Integer.parseInt(year)*10000 + Integer.parseInt(month)*100 + Integer.parseInt(day));
	}

	/**
	 * Turns a time into a numeric value, numeric values bigger than the others are times later than the others.
	 * The time must be of the following format: "hh:mm:ss"
	 * 
	 * @param time The time to be converted.
	 * @return {@code long} numericVal after conversion.
	 */
	public static long getTimeNumericValue (String time)
	{
		String hour, minute, second;
		
		hour = time.substring(0,2);
		minute = time.substring(3,5);
		second = time.substring(6,8);
		
		return (Integer.parseInt(hour)*10000+Integer.parseInt(minute)*100 + Integer.parseInt(second));
	}
	
	/**
	 * Turns a dateTime into a numeric value, numeric values bigger than the others are dateTimes later than the others.
	 * The dateTime must be of the following format: "yyyy-mm-dd hh:mm:ss"
	 * 
	 * @param dateTime The dateTime to be converted.
	 * @return {@code long} numericVal after conversion.
	 */
	public static long getDateTimeNumericValue (String dateTime)
	{
		String date, time;
		date = dateTime.substring(0,10);
		time = dateTime.substring(11,19);
		
		return (getDateNumericValue(date) + getTimeNumericValue(time));
	}
	
	/**
	 * Checks if the given String is a valid time.
	 * @param time The time to be checked
	 * @return boolean isTimeValid
	 */
	public static boolean checkTime (String time)
	{
		char seperator;
		int hour, minute, second;
		try {
			if (time.length() != 8)
				return false;
			seperator = time.charAt(2);
			if (seperator != ':' && seperator != '-')
				return false;
			seperator = time.charAt(5);
			if ((seperator != ':' && seperator != '-') || seperator != time.charAt(2))
				return false;
			hour = Integer.parseInt(time.substring(0,2));
			if (hour < 0 || hour > 23)
				return false;
			minute = Integer.parseInt(time.substring(3,5));
			if (minute < 0 || minute > 59)
				return false;
			second = Integer.parseInt(time.substring(6,8));
			if (second < 0 || second > 59)
				return false;
		}
		catch (Exception ex)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if the given String is a valid date
	 * @param date The date to be checked.
	 * @return boolean isDateValid
	 */
	public static boolean checkDate (String date)
	{
		char seperator;
		int year, month ,day;
		try {
			if (date.length() != 10)
				return false;
			seperator = date.charAt(4);
			if (seperator != '-' && seperator != '/' && seperator != '\\' && seperator != '.')
				return false;
			seperator = date.charAt(7);
			if ((seperator != '-' && seperator != '/' && seperator != '\\' && seperator != '.')|| seperator != date.charAt(4))
				return false;
			year = Integer.parseInt(date.substring(0,4));
			if (year < 0)
				return false;
			month = Integer.parseInt(date.substring(5,7));
			if (month < 1 || month > 12)
				return false;
			day = Integer.parseInt(date.substring(8,10));
			Calendar cr = Calendar.getInstance();
			Calendar mycal = new GregorianCalendar(year,month-1,1);
			int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;
			if (day < 1 || day > daysInMonth)
				return false;
		}
		catch (Exception ex)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Adds the given number of hours to the given dateTime
	 * @return String newDateTime
	 */
	public static String addHour (String dateTime, int hours)
	{
		int sec,min,hour,day,month,year;
		long time,date;
		date = getDateNumericValue(dateTime.substring(0,10));
		time = getTimeNumericValue(dateTime.substring(11,19));
		
		sec =(int)time % 100;
		min = (int) (time/100)%100;
		hour = (int)time/10000;
		day = (int) date%100;
		month = (int) (date/100)%100;
		year = (int) date / 10000;
		
		hour += hours;
		if (hour > 23)
		{
			day++;
			hour = hour % 24;
			Calendar mycal = new GregorianCalendar(year, month-1 ,1);
			int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
			if (day > daysInMonth)
			{
				day = day - daysInMonth;
				month++;
				if (month > 12)
				{
					month = month-12;
					year++;
				}
			}
		}
		String res = String.format("%4d-%2d-%2dX%2d:%2d:%2d",year,month,day,hour,min,sec);
		res = res.replace(' ','0');
		res = res.replace('X',' ');
		return res;
	}
	
	/**
	 * returns the number of secs left until the given date. Both dates can only be a month apart.
	 * @param dateTime the date from which we count hours.
	 * @return long hours left
	 */
	public static long getSecsLeft (String dateTime)
	{
		int year,month,day,hour,min,sec;
		int cYear,cMonth,cDay,cHour,cMin,cSec;
		
		year = Integer.parseInt(dateTime.substring(0,4));
		month = Integer.parseInt (dateTime.substring(5,7));
		day = Integer.parseInt (dateTime.substring(8,10));
		hour = Integer.parseInt (dateTime.substring(11,13));
		min = Integer.parseInt (dateTime.substring(14,16));
		sec = Integer.parseInt (dateTime.substring(17,19));
		
		Calendar cr = Calendar.getInstance();
		cYear = cr.get(Calendar.YEAR);
		cMonth = cr.get(Calendar.MONTH) + 1;
		cDay = cr.get(Calendar.DAY_OF_MONTH);
		cHour = cr.get(Calendar.HOUR_OF_DAY);
		cMin = cr.get(Calendar.MINUTE);
		cSec = cr.get(Calendar.SECOND);
		
		Calendar mycal = new GregorianCalendar(year, month-2 ,1);
		int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		sec -= cSec;
		if (sec < 0)
		{
			sec += 60;
			min --;
		}
		min -= cMin;
		if (min < 0)
		{
			min += 60;
			hour --;
		}
		hour -= cHour;
		if (hour < 0)
		{
			hour += 24;
			day --;
		}
		day -= cDay;
		if (day < 0)
		{
			day += daysInMonth;
			month --;
		}
		month -= cMonth;
		if (month < 0)
		{
			month += 12;
			year --;
		}
		year -= cYear;
		if (year < 0)
			return 0;
		
		long res = (sec + min*60 + hour*3600 + day*3600*24 + month*3600*24*daysInMonth + year*3600*24*daysInMonth*12);
		return res;
	}
	
	// ===================================== BUFFERS ==================================

	// ================= PERIODIC DISCOUNT TEMPLATE
	/**
	 *  returns the PeriodicDiscountTemplates buffer that contains all PeriodicDiscountTemplates. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<PeriodicDiscountTemplate> buffer.
	 */
	public static List<PeriodicDiscountTemplate> getPeriodicDiscountTemplates() {
		return periodicDiscountTemplates;
	}
	
	/**
	 *  Returns a {@code PeriodicDiscountTemplate} out of the {@code PeriodicDiscountTemplate} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The PeriodicDiscountTemplateID that is searched for.
	 * @return {@code PeriodicDiscountTemplate} with matching ID or NULL if entity was not found.
	 */
	public static PeriodicDiscountTemplate getPeriodicDiscountTemplateById(int id){
		
		
		for (PeriodicDiscountTemplate periodicDiscountTemplate : periodicDiscountTemplates)
		{
			if (periodicDiscountTemplate.getPeriodicDiscountTemplateID() == id)
				return periodicDiscountTemplate;
		}
		return fetchPeriodicDiscountTemplate(id);
	}
	
	/**
	 * Returns a list of all PeriodicDiscountTemplates after updating it from the DB. 
	 * @return {@code List<PeriodicDiscountTemplate>} buffer
	 */
	public static List<PeriodicDiscountTemplate> getAllPeriodicDiscountTemplates(){
		
		fetchAllPeriodicDiscountTemplates ();
		return periodicDiscountTemplates;
	}

	// ==================== PERIODIC DISCOUNT
	/**
	 *  returns the PeriodicDiscounts buffer that contains all PeriodicDiscounts. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<PeriodicDiscount> buffer.
	 */
	public static List<PeriodicDiscount> getPeriodicDiscounts() {
		return periodicDiscounts;
	}
	
	/**
	 *  Returns a {@code PeriodicDiscount} out of the {@code PeriodicDiscount} buffer with a matching id. If the invoice doesn't exist attempts to fetch it from DB.
	 * @param id  The PeriodicDiscountID that is searched for.
	 * @return {@code PeriodicDiscount} with matching ID or NULL if entity was not found.
	 */
	public static PeriodicDiscount getPeriodicDiscountById(int id){
		
		
		for (PeriodicDiscount periodicDiscount : periodicDiscounts)
		{
			if (periodicDiscount.getPeriodicDiscountID() == id)
				return periodicDiscount;
		}
		return fetchPeriodicDiscount(id);
	}
	
	/**
	 * Returns a list of all PeriodicDiscounts after updating it from the DB. 
	 * @return {@code List<PeriodicDiscount>} buffer
	 */
	public static List<PeriodicDiscount> getAllPeriodicDiscounts(){
		
		fetchAllPeriodicDiscounts ();
		return periodicDiscounts;
	}
	
	// ===================================== ACTIVE HANDLING ===================================
	
	/**
	 *  Returns the {@code PeriodicDiscountTemplate} that is currently being worked on.
	 * @return {@code PeriodicDiscountTemplate} loaded as active.
	 */
	public static PeriodicDiscountTemplate getActive() {
		return active;
	}
	
	/**
	 *  Designates a new {@code PeriodicDiscountTemplate} as the one being worked on. 
	 * @param activePeriodicDiscountTemplate The {@code PeriodicDiscountTemplate} to be worked on.
	 */
	public static void setActive(PeriodicDiscountTemplate activePeriodicDiscountTemplate) {
		active = activePeriodicDiscountTemplate;
	}
	
	/**
	 *  Initialises a new {@code PeriodicDiscountTemplate} and puts it as active.
	 */		
	public static void loadNewActive ()
	{
		active = new PeriodicDiscountTemplate ();
	}
	
	// ===================================== DB ACESSS ===============================
	
	/**
	 *  Attempts to fetch a {@code PeriodicDiscountTemplate} from DB. Recommended using {@code getPeriodicDiscountTemplateById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code PeriodicDiscountTemplate} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static PeriodicDiscountTemplate fetchPeriodicDiscountTemplate (int id)
	{
		PeriodicDiscountTemplate res = null;
		
		Op op = new Op (Operations.GET_PERIODIC_DISCOUNT_TEMPLATE, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (PeriodicDiscountTemplate) op.getEntity();
		if (res != null)
		{
			for (PeriodicDiscountTemplate compared : periodicDiscountTemplates)
				if (compared.getPeriodicDiscountTemplateID() == res.getPeriodicDiscountTemplateID())
					return compared;
				periodicDiscountTemplates.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the PeriodicDiscountTemplate buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllPeriodicDiscountTemplates ()
	{
		List<PeriodicDiscountTemplate> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_PERIODIC_DISCOUNT_TEMPLATE_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<PeriodicDiscountTemplate>) op.getEntity();
		if (res != null)
		{
			for (PeriodicDiscountTemplate resS : res)
			{
				addCurrent = true;
				for (PeriodicDiscountTemplate compared : periodicDiscountTemplates)
					if (compared.getPeriodicDiscountTemplateID() == resS.getPeriodicDiscountTemplateID())
						addCurrent = false;
				
				if (addCurrent)
					periodicDiscountTemplates.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the PeriodicDiscountTemplate with the given PeriodicDiscountTemplateID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the PeriodicDiscountTemplate to be removed.
	 */
	public static void evictPeriodicDiscountTemplate (int id)
	{
		for (PeriodicDiscountTemplate periodicDiscountTemplate : periodicDiscountTemplates)
		{
			if (periodicDiscountTemplate.getPeriodicDiscountTemplateID() == id)
			{
				Op op = new Op (Operations.UPDATE_PERIODIC_DISCOUNT_TEMPLATE,periodicDiscountTemplate);
				LoginCont.client.handleMessageFromClientUI(op);
				
				for (PeriodicDiscount item : periodicDiscounts)
				{
					if (item.getPeriodicDiscountTemplateID() == periodicDiscountTemplate.getPeriodicDiscountTemplateID())
						item.setPeriodicDiscountTemplate(dummyPeriodicDiscountTemplate);
				}
				
				periodicDiscountTemplates.remove (periodicDiscountTemplate);
				break;
			}
		}
	}
	
	/**
	 * Updates the PeriodicDiscountTemplate with the given PeriodicDiscountTemplateID in the DB.
	 * @param id of the PeriodicDiscountTemplate to be updated
	 */
	public static void updatePeriodicDiscountTemplate (int id)
	{
		for (PeriodicDiscountTemplate periodicDiscountTemplate : periodicDiscountTemplates)
		{
			if (periodicDiscountTemplate.getPeriodicDiscountTemplateID() == id)
			{
				Op op = new Op (Operations.UPDATE_PERIODIC_DISCOUNT_TEMPLATE,periodicDiscountTemplate);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given PeriodicDiscountTemplate in the DB.
	 * @param PeriodicDiscountTemplate to be created in the DB.
	 */
	public static void createPeriodicDiscountTemplate (PeriodicDiscountTemplate periodicDiscountTemplate)
	{
		Op op = new Op (Operations.CREATE_PERIODIC_DISCOUNT_TEMPLATE,periodicDiscountTemplate);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
	
	// ==================== PERIODIC DISCOUNT
	
	/**
	 *  Attempts to fetch a {@code PeriodicDiscount} from DB. Recommended using {@code getPeriodicDiscountById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code PeriodicDiscount} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static PeriodicDiscount fetchPeriodicDiscount (int id)
	{
		PeriodicDiscount res = null;
		
		Op op = new Op (Operations.GET_PERIODIC_DISCOUNT, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (PeriodicDiscount) op.getEntity();
		if (res != null)
		{
			for (PeriodicDiscount compared : periodicDiscounts)
				if (compared.getPeriodicDiscountID() == res.getPeriodicDiscountID())
					return compared;
				periodicDiscounts.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the PeriodicDiscount buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllPeriodicDiscounts ()
	{
		List<PeriodicDiscount> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_PERIODIC_DISCOUNT_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<PeriodicDiscount>) op.getEntity();
		if (res != null)
		{
			for (PeriodicDiscount resS : res)
			{
				addCurrent = true;
				for (PeriodicDiscount compared : periodicDiscounts)
					if (compared.getPeriodicDiscountID() == resS.getPeriodicDiscountID())
						addCurrent = false;
				
				if (addCurrent)
					periodicDiscounts.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the PeriodicDiscount with the given PeriodicDiscountID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the PeriodicDiscount to be removed.
	 */
	public static void evictPeriodicDiscount (int id)
	{
		for (PeriodicDiscount periodicDiscount : periodicDiscounts)
		{
			if (periodicDiscount.getPeriodicDiscountID() == id)
			{
				Op op = new Op (Operations.UPDATE_PERIODIC_DISCOUNT,periodicDiscount);
				LoginCont.client.handleMessageFromClientUI(op);
				
				for (Order item : OrderControl.getOrders())
				{
					if (item.getPeriodicDiscountID() == periodicDiscount.getPeriodicDiscountID())
						item.setPeriodicDiscount(dummyPeriodicDiscount);
				}
				
				periodicDiscounts.remove (periodicDiscount);
				break;
			}
		}
	}
	
	/**
	 * Updates the PeriodicDiscount with the given PeriodicDiscountID in the DB.
	 * @param id of the PeriodicDiscount to be updated
	 */
	public static void updatePeriodicDiscount (int id)
	{
		for (PeriodicDiscount periodicDiscount : periodicDiscounts)
		{
			if (periodicDiscount.getPeriodicDiscountID() == id)
			{
				Op op = new Op (Operations.UPDATE_PERIODIC_DISCOUNT,periodicDiscount);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given PeriodicDiscount in the DB.
	 * @param periodicDiscount to be created in the DB.
	 */
	public static void createPeriodicDiscount (PeriodicDiscount periodicDiscount)
	{
		Op op = new Op (Operations.CREATE_PERIODIC_DISCOUNT ,periodicDiscount);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
}
