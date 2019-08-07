package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.bean.Opepayrecordbean;
import com.his.pojo.OperPayRecord;
/**  
* @ClassName: IOperPayRecordDao  
* @Description: TODO(手术缴费记录dao)  
* @author TRC
* @date 2019年7月30日  上午9:06:27
*    
*/
public interface IOperPayRecordDao extends CrudRepository<OperPayRecord, String>{
	/**
	 * 手术缴费记录分页加模糊查询by病人姓名
	* @Title:getOperationRecordbeans
	* @Description:TODO
	* @param:@param sou
	* @param:@param page
	* @param:@return
	* @return:List<OperationRecordbean>
	* @throws
	* @author:TRC
	* @Date:2019年8月5日 上午10:03:58
	 */
    @Query(value="select new com.his.bean.Opepayrecordbean(o.operationPay.operPayName,o.medicalCard.cardName,o.operationPay.operPayPrice,o.empInformation.ygName,o.operJfTime) from OperPayRecord o where o.medicalCard.cardName like ?1 order by o.operJfTime desc")
	public List<Opepayrecordbean> getOperationRecordbeans(String sou,Pageable page);
    /**
     * 
    * @Title:getpayrecordcount
    * @Description:TODO查询手术记录数量by病人name
    * @param:@param sou
    * @param:@return
    * @return:long
    * @throws
    * @author:TRC
    * @Date:2019年8月5日 上午10:48:33
     */
    @Query(value="select count(*) from OperPayRecord o where o.medicalCard.cardName like ?1")
	public long getpayrecordcount(String sou);
}
