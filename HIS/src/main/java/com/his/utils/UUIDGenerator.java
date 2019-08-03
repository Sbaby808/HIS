package com.his.utils;

import java.util.UUID;

/**  
* @ClassName: UUIDGenerator  
* @Description: TODO(生成uuid主键)  
* @author TRC
* @date 2019年8月1日  上午11:16:56
*    
*/
public class UUIDGenerator {
	public UUIDGenerator() {
	}
 
	/**
	 * 获得一个UUID
	 * 
	 * @return String UUID
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		// 去掉“-”符号
		return s.substring(7, 8) + s.substring(9, 13) + s.substring(17, 18)
				+ s.substring(22, 23) + s.substring(24,25);
	}

}
