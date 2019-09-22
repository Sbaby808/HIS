package com.his.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.pojo.JsonResult;
import com.his.pojo.MedicalCard;
import com.his.utils.ACR122uTools;
import com.his.utils.MD5Tools;

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
	
	@Autowired
	private MedicalCardService medicalCardService;

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
	public JsonResult readCard() throws Exception {
		JsonResult result = new JsonResult();
		String password = "";
		password = ACR122uTools.getPassword();
		String cardId = "";
		cardId = ACR122uTools.getCardNum();
		MedicalCard medicalCard = medicalCardService.getCardByCid(cardId);
		if(password.equals(MD5Tools.KL(medicalCard.getPasswd()))) {
			result.setResult(cardId);
			result.setStatus("ok");
		} else {
			result.setResult("就诊卡校验值不匹配，请确认是最新办理的就诊卡！");
			result.setStatus("error");
		}
		return result;
//			// TODO 未进行线程超时处理 
//			new Thread(new ACRThread()).start();
		
	}
	
	/**
	* @Title:writeCard
	* @Description:写卡
	* @param:@param cardId
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年9月23日 上午12:06:05
	 */
	public void writeCard(String cardId) throws Exception {
		MedicalCard medicalCard = medicalCardService.getCardByCid(cardId);
		ACR122uTools.setCardNum(cardId);
		ACR122uTools.setPasswd(MD5Tools.KL(medicalCard.getPasswd()));
	}

}
