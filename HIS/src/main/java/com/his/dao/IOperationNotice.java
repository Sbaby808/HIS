package com.his.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.bean.OpeNoticebean;
import com.his.pojo.OpeNotice;

/**  
* @ClassName: IOperationNotice  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author TRC
* @date 2019年8月2日  上午11:48:40
*    
*/
public interface IOperationNotice extends CrudRepository<OpeNotice, String>{
	@Query(value="select o.solveScheme.history.outpatientRegistration.medicalCard.cardName from OpeNotice o")
      public String getNoticebeans();
}
