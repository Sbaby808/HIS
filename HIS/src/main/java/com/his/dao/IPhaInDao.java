package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.PhaIn;

/**
 * @ClassName: IPhaInDao
 * @Description: 药房入库单dao
 * @author crazy_long
 * @date 2019年7月30日 上午10:56:59
 * 
 */
public interface IPhaInDao extends CrudRepository<PhaIn, String> {

}
