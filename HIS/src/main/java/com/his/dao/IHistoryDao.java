package com.his.dao;

import com.his.pojo.History;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author Sbaby
 * @Date 2019/08/20 11:56
 * @Version 1.0
 */
public interface IHistoryDao extends CrudRepository<History, String> {

}
