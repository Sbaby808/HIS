package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.MedicalCard;

public interface IMedicalCardDao extends CrudRepository<MedicalCard, String>{

	@Query("from MedicalCard m where m.cardId=?1")
	public MedicalCard getCardByCid(String cardId);
	
	/**
	* @Title:checkCardByPersonId
	* @Description:查询此身份证号下的就诊卡数量（1 or 0）
	* @param:@param person_id
	* @param:@return
	* @return:Integer
	* @throws
	* @author:Sbaby
	* @Date:2019年8月2日 上午11:28:49
	 */
	@Query("select count(*) from MedicalCard m where m.personId = ?1")
	public Integer checkCardByPersonId(String person_id);
	
	/**
	* @Title:queryByPersonId
	* @Description:根据身份证号查询就诊卡
	* @param:@param person_id
	* @param:@return
	* @return:MedicalCard
	* @throws
	* @author:Sbaby
	* @Date:2019年8月2日 上午11:30:12
	 */
	@Query("from MedicalCard m where m.personId = ?1")
	public MedicalCard queryByPersonId(String person_id);
	
	/**
	* @Title:queryByPage
	* @Description:分页查询就诊卡信息
	* @param:@param pageable
	* @param:@return
	* @return:List<MedicalCard>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 下午2:30:04
	 */
	@Query("from MedicalCard m")
	public List<MedicalCard> queryByPage(Pageable pageable);
	
	/**
	* @Title:queryAllPages
	* @Description:查询就诊卡总记录条数
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 下午2:31:18
	 */
	@Query("select count(*) from MedicalCard m")
	public int queryAllPages();
}
