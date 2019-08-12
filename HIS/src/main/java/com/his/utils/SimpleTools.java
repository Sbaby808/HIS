package com.his.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
* @ClassName: SimpleTools  
* @Description: 一些简单的工具方法
* @author Sbaby
* @date 2019年8月2日  上午10:33:48
*    
*/
public class SimpleTools {
	private static Logger log = LoggerFactory.getLogger(SimpleTools.class);

	/**
	* @Title:calAgeByBirthday
	* @Description:根据出生日期计算年龄
	* @param:@return
	* @return:Integer
	* @throws
	* @author:Sbaby
	* @Date:2019年8月2日 上午10:35:01
	 */
	public static int calAgeByBirthday(Date birthday) {
		int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthday)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
	}
	
	/**
	* @Title:addCharForSearch
	* @Description:给字符串两端加上百分号
	* @param:@param source
	* @param:@return
	* @return:String
	* @throws
	* @author:Sbaby
	* @Date:2019年8月5日 上午10:12:53
	 */
	public static String addCharForSearch(String source) {
		source = source == null ? "" : source;
		String dest = "%" + source + "%";
		return dest;
	}
	
	/**
	* @Title:isToday
	* @Description:检查日期是否是今天
	* @param:@param date
	* @param:@return
	* @return:boolean
	* @throws
	* @author:Sbaby
	* @Date:2019年8月12日 下午12:10:41
	 */
	public static boolean isToday(Date date) {
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
	     if(fmt.format(date).toString().equals(fmt.format(new Date()).toString())){//格式化为相同格式
	          return true;
	      }else {
	        return false;
	      }
	}
}
