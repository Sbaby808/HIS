package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.MedicalCard;
import com.his.pojo.Medicine;

/**
 * @ClassName: IMedicineDao
 * @Description: 药房药品dao
 * @author crazy_long
 * @date 2019年7月30日 上午10:59:20
 * 
 */
public interface IMedicineDao extends CrudRepository<Medicine, String> {

}
