package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.his.bean.OperationPaybean;
import com.his.dao.ICheckItemDAO;
import com.his.pojo.CheckItem;

/**  
* @ClassName: CheckItemService  
* @Description: TODO(检查小项service)  
* @author TRC
* @date 2019年8月12日  上午8:53:03
*    
*/
@Service
public class CheckItemService {
	@Autowired
	private ICheckItemDAO iCheckItemDAO;
	/**
	 * 
	* @Title:AddCheckItem
	* @Description:TODO添加检查小项
	* @param:@param list
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年8月12日 上午9:02:56
	 */
	public void AddCheckItem(List<CheckItem> list) {
		for (CheckItem checkItem : list) {
			
			iCheckItemDAO.save(checkItem);
		}
	}
	/**
	 * 
	* @Title:getallItems
	* @Description:TODO查看所有小项
	* @param:@return
	* @return:List<CheckItem>
	* @throws
	* @author:TRC
	* @Date:2019年8月12日 上午9:47:06
	 */
	public Map getallcheck(int curpage, int pagesize,String sou){
    	List<CheckItem> list=iCheckItemDAO.getallcheck(sou, PageRequest.of(curpage - 1,
		  pagesize));
    	  long total=iCheckItemDAO.getcount(sou);
    	  Map map = new HashMap();
		  map.put("total", total);
		  map.put("list", list);
    	return map;
    }
   
}
