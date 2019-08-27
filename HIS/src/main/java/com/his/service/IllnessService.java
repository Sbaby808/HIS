package com.his.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IIllnessDao;
import com.his.pojo.Illness;

/**  
* @ClassName: IllnessService  
* @Description: 疾病service
* @author Sbaby
* @date 2019年8月21日  下午3:26:14
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class IllnessService {

	@Autowired
	private IIllnessDao illnessDao;
	
	/**
	* @Title:batchImpoet
	* @Description:批量导入疾病信息
	* @param:@param list
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月21日 下午3:27:46
	 */
	public void batchImpoet(List<Illness> list) {
		for (Illness illness : list) {
			illness.setIllId(UUID.randomUUID().toString().replaceAll("-", ""));
			illnessDao.save(illness);
		}
	}
	
}
