package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.InjectionDetail;
import com.his.pojo.InjectionDetailPK;
import com.his.pojo.OutPreItem;

/**  
* @ClassName: IInjectionDetailDao  
* @Description: 用药明细Dao
* @author Sbaby
* @date 2019年9月3日  下午4:29:32
*    
*/
public interface IInjectionDetailDao extends CrudRepository<InjectionDetail, InjectionDetailPK> {
	
	/**
	* @Title:queryOutPreItemByPreId
	* @Description:根据处方单查找明细
	* @param:@param prescriptionId
	* @param:@return
	* @return:List<InjectionDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月11日 上午11:16:59
	 */
	@Query("from InjectionDetail ind where ind.useDrugRecord.prescription.prescriptionId = ?1 ")
	public List<InjectionDetail> queryBackItemByPreId(String prescriptionId);
	
	/**
	* @Title:getAllDetialByInjId
	* @Description:获取一个未完成的用药记录的所有明细
	* @param:@param injId
	* @param:@return
	* @return:List<InjectionDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月10日 上午10:33:39
	 */
	@Query("from InjectionDetail i where i.useDrugRecord.injId = ?1 and i.useDrugRecord.state = '未取药' ")
	public List<InjectionDetail> getAllDetialForNo(String injId);
	
	/**
	* @Title:getAllDetialByInjId
	* @Description:获取一个用药记录的所有明细
	* @param:@param injId
	* @param:@return
	* @return:List<InjectionDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月10日 上午9:39:26
	 */
	@Query("from InjectionDetail i where i.useDrugRecord.injId = ?1 ")
	public List<InjectionDetail> getAllDetialByInjId(String injId);
	
	/**
	* @Title:getAllYpIdByInjId
	* @Description:查找已经存在的用药明细的ypid
	* @param:@param injId
	* @param:@return
	* @return:List<String>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月9日 下午10:52:21
	 */
	@Query(value=("select di.yp_id from injection_detail ide " + 
			"left outer join use_drug_record udr on udr.inj_id = ide.inj_id " + 
			"left outer join medicine m on ide.medicine_id = m.medicine_id " + 
			"left outer join drug_warehouse dw on m.pckc_id = dw.pckc_id " + 
			"left outer join drug_information di on di.yp_id = dw.yp_id " + 
			"where udr.inj_id = ?1 "),nativeQuery = true)
	public List<String> getAllYpIdByInjId(String injId);

}
