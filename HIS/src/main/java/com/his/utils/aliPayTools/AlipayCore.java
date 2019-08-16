package com.his.utils.aliPayTools;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlipayCore {
	
	public static Map<String, String> paraFilter(Map<String, String> sArray) {
	
			Map<String, String> result = new HashMap<String, String>();
	
	
			if (sArray == null || sArray.size() <= 0) {
				return result;
			}
	
			for (String key : sArray.keySet()) {
				String value = sArray.get(key);
				if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
						|| key.equalsIgnoreCase("sign_type")) {
					continue;
				}
				result.put(key, value);
				}
	
	
			return result;
	}

	public static String createLinkString(Map<String, String> params) {

		List<String> keys = new ArrayList<String>(params.keySet());

		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
				} else {
					prestr = prestr + key + "=" + value + "&";
				}
			}
	
			return prestr;
		}


	public static void logResult(String sWord,String filename) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(AlipayConfig.log_path + "alipay_log_" + System.currentTimeMillis()+filename+".txt");
			writer.write(sWord);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (writer != null) {
					try {
						writer.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
			}
	}
}