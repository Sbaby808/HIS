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
	* @Title:getNoPriceCount
	* @Description:查询未划价药品总记录条数
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 下午4:35:23
	 */
	@Query(value = "select count(*) from drug_information d where d.medicine_pay_id is null", nativeQuery = true)
	public int getNoPriceCount();
	
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
	@Query("from DrugInformation d where d.medicinePayId is not null and d.ypName like ?1 ")
	public List <DrugInformation> getAllDrugInformation(String ypName);
	
	/**
	* @Title:getYpType
	* @Description:获取药品大类
	* @param:@return
	* @return:List<String>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月10日 上午12:19:54
	 */
	@Query("select d.ypType from DrugInformation d group by d.ypType")
	public List<String> getAllYpType();
	
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
//			+ "and d.medicinePay.empInformation.ygGh like ?5 "
			+ "and d.medicinePay.medicinePayPrice >= ?5 "
			+ "and d.medicinePay.medicinePayPrice <= ?6 ")
	public int  searchPriceCount(String searchKey, String searchType, String searchSubclass, String searchGys,
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
//			+ "and d.medicinePay.empInformation.ygGh like ?5 "
			+ "and d.medicinePay.medicinePayPrice >= ?5 "
			+ "and d.medicinePay.medicinePayPrice <= ?6 ")
	public List<DrugInformation> searchPriceByPage(String searchKey, String searchType, String searchSubclass, String searchGys,
			BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
/**
	* @Title:searchNoPriceCount
	* @Description:模糊查询未划价药品的数量
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月9日 上午11:14:29
	 */
	@Query(value = "select count(*) from drug_information d "
			+ "where (d.yp_name like ?1 "
			+ "or d.vocode like ?1 )"
			+ "and d.yp_type like ?2 "
			+ "and d.subclass_id like ?3 "
			+ "and d.gys_id like ?4 "
			+ "and d.yp_price >= ?5 "
			+ "and d.yp_price <= ?6 "
			+ "and d.medicine_pay_id is null", nativeQuery = true)
	public int searchNoPriceCount(String searchKey, String searchType, String searchSubclass, String searchGys,
			BigDecimal minPrice, BigDecimal maxPrice);
	
	/**
	* @Title:searchPriceCount
	* @Description:搜索已经划价的药品
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月21日 下午5:15:23
	 */
	@Query(value = "select count(*) from drug_information d "
			+ "where (d.yp_name like ?1 "
			+ "or d.vocode like ?1 )"
			+ "and d.yp_type like ?2 "
			+ "and d.subclass_id like ?3 "
			+ "and d.gys_id like ?4 "
			+ "and d.medicine_pay_id is not null", nativeQuery = true)
	public int searchPriceCount(String searchKey, String searchType, String searchSubclass, String searchGys);
	
	/**
	* @Title:searchNoPrice
	* @Description:搜索未划价记录
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 下午4:33:45
	 */
	@Query(value = "select * from drug_information d "
			+ "where (d.yp_name like ?1 "
			+ "or d.vocode like ?1 )"
			+ "and d.yp_type like ?2 "
			+ "and d.subclass_id like ?3 "
			+ "and d.gys_id like ?4 "
			+ "and d.yp_price >= ?5 "
			+ "and d.yp_price <= ?6 "
			+ "and d.medicine_pay_id is null", nativeQuery = true)
	public List<DrugInformation> searchNoPrice(String searchKey, String searchType, String searchSubclass, String searchGys,
			BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
	
	/**
	* @Title:searchNoPrice
	* @Description:分页搜索已划价药品的信息
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param pageable
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月21日 下午5:19:28
	 */
	@Query(value = "select * from drug_information d "
			+ "where (d.yp_name like ?1 "
			+ "or d.vocode like ?1 )"
			+ "and d.yp_type like ?2 "
			+ "and d.subclass_id like ?3 "
			+ "and d.gys_id like ?4 "
			+ "and d.medicine_pay_id is not null", nativeQuery = true)
	public List<DrugInformation> searchPrice(String searchKey, String searchType, String searchSubclass, String searchGys,
			Pageable pageable);
	
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
	
	/**
	* @Title:searchAllInformationByPage
	* @Description:查询药品的所有信息
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param searchMinorDefect
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@param pageable
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月9日 下午11:10:55
	 */
	@Query("from DrugInformation d "
			+ "where (d.ypName like ?1 "
			+ "or d.vocode like ?1 )"
			+ "and d.ypType like ?2 "
			+ "and d.drugSubclass.subclassId like ?3 "
			+ "and d.supplier.gysId like ?4 "
			+ "and d.drugSubclass.drugMinorDefect.minorDefectsId like ?5 "
			+ "and d.ypPrice >= ?6 "
			+ "and d.ypPrice <= ?7 ")
	public List<DrugInformation> searchAllInformationByPage(String searchKey, String searchType, String searchSubclass, String searchGys, String searchMinorDefect,
			BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);


	/**
	* @Title:searchAllInformationByPageCount
	* @Description:查询符合搜索条件的条数
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param searchMinorDefect
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@param pageable
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年8月10日 上午9:55:56
	 */
	@Query("select count(*) from DrugInformation d "
		+ "where (d.ypName like ?1 "
		+ "or d.vocode like ?1 )"
		+ "and d.ypType like ?2 "
		+ "and d.drugSubclass.subclassId like ?3 "
		+ "and d.supplier.gysId like ?4 "
		+ "and d.drugSubclass.drugMinorDefect.minorDefectsId like ?5 "
		+ "and d.ypPrice >= ?6 "
		+ "and d.ypPrice <= ?7 ")
	public int searchAllInformationByPageCount(String searchKey, String searchType, String searchSubclass, String searchGys, String searchMinorDefect,
			BigDecimal minPrice, BigDecimal maxPrice);
	
	/**
	* @param  
	 * @Title:getNoPrice
	* @Description:查询未划价的药品
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月7日 上午9:22:56
	 */
	@Query(value="select * from drug_information d where d.medicine_pay_id is null order by d.yp_name",nativeQuery=true)
	public List<DrugInformation> getNoPrice(Pageable pageable);
	
	/**
	* @Title:getAllDrugBySubclassId
	* @Description:根据小类id查询对应的所有药品
	* @param:@param subclassId
	* @param:@return
	* @return:List<DrugInformation>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月12日 下午12:09:07
	 */
	@Query(value="from DrugInformation d where d.drugSubclass.subclassId = ?1")
	public List<DrugInformation> queryAllBySubclassId(String subclassId);

}
