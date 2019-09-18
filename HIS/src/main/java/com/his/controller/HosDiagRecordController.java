package com.his.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.HosDiagnosticRecord;
import com.his.service.HosDiagRecordService;

/**
 * 
* @ClassName: HosDiagRecordController  
* @Description: 住院诊断记录  
* @author Hamster
* @date 2019年8月6日  上午8:58:19
*
 */
@Controller
public class HosDiagRecordController {

	@Autowired
	private HosDiagRecordService hosDiagRecordService;
	
	/**
	 * 
	* @Title:getDiagRecord
	* @Description:无分页查询所有住院诊断记录
	* @param:@return
	* @return:List<HosDiagnosticRecord>
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午11:39:12
	 */
	@ResponseBody
	@GetMapping("/get_diag_record")
	public List <HosDiagnosticRecord> getDiagRecord(){
		return hosDiagRecordService.getDiagRecord();
	}
	
	/**
	 * 
	* @Title:getDiagRecordByPage
	* @Description:分页查询所有住院诊断记录
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@return
	 * @return 
	* @return:Map
	 * @throws ParseException 
	* @throws
	* @author:Hamster
	* @Date:2019年8月5日 上午11:39:40
	 */
	@ResponseBody
	@GetMapping("/get_diag_record_byPage")
	public Map getDiagRecordByPage(String start,String end,String text1,String text2,String text3,int curpage,int pagesize) throws ParseException{
		String cardName = "%"+text1+"%";
		String ksName = "%"+text2+"%";
		String roomName = "%"+text3+"%";
		return hosDiagRecordService.getDiagRecordByPage(start,end,cardName,ksName,roomName,curpage, pagesize);
	}
	
	/**
	 * 
	* @Title:addHosDiagRecord
	* @Description:新增住院诊断记录
	* @param:@param record
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月6日 上午8:59:02
	 */
	@ResponseBody
	@PostMapping("/add_hos_diag_record")
	public void addHosDiagRecord(@RequestBody HosDiagnosticRecord record) throws ParseException{
		hosDiagRecordService.addHosDiagRecord(record);
	}
	
	/**
	 * 
	* @Title:updateHosDiagRecord
	* @Description:修改住院诊断记录
	* @param:@param record
	* @param:@throws ParseException
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月6日 上午9:00:44
	 */
	@ResponseBody
	@PostMapping("/change_hos_diag_record")
	public void updateHosDiagRecord(@RequestBody HosDiagnosticRecord record) throws ParseException{
		hosDiagRecordService.addHosDiagRecord(record);
	}
	
	/**
	 * 
	* @Title:delHosDiagRecord
	* @Description:删除诊断记录
	* @param:@param record
	* @return:void
	* @throws
	* @author:Hamster
	* @Date:2019年8月6日 上午9:02:41
	 */
	@ResponseBody
	@PostMapping("/del_hos_diag_record")
	public void delHosDiagRecord(@RequestBody HosDiagnosticRecord record){
		hosDiagRecordService.delHosDiagRecord(record);
	}
	
	@ResponseBody
	@GetMapping("/get_diag_record_byMid")
	public List <HosDiagnosticRecord> getDiagRecordbyMid(String medRid){
		return hosDiagRecordService.getDiagRecordbyMid(medRid);
	}
}
