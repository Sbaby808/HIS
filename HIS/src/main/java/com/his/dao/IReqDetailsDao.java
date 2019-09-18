package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.ReqDetail;
import com.his.pojo.ReqDetailPK;

/**  
* @ClassName: IReqDetailsDao  
* @Description: 药品申领明细
* @author crazy_long
* @date 2019年7月30日  上午10:43:23
*    
*/
public interface IReqDetailsDao extends CrudRepository<ReqDetail, ReqDetailPK>{
	
	/**
	* @Title:qeuryRequestDetail
	* @Description:查找有没有未处理的药品
	* @param:@param reqId
	* @param:@return
	* @return:List<ReqDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年9月3日 下午3:49:26
	 */
	@Query("select count(*) from ReqDetail r where r.outpatientRequestionMedicine.reqId = ?1 and r.state = '未处理'")
	public int isHaveNoOut(String reqId);
	
	/**
	* @Title:qeuryRequestDetail
	* @Description:根据申领单号查找对应的明细
	* @param:@param reqId
	* @param:@return
	* @return:List<ReqDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 下午7:40:43
	 */
	@Query("from ReqDetail r where r.outpatientRequestionMedicine.reqId = ?1 and r.state = '未处理'")
	public List<ReqDetail> qeuryRequestDetail(String reqId);
	
	/**
	* @Title:queryForNoRequest
	* @Description:查找未申领中的药品
	* @param:@param page
	* @param:@return
	* @return:List<ReqDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 上午12:15:38
	 */
	@Query("from ReqDetail r where r.outpatientRequestionMedicine.reqStatus = '否'  order by r.outpatientRequestionMedicine.reqTime desc")
	public List<ReqDetail> queryForNoRequest(Pageable page);
	
	/**
	* @Title:queryForNoRequest
	* @Description:查找未申领中的药品的条数
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 上午10:55:22
	 */
	@Query("select count(*) from ReqDetail r where r.outpatientRequestionMedicine.reqStatus = '否'  ")
	public int queryForNoRequestCount();
	
	/**
	* @Title:queryForYesRequest
	* @Description:查找已申领中的药品
	* @param:@param page
	* @param:@return
	* @return:List<ReqDetail>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 上午12:16:56
	 */
	@Query("from ReqDetail r where r.outpatientRequestionMedicine.reqStatus = '是'  order by r.outpatientRequestionMedicine.reqTime desc")
	public List<ReqDetail> queryForYesRequest(Pageable page);
	
	/**
	* @Title:queryForYesRequest
	* @Description:查找已申领中的药品的条数
	* @param:@return
	* @return:int
	* @throws
	* @author:crazy_long
	* @Date:2019年8月29日 上午10:56:49
	 */
	@Query("select count(*) from ReqDetail r where r.outpatientRequestionMedicine.reqStatus = '是'  ")
	public int queryForYesRequestCount();
	
}
