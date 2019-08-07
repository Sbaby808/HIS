package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.bean.Opeempbean;
import com.his.bean.OperationRecordbean;
import com.his.dao.IOpeEmpDao;
import com.his.pojo.OpeEmp;

/**  
* @ClassName: OpeEmpService  
* @Description: TODO(手术员工service)  
* @author TRC
* @date 2019年7月30日  上午9:08:35
*    
*/
@Service
public class OpeEmpService {
	@Autowired
	private IOpeEmpDao iOpeEmpDao;
	/**
	 * 
	* @Title:AddOpeEmp
	* @Description:TODO添加手术-员工记录
	* @param:@param opeEmp
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午4:31:35
	 */
    public void AddOpeEmp(OpeEmp opeEmp) {
    	iOpeEmpDao.save(opeEmp);
    }
    /**
     * 
    * @Title:getallOpeEmps
    * @Description:TODO查询所有手术-员工记录
    * @param:@return
    * @return:List<OpeEmp>
    * @throws
    * @author:TRC
    * @Date:2019年7月30日 下午4:34:56
     */
    public List<OpeEmp> getallOpeEmps(){
    	return (List<OpeEmp>) iOpeEmpDao.findAll();
    }
    public List<Opeempbean> getaList(String opeid){
    	return iOpeEmpDao.getOpeEmps(opeid);
    }
  
}
