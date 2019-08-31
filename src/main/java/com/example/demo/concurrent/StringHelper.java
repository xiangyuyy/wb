package com.example.demo.concurrent;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


/**
 * 类StringHelper.java的实现描述：String帮助类
 * 
 * @author cq 2016年6月25日 下午6:58:22
 */
public abstract class StringHelper {

	private static final String charset = "UTF-8";

	public static String appendSlant(String url) {
		if (url == null) {
			return null;
		}
		url = url.replace("\\", "/");
		if (url.endsWith("/")) {
			return url;
		}
		return url + "/";
	}

	public static byte[] getBytes(String s) {
		if (s == null) {
			return new byte[0];
		}
		try {
			return s.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			// ignored
			return new byte[0];
		}
	}

	public static String getString(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		try {
			return new String(bytes, charset);
		} catch (UnsupportedEncodingException e) {
			// ignored
			return "";
		}
	}

	public static String[] mergeArray(String[] a, String[] b) {
		if (a == null) {
			return b;
		}
		if (b == null) {
			return a;
		}
		String[] newArray = new String[a.length + b.length];
		System.arraycopy(a, 0, newArray, 0, a.length);
		System.arraycopy(b, 0, newArray, a.length, b.length);
		return newArray;
	}

	public static String[] emptyArray() {
		return new String[0];
	}

	public static String emptyString() {
		return "";
	}

	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}

	public static boolean isEmpty(String[] s) {
		return s == null || s.length == 0;
	}

	public static String isEmpty(String value, String defaultValue) {
		if (isEmpty(value)) {
			return defaultValue;
		} else {
			return value;
		}
	}

	public static String[] split(String s) {
		if (isEmpty(s)) {
			return new String[0];
		}
		return s.split(",|;|:");
	}

	public static List<String> splitToList(String s) {
		return Arrays.asList(split(s));
	}

	/**
	 * 验证手机号码和座机
	 */
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			if (StringUtils.isNotBlank(mobileNumber)) {
				Pattern p = Pattern.compile("^1[0-9][0-9]\\d{8}||0\\d{2,3}-?\\d{7,8}$");
				Matcher m = p.matcher(mobileNumber);
				flag = m.matches();
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	

	/**
	 * 验证座机
	 */
	public static boolean checkMobilePhone(String mobileNumber) {
		boolean flag = false;
		try {
			if (StringUtils.isNotBlank(mobileNumber)) {
				Pattern p = Pattern.compile("^0\\d{2,3}-?\\d{7,8}$");
				Matcher m = p.matcher(mobileNumber);
				flag = m.matches();
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证身份证
	 */
	public static boolean checkIdCard(String idCard) {
		boolean flag = false;
		try {
			if (StringUtils.isNotBlank(idCard)) {
				Pattern regex = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
				Matcher matcher = regex.matcher(idCard);
				flag = matcher.matches();
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	// 替换表情为*号
	public static String reaplc(String source) {
		String str = source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
		return str.trim();
	}

/*	*//**
	 * 获取汉字串拼音首字母，英文字符不变
	 * 
	 * @param chinese
	 *            汉字串
	 * @return 汉语拼音首字母
	 *//*
	public static String getFirstSpell(String chinese) {
		StringBuffer pybf = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
				try {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
					if (temp != null) {
						pybf.append(temp[0].charAt(0));
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pybf.append(arr[i]);
			}
		}
		return pybf.toString().replaceAll("\\W", "").trim();
	}*/
	
	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if(StringUtils.isBlank(str)){
			return false;
		}
		Pattern pattern = Pattern.compile("([1-9]+[0-9]*|0)(\\.[\\d]+)?");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断是否是整数
	 * @param str
	 * @return
	 */
	public static boolean isNumericInt(String str){
		if(StringUtils.isBlank(str)){
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*"); 
		return pattern.matcher(str).matches();  
	}
	
	/**
	 * 银行卡效验
	 * @param str
	 * @return
	 */
	public static boolean isIdCard(String str){
		if(StringUtils.isBlank(str)){
			return false;
		}
		Pattern pattern = Pattern.compile("/^(\\d{16}|\\d{19})$/"); 
		return pattern.matcher(str).matches();  
	}
	
	public static void main(String[] args) {
		System.out.println(isIdCard("657654278916290561"));
	}
}
