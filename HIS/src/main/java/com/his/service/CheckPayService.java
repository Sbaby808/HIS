package com.his.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.dialect.unique.DB2UniqueDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.bean.OperationPaybean;
import com.his.dao.ICheckItemDAO;
import com.his.dao.ICheckPayDao;
import com.his.dao.ICheckPayRecordDao;
import com.his.dao.IEmpInformationDao;
import com.his.pojo.CheckItem;
import com.his.pojo.CheckPay;
import com.his.pojo.CheckPayRecord;
import com.his.utils.SimpleTools;
import com.his.utils.UUIDGenerator;
import com.sun.org.apache.regexp.internal.recompile;
import com.sun.org.apache.xerces.internal.dom.DeepNodeListImpl;

/**  
* @ClassName: CheckPayService  
* @Description: TODO(检查划价，缴费Service)  
* @author TRC
* @date 2019年7月30日  上午9:42:28
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class CheckPayService {
	@Autowired
	private ICheckPayDao iCheckPayDao;
	@Autowired
	private ICheckItemDAO iCheckItemDAO;
	@Autowired
	private IEmpInformationDao iEmpInformationDao;
	@Autowired
	private ICheckPayRecordDao iCheckPayRecordDao;
/**
 * 
* @Title:AddCheckPay
* @Description:TODO添加检查收费项
* @param:@param checkPay
* @return:void
* @throws
* @author:TRC
* @Date:2019年7月30日 下午4:57:23
 */
	public void AddCheckPay(CheckPay checkPay) {
		iCheckPayDao.save(checkPay);
	}
	/**
	 * 
	* @Title:modificationCheckPay
	* @Description:TODO修改检查收费项
	* @param:@param checkPay
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午4:59:42
	 */
	public void modificationCheckPay(CheckPay checkPay) {
		iCheckPayDao.save(checkPay);
	}
	/**
	 * 
	* @Title:getallCheckPays
	* @Description:TODO查询所有检查收费项
	* @param:@return
	* @return:List<CheckPay>
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午5:03:08
	 */
	public List<CheckPay> getallCheckPays(){
		return (List<CheckPay>) iCheckPayDao.findAll();
	}
	/**
	 * 
	* @Title:DelCheckPay
	* @Description:TODO删除指定检查收费项
	* @param:@param id
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午5:07:12
	 */
	public void DelCheckPay(String id) {
		iCheckPayDao.deleteById(id);
	}
	
	/**
	 * 
	* @Title:getAllCheckPay
	* @Description:查询所有检查项
	* @param:@return
	* @return:List<CheckPay>
	* @throws
	* @author:Hamster
	* @Date:2019年8月10日 上午11:05:49
	 */
	public List <CheckPay> getAllCheckPay(){
		return iCheckPayDao.getAllCheckPay();
	}
	/**
	 * 
	* @Title:getallcheckpay
	* @Description:TODO获得所有检查缴费项
	* @param:@return
	* @return:List<CheckPay>
	* @throws
	* @author:TRC
	* @Date:2019年8月12日 上午10:46:26
	 */
	public Map getallcheckpay(int curpage, int pagesize,String sou){
		List<CheckPay> list=iCheckPayDao.getcheckbysou(sou, PageRequest.of(curpage - 1,
				  pagesize));
		    	  long total=iCheckPayDao.getcount(sou);
		    	  Map map = new HashMap();
				  map.put("total", total);
				  map.put("list", list);
		return map;
	}
	public List<CheckItem> getitems(){
	    	return (List<CheckItem>) iCheckItemDAO.findAll();
	    }
	public String getnamebyitemid(String str) {
		String a[]=str.split(",");
		String nameString="";
		for (String string : a) {
			nameString+=iCheckItemDAO.findById(string).get().getItemName()+",";
		}
		return nameString;
	}
	public void addcheckpay(String str,String cname,String cdesc,BigDecimal cprice,String ygxh,String checkid) {
		if(checkid.length()!=0) {
			CheckPay checkPay=iCheckPayDao.findById(checkid).get();
			checkPay.setCheckFormPath(str);
			checkPay.setCheckPayMoney(cprice);
			checkPay.setCheckPayName(cname);
			checkPay.setCheckPayTime(new Date());
			checkPay.setCheckPayDesc(cdesc);
			checkPay.setEmpInformation(iEmpInformationDao.findById(ygxh).get());
			iCheckPayDao.save(checkPay);
		}
		else {
			CheckPay checkPay=new CheckPay();
			checkPay.setCheckFormPath(str);
			checkPay.setCheckPayMoney(cprice);
			checkPay.setCheckPayName(cname);
			checkPay.setCheckPayTime(new Date());
			checkPay.setCheckPayDesc(cdesc);
			checkPay.setEmpInformation(iEmpInformationDao.findById(ygxh).get());
			UUIDGenerator uuid=new UUIDGenerator();
			System.err.println(uuid.getUUID());
			checkPay.setCheckId(uuid.getUUID());
			iCheckPayDao.save(checkPay);
		}
		
		
	}
	public List<CheckItem> getbycheck(String checkid){
		CheckPay checkPay=iCheckPayDao.findById(checkid).get();
		List<CheckItem> list=new ArrayList<CheckItem>();
		String a=checkPay.getCheckFormPath();
		String b[]=a.split(",");
		for (String string : b) {
			list.add(iCheckItemDAO.findById(string).get());
		}
		return list;
	}
	public void delcheck(String checkid) {
		iCheckPayDao.deleteById(checkid);
	}
	public Map getcheckayrecord(int curpage, int pagesize,String sou) {
		List<CheckPayRecord> list=iCheckPayRecordDao.getpayrecord(sou, PageRequest.of(curpage - 1,
		  pagesize));
		
		long total=iCheckPayRecordDao.getgount(sou);
		Map map=new HashMap();
		map.put("list", list);;
		map.put("total", total);
		return map;
	}
	
	/**
	* @Title:searchCheckPay
	* @Description:模糊查询检查项
	* @param:@param key
	* @param:@return
	* @return:List<CheckPay>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午9:28:22
	 */
	public List<CheckPay> searchCheckPay(String key) {
		PageRequest page = PageRequest.of(0, 6);
		return iCheckPayDao.searchCheck(SimpleTools.addCharForSearch(key), page);
	}
}
