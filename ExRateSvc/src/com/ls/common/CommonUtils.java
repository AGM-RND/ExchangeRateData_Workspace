package com.ls.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.ls.handlers.IHandler;

public class CommonUtils {
	public static IHandler getEJB(){
		try{
		Properties p = new Properties();  
        p.put("java.naming.factory.initial", "org.apache.openejb.client.LocalInitialContextFactory");  
  
        Context ctx = new InitialContext(p);  
        IHandler iHandler = (IHandler)ctx.lookup("java:global/ExRateSvc/HandlerEJB");
        
        return iHandler;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date parseDate(String dateStr){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateInString = dateStr;
		try {
			Date date = formatter.parse(dateInString);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
