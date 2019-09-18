package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.RemainRecord;

public interface IRemainRecordDAO extends CrudRepository<RemainRecord, String>{
	//分页查询
	@Query("from RemainRecord order by remainTime desc")
	public List<RemainRecord> findbypage(Pageable pageable);
	
	//查询之前的记录，如果是有就不能去执行
	@Query("select count(*) from RemainRecord r where r.medicalCard.cardId=?1 and r.remainState='留观中' ")
	public long findcount(String cardId);
	@Query("select count(*) from RemainRecord")
	public long findcounts();
	//找是不是以前登记过的人
	@Query("select count(*) from RemainRecord r where r.medicalCard.cardId=?1")
	public long findcountbycardid(String cardId);
	//找到留观登记记录，一个人只有一条记录，但有多个历史床位
	@Query("from RemainRecord  r where r.medicalCard.cardId=?1")
	public RemainRecord findone(String cardId);
}
