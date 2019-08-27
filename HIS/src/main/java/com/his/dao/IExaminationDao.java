package com.his.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Examination;

/**  
* @ClassName: IExaminationDao  
* @Description: 体检Dao
* @author Sbaby
* @date 2019年8月22日  上午11:21:11
*    
*/
public interface IExaminationDao extends CrudRepository<Examination, String> {

	/**
	* @Title:getExamByRegId
	* @Description:根据挂号id查询体检
	* @param:@param regId
	* @param:@return
	* @return:Examination
	* @throws
	* @author:Sbaby
	* @Date:2019年8月22日 下午3:25:36
	 */
	@Query("from Examination ex where ex.outpatientRegistration.regId = ?1")
	public Examination getExamByRegId(String regId);
	
}
