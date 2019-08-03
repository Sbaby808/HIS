package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.bean.OpeNoticebean;
import com.his.dao.IOperationNotice;
import com.his.pojo.OpeNotice;

/**  
* @ClassName: OpeNoticeService  
* @Description: TODO(手术通知单service)  
* @author TRC
* @date 2019年8月2日  上午11:50:58
*    
*/
@Service
public class OpeNoticeService {
	@Autowired
	private IOperationNotice iOperationNotice;
    public List<OpeNotice> getallNotices(){
	  return (List<OpeNotice>) iOperationNotice.findAll();
  }
    public String getaNoticebeans(){
    	return iOperationNotice.getNoticebeans();
    }
    
}
