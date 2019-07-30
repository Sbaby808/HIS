package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PhaInDetail;
import com.his.pojo.PhaInDetailPK;

/**
 * @ClassName: PhaInDetailsDao
 * @Description: 药房入库单明细dao
 * @author crazy_long
 * @date 2019年7月30日 上午10:57:58
 * 
 */
public interface IPhaInDetailsDao extends CrudRepository<PhaInDetail, PhaInDetailPK> {

}
