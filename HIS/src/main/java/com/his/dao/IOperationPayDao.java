package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.bean.OperationPaybean;
import com.his.pojo.OperationPay;

/**  
* @ClassName: IOperationPayDao  
* @Description: TODO(手术划价dao)  
* @author TRC
* @date 2019年7月30日  上午9:00:49
*    
*/
public interface IOperationPayDao extends CrudRepository<OperationPay, String>{
	/**
	 * 
	* @Title:getall_ope_pays
	* @Description:TODO查询手术收费项和制定他的员工姓名
	* @param:@return
	* @return:List<OperationPaybean>
	* @throws
	* @author:TRC
	* @Date:2019年7月31日 下午5:57:12
	 */
    @Query(value="select new com.his.bean.OperationPaybean(o.operPayId, o.operPayDesc,o.operPayName, o.operPayPrice, o.operPayTime,e.ygName,e.ygxh) from OperationPay o, EmpInformation e where o.empInformation = e.ygxh and o.operPayName like ?1 order by  o.operPayTime desc")
	public List<OperationPaybean> getall_ope_pays(String sou,Pageable page);
    @Query(value="select count(*) from OperationPay o where o.operPayName like ?1")
    public long getcount(String sou);
    
    /**
     * 
    * @Title:getAllOperationPay
    * @Description:查询所有手术项
    * @param:@return
    * @return:List<OperationPay>
    * @throws
    * @author:Hamster
    * @Date:2019年8月10日 下午1:42:24
     */
    @Query("from OperationPay o")
    public List <OperationPay> getAllOperationPay();
    
    /**
    * @Title:searchByKey
    * @Description:模糊查询手术项
    * @param:@param key
    * @param:@param pageable
    * @param:@return
    * @return:List<OperationPay>
    * @throws
    * @author:Sbaby
    * @Date:2019年8月24日 上午9:43:55
     */
    @Query("from OperationPay o where o.operPayName like ?1")
    public List<OperationPay> searchByKey(String key, Pageable pageable);
    
    
}
