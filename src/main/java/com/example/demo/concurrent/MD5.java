package com.example.demo.concurrent;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {
	
	private final static char[] hexDigits =
    { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F' };

    private static String bytesToHex(byte[] bytes)
    {
        StringBuffer sb = new StringBuffer();
        int t;
        for (int i = 0; i < 16; i++)
        {
            t = bytes[i];
            if (t < 0)
            {
                t += 256;                
            }
            sb.append(hexDigits[(t >>> 4)]);
            sb.append(hexDigits[(t % 16)]);
        }
        return sb.toString();
    }

    /**
     * 32位MD5加密函数
     * @author hKF21835
     * @version [版本号,YYYY-MM-DD]
     * @see [相关类/方法]
     * @since [产品/模块版本]
     * @param input 需要加密的信息
     * @return
     * @throws Exception
     */
    public static String code(String input) throws Exception
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance(System.getProperty(
                    "MD5.algorithm", "MD5"));
            //return bytesToHex(md.digest(input.getBytes("utf-8"))).substring(8, 24);
            return bytesToHex(md.digest(input.getBytes("utf-8")));
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            throw new Exception("Could not found MD5 algorithm.", e);
        }
    }
    

    /** 
     * 加密解密算法 执行一次加密，两次解密 
     */   
    public static String convertMD5(String inStr){  
  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
  
    }  
    
    /**
     * 32位MD5加密函数
     * @author hKF21835
     * @version [版本号,YYYY-MM-DD]
     * @see [相关类/方法]
     * @since [产品/模块版本]
     * @param input 需要加密的信息
     * @return
     * @throws Exception
     */
    public static String codeByGBK(String input) throws Exception
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance(System.getProperty(
                    "MD5.algorithm", "MD5"));
            //return bytesToHex(md.digest(input.getBytes("utf-8"))).substring(8, 24);
            return bytesToHex(md.digest(input.getBytes("GBK")));
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            throw new Exception("Could not found MD5 algorithm.", e);
        }
    }
    
    
    public final static String getMessageDigest(byte[] buffer) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(buffer);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
    
    public static String getMD5(String value) throws Exception {
    	MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(value.getBytes());
        byte b[] = md.digest();

        int i;

        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
         i = b[offset];
         if (i < 0)
          i += 256;
         if (i < 16)
          buf.append("0");
         buf.append(Integer.toHexString(i));
        }
        return  buf.toString();
    }
    
    public static void main(String[] args) {
    	String a="1,2,3";
		//System.out.println(convertMD5(a));
		System.out.println(convertMD5("EXFXG"));
	}

	  /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @return 签名结果
     */
    public static String sign(String text) {
        return DigestUtils.md5Hex(getContentBytes(text, "utf-8"));
    }
    
    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
}
