package org.zframework.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTransform {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "201604151700";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");

		long millionSeconds;
		try {
			millionSeconds = sdf.parse(str).getTime();
			System.out.println(millionSeconds);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//毫秒

	}

}
