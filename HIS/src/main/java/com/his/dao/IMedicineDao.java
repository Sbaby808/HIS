package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.bean.Medicinebean;
import com.his.pojo.MedicalCard;
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

}
