package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.Examination;

/**  
* @ClassName: IExaminationDao  
* @Description: 体检Dao
* @author Sbaby
* @date 2019年8月22日  上午11:21:11
*    
*/
public interface IExaminationDao extends CrudRepository<Examination, String> {

}
