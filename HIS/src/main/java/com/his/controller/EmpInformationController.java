package com.his.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.Department;
import com.his.pojo.Dept;
import com.his.pojo.EmpInformation;
import com.his.pojo.JsonResult;
import com.his.pojo.Role;
import com.his.pojo.WaitingRoom;
import com.his.service.EmpInformationService;
import com.his.utils.Result;

/**  
* @ClassName: EmpInformationController  
* @Description: 员工Controller 
* @author crazy_long
* @date 2019年8月2日  下午3:13:44
*    
*/
@Controller
public class EmpInformationController {
	
	@Autowired
	private EmpInformationService empInformationService; 
	
	/**
	* @Title:get_dept_by_ygxh
	* @Description:根据员工序号查找dept
	* @param:@param ygxh
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:crazy_long
	* @Date:2019年9月5日 下午2:52:59
	 */
	@ResponseBody
	@GetMapping("get_dept_by_ygxh")
	public JsonResult get_dept_by_ygxh(String ygxh) {
		JsonResult jsonResult = new JsonResult();
		try {
			jsonResult.setResult(empInformationService.getDeptByYgxh(ygxh));
			jsonResult.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setStatus("error");
		}
		return jsonResult;
	}
	
	@ResponseBody
	@PostMapping("add_emp_information")
	public Result addEmpInformation(@RequestBody EmpInformation emp,HttpSession httpSession) {
		try {
			empInformationService.addEmpAllInformation(emp,httpSession);
			/*System.out.println("----------------------------");
			System.out.println(emp.getWaitingRoomId());
			System.out.println(emp.getTechnicalPost().getTpId());
			System.out.println(emp.getYgName());*/
			return new Result();
		} catch (Exception e) {
			return new Result("添加员工失败");
		}
	}
	
	@ResponseBody
	@PostMapping("edit_emp_information")
	public Result edit_emp_information(@RequestBody EmpInformation emp) {
		try {
			empInformationService.updateEmpInformation(emp);
			return new Result();
		} catch (Exception e) {
			return new Result("修改员工失败");
		}
	}
	
	@ResponseBody
	@GetMapping("get_emp_by_page")
	public Map get_all_emp(int curpage,int pagesize,String searchState,String searchContent){
		if(searchState.equals("1")) {
			return empInformationService.queryByGH(searchContent);
			
		}else if(searchState.equals("2")) {
			return empInformationService.queryEmpByYgname(searchContent);
		}else {
			return empInformationService.queryEmpByPage(curpage, pagesize);
		}
		
	}
	
	@ResponseBody
	@GetMapping("get_emp_by_nameAndxh")
	public List<Object[]> get_emp_by_nameAndxh(){
		return empInformationService.queryEmpforNameAndXH();
	}
	
	/**
	 * 
	* @Title:getDoctorsByWkAndKs
	* @Description:根据科室查询当日排班的医生
	* @param:@param ksId
	* @param:@return
	* @return:List<EmpInformation>
	* @throws
	* @author:Hamster
	* @Date:2019年8月14日 上午9:21:16
	 */
	@ResponseBody
	@GetMapping("/get_doctors_by_ksId")
	public List <EmpInformation> getDoctorsByWkAndKs(String ksId){
		return empInformationService.getDoctorsByWkAndKs(ksId);
	}
	/**
	 * 
	* @Title:getdept
	* @Description:TODO获得所有部门对象
	* @param:@return
	* @return:List<Dept>
	* @throws
	* @author:TRC
	* @Date:2019年9月13日 下午12:01:30
	 */
	@ResponseBody
	@GetMapping("getdept")
	public List<Dept> getdept(){
		return empInformationService.getdept();
	} 
	/**
	 * 
	* @Title:getDepartments
	* @Description:TODO
	* @param:@param deptname获得所有科室对象
	* @param:@return
	* @return:List<Department>
	* @throws
	* @author:TRC
	* @Date:2019年9月13日 下午2:02:26
	 */
	@ResponseBody
	@GetMapping("getks")
	public List<Department> getDepartments(String deptid){
		return empInformationService.getDepartments(deptid);
	}
	/**
	 * 
	* @Title:getroles
	* @Description:TODO获得该科室的所有职位
	* @param:@param ksid
	* @param:@return
	* @return:List<Role>
	* @throws
	* @author:TRC
	* @Date:2019年9月13日 下午2:44:14
	 */
	@ResponseBody
	@GetMapping("getzhiwei")
	public List<Role> getroles(String ksid){
		return empInformationService.getrole(ksid);
	}
	/**
	 * 
	* @Title:getRooms
	* @Description:TODO根据科室id获取候诊厅
	* @param:@param ksid
	* @param:@return
	* @return:List<WaitingRoom>
	* @throws
	* @author:TRC
	* @Date:2019年9月15日 上午2:46:36
	 */
	@ResponseBody
	@GetMapping("gethouzhen")
	public List<WaitingRoom> getRooms(String ksid){
		return empInformationService.getWaitingRooms(ksid);
	}
	/**
	 * 
	* @Title:gaipassword
	* @Description:TODO修改密码
	* @param:@param ygxh
	* @param:@param old
	* @param:@param xin
	* @param:@return
	* @return:String
	* @throws
	* @author:TRC
	* @Date:2019年9月15日 上午2:47:52
	 */
	@ResponseBody
	@GetMapping("xiupassword")
	public String gaipassword(String ygxh,String old,String xin) {
		return empInformationService.gaipassword(ygxh, old, xin);
	}
	/**
	 * 
	* @Title:getemp
	* @Description:TODO根据部门，科室，职位筛选id
	* @param:@param bumenid
	* @param:@param ksid
	* @param:@param roleid
	* @param:@return
	* @return:List<EmpInformation>
	* @throws
	* @author:TRC
	* @Date:2019年9月21日 下午4:07:33
	 */
	@ResponseBody
	@GetMapping("getempby")
	public Map getemp(String bumenid,String ksid,String roleid,String name,int curpage, int pagesize) {
		return empInformationService.getempby(bumenid, ksid, roleid,name,curpage,pagesize);
	}
}
