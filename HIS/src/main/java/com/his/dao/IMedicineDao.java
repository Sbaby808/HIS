package com.his.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.DrugInformation;
import com.his.pojo.DrugWarehouse;
import com.his.pojo.Medicine;

/**
 * @ClassName: IMedicineDao
 * @Description: 药房药品库存dao
 * @author crazy_long
 * @date 2019年7月30日 上午10:59:20
 * 
 */
public interface IMedicineDao extends CrudRepository<Medicine, String> {
	
	/**
	* @Title:getAllWarehouse
	* @Description:查找对应部门存在库存且没有过期的药品 
	* @param:@param ypId
	* @param:@param deptId
	* @param:@return
	* @return:List<DrugWarehouse>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月9日 下午5:08:34
	 */
	@Query("from Medicine m where m.drugWarehouse.drugInformation.ypId = ?1 and m.dept.deptId = ?2 and m.medicineName > 0 and m.drugWarehouse.state = '否'  order by m.drugWarehouse.produceDate asc")
	public List<Medicine> getMedicineCanUse(String ypId,String deptId);
	
	/**
	* @Title:searchDrugByPage
	* @Description:查找某一个部门的药房药品
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param searchMinorDefect
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@param minNumber
	* @param:@param maxNumber
	* @param:@param deptId
	* @param:@param pageable
	* @param:@return
	* @return:List<Medicine>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午2:06:59
	 */
	@Query("from Medicine d "
			+ "where (d.drugWarehouse.drugInformation.ypName like ?1 "
			+ "or d.drugWarehouse.drugInformation.vocode like ?1 )"
			+ "and d.drugWarehouse.drugInformation.ypType like ?2 "
			+ "and d.drugWarehouse.drugInformation.drugSubclass.subclassId like ?3 "
			+ "and d.drugWarehouse.drugInformation.supplier.gysId like ?4 "
			+ "and d.drugWarehouse.drugInformation.drugSubclass.drugMinorDefect.minorDefectsId like ?5 "
			+ "and d.drugWarehouse.drugInformation.ypPrice >= ?6 "
			+ "and d.drugWarehouse.drugInformation.ypPrice <= ?7 "
			+ "and d.drugWarehouse.state = '否' "
			+ "and d.medicineName >= ?8 "
			+ "and d.medicineName <= ?9 "
			+ "and d.dept.deptId = ?10 ")
	public List<Medicine> searchDrugByPage(String searchKey, String searchType, String searchSubclass, String searchGys, String searchMinorDefect,
			BigDecimal minPrice, BigDecimal maxPrice,BigDecimal minNumber,BigDecimal maxNumber,String deptId,Pageable pageable);
	
	/**
	* @Title:searchDrugCount
	* @Description:查找某一个部门的药房药品的条数
	* @param:@param searchKey
	* @param:@param searchType
	* @param:@param searchSubclass
	* @param:@param searchGys
	* @param:@param searchMinorDefect
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@param minNumber
	* @param:@param maxNumber
	* @param:@param deptId
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 下午2:07:28
	 */
	@Query("select count(*) from Medicine d "
			+ "where (d.drugWarehouse.drugInformation.ypName like ?1 "
			+ "or d.drugWarehouse.drugInformation.vocode like ?1 )"
			+ "and d.drugWarehouse.drugInformation.ypType like ?2 "
			+ "and d.drugWarehouse.drugInformation.drugSubclass.subclassId like ?3 "
			+ "and d.drugWarehouse.drugInformation.supplier.gysId like ?4 "
			+ "and d.drugWarehouse.drugInformation.drugSubclass.drugMinorDefect.minorDefectsId like ?5 "
			+ "and d.drugWarehouse.drugInformation.ypPrice >= ?6 "
			+ "and d.drugWarehouse.drugInformation.ypPrice <= ?7 "
			+ "and d.drugWarehouse.state = '否' "
			+ "and d.medicineName >= ?8 "
			+ "and d.medicineName <= ?9 "
			+ "and d.dept.deptId = ?10 ")
		public int searchDrugCount(String searchKey, String searchType, String searchSubclass, String searchGys, String searchMinorDefect,
				BigDecimal minPrice, BigDecimal maxPrice,BigDecimal minNumber,BigDecimal maxNumber,String deptId);
	
