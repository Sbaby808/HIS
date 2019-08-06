package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IWaitingRoomDao;
import com.his.pojo.WaitingRoom;

/**  
* @ClassName: WaitingRoomService  
* @Description: 候诊厅Service 
* @author crazy_long
* @date 2019年8月3日  上午9:57:39
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class WaitingRoomService {
	
	@Autowired
	private IWaitingRoomDao waitingroomdao;

	/**
	 * 
	* @Title:queryAllWaitingroom
	* @Description:获取所有的候诊厅信息
	* @param:@return
	* @return:List<WaitingRoom>
	* @throws
	* @author:crazy_long
	* @Date:2019年8月3日 上午10:03:12
	 */
	public List<WaitingRoom> queryAllWaitingroom(){
		return (List<WaitingRoom>) waitingroomdao.findAll();
	}
	
	/**
	* @Title:addWaitingroom
	* @Description:插入候诊厅test
	* @param:@param waitingRoom
	* @return:void
	* @throws
	* @author:crazy_long
	* @Date:2019年8月3日 上午10:27:02
	 */
	public void addWaitingroom(WaitingRoom waitingRoom) {
		waitingroomdao.save(waitingRoom);
	}
	
	public WaitingRoom getWaitingroomById(String wid) {
		return waitingroomdao.findById(wid).get();
	}
}
