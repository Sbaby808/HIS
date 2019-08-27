package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.bean.Checknoticebean;
import com.his.pojo.CheckItem;
import com.his.pojo.CheckNoticeForm;

/**  
* @ClassName: ICheckNoticeDao  
* @Description: TODO(检查通知单dao)  
* @author TRC
* @date 2019年8月13日  下午4:07:09
*    
*/
public interface ICheckNoticeDao extends CrudRepository<CheckNoticeForm, String>{
	
	@Query(value="select c.solveScheme.history.outpatientRegistration.medicalCard.cardName,c.solveScheme.history.outpatientRegistration.medicalCard.cardId,c.checkPay.checkPayName,c.checkPay.checkId from CheckNoticeForm c where c.mcheckId=?1")
	public String getbnameandid(String id);
	
	@Query(value="from CheckNoticeForm c where c.solveScheme.history.outpatientRegistration.medicalCard.cardId=?1 and c.mcheckComment!='已处理'")
    public List<CheckNoticeForm> getbysoufenye(String sou,Pageable page);
	
	@Query(value="select count(1) from CheckNoticeForm c where c.solveScheme.history.outpatientRegistration.medicalCard.cardId=?1 and c.mcheckComment!='已处理'")
	public long getcount(String sou);
	
	@Query(value="from CheckNoticeForm c where c.mcheckId=?1")
	public List<CheckNoticeForm> getbyid(String id);
	
	/**
	* @Title:getAll
	* @Description:根据医嘱编号查询所有检查通知项
	* @param:@param solveId
	* @param:@return
	* @return:List<CheckNoticeForm>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午11:42:48
	 */
	@Query("from CheckNoticeForm c where c.solveScheme.scheId = ?1")
	public List<CheckNoticeForm> getAll(String solveId);
	
	/**
	* @Title:getByHistoryId
	* @Description:根据诊断记录编号查询检查通知项
	* @param:@param hostoryId
	* @param:@return
	* @return:List<CheckNoticeForm>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月25日 下午1:51:44
	 */
	@Query("from CheckNoticeForm c where c.solveScheme.history.historyId = ?1")
	public List<CheckNoticeForm> getByHistoryId(String hostoryId);
}
