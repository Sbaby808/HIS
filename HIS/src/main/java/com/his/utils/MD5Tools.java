package com.his.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**  
* @ClassName: MD5Tools  
* @Description: MD5加密解密工具类
* @author Sbaby
* @date 2019年7月31日  下午2:16:55
*    
*/
public class MD5Tools {
	
	/**
	 * 
	* @Title:Md5
	* @Description:MD5加密
	* @param:@param plainText
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年7月31日 下午2:28:35
	 */
	public static String Md5(String plainText){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
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
     
			//32
			return buf.toString();
//			System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密
 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 
	* @Title:KL
	* @Description:MD5后再加密 （可逆的加密算法）
	* @param:@param inStr
	* @param:@return
	* @return:String
	* @throws
	* @author:Sbaby
	* @Date:2019年7月31日 下午2:23:07
	 */
	public static String KL(String inStr) {
		// String s = new String(inStr);
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}
	
	/**
	 * 
	* @Title:JM
	* @Description:KL加密后解密
	* @param:@param inStr
	* @param:@return
	* @return:String
	* @throws
	* @author:Sbaby
	* @Date:2019年7月31日 下午2:23:55
	 */
	public static String JM(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String k = new String(a);
		return k;
	}
	
	/**
	* @Title:check
	* @Description:验证密码
	* @param:@param sourceStr
	* @param:@param destStr
	* @param:@return
	* @return:Boolean
	* @throws
	* @author:Sbaby
	* @Date:2019年7月31日 下午2:41:09
	 */
	public static Boolean check(String sourceStr, String destStr) {
		if(Md5(sourceStr).equals(JM(destStr))){
			return new Boolean(true);
		} else {
			return new Boolean(false);
		}
	}

}
