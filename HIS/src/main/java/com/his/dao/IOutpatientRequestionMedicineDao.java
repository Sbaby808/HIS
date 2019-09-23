package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OutpatientRequestionMedicine;
import com.his.pojo.Outstock;

/**  
* @ClassName: IOutpatientRequestionMedicine  
* @Description: 药品申领表Dao
* @author crazy_long
* @date 2019年7月30日  上午10:20:13
*    
*/
public interface IOutpatientRequestionMedicineDao extends CrudRepository<OutpatientRequestionMedicine, String>{
	
	/**
	* @Title:getAllRequestionForrStatusByPage
	* @Description:分页查询某一个部门的“某一状态”的申领单
	* @param:@param deptId
	* @param:@param state
	* @param:@param page
	* @param:@return
	* @return:List<OutpatientRequestionMedicine>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月21日 下午11:34:20
	 */
	@Query("from OutpatientRequestionMedicine o where o.dept.deptId = ?1 and o.reqStatus=?2 order by o.reqTime desc")
	public List<OutpatientRequestionMedicine> getAllRequestionForrStatusByPage(String deptId,String state,Pageable page);
	
	/**
	* @Title:getAllRequestionForrStatusCount
	* @Description:分页查询某一个部门的“某一状态”的申领单的条数
	* @param:@param deptId
	* @param:@param state
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月21日 下午11:35:57
	 */
	@Query("select count(*) from OutpatientRequestionMedicine o where o.dept.deptId = ?1 and o.reqStatus=?2 ")
	public int getAllRequestionForrStatusCount(String deptId,String state);
	
	/**
	* @Title:getNewCreateById
	* @Description:查看对应部门的申领单
	* @param:@param deptId
	* @param:@return
	* @return:List<OutpatientRequestionMedicine>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月14日 上午10:51:08
	 */
	@Query("from OutpatientRequestionMedicine o where o.dept.deptId = ?1 and o.reqStatus='未提交' ")
	public List<OutpatientRequestionMedicine> getNewCreateById(String deptId);
	
	/**
	* @Title:getAlreadyOutStockByDeptId
	* @Description:查找对应部门已出库状态的申领单
	* @param:@param deptId
	* @param:@return
	* @return:List<OutpatientRequestionMedicine>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午5:25:56
	 */
	@Query("from OutpatientRequestionMedicine o where o.dept.deptId = ?1 and o.reqStatus='已出库' ")
	public List<OutpatientRequestionMedicine> getAlreadyOutStockByDeptId(String deptId);
	
	/**
	* @Title:getAlreadyOutStockByDeptIdCount
	* @Description:查找已入库的申领单条数
	* @param:@param deptId
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月14日 下午4:13:32
	 */
	@Query("select count(*) from OutpatientRequestionMedicine o where o.dept.deptId = ?1 and o.reqStatus='已入库' ")
	public int getHavePutStockCount(String deptId);
	
	
	/**
	* @Title:getOutStockByDeptId
	* @Description:查找对应部门已出库状态的申领单条数
	* @param:@param deptId
	* @param:@return
	* @return:List<Outstock>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午4:59:59
	 */
	@Query("select count(*) from OutpatientRequestionMedicine o where o.dept.deptId = ?1 and o.reqStatus='已出库' ")
	public int getAlreadyOutStockByDeptIdCount(String deptId);
	
	/**
	* @Title:queryRequestBydeptName
	* @Description:根据部门名称查询对应的申领单
	* @param:@param deptName
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年9月2日 上午9:11:26
	 */
	@Query("from OutpatientRequestionMedicine o where o.reqStatus = '未申领'  and o.dept.deptName = ?1 order by o.reqTime desc ")
	public List<OutpatientRequestionMedicine> queryRequestBydeptName(String deptName);
	
	/**
	* @Title:queryRequestCountBydeptId
	* @Description:根据部门id查询对应申领药品的条数
	* @param:@param deptId
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 下午11:51:41
	 */
	@Query("select count(*) from OutpatientRequestionMedicine o where o.reqStatus = '未申领'  and o.dept.deptId = ?1 order by o.reqTime desc ")
	public int queryRequestCountBydeptId(String deptId);
	
	/**
	* @Title:queryRequestForNo
	* @Description:查询未申领状态的申领单
	* @param:@return
	* @return:List<OutpatientRequestionMedicine>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 上午11:09:40
	 */
	@Query("from OutpatientRequestionMedicine o where o.reqStatus = '未申领'  order by o.reqTime desc ")
	public List<OutpatientRequestionMedicine> queryRequestForNo(Pageable page);
	
	/**
	* @Title:queryRequestInNowCount
	* @Description:查询未申领状态的申领单的条数
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年8月28日 下午11:53:53
	 */
	@Query("select count(*) from OutpatientRequestionMedicine o where o.reqStatus = '未申领'  order by o.reqTime desc ")
	public int queryRequestInNowCount();
	
	/**
	* @Title:queryRequestForNo
	* @Description:查询已经申领状态的申领单
	* @param:@return
	* @return:List<OutpatientRequestionMedicine>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 上午11:09:40
	 */
	@Query("from OutpatientRequestionMedicine o where o.reqStatus = '已入库'  order by o.reqTime desc ")
	public List<OutpatientRequestionMedicine> queryRequestForYes(Pageable page);
	
	/**
	* @Title:queryRequestInNowCount
	* @Description:查询已经申领状态的申领单的条数
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年8月28日 下午11:53:53
	 */
	@Query("select count(*) from OutpatientRequestionMedicine o where o.reqStatus = '已入库'  order by o.reqTime desc ")
	public int queryRequestForYesCount();
	
	

}
