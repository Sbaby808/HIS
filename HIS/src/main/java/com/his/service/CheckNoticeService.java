package com.his.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.bean.Checknoticebean;
import com.his.dao.ICheckItemDAO;
import com.his.dao.ICheckNoticeDao;
import com.his.dao.ICheckPayDao;
import com.his.dao.ICheckPayRecordDao;
import com.his.dao.ICheckResultDetailDao;
import com.his.dao.ICheckResultFormDao;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IMedicalCardDao;
import com.his.pojo.CheckItem;
import com.his.pojo.CheckNoticeForm;
import com.his.pojo.CheckPay;
import com.his.pojo.CheckPayRecord;
import com.his.pojo.CheckResultDetail;
import com.his.pojo.CheckResultDetailPK;
import com.his.pojo.CheckResultForm;
import com.his.utils.UUIDGenerator;

import oracle.net.aso.p;

/**  
* @ClassName: CheckNoticeService  
* @Description: TODO(检查通知单service)  
* @author TRC
* @date 2019年8月13日  下午4:05:31
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class CheckNoticeService {
	@Autowired
	private ICheckNoticeDao iCheckNoticeDao;
	@Autowired
	private ICheckPayDao iCheckPayDao;
	@Autowired
	private IEmpInformationDao iEmpInformationDao;
	@Autowired
	private IMedicalCardDao iMedicalCardDao;
	@Autowired
	private ICheckPayRecordDao iCheckPayRecordDao;
	@Autowired
	private ICheckItemDAO iCheckItemDAO;
	@Autowired
	private ICheckResultFormDao iCheckResultFormDao;
	@Autowired
	private ICheckResultDetailDao iCheckResultDetailDao;
	/**
	 * 
	* @Title:get
	* @Description:TODO获得门诊的检查通知单
	* @param:@param curpage
	* @param:@param pagesize
	* @param:@param sou
	* @param:@return
	* @return:Map
	* @throws
	* @author:TRC
	* @Date:2019年8月14日 下午3:02:06
	 */
	public Map get(int curpage, int pagesize,String sou){
		List<Checknoticebean> cList=new ArrayList<Checknoticebean>();
		List<CheckNoticeForm> list=(List<CheckNoticeForm>) iCheckNoticeDao.getbysoufenye(sou, PageRequest.of(curpage - 1,
		  pagesize));	
		for (CheckNoticeForm checkNoticeForm : list) {	
			String a[]=iCheckNoticeDao.getbnameandid(checkNoticeForm.getMcheckId()).split(",");
			Checknoticebean cbean=new Checknoticebean(checkNoticeForm.getMcheckId(), a[0], a[1], a[2], a[3], checkNoticeForm.getMcheckComment(), checkNoticeForm.getMcheckTime());
		    cList.add(cbean);
		}
		long total=iCheckNoticeDao.getcount(sou);
		Map map=new HashMap();
		map.put("list", cList);
		map.put("total", total);
		return map;
	}
	public BigDecimal getmoneybyid(String checkpayid) {
		CheckPay cpay=iCheckPayDao.findById(checkpayid).get();
		return cpay.getCheckPayMoney();
	}
	/**
	 * 
	* @Title:getbyid
	* @Description:TODO根据检查项id获得检查项pojo
	* @param:@param str
	* @param:@return
	* @return:List<CheckPay>
	* @throws
	* @author:TRC
	* @Date:2019年8月14日 下午4:17:25
	 */
	public List<CheckPay> getbyid(String str){
		List<CheckPay> list=new ArrayList<CheckPay>();
		String a[]=str.split(",");
		for (String string : a) {
			CheckPay checkPay=iCheckPayDao.findById(string).get();
			list.add(checkPay);
		}
		return list;
	}
	public void checkpayfun(String brcard_id,String checkpayid,String noticeid,String ygxh) {
		CheckPayRecord pRecord=new CheckPayRecord();
		UUIDGenerator uuid=new UUIDGenerator();
		pRecord.setCheckJfId(uuid.getUUID());
		pRecord.setCheckJfTime(new Date());
		pRecord.setCheckPay(iCheckPayDao.findById(checkpayid).get());
		pRecord.setEmpInformation(iEmpInformationDao.findById(ygxh).get());
		pRecord.setMedicalCard(iMedicalCardDao.findById(brcard_id).get());
        iCheckPayRecordDao.save(pRecord);
        CheckNoticeForm cForm=iCheckNoticeDao.findById(noticeid).get();
        cForm.setMcheckComment("已处理");
        iCheckNoticeDao.save(cForm);
	}
	public void picheckfun(String brcard_id,String checkpayid,String noticeid,String ygxh) {
		String a[]=checkpayid.split(",");
		for (String string : a) {
			UUIDGenerator uuid=new UUIDGenerator();
			CheckPayRecord pRecord=new CheckPayRecord();
			pRecord.setCheckJfId(uuid.getUUID());
			pRecord.setCheckJfTime(new Date());
			pRecord.setCheckPay(iCheckPayDao.findById(string).get());
			pRecord.setEmpInformation(iEmpInformationDao.findById(ygxh).get());
			pRecord.setMedicalCard(iMedicalCardDao.findById(brcard_id).get());
			iCheckPayRecordDao.save(pRecord);
		}
		String b[]=noticeid.split(",");
		for (String stringb : b) {
			CheckNoticeForm cForm=iCheckNoticeDao.findById(stringb).get();
			cForm.setMcheckComment("已处理");
			iCheckNoticeDao.save(cForm);
		}
	}
	public List<CheckPay> getPays(){
		return (List<CheckPay>) iCheckPayDao.findAll();
	}
	public Map getcheckresult(String card_id,String cheid){
		CheckPayRecord cRecord=iCheckPayRecordDao.getPayRecord(card_id, cheid);
		if(cRecord!=null) {
			List<String> list=new ArrayList<String>();
			List<String> listid=new ArrayList<String>();
			String b[]=iCheckPayDao.findById(cheid).get().getCheckFormPath().split(",");
			for (String string : b) {
				CheckItem checkItem=iCheckItemDAO.findById(string).get();
			    list.add(checkItem.getItemName()+":"+checkItem.getCheckItemStd());
			    listid.add(checkItem.getCheckItemId());
			}
			String name=iMedicalCardDao.findById(card_id).get().getCardName();
			Map map=new HashMap();
			map.put("list", list);
			map.put("listid", listid);
			map.put("name", name);
			return map;
		}
		else {
			return null;
		}
		
	}
	public List<Integer> fuzhi(int changdu){
		List<Integer>  list=new ArrayList<Integer>();
		for(int i=0;i<changdu;i++) {
			Integer cInteger=(int) (1+Math.random()*(20-1+1));
			list.add(cInteger);
		}
		return list;
	}
	public void addcheckrecord(String card_id,String cheid,String itemid,String itemval,String advice,String ygxh) {
		CheckPayRecord cRecord=iCheckPayRecordDao.getPayRecord(card_id, cheid);
		UUIDGenerator uuid=new UUIDGenerator();
		String crecordid=uuid.getUUID();
		CheckResultForm cForm=new CheckResultForm();
		cForm.setCheckComment(advice);
	    cForm.setCheckResultId(crecordid);
	    cForm.setCheckTime(new Date());
	    cForm.setEmpInformation(iEmpInformationDao.findById(ygxh).get());
	    iCheckResultFormDao.save(cForm);
	    CheckResultForm ccForm=iCheckResultFormDao.findById(crecordid).get();
	    cRecord.setCheckResultForm(ccForm);
	    iCheckPayRecordDao.save(cRecord);
	    String a[]=itemid.split(",");
	    String b[]=itemval.split(",");
	    int len=a.length;
	    for(int i=0;i<len;i++) {
	    	CheckResultDetail cDetail=new CheckResultDetail();
	    	cDetail.setCheckItemVal(b[i]);
	    	CheckResultDetailPK pk=new CheckResultDetailPK();
	    	pk.setCheckItemId(a[i]);
	    	pk.setCheckResultId(crecordid);
	    	cDetail.setId(pk);
	    	iCheckResultDetailDao.save(cDetail);
	    }
	    
	}
 
}
