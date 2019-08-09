package com.his.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IDrugSubclassDao;
import com.his.pojo.DrugSubclass;
import com.his.utils.ServiceException;

/**  
* @ClassName: DrugSubclassService  
* @Description:药品小类service
* @author crazy_long
* @date 2019年7月30日  下午12:10:52
*    
*/

@Service
@Transactional(rollbackFor=Exception.class)
public class DrugSubclassService {
	
	@Autowired
	private IDrugSubclassDao drugSubclassDao;
	
	/**
	* @Title:addDrugSubclass
	* @Description:添加药品小类
	* @param:@param drugSubclass
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月8日 上午9:59:48
	 */
	public void addDrugSubclass(DrugSubclass drugSubclass) throws ServiceException{
		try {
			drugSubclass.setSubclassId(UUID.randomUUID().toString().replace("-", ""));
			drugSubclassDao.save(drugSubclass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("添加小类药品失败");
		}
	}
	
	/**
	* @Title:updataDrugSubclass
	* @Description:修改小类药品信息
	* @param:@param drugSubclass
	* @param:@throws ServiceException
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月8日 上午10:05:32
	 */
	public void updataDrugSubclass(DrugSubclass drugSubclass) throws ServiceException{
		try {
			drugSubclassDao.save(drugSubclass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("修改小类失败");
		}
	}
	
	/**
	* @Title:queryDrugSubclassById
	* @Description:根据id查找对应的小类信息
	* @param:@param subclassId
	* @param:@return
	* @return:DrugSubclass
	* @throws
	* @author:crazy_long
	* @Date:2019年8月8日 上午10:07:16
	 */
	public DrugSubclass queryDrugSubclassById(String subclassId) {
		return drugSubclassDao.findById(subclassId).get();
	}
	
	/**
	* @Title:queryAllDrugSubclass
	* @Description:查找所有的小类药品信息
	* @param:@return
	* @return:List<DrugSubclass>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月8日 上午10:08:54
	 */
	public List<DrugSubclass> queryAllDrugSubclass() {
		return (List<DrugSubclass>) drugSubclassDao.findAll();
	}
	
	/**
	* @Title:queryEmpByPage
	* @Description:分页查询所有药品小类的信息
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:crazy_long
	* @Date:2019年8月8日 上午10:14:25
	 */
	public Map queryEmpByPage(int curpage, int pagesize) {
		List<DrugSubclass> list = drugSubclassDao.qeuryAllByPage(PageRequest.of(curpage - 1, pagesize));
		//把员工角色循环加入到list
		long total = drugSubclassDao.count();
		Map map = new HashMap();
		map.put("total", total);
		map.put("list", list);

		return map;
	}
	
	/**
	* @Title:getAllSubclass
	* @Description:获取所有药品小类以及相应的中类
	* @param:@return
	* @return:Map<String,List<DrugSubclass>>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月8日 下午2:56:40
	 */
	public Map<String, List<DrugSubclass>> getAllSubclass() {
		List<DrugSubclass> list =  (List<DrugSubclass>) drugSubclassDao.findAll();
		Map<String, List<DrugSubclass>> map = new HashMap<>();
		for (DrugSubclass subclass : list) {
			if(!map.containsKey(subclass.getDrugMinorDefect().getMinorDefectsName())) {
				List<DrugSubclass> nlist = new ArrayList<>();
				nlist.add(subclass);
				map.put(subclass.getDrugMinorDefect().getMinorDefectsName(), nlist);
			} else {
				map.get(subclass.getDrugMinorDefect().getMinorDefectsName()).add(subclass);
			}
		}
		return map;
	}

}
