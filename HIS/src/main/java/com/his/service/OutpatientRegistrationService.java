package com.his.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.demo.trade.model.GoodsDetail;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.util.BytePictureUtils;
import com.his.dao.IDepartmentDao;
import com.his.dao.IEmpInformationDao;
import com.his.dao.IOutpatientRegistrationDao;
import com.his.dao.ITechnicalPostDao;
import com.his.dao.IWorkTimeDao;
import com.his.pojo.AliPayEntity;
import com.his.pojo.Department;
import com.his.pojo.EmpInformation;
import com.his.pojo.OtherProject;
import com.his.pojo.OutpatientRegistration;
import com.his.pojo.RegEmp;
import com.his.pojo.TechnicalPost;
import com.his.pojo.WorkTime;
import com.his.utils.AliPay;
import com.his.utils.GeneratorWord;
import com.his.utils.QRCodeUtil;
import com.his.utils.SimpleTools;

import oracle.net.aso.e;

/**  
* @ClassName: OutpatientRegistrationService  
* @Description: 门诊挂号Service
* @author Sbaby
* @date 2019年8月9日  下午4:24:16
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class OutpatientRegistrationService {

	@Autowired
	private IOutpatientRegistrationDao outpatientRegistrationDao;
	@Autowired
	private IDepartmentDao departmentDao;
	@Autowired
	private ITechnicalPostDao technicalPostDao;
	@Autowired
	private IEmpInformationDao empInformationDao;
	@Autowired
	private IWorkTimeDao workTimeDao;
	@Autowired
	private OtherProjectService otherProjectService;
	
	/**
	* @Title:getKSbyOut
	* @Description:查询门诊的所有科室
	* @param:@return
	* @return:List<Department>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月9日 下午4:28:45
	 */
	public List<Department> getKSbyOut() {
		return departmentDao.getKSByOut("outpatient");
	}
	
	/**
	* @Title:getTpbyOut
	* @Description:查询门诊的所有职称
	* @param:@return
	* @return:List<TechnicalPost>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月9日 下午4:57:49
	 */
	public List<TechnicalPost> getTpbyOut() {
		return technicalPostDao.getByOut("outpatient");
	}
	
	/**
	* @Title:getDocByKsAndTp
	* @Description:根据日期、科室和职称查询门诊医生
	* @param:@param ks
	* @param:@param tp
	* @param:@return
	* @return:List<EmpInformation>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 上午8:50:39
	 */
	public List<EmpInformation> getDocByKsAndTp(String ks, String tp, Long doDate) {
		return empInformationDao.getDocByKsAndTp(tp, ks, doDate);
	}
	
	/**
	* @Title:getRandomDocByKsAndTp
	* @Description:根据日期、科室和职称查询门诊医生
	* @param:@param ks
	* @param:@param tp
	* @param:@param doDate
	* @param:@return
	* @return:EmpInformation
	* @throws
	* @author:Sbaby
	* @Date:2019年8月12日 下午2:59:48
	 */
	public EmpInformation getRandomDocByKsAndTp(String ks, String tp, Long doDate) {
		List<EmpInformation> list = empInformationDao.getDocByKsAndTp(tp, ks, doDate);
		return list.get((int)Math.floor(Math.random() * list.size()));
	}
	
	/**
	* @Title:getWorktimeByEmpid
	* @Description:根据医生编号查询排班时间（当天与第二天的）
	* @param:@param empId
	* @param:@return
	* @return:List<WorkTime>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 上午9:25:32
	 */
	public List<WorkTime> getWorktimeByEmpid(String empId) {
		return workTimeDao.getDocById(empId);
	}
	
	/**
	* @Title:getRegistrationCount
	* @Description:根据医生编号查询当日挂号总数
	* @param:@param empId
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 下午2:06:22
	 */
	public int getRegistrationCount(String empId) {
		return outpatientRegistrationDao.getRegsitrationCountByEmpId(empId);
	}
	
	/**
	* @Title:getOutpatientDoctorCount
	* @Description:查询门诊所有科室的医生挂号详情
	* @param:@return
	* @return:Map<String,Map<EmpInformation,Integer>>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 下午2:56:27
	 */
	public Map<String, List<Map<String, String>>> getOutpatientDoctorCount() {
		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();
		// 查询所有科室
		List<Department> departments = getKSbyOut();
		for (Department department : departments) {
			// 查询该科室下今日排班的所有医生
			List<Map<String, String>> list = new ArrayList<>();
			List<EmpInformation> doctors = empInformationDao.getDoctorsByWkAndKs(department.getKsId());
			for (EmpInformation empInformation : doctors) {
				// 查询该医生今日已有的挂号数量
//				Object[] objs = {empInformation.getYgName(), 
//						empInformation.getTechnicalPost().getTpId(), 
//						getRegistrationCount(empInformation.getYgxh()),
//						department.getKsId()};
				Map<String, String> mmap = new HashMap<>();
				mmap.put("ygName", empInformation.getYgName());
				mmap.put("tpName", empInformation.getTechnicalPost().getTpName());
				mmap.put("count", getRegistrationCount(empInformation.getYgxh()) + "");
				list.add(mmap);
			}
			map.put(department.getKsName(), list);
		}
		return map;
	}
	
	/**
	* @Title:addReg
	* @Description:添加挂号信息
	* @param:@param outpatientRegistration
	* @param:@return
	* @return:OutpatientRegistration
	* @throws
	* @author:Sbaby
	* @Date:2019年8月12日 上午11:42:29
	 */
	public OutpatientRegistration addReg(OutpatientRegistration outpatientRegistration) {
		outpatientRegistration.setRegId(UUID.randomUUID().toString().replaceAll("-", ""));
		outpatientRegistration.setTimeType(SimpleTools.isToday(outpatientRegistration.getDoDate()) ? "当日" : "预约");
		System.out.println(outpatientRegistration.getTimeType());
		outpatientRegistration.setRegTime(new Date());
		outpatientRegistration.setRegStatus("待缴费");
		outpatientRegistrationDao.save(outpatientRegistration);
		return outpatientRegistration;
	}
	
	/**
	* @Title:checkEmp
	* @Description:检查医生是否与余号
	* @param:@param ygxh
	* @param:@return
	* @return:boolean
	* @throws
	* @author:Sbaby
	* @Date:2019年8月12日 下午3:48:46
	 */
	public boolean checkEmp(String ygxh, int total) {
		int num = outpatientRegistrationDao.checkEmp(ygxh);
		return num < total ? true : false;
	}
	
	/**
	* @Title:generatorRegTable
	* @Description:打印挂号单
	* @param:@param response
	* @param:@param regId
	* @param:@return
	* @return:HttpServletResponse
	* @throws
	* @author:Sbaby
	* @Date:2019年8月12日 下午8:43:53
	 */
	public HttpServletResponse generatorRegTable(HttpServletResponse response, String regId, String fileName, Map<String, String> map) {
		OutpatientRegistration reg = outpatientRegistrationDao.findById(regId).get();
		Map<String, Object> datas = new HashMap<>();
    	datas.put("type", "当日".equals(reg.getTimeType()) ? "" : "预约");
    	datas.put("cardName",reg.getMedicalCard().getCardName());
    	datas.put("gender",  reg.getMedicalCard().getGender());
    	datas.put("birthday", SimpleTools.formatDate(reg.getMedicalCard().getBirthday(), "yyyy-MM-dd"));
    	datas.put("cardNum", reg.getMedicalCard().getPersonId());
    	datas.put("regType", reg.getRegType());
    	datas.put("regKs", reg.getDepartment().getKsName());
    	datas.put("regTp", reg.getTechnicalPost().getTpName());
    	datas.put("doDate", SimpleTools.formatDate(reg.getDoDate(), "yyyy-MM-dd"));
    	datas.put("doctor", getDoctor(reg.getRegEmps(), "医生").getYgName());
    	datas.put("waitingRoom", getDoctor(reg.getRegEmps(), "医生").getWaitingRoom().getWaitingRoomName());
    	datas.put("regEmp", getDoctor(reg.getRegEmps(), "挂号员").getYgName());
    	datas.put("regTime", SimpleTools.formatDate(reg.getRegTime(), "yyyy-MM-dd HH:mm:ss"));
    	// 生成二维码
    	QRCodeUtil.zxingCodeCreate(map.get("code"), 160, 160, "D://HIS//reg_pay_code//" + regId + ".jpg", "jpg");
    	// 本地图片
    	datas.put("payCode", new PictureRenderData(160, 160, "D://HIS//reg_pay_code//" + regId + ".jpg"));
    	
    	GeneratorWord.makeWord(datas, "D:\\HIS\\reg_table\\", "挂号单模版.docx", fileName);
    	
    	return response;
	}
	
	/**
	* @Title:getDoctor
	* @Description:从员工挂号表中获取医生
	* @param:@param list
	* @param:@return
	* @return:String
	* @throws
	* @author:Sbaby
	* @Date:2019年8月12日 下午8:28:11
	 */
	public EmpInformation getDoctor(List<RegEmp> list, String type) {
		EmpInformation empInformation = new EmpInformation();
		for (RegEmp regEmp : list) {
			if(type.equals(regEmp.getRegDuty())){
				empInformation =  regEmp.getEmpInformation();
				break;
			}
		}
		return empInformation;
	}
	
	/**
	* @Title:getCardQrCode
	* @Description:获取挂号缴费二维码
	* @param:@return
	* @return:Map<String,String>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月13日 下午2:14:20
	 */
	public Map<String, String> getCardQrCode(String regId) {
		// 获取挂号对象
		OutpatientRegistration reg = outpatientRegistrationDao.findById(regId).get();
		Map<String, String> res = new HashMap<>();
		AliPayEntity payEntity = new AliPayEntity();
		payEntity.setOutTradeNo(UUID.randomUUID().toString().replace("-", ""));
		OtherProject otherProject = otherProjectService.getPriceByReg(reg);
		payEntity.setSubject(otherProject.getProjectName());
		payEntity.setTotalAmount(otherProject.getProjectPrice() + "");
		payEntity.setBody(otherProject.getProjectDesc());
		List<GoodsDetail> goods = new ArrayList<>();
		GoodsDetail good = GoodsDetail.newInstance(otherProject.getProjectId(), otherProject.getProjectName(), Long.parseLong(otherProject.getProjectPrice().toString()), 1);
		goods.add(good);
		res.put("code", AliPay.pay(payEntity, goods));
		res.put("outTradeNo", payEntity.getOutTradeNo());
		return res;
	}
}
