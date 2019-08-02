package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.WardRoom;

/**
 * 住院病房
 * @author dell
 *
 */
public interface IWardRoomDao extends CrudRepository<WardRoom, String>{
	
	@Query("from WardRoom w")
	public List <WardRoom> getAllWardRoom(Pageable page);

	@Query(value="select * from ward_room w where w.ward_id=?1",nativeQuery=true)
	public List <WardRoom> getWardRoomByWid(String wid);
}
