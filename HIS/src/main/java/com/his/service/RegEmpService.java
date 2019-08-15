package com.his.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.RegEmpDao;
import com.his.pojo.RegEmp;
import com.his.pojo.RegEmpPK;

/**  
* @ClassName: RegEmpService  
* @Description: 挂号员工Service
* @author Sbaby
* @date 2019年8月12日  下午2:04:04
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class RegEmpService {
	
	@Autowired
	private RegEmpDao regEmpDao;

	/**
	* @Title:addRegEmp
	* @Description:添加挂号员工
	* @param:@param regEmp
	* @param:@return
	* @return:RegEmp
	* @throws
	* @author:Sbaby
	* @Date:2019年8月12日 下午2:11:36
	 */
	public RegEmp addRegEmp(RegEmp regEmp) {
		RegEmpPK pk = new RegEmpPK();
		pk.setRegId(regEmp.getOutpatientRegistration().getRegId());
		pk.setYgxh(regEmp.getEmpInformation().getYgxh());
		
		regEmp.setId(pk);
		
		regEmpDao.save(regEmp);
		return regEmp;
	}
	
}
