package com.chetty.reporting.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getTimeStampForOrderID("240"));
	}
	
	public static String getTimeStampForOrderID(String invl) {

        int interval = Integer.parseInt(invl);
        int frequency = 5;

        long unix_time = (System.currentTimeMillis() / 1000L) - (interval * 60);

        if (frequency != interval)
                unix_time -= (frequency * 60);

        long target_time = ((long) (unix_time / (frequency * 60)) * (frequency * 60));

        Date date = new Date((long) target_time * 1000L);
        DateFormat dfm = new SimpleDateFormat("yyyyMMddHHmm");

        return dfm.format(date);
}

}
