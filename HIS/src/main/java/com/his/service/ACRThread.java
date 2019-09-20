package com.his.service;

import com.his.utils.ACR122uTools;

/**  
* @ClassName: ACRThread  
* @Description: ACR线程类
* @author Sbaby
* @date 2019年9月20日  下午5:47:51
*    
*/
public class ACRThread implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			ACR122uTools.getCardNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
