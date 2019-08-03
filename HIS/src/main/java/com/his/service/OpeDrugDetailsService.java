package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.IOpeDrugDetailDao;
import com.his.pojo.OpeDrugDetail;

/**  
* @ClassName: OpeDrugDetailsService  
* @Description: TODO(手术用药详情)  
* @author TRC
* @date 2019年7月30日  上午9:12:14
*    
*/
@Service
public class OpeDrugDetailsService {
	@Autowired
	private IOpeDrugDetailDao iOpeDrugDetailDao;
	/**
	 * 
	* @Title:AddOpeDrugDetail
	* @Description:TODO手术用药详情
	* @param:@param opeDrugDetail
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午4:44:34
	 */
	public void AddOpeDrugDetail(OpeDrugDetail opeDrugDetail) {
		iOpeDrugDetailDao.save(opeDrugDetail);
	}
	/**
	 * 
	* @Title:getallOpeDrugDetails
	* @Description:TODO查询所有手术用药信息
	* @param:@return
	* @return:List<OpeDrugDetail>
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午4:46:22
	 */
	public List<OpeDrugDetail> getallOpeDrugDetails(){
		return (List<OpeDrugDetail>) iOpeDrugDetailDao.findAll();
	}

}
