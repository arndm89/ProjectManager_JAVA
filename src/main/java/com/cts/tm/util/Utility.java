package com.cts.tm.util;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class Utility {

	public static boolean isValidString(String str, int minLength, int maxLength) {

		if(StringUtils.isEmpty(str)) {	//	 not empty
			return false;
		}else if(!(str.length() >= minLength && str.length() <= maxLength)) {
			return false;
		}
		return true;
	}

	public static boolean isEmptyStr(String str) {
		if(str==null || StringUtils.isEmpty(str) || str.equalsIgnoreCase("null")) {
			return true;
		}
		return false;
	}

	public static Date parseDate(String dateStr) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDt = null;
		try {
			parsedDt = formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsedDt;
	}

	public static <C> C checkAndPopulateNullObjectValue(C c) {

		try {
			Field[] fields = c.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if(field.get(c).equals(""))
					field.set(c, null);
			}
			return c;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}
}
