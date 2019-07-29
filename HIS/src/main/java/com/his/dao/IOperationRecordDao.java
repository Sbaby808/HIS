package com.his.dao;

import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OperationRecord;

public interface IOperationRecordDao extends CrudRepository<OperationRecord, String>{

}
