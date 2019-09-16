package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Purchase;

/**  
* @ClassName: IPurchaseDao  
* @Description: 药品采购dao
* @author crazy_long
* @date 2019年7月30日  上午11:08:35
*    
*/
public interface IPurchaseDao extends CrudRepository<Purchase, String>{
	
	/**
	* @Title:getAllHaveToSubmit
	* @Description:获取已提交的采购单
	* @param:@return
	* @return:List<Purchase>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月15日 上午2:26:28
	 */
	@Query("from Purchase p where p.state = '已提交'  order by p.cgTime desc")
	public List<Purchase> getAllHaveToSubmit();
	
	/**
	* @Title:getAllHaveToSubmit
	* @Description:分页获取已提交的采购单
	* @param:@return
	* @return:List<Purchase>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月15日 上午2:26:50
	 */
	@Query("from Purchase p where p.state = '已提交'  order by p.cgTime desc")
	public List<Purchase> getAllHaveToSubmitByPage(Pageable page);
	
	/**
	* @Title:getAllForNo
	* @Description:查询所有未执行的采购计划
	* @param:@return
	* @return:List<Purchase>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 上午9:24:56
	 */
	@Query("from Purchase p where p.state = '未提交'  order by p.cgTime desc")
	public List<Purchase> getAllForNoDo();
	
	/**
	* @Title:getAllForNo
	* @Description:分页查询所有未执行的采购计划
	* @param:@return
	* @return:List<Purchase>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 上午9:24:56
	 */
	@Query("from Purchase p where p.state = '未提交'  order by p.cgTime desc")
	public List<Purchase> getAllForNo(Pageable page);
	
	/**
	* @Title:getAllForNoCount
	* @Description:分页查询未执行的采购计划的数量
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 上午10:45:11
	 */
	@Query("select count(*) from Purchase p where p.state = '已提交'  order by p.cgTime desc")
	public int getAllForNoCount();
	
	/**
	* @Title:getAllForYes
	* @Description:分页查询所有已执行的采购计划
	* @param:@param page
	* @param:@return
	* @return:List<Purchase>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 下午2:52:21
	 */
	@Query("from Purchase p where p.state = '已入库'  order by p.cgTime desc")
	public List<Purchase> getAllForYes(Pageable page);
	
	/**
	* @Title:getAllForYesCount
	* @Description:分页查询已执行的采购计划的数量
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年8月14日 下午2:53:23
	 */
	@Query("select count(*) from Purchase p where p.state = '已入库'  order by p.cgTime desc")
	public int getAllForYesCount();

}
