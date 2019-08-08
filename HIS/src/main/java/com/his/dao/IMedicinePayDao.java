package com.his.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.MedicinePay;

/**  
* @ClassName: IMedicinePayDao  
* @Description: 药品划价项Dao
* @author Sbaby
* @date 2019年8月6日  下午5:01:48
*    
*/
public interface IMedicinePayDao extends CrudRepository<MedicinePay, String> {
	
	/**
	* @Title:getByPage
	* @Description:分页查询药品收费项
	* @param:@param pageable
	* @param:@return
	* @return:List<MedicinePay>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 下午5:18:50
	 */
	@Query("from MedicinePay m")
	public List<MedicinePay> getByPage(Pageable pageable);
	
	/**
	* @Title:getAllCount
	* @Description:查询药品划价项总记录条数
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 下午5:35:19
	 */
	@Query("select count(*) from MedicinePay m")
	public int getAllCount();
	
	/**
	* @Title:findByUUID
	* @Description:根据id查询药品收费项
	* @param:@param id
	* @param:@return
	* @return:MedicinePay
	* @throws
	* @author:Sbaby
	* @Date:2019年8月7日 上午11:20:45
	 */
	@Query("from MedicinePay m where m.medicinePayId = ?1")
	public MedicinePay findByUUID(String id);
	
	/**
	* @Title:searchByPage
	* @Description:根据条件搜索
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchGys
	* @param:@param searchEmp
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@param pageable
	* @param:@return
	* @return:List<MedicinePay>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月8日 上午8:42:25
	 */
	@Query("from MedicinePay m "
			+ "where (m.drugInformation.ypName like ?1 "
			+ "or m.medicinePayDesc like ?1 )"
			+ "and (m.drugInformation.ypType like ?2 )"
			+ "and (m.drugInformation.drugSubclass.subclassId like ?3) "
			+ "and (m.drugInformation.supplier.gysId like ?4) "
			+ "and (m.empInformation.ygxh like ?5) "
			+ "and m.medicinePayPrice >= ?6 and m.medicinePayPrice <= ?7 ")
	public List<MedicinePay> searchByPage(String searchKey, String searchType, String searchSubclass, String searchGys, String searchEmp, 
			BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
	
	/**
	* @Title:searchCount
	* @Description:查询符合搜索条件的记录条数
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchGys
	* @param:@param searchEmp
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月8日 上午8:43:50
	 */
	@Query("select count(*) from MedicinePay m "
			+ "where (m.drugInformation.ypName like ?1 "
			+ "or m.medicinePayDesc like ?1 )"
			+ "and (m.drugInformation.ypType like ?2 )"
			+ "and (m.drugInformation.drugSubclass.subclassId like ?3) "
			+ "and (m.drugInformation.supplier.gysId like ?4) "
			+ "and (m.empInformation.ygxh like ?5) "
			+ "and m.medicinePayPrice >= ?6 and m.medicinePayPrice <= ?7 ")
	public int searchCount(String searchKey, String searchType, String searchSubclass, String searchGys, String searchEmp,
			BigDecimal minPrice, BigDecimal maxPrice);
}
