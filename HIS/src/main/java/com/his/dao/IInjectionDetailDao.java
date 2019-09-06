package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.InjectionDetail;
import com.his.pojo.InjectionDetailPK;

/**  
* @ClassName: IInjectionDetailDao  
* @Description: 用药明细Dao
* @author Sbaby
* @date 2019年9月3日  下午4:29:32
*    
*/
public interface IInjectionDetailDao extends CrudRepository<InjectionDetail, InjectionDetailPK> {

}
