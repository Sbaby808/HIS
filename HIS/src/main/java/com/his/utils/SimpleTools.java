package com.his.utils;

import java.util.Calendar;
import java.util.Date;

/**  
* @ClassName: SimpleTools  
* @Description: 一些简单的工具方法
* @author Sbaby
* @date 2019年8月2日  上午10:33:48
*    
*/
public class SimpleTools {

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
	
}
