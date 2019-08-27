package com.his.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Medicine;

/**
 * @ClassName: IMedicineDao
 * @Description: 药房药品dao
 * @author crazy_long
 * @date 2019年7月30日 上午10:59:20
 * 
 */
public interface IMedicineDao extends CrudRepository<Medicine, String> {
	
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
	@Query("from Medicine m where m.medicineName >= ?1 and m.medicineName <= ?2 and m.drugWarehouse.state = '否'")
	public List<Medicine> queryNowNumber(BigDecimal minNuber,BigDecimal maxNumber);
	
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
	@Query("select count(*) from Medicine m where m.medicineName >= ?1 and m.medicineName <= ?2 and m.drugWarehouse.state = '否'")
	public int queryNowNumberCount(BigDecimal minNuber,BigDecimal maxNumber);
	
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
}
