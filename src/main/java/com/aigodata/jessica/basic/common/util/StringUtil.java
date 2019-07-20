package com.aigodata.jessica.basic.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.Assert;

import net.sf.json.JSONObject;

/***
 * 
 * @author saps
 *
 */
public class StringUtil {
	public static SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat numberDateTime = new SimpleDateFormat("yyyyMMddHHmmss");
	public static SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat ymfmt = new SimpleDateFormat("yyyyMM");

	public static void main(String[] args) {
		Assert.hasLength("", "elastic-job register zookeeper,serverList is null");
		System.out.println(12323);
	}

	public static String ifNull(Object obj) {
		if (obj == null || "".equals(obj.toString())) {
			return "";
		}
		return obj.toString();
	}

	public static String ifNull(Object obj, String defaultValue) {
		if (obj == null || "".equals(obj.toString())) {
			return defaultValue;
		}
		return obj.toString();
	}

	public static long ifLongNull(Object obj) {
		if (obj == null || "".equals(obj.toString())) {
			return 0;
		}
		return Long.parseLong(obj.toString());
	}

	public static int ifIntNull(Object obj) {
		if (obj == null || "".equals(obj.toString())) {
			return 0;
		}
		return Integer.parseInt(obj.toString());
	}

	public static int ifIntNull(Object obj, int defaultValue) {
		if (obj == null || "".equals(obj.toString())) {
			return defaultValue;
		}
		return Integer.parseInt(obj.toString());
	}

	public static boolean isNull(Object obj) {
		if (obj == null || "".equals(obj.toString())) {
			return true;
		}
		return false;
	}

	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}
	
	public static String getJsonProps(JSONObject json,String key) {
		return json.getString(key);
	}
	public static int getJsonProps4Int(JSONObject json,String key) {
		return StringUtil.ifIntNull(json.get(key));
	}
	public static long getJsonProps4Long(JSONObject json,String key) {
		return StringUtil.ifLongNull(json.get(key));
	}
	public static Date getJsonProps4Date(JSONObject json,String key) {
		if(StringUtil.isNotNull(json.get(key))) {
			try {
				return StringUtil.dateTime.parse(json.get(key).toString());
			} catch (Exception e) {
				
			}
		}
		return null;
	}
	
	public static long[] str2long(String str) {
		long[] array = null;
		if (isNotNull(str)) {
			String a[] = str.split(",");
			int len = a.length;
			array = new long[len];
			for (int i = 0; i < len; i++) {
				array[i] = Long.parseLong(a[i]);
			}
		}
		return array;
	}
}