	/**
	* @Title:queryNoKuCun
	* @Description:判断一个部门的这个药品的批次存不存在
	* @param:@param pckcId
	* @param:@param deptId
	* @param:@return
	* @return:Medicine
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 上午12:44:09
	 */
	@Query("from Medicine m where m.drugWarehouse.pckcId = ?1 and m.dept.deptId = ?2 ")
	public Medicine warehouseIsHave(String pckcId ,String deptId);
	
	/**
	* @Title:queryNoKuCun
	* @Description:查找没有库存的药品
	* @param:@param minNuber
	* @param:@param maxNumber
	* @param:@param deptId
	* @param:@return
	* @return:List<Medicine>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月6日 上午12:39:46
	 */
	@Query("from Medicine m where m.medicineName >= ?1 and m.medicineName <= ?2 and m.dept.deptId = ?3 and m.drugWarehouse.nowNumber = 0 and m.drugWarehouse.state = '否'")
	public List<Medicine> queryNoKuCun(BigDecimal minNuber,BigDecimal maxNumber,String deptId);
	
	/**
	* @Title:queryByNowNumber
	* @Description:根据药房药品库存段查找对应的药品
	* @param:@param minNuber
	* @param:@param maxNumber
	* @param:@param page
	* @param:@return
	* @return:List<Medicine>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月26日 下午4:28:07
	 */
	@Query("from Medicine m where m.medicineName >= ?1 and m.medicineName <= ?2 and m.dept.deptId = ?3 and m.drugWarehouse.nowNumber > 0 and m.drugWarehouse.state = '否'")
	public List<Medicine> queryNowNumber(BigDecimal minNuber,BigDecimal maxNumber,String deptId);
	
	/**
	* @Title:queryNowNumberByPage
	* @Description:根据药房药品库存段查找对应的药品的数量
	* @param:@param minNuber
	* @param:@param maxNumber
	* @param:@param page
	* @param:@return
	* @return:List<Medicine>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月26日 下午4:42:42
	 */
	@Query("select count(*) from Medicine m where m.medicineName >= ?1 and m.medicineName <= ?2 and m.dept.deptId = ?3 and m.drugWarehouse.nowNumber > 0 and m.drugWarehouse.state = '否'")
	public int queryNowNumberCount(BigDecimal minNuber,BigDecimal maxNumber,String deptId);
	
	/**
	 * 
	* @Title:getMedicinebeans
	* @Description:TODO分组查询所有药品名，规格，批次id，过期时间。根据过期时间排序
	* @param:@return
	* @return:List<String[]>
	* @throws
	* @author:TRC
	* @Date:2019年8月9日 下午3:54:50
	 */
	@Query(value="select m.drugWarehouse.drugInformation.ypName,m.drugWarehouse.drugInformation.ypGuige,m.drugWarehouse.pckcId,m.drugWarehouse.expireDate from Medicine m group by m.drugWarehouse.drugInformation.ypName,m.drugWarehouse.drugInformation.ypGuige,m.drugWarehouse.pckcId,m.drugWarehouse.expireDate order by m.drugWarehouse.expireDate")
	public List<String []> getMedicinebeans();
	/**
	 * 
	* @Title:getmid
	* @Description:TODO根据批次id查药品库存编号
	* @param:@param pcid
	* @param:@return
	* @return:String[]
	* @throws
	* @author:TRC
	* @Date:2019年8月9日 下午3:56:22
	 */
	@Query(value="select m.medicineId from Medicine m where m.drugWarehouse.pckcId=?1")
	public String[] getmid(String pcid);

	/**
	 * 
	* @Title:getMedBypcId
	* @Description:根据批次id查询住院部门的药品信息
	* @param:@param pcId
	* @param:@return
	* @return:Medicine
	* @throws
	* @author:Hamster
	* @Date:2019年8月26日 下午3:09:13
	 */
	@Query("from Medicine m where m.dept.deptId='cccccccc' and m.drugWarehouse.pckcId=?1")
	public Medicine getMedBypcId(String pcId);
	
	/**
	* @Title:checkMedicineNum
	* @Description:查询药房药品库存
	* @param:@param ypId
	* @param:@param deptId
	* @param:@return
	* @return:Medicine
	* @throws
	* @author:Sbaby
	* @Date:2019年9月3日 下午9:32:19
	 */
	@Query("from Medicine m where m.drugWarehouse.drugInformation.ypId = ?1 and m.dept.deptId = ?2")
	public Medicine checkMedicineNum(String ypId, String deptId);
}
