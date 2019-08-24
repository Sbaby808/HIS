package com.his.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.demo.trade.model.GoodsDetail;
import com.his.bean.Checknoticebean;
import com.his.bean.Zhuyuanchecknotice;
import com.his.dao.ICheckItemDAO;
import com.his.dao.ICheckNoticeDao;
import com.his.dao.ICheckPayDao;
import com.his.dao.ICheckPayRecordDao;
import com.his.dao.ICheckResultDetailDao;
import com.his.dao.ICheckResultFormDao;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IHosCheckNoticeDao;
import com.his.dao.IMedicalCardDao;
import com.his.pojo.AliPayEntity;
import com.his.pojo.CheckItem;
import com.his.pojo.CheckNoticeForm;
import com.his.pojo.CheckPay;
import com.his.pojo.CheckPayRecord;
import com.his.pojo.CheckResultDetail;
import com.his.pojo.CheckResultDetailPK;
import com.his.pojo.CheckResultForm;
import com.his.pojo.HosCheckNotice;
import com.his.pojo.OtherAdvice;
import com.his.pojo.SolveScheme;
import com.his.utils.AliPay;
import com.his.utils.UUIDGenerator;

import oracle.net.aso.l;
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
	@Autowired
	private IHosCheckNoticeDao iHosCheckNoticeDao;
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
		String name=iMedicalCardDao.findById(sou).get().getCardName();
		System.out.println(name);
		Map map=new HashMap();
		map.put("list", cList);
		map.put("total", total);
		map.put("name", name);
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
        List<CheckNoticeForm> cForm=iCheckNoticeDao.getbyid(noticeid);
        if(!cForm.isEmpty()) {
        	 cForm.get(0).setMcheckComment("已处理");
             iCheckNoticeDao.save(cForm.get(0));
        }
        else {
        	HosCheckNotice hCheckNotice=iHosCheckNoticeDao.findById(noticeid).get();
        	hCheckNotice.setState("已处理");
        	iHosCheckNoticeDao.save(hCheckNotice);
        }
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
			List<CheckNoticeForm> cForm=iCheckNoticeDao.getbyid(stringb);
			if(!cForm.isEmpty()) {
				cForm.get(0).setMcheckComment("已处理");
				iCheckNoticeDao.save(cForm.get(0));
			}
			else {
				HosCheckNotice hCheckNotice=iHosCheckNoticeDao.findById(stringb).get();
				hCheckNotice.setState("已处理");
				iHosCheckNoticeDao.save(hCheckNotice);
			}
		}
	}
	public List<CheckPay> getPays(){
		return (List<CheckPay>) iCheckPayDao.findAll();
	}
	public Map getcheckresult(String card_id,String cheid){
		CheckPayRecord cRecord=iCheckPayRecordDao.getPayRecord(card_id, cheid).get(0);
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
			String chename=iCheckPayDao.findById(cheid).get().getCheckPayName();
			Map map=new HashMap();
			map.put("list", list);
			map.put("listid", listid);
			map.put("name", name);
			map.put("chename", chename);
			return map;
		}
		else {
			return null;
		}
		
	}
	public Map fuzhi(int changdu,String cheitemid){
		List<String> itemlist=new ArrayList<String>();
		String a[]=cheitemid.split(",");
		for (String string : a) {
			String cItem=iCheckItemDAO.findById(string).get().getCheckItemStd();
			itemlist.add(cItem);
			System.out.println(cItem);
		}
		List<Double>  list=new ArrayList<Double>();
		List<Integer>  pandua=new ArrayList<Integer>();
		for(int i=0;i<changdu;i++) {
			Double nocInteger=(Double) (1+Math.random()*(20-1+1));
			DecimalFormat dcmFmt = new DecimalFormat("0.0");
			Double cInteger=Double.parseDouble(dcmFmt.format(nocInteger));
			String zhi[]=itemlist.get(i).split(" ");
			String zhongji[]=zhi[0].split("~");
			if(cInteger<Double.parseDouble(zhongji[0])) {
				pandua.add(1);
			}
			else if(cInteger>Double.parseDouble(zhongji[1])){
				pandua.add(2);
			}
			else {
				pandua.add(3);
			}
			list.add(cInteger);
		}
		Map map=new HashMap();
		map.put("zhilist", list);
		map.put("panduanlist", pandua);
		return map;
	}
	public void addcheckrecord(String card_id,String cheid,String itemid,String itemval,String beizhu,String ygxh) {
		CheckPayRecord cRecord=iCheckPayRecordDao.getPayRecord(card_id, cheid).get(0);
		UUIDGenerator uuid=new UUIDGenerator();
		String crecordid=uuid.getUUID();
		CheckResultForm cForm=new CheckResultForm();
		cForm.setCheckComment(beizhu);
	    cForm.setCheckResultId(crecordid);
	    cForm.setCheckTime(new Date());
	    cForm.setEmpInformation(iEmpInformationDao.findById(ygxh).get());
	    iCheckResultFormDao.save(cForm);
	    CheckResultForm ccForm=iCheckResultFormDao.findById(crecordid).get();
	    cRecord.setCheckResultForm(ccForm);
	    ccForm.setCheckJfId(cRecord.getCheckJfId());
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
	public List<CheckResultForm> getform(){
		return (List<CheckResultForm>) iCheckResultFormDao.findAll();
	}
	public Map getZhuyuanchecknotices(int curpage, int pagesize,String sou){
		List<Checknoticebean> list=iHosCheckNoticeDao.getZhuyuanchecknotices(sou, PageRequest.of(curpage - 1,
				  pagesize));
		long total=iHosCheckNoticeDao.getcount(sou);
		Map map=new HashMap();
		map.put("zhuyualist", list);
		map.put("zhuyuantotal", total);
		return map;
	}
	public Map gettwoeima(String checkpayid) {
		Map res = new HashMap<>();
		AliPayEntity payEntity = new AliPayEntity();
		payEntity.setOutTradeNo(UUID.randomUUID().toString().replace("-", ""));
		CheckPay cPay=iCheckPayDao.findById(checkpayid).get();
		payEntity.setSubject(cPay.getCheckPayName());
		payEntity.setTotalAmount(cPay.getCheckPayMoney() + "");
		payEntity.setBody(cPay.getCheckPayDesc());
		List<GoodsDetail> goods = new ArrayList<>();
		GoodsDetail good = GoodsDetail.newInstance(cPay.getCheckId(), cPay.getCheckPayName(), Long.parseLong(cPay.getCheckPayMoney().toString()), 1);
		goods.add(good);
		res.put("code", AliPay.pay(payEntity, goods));
		res.put("outTradeNo", payEntity.getOutTradeNo());
		return res;
	}
	public boolean whetherpay(String outTradeNo) {
		boolean flag = false;
		flag =  AliPay.query(outTradeNo);
		return flag;
	}
	
	/**
	* @Title:getAllCheckPay
	* @Description:查询所有检查
	* @param:@return
	* @return:List<CheckPay>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午5:47:49
	 */
	public List<CheckPay> getAllCheckPay() {
		return (List<CheckPay>) iCheckPayDao.findAll();
	}
 
	/**
	* @Title:getAllCheckNotice
	* @Description:查询所有检查通知项
	* @param:@param solveId
	* @param:@return
	* @return:List<CheckNoticeForm>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午11:41:37
	 */
	public List<CheckNoticeForm> getAllCheckNotice(String solveId) {
		return iCheckNoticeDao.getAll(solveId);
	}
	
	/**
	* @Title:addCheckNotice
	* @Description:添加检查通知项
	* @param:@param checkNoticeForm
	* @param:@return
	* @return:List<CheckNoticeForm>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午11:44:02
	 */
	public List<CheckNoticeForm> addCheckNotice(CheckNoticeForm checkNoticeForm) {
		checkNoticeForm.setMcheckId(UUID.randomUUID().toString().replaceAll("-", ""));
		iCheckNoticeDao.save(checkNoticeForm);
		return this.getAllCheckNotice(checkNoticeForm.getSolveScheme().getScheId());
	}
	
	/**
	* @Title:delCheckNotice
	* @Description:删除检查通知项
	* @param:@param checkNoticeId
	* @param:@return
	* @return:List<CheckNoticeForm>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月24日 上午12:07:56
	 */
	public List<CheckNoticeForm> delCheckNotice(String checkNoticeId) {
		CheckNoticeForm checkNoticeForm = iCheckNoticeDao.findById(checkNoticeId).get();
		SolveScheme scheme = checkNoticeForm.getSolveScheme();
		iCheckNoticeDao.delete(checkNoticeForm);
		return this.getAllCheckNotice(scheme.getScheId());
	}
	
}
