package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Prescription;

/**  
* @ClassName: IPrescriptionDao  
* @Description: 门诊处方Dao
* @author Sbaby
* @date 2019年8月22日  上午11:01:15
*    
*/
public interface IPrescriptionDao extends CrudRepository<Prescription, String> {
	
	/**
	* @Title:qureyPriscriptionByCardId
	* @Description:根据就诊卡id查询"未取药"的处方单
	* @param:@param cardId
	* @param:@return
	* @return:List<Prescription>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月23日 上午1:51:12
	 */
	@Query(" from Prescription p where p.useDrugRecord IS NULL"
			+ " and p.history.outpatientRegistration.medicalCard.cardId = ?1 "
			+ " order by p.history.hisTime desc ")
	public List<Prescription> qureyPriscriptionByCardId(String cardId);
	
	/**
	* @Title:qureyPriscriptionNotNullByCardId
	* @Description:根据就诊卡id查询"已取药"的处方单
	* @param:@param cardId
	* @param:@return
	* @return:List<Prescription>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月23日 上午3:08:06
	 */
	@Query(" from Prescription p where p.useDrugRecord IS NOT NULL"
			+ " and p.history.outpatientRegistration.medicalCard.cardId = ?1 "
			+ " order by p.history.hisTime desc ")
	public List<Prescription> qureyPriscriptionNotNullByCardId(String cardId);
	
	/**
	* @Title:qureyPriscriptionCountByCardId
	* @Description:根据就诊卡id查询未取药的处方单数量
	* @param:@param cardId
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月23日 上午1:54:45
	 */
	@Query("select count(*) from Prescription p where p.useDrugRecord IS NULL"
			+ " and p.history.outpatientRegistration.medicalCard.cardId = ?1 "
			+ " order by p.history.hisTime desc ")
	public int qureyPriscriptionCountByCardId(String cardId);

	/**
	* @Title:getPriscriptionCountByCardId
	* @Description:根据就诊卡id查询未缴费处方数量
	* @param:@param cardId
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月26日 下午2:13:04
	 */
	@Query("select count(*) from Prescription p where p.outPrePay IS NULL"
			+ " and p.history.outpatientRegistration.medicalCard.cardId = ?1 "
			+ " order by p.history.hisTime desc ")
	public int getPriscriptionCountByCardId(String cardId);
	
	/**
	* @Title:getPriscriptionByCardId
	* @Description:根据就诊卡id查询未缴费处方
	* @param:@param cardId
	* @param:@return
	* @return:List<Prescription>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月26日 下午2:46:33
	 */
	@Query(" from Prescription p where p.outPrePay IS NULL"
			+ " and p.history.outpatientRegistration.medicalCard.cardId = ?1 "
			+ " order by p.history.hisTime desc ")
	public List<Prescription> getPriscriptionByCardId(String cardId);
	
}
