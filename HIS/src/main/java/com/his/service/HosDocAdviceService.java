package com.his.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IHosCheckNoticeDao;
import com.his.dao.IHosDocAdviceDao;
import com.his.dao.IHosOutNoticeDao;
import com.his.dao.IHosSurgeryNoticeDao;
import com.his.pojo.HosCheckNotice;
import com.his.pojo.HosDoctorAdvice;
import com.his.pojo.HosOutNotice;
import com.his.pojo.HosSurgeryNotice;

/**
 * 
* @ClassName: HosDocAdviceService  
* @Description: 住院医嘱  
* @author Hamster
* @date 2019年8月7日  上午9:02:25
*
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HosDocAdviceService {

	@Autowired
	private IHosDocAdviceDao hosDocAdviceDao;
	@Autowired
	private IHosCheckNoticeDao hosCheckNoticeDao;
	@Autowired
	private IHosSurgeryNoticeDao hosSurgeryNoticeDao;
	@Autowired
	private IHosOutNoticeDao hosOutNoticeDao;
	
	/**
	 * 
	* @Title:getHosDocAdvice
	* @Description:查询所有住院医嘱
	* @param:@return
	* @return:List<HosDoctorAdvice>
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 上午9:02:37
	 */
	public List <HosDoctorAdvice> getHosDocAdvice(){
		return hosDocAdviceDao.getHosDocAdvice();
	}
	
	/**
	 * 
	* @Title:getHosDocAdviceByDid
	* @Description:根据诊断记录id查询医嘱
	* @param:@param diagId
	* @param:@return
	* @return:HosDoctorAdvice
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 上午10:01:49
	 */
	public HosDoctorAdvice getHosDocAdviceByDid(String diagId){
		return hosDocAdviceDao.getHosDocAdviceByDid(diagId);
	}
	
	/**
	 * 
	* @Title:addHosDocAdvice
	* @Description:新增住院医嘱
	* @param:@param advice
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 上午10:20:49
	 */
	public void addHosDocAdvice(HosDoctorAdvice advice) throws ParseException{
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(new Date());
		advice.setHosStartTime(dateFormat.parse(time));
		hosDocAdviceDao.save(advice);
		
		List <HosCheckNotice> checkNotices = advice.getHosCheckNotices();
		for(int i=0;i<checkNotices.size();i++){
			checkNotices.get(i).setHosCheckNtime(dateFormat.parse(time));
			checkNotices.get(i).setHosDoctorAdvice(advice);
			hosCheckNoticeDao.save(checkNotices.get(i));
		}
		
		List <HosSurgeryNotice> surgeryNotices = advice.getHosSurgeryNotices();
		System.out.println(surgeryNotices.size());
		for(int i=0;i<surgeryNotices.size();i++){
			surgeryNotices.get(i).setHosSurTime(dateFormat.parse(time));
			surgeryNotices.get(i).setHosDoctorAdvice(advice);
			hosSurgeryNoticeDao.save(surgeryNotices.get(i));
		}
		
		List <HosOutNotice> outNotices = advice.getHosOutNotices();
		for(int i=0;i<outNotices.size();i++){
			outNotices.get(i).setHosOutTime(dateFormat.parse(time));
			outNotices.get(i).setHosDoctorAdvice(advice);
			hosOutNoticeDao.save(outNotices.get(i));
		}
	}
	
	/**
	 * 
	* @Title:getDocAdviceByDocId
	* @Description:根据医嘱id查询医嘱
	* @param:@param docId
	* @param:@return
	* @return:HosDoctorAdvice
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 上午11:46:29
	 */
	public HosDoctorAdvice getDocAdviceByDocId(String docId){
		return hosDocAdviceDao.getDocAdviceByDocId(docId);
	}
	
	/**
	 * 
	* @Title:changeHosDocAdvice
	* @Description:修改医嘱
	* @param:@param advice
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 下午12:00:16
	 */
	public void changeHosDocAdvice(HosDoctorAdvice advice){
		hosDocAdviceDao.save(advice);
	}
	
	/**
	 * 
	* @Title:endHosDocAdvice
	* @Description:医嘱终止
	* @param:@param advice
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月7日 下午6:58:50
	 */
	public void endHosDocAdvice(HosDoctorAdvice advice) throws ParseException{
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(new Date());
		advice.setHosEndTime(dateFormat.parse(time));
		hosDocAdviceDao.save(advice);
	}
	
	
	
	
}
