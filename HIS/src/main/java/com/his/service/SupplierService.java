package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.ISupplierDao;
import com.his.pojo.Supplier;
import com.his.utils.ServiceException;

/**  
* @ClassName: SupplierService  
* @Description: 药品供应商service
* @author crazy_long
* @date 2019年8月6日  下午10:36:59
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class SupplierService {

	@Autowired
	private ISupplierDao supplierdao;
	
	/**
	* @Title:addSupplier
	* @Description:添加一个供应商
	* @param:@param supplier
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月6日 下午10:42:10
	 */
	public void addSupplier(Supplier supplier) throws ServiceException{
		try {
			supplier.setGysId(UUID.randomUUID().toString().replace("-", ""));
			supplierdao.save(supplier);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	/**
	* @Title:updataSupplier
	* @Description:修改供应商信息
	* @param:@param editsupplier
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月7日 上午9:44:31
	 */
	public void updataSupplier(Supplier editsupplier) throws ServiceException{
		try {
			supplierdao.save(editsupplier);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("供应商信息修改失败");
		}
	}
	
	/**
	* @Title:getAllSuppliers
	* @Description:分页查找供应商
	* @param:@param curpage
	* @param:@param pageSize
	* @param:@return
	* @return:List<Supplier>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月7日 上午12:05:15
	 */
	public Map getSupplierByPage(int curpage,int pageSize){
		Map map = new HashMap();
		List<Supplier> list = supplierdao.queryByPage(PageRequest.of(curpage-1, pageSize));
		long total = supplierdao.count();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
}
