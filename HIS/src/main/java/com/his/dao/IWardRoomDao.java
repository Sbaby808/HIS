package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.WardRoom;

public interface IWardRoomDao extends CrudRepository<WardRoom, String>{
	
	@Query("from WardRoom w")
	public List <WardRoom> getAllWardRoom(Pageable page);
}
