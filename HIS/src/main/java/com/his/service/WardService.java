package com.his.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IWardDao;
import com.his.pojo.Ward;


/**
 * 住院病区
 * @author dell
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class WardService {

	@Autowired
	private IWardDao wardDao;
	
	/**
	 * 分页查询所有病区
	* @Title:getAllWardByPage
	* @Description:TODO
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	* @return:Map
	* @throws
	* @author:Hamster
	* @Date:2019年7月30日 下午5:12:33
	 */
	public Map getAllWardByPage(String wardName,String ksName,int curpage,int pagesize){
		List <Ward> list = wardDao.getAllWardByPage(wardName,ksName,PageRequest.of(curpage-1, pagesize));
		long total = wardDao.countNum(wardName, ksName);
		Map map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 查询所有病区
	* @Title:getAllWard
	* @Description:TODO
	* @param:@return
	* @return:List<Ward>
	* @throws
	* @author:Hamster
	* @Date:2019年7月30日 下午5:13:47
	 */
	public List <Ward> getAllWard(){
		return wardDao.getAllWard();
	}
	
	
	/**
	 * 新增和修改病区信息
	* @Title:addWard
	* @Description:TODO
	* @param:@param ward
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年7月30日 下午5:12:50
	 */
	public void addWard(Ward ward){
		if(ward.getWardId()!=null){
			wardDao.save(ward);
		}
		else{
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			ward.setWardId(uuid.substring(0,8));
			wardDao.save(ward);
		}	
	}
	
	/**
	 * 
	* @Title:getWardByDid
	* @Description:根据科室id查询病区信息
	* @param:@param ks_id
	* @param:@return
	* @return:List<Ward>
	* @throws
	* @author:Hamster
	* @Date:2019年8月2日 下午8:33:38
	 */
	public List<Ward> getWardByDid(String ks_id){
		return wardDao.getWardByDid(ks_id);
	}
}
