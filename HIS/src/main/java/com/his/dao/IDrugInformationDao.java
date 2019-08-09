package com.his.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.DrugInformation;

/**  
* @ClassName: IDrugInformationDao  
* @Description: 药品信息dao
* @author crazy_long
* @date 2019年7月30日  上午10:24:32
*    
*/
public interface IDrugInformationDao extends CrudRepository<DrugInformation, String>{

	/**
	* @Title:getNoPrice
	* @Description:查询未划价的药品
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月7日 上午9:22:56
	 */
	@Query(value="select * from drug_information d where d.medicine_pay_id is null",nativeQuery=true)
	public List<DrugInformation> getNoPrice();
	
	/**
	* @Title:getPrice
	* @Description:查询所有具有收费项的药品信息
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月8日 上午10:16:48
	 */
	@Query(value="select * from drug_information d where d.medicine_pay_id is not null",nativeQuery=true)
	public List<DrugInformation> getPrice();
	
	/**
	* @Title:getPriceByPage
	* @Description:分页查询所有药品信息
	* @param:@param pageable
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月8日 上午10:21:03
	 */
	@Query("from DrugInformation d")
	public List<DrugInformation> getPriceByPage(Pageable pageable);
	
	/**
	 * 
	* @Title:getAllDrugInformation
	* @Description:查询所有药品信息
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 下午9:12:41
	 */
	@Query("from DrugInformation d")
	public List <DrugInformation> getAllDrugInformation();
	
	/**
	* @Title:searchPriceCount
	* @Description:查询符合搜索条件的记录条数
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param searchEmp
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月8日 上午10:43:00
	 */
	@Query("select count(*) from DrugInformation d "
			+ "where (d.ypName like ?1 "
			+ "or d.medicinePay.medicinePayDesc like ?1 )"
			+ "and d.ypType like ?2 "
			+ "and d.drugSubclass.subclassName like ?3 "
			+ "and d.supplier.gysId like ?4 "
			+ "and d.medicinePay.empInformation.ygGh like ?5 "
			+ "and d.medicinePay.medicinePayPrice >= ?6 "
			+ "and d.medicinePay.medicinePayPrice <= ?7 ")
	public int  searchPriceCount(String searchKey, String searchType, String searchSubclass, String searchGys, String searchEmp,
			BigDecimal minPrice, BigDecimal maxPrice);
	
	/**
	* @Title:searchPriceByPage
	* @Description:分页查询符合搜索条件的记录
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param searchEmp
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@param pageable
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月8日 上午10:45:39
	 */
	@Query("from DrugInformation d "
			+ "where (d.ypName like ?1 "
			+ "or d.medicinePay.medicinePayDesc like ?1 )"
			+ "and d.ypType like ?2 "
			+ "and d.drugSubclass.subclassName like ?3 "
			+ "and d.supplier.gysId like ?4 "
			+ "and d.medicinePay.empInformation.ygGh like ?5 "
			+ "and d.medicinePay.medicinePayPrice >= ?6 "
			+ "and d.medicinePay.medicinePayPrice <= ?7 ")
	public List<DrugInformation> searchPriceByPage(String searchKey, String searchType, String searchSubclass, String searchGys, String searchEmp,
			BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
	
	/**
	 * 
	* @Title:getAllDrugInformation
	* @Description:模糊查询
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 下午9:12:41
	 */
	@Query("from DrugInformation d where d.ypName like ?1")
	public List <DrugInformation> getDrugInformation(String ypName);
}
