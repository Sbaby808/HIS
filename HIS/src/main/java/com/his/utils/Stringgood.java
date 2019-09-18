package com.his.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: Stringgood
 * @Description: 去掉字符串数组中空的字符串
 * @author jack
 * @date 2019年8月19日 下午2:49:29
 * 
 */
public class Stringgood {
	public static String[] removeArrayEmptyTextBackNewArray(String[] strArray) {

		List<String> strList = Arrays.asList(strArray);

		List<String> strListNew = new ArrayList<>();

		for (int i = 0; i < strList.size(); i++) {

			if (strList.get(i) != null && !strList.get(i).equals("")) {

				strListNew.add(strList.get(i));
			}

		}

		String[] strNewArray = strListNew.toArray(new String[strListNew.size()]);

		return strNewArray;

	}
}
