package com.his.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.bean.Addmedicbean;
import com.his.bean.Medicinebean;
import com.his.dao.IDrugWarehouseDao;
import com.his.dao.IMedicineDao;
import com.his.dao.IOpeDrugDetailDao;
import com.his.dao.IOperationRecordDao;
import com.his.pojo.Medicine;
import com.his.pojo.OpeDrugDetail;
import com.his.pojo.OpeDrugDetailPK;
import com.his.pojo.OperationRecord;

/**  
* @ClassName: OpeDrugDetailsService  
* @Description: TODO(手术用药详情)  
* @author TRC
* @date 2019年7月30日  上午9:12:14
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class OpeDrugDetailsService {
	@Autowired
	private IOpeDrugDetailDao iOpeDrugDetailDao;
	@Autowired
	private IMedicineDao iMedicineDao;
	@Autowired
	private IDrugWarehouseDao iDrugWarehouseDao;
	@Autowired
	private IOperationRecordDao iOperationRecordDao;
	
	
	/**
	 * 
	* @Title:AddOpeDrugDetail
	* @Description:TODO手术用药详情
	* @param:@param opeDrugDetail
	* @return:void
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午4:44:34
	 */
	public void AddOpeDrugDetail(OpeDrugDetail opeDrugDetail) {
		iOpeDrugDetailDao.save(opeDrugDetail);
	}
	/**
	 * 
	* @Title:getallOpeDrugDetails
	* @Description:TODO查询所有手术用药信息
	* @param:@return
	* @return:List<OpeDrugDetail>
	* @throws
	* @author:TRC
	* @Date:2019年7月30日 下午4:46:22
	 */
	public List<OpeDrugDetail> getallOpeDrugDetails(){
		return (List<OpeDrugDetail>) iOpeDrugDetailDao.findAll();
	}
	/**
	 * 
	* @Title:getMedicinebeans
	* @Description:TODO去除批次不同但属于同一种的药的重复，选择最快过期的批次
	* @param:@return
	* @return:List<Medicinebean>
	* @throws
	* @author:TRC
	* @Date:2019年8月9日 下午3:49:00
	 */
    public List<Medicinebean> getMedicinebeans(){
    	/**
    	List<String []> list=iMedicineDao.getMedicinebeans();
    	for(int i=0;i<list.size();i++) {
    		int num=0;
    		for(int j=0;j<list.size();j++) {
    		   if(list.get(i)[0].equals(list.get(j)[0])) {
    			   num+=1;
    			   if(num>1) {
    				   list.get(j)[0]="";
    				   num=1;
    			   }
    		   }
    		}
    	}
    	List<Medicinebean> listme=new ArrayList<Medicinebean>();
    	for (String [] str : list) {
    		if(!str[0].equals("")) {	
			Medicinebean medicinebean=new Medicinebean(str[0]+"/"+str[1], str[2]);
			listme.add(medicinebean);
    		}
			
		}
    	*/
    	List<Medicinebean> listme=new ArrayList<Medicinebean>();
    	List<String []> list=iMedicineDao.getMedicinebeans();
    	for (String[] strings : list) {
			Medicinebean medicinebean=new Medicinebean(strings[0]+"/"+strings[1]+" _批次号:"+strings[2],strings[2]);
			listme.add(medicinebean);
		}
    	
    	return listme;
    }
    /**
     * 
    * @Title:getname
    * @Description:TODO给选中的药品一个可以添加数量的字段
    * @param:@param pcid
    * @param:@return
    * @return:List<Addmedicbean>
    * @throws
    * @author:TRC
    * @Date:2019年8月9日 上午9:46:53
     */
    public List<Addmedicbean> getname(String pcid){
    	List<Addmedicbean> list=new ArrayList<Addmedicbean>();
    	String a[]=pcid.split(",");
    	for (String string : a) {
		String zhi[]=iDrugWarehouseDao.getname(string);
		String name=zhi[0].replace(",", "/");
		Addmedicbean medicinebean=new Addmedicbean(string,name,new BigDecimal(1),"0");
		list.add(medicinebean);
		}
    	return list;
    }
    /**
     * 
    * @Title:addopdrug
    * @Description:TODO添加用药详情
    * @param:@param list
    * @return:void
    * @throws
    * @author:TRC
    * @Date:2019年8月9日 下午3:50:46
     */
    public String addopdrug(List<Addmedicbean> list) {
    	String opeid=list.get(0).getOpeid();
        List<OpeDrugDetail> druglist=iOperationRecordDao.findById(opeid).get().getOpeDrugDetails();
        if(druglist.size()>0) {
        	return "您已经编辑过用药信息了！";
        }
        else {
    	for (Addmedicbean addmedicbean : list) {
    		Medicine medicine=iMedicineDao.findById(iMedicineDao.getmid(addmedicbean.getPcid())[0]).get();
    		medicine.setMedicineName(medicine.getMedicineName().subtract(addmedicbean.getTotal()));
    		iMedicineDao.save(medicine);
    		OpeDrugDetail opeDrugDetail=new OpeDrugDetail();
        	opeDrugDetail.setOpeDrugUnit(addmedicbean.getYaoname().substring(addmedicbean.getYaoname().length()-1,addmedicbean.getYaoname().length()));
        	opeDrugDetail.setOpeDrugNum(addmedicbean.getTotal());
        	OpeDrugDetailPK pk=new OpeDrugDetailPK();
        	pk.setMedicineId(iMedicineDao.getmid(addmedicbean.getPcid())[0]);
        	pk.setOpeId(opeid);
        	opeDrugDetail.setId(pk);
        	iOpeDrugDetailDao.save(opeDrugDetail);
		}
    	return "用药信息编辑成功！";
    	}
    }
    /**
     * 
    * @Title:getopemedic
    * @Description:TODO查找手术用药详情
    * @param:@param opeid
    * @param:@return
    * @return:List<Addmedicbean>
    * @throws
    * @author:TRC
    * @Date:2019年8月9日 下午3:51:17
     */
    public List<Addmedicbean> getopemedic(String opeid){
    	OperationRecord operationRecord=iOperationRecordDao.findById(opeid).get();
    	List<OpeDrugDetail> list=operationRecord.getOpeDrugDetails();
    	List<Addmedicbean> mediclist=new ArrayList<Addmedicbean>();
    	for (OpeDrugDetail oped : list) {
    		Addmedicbean amedic=new Addmedicbean(oped.getMedicine().getDrugWarehouse().getPckcId(), oped.getMedicine().getDrugWarehouse().getDrugInformation().getYpName()+"/"+oped.getOpeDrugUnit(), oped.getOpeDrugNum(), opeid);
		     mediclist.add(amedic);
    	}
    	return mediclist;
    }
}
