package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PsBackDetail;
import com.his.pojo.PsBackDetailPK;

/**
 * @ClassName: IPsBackDetailsDao
 * @Description: 药库退药明细dao
 * @author crazy_long
 * @date 2019年7月30日 上午10:55:23
 * 
 */
public interface IPsBackDetailsDao extends CrudRepository<PsBackDetail, PsBackDetailPK> {

}
