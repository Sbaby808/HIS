package com.his.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.utils.ACR122uTools;

/**  
* @ClassName: ACR122uService  
* @Description: ACR122u读写卡Service
* @author Sbaby
* @date 2019年9月20日  下午5:42:08
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class ACR122uService {

	/**
	* @Title:readRead
	* @Description:读卡
	* @param:@return
	* @return:String
	 * @throws Exception 
	* @throws
	* @author:Sbaby
	* @Date:2019年9月20日 下午6:02:35
	 */
	public String readCard() throws Exception {
		return ACR122uTools.getCardNum();
//			// TODO 未进行线程超时处理 
//			new Thread(new ACRThread()).start();
		
	}

}
