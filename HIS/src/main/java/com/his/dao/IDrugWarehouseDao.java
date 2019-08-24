package com.his.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.DrugWarehouse;

/**  
* @ClassName: IDrugWarehouseDao  
* @Description: 药库批次库存dao
* @author crazy_long
* @date 2019年7月30日  上午10:50:06
*    
*/
public interface IDrugWarehouseDao extends CrudRepository<DrugWarehouse, String>{
	
	/**
	* @Title:queryBackDrug
	* @Description:根据药品名和供应商id查找要退还的药品
	* @param:@param ypName
	* @param:@param supplierId
	* @param:@return
	* @return:List<DrugWarehouse>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月24日 上午9:09:24
	 */
	@Query(value="from DrugWarehouse d "
			+ "where (d.drugInformation.ypName like ?1 "
			+ "or d.drugInformation.vocode like ?1) "
			+ "and d.drugInformation.supplier.gysId like ?2 "
			+ "and d.state = '否' ")
	public List<DrugWarehouse> queryBackDrug(String ypNameOrVocode,String supplierId,Pageable page);
	
	/**
	* @Title:queryBackDrugCount
	* @Description:根据药品名和供应商id查找要退还的药品的条数
	* @param:@param ypName
	* @param:@param supplierId
	* @param:@return
	* @return:List<DrugWarehouse>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月24日 上午9:48:19
	 */
	@Query(value="select count(*) from DrugWarehouse d "
			+ "where (d.drugInformation.ypName like ?1 "
			+ "or d.drugInformation.vocode like ?1) "
			+ "and d.drugInformation.supplier.gysId like ?2 "
			+ "and d.state = '否' ")
	public int queryBackDrugCount(String ypNameOrVocode,String supplierId);
	
	/**
	* @Title:getDrugByNameOrproduceDate
	* @Description:根据药品名（音码）查找药品
	* @param:@return
	* @return:List<DrugWarehouse>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月22日 上午11:55:01
	 */
	@Query(value="from DrugWarehouse d where "
			+ "(d.drugInformation.ypName like ?1 "
			+ "or d.drugInformation.vocode like ?1) "
			+ "and d.state = '否'")
	public List<DrugWarehouse> getDrugByName(String ypNameOrvocode,Pageable page);
	
	/**
	* @Title:getDrugByNameOrproduceDate
	* @Description:根据药品名（音码）查找药品的条数
	* @param:@return
	* @return:List<DrugWarehouse>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月22日 上午11:55:01
	 */
	@Query(value="select count(*) from DrugWarehouse d where "
			+ "(d.drugInformation.ypName like ?1 "
			+ "or d.drugInformation.vocode like ?1 )"
			+ "and d.state = '否'")
	public int getDrugByNameCount(String ypNameOrvocode);
	
	/**
	* @Title:getDrugByNameOrproduceDate
	* @Description:根据药品名（音码）或 库存批次查找药品
	* @param:@return
	* @return:List<DrugWarehouse>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月22日 上午11:55:01
	 */
	@Query(value="from DrugWarehouse d where "
			+ "(d.drugInformation.ypName like ?1 "
			+ "or d.drugInformation.vocode like ?1) "
			+ "and d.produceDate = ?2 "
			+ "and d.state = '否'")
	public List<DrugWarehouse> getDrugByNameOrproduceDate(String ypNameOrvocode,Date prodeceDate,Pageable page);
	
	/**
	* @Title:getDrugByNameOrproduceDate
	* @Description:根据药品名（音码）或 库存批次查找药品的条数
	* @param:@return
	* @return:List<DrugWarehouse>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月22日 上午11:55:01
	 */
	@Query(value="select count(*) from DrugWarehouse d where "
			+ "(d.drugInformation.ypName like ?1 "
			+ "or d.drugInformation.vocode like ?1) "
			+ "and d.produceDate = ?2 "
			+ "and d.state = '否'")
	public int getDrugByNameOrproduceDateCount(String ypNameOrvocode,Date prodeceDate);
	
	/**
	* @Title:getAllOverdueDrug
	* @Description:查找所有过期药品
	* @param:@param todate
	* @param:@param page
	* @param:@return
	* @return:List<DrugWarehouse>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月20日 下午5:33:13
	 */
	@Query(value="from DrugWarehouse d where d.expireDate < ?1 and d.state = '否'")
	public List<DrugWarehouse> getAllOverdueDrug(Date todate);
	
	/**
	* @Title:getAllOverdueDrug
	* @Description:分页查询已经过期的所有药品
	* @param:@param todate
	* @param:@param page
	* @param:@return
	* @return:List<DrugWarehouse>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月20日 下午4:16:50
	 */
	@Query(value="from DrugWarehouse d where d.expireDate < ?1 and d.state = '否'")
	public List<DrugWarehouse> getAllOverdueDrugByPage(Date todate,Pageable page);
		
	/**
	* @Title:getAllOverdueDrugCount
	* @Description:查找已经过期产品的数目
	* @param:@param todate
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年8月20日 下午5:32:30
	 */
	@Query(value="select count(*) from DrugWarehouse d where d.expireDate < ?1 and d.state = '否'")
	public int getAllOverdueDrugCount(Date todate);
	
	/**
	* @Title:batchIsExist
	* @Description:List的长度判断药品批次是否存在
	* @param:@param ypid
	* @param:@param ypProduceDate
	* @param:@return
	* @return:List<DrugWarehouse>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月17日 下午3:31:15
	 */
	@Query("from DrugWarehouse d where d.drugInformation.ypId = ?1 and d.produceDate = ?2")
	public List<DrugWarehouse> batchIsExist(String ypid,Date ypProduceDate);
	
	
	/**
	 * 
	* @Title:getname
	* @Description:TODO根据批次id获得药品名和规格
	* @param:@param pcid
	* @param:@return
	* @return:String[]
	* @throws
	* @author:TRC
	* @Date:2019年8月9日 下午3:54:18
	 */
	@Query(value="select d.drugInformation.ypName,d.drugInformation.ypGuige from DrugWarehouse d where d.pckcId=?1")
	public String [] getname(String pcid);

}
