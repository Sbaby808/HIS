package com.his.dao;

import com.his.pojo.OutMedicalRecord;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author Sbaby
 * @Description 门诊就诊排队Dao
 * @Date 2019/08/17 15:46
 * @Version 1.0
 */
public interface IOutMedicalRecordDao extends CrudRepository<OutMedicalRecord, String> {
}
