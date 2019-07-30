package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.AllocationOutbound;

/**  
* @ClassName: IAllocationOutboundDao  
* @Description: 调拨出库表dao 
* @author crazy_long
* @date 2019年7月30日  上午11:01:18
*    
*/
public interface IAllocationOutboundDao extends CrudRepository<AllocationOutbound, String>{

}
