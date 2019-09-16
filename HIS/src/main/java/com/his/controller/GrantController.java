package com.his.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.EmpInformation;
import com.his.pojo.Function;
import com.his.pojo.FunctionClassify;
import com.his.pojo.Role;
import com.his.service.GrantService;
import com.his.utils.Result2;

/**  
* @ClassName: GrantController  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author jack
* @date 2019年8月17日  上午8:57:47
*    
*/@Controller
public class GrantController {
	@Autowired
	private GrantService grantService;
	@ResponseBody
	@GetMapping("/findfunctionids")
	public Set<String> findfunctionidsbyygxh(String ygxh){
		return grantService.findfunctionsid(ygxh);
	}
	@ResponseBody
	@GetMapping("/findcountqx")
	public long findcountqx(String functionName) {
		return grantService.findcountqx(functionName);
	}
	@ResponseBody
	@GetMapping("/findcountquanxianfenlei")
	public long findcountquanxianfenleiname(String funcClassifyName) {
		return grantService.findcountquanxian(funcClassifyName);
	}
	@ResponseBody
	@PostMapping("/updatefunction")
	public void updatefunction(@RequestBody Function function) {
		grantService.updatefunction(function);
	}
	@ResponseBody
	@GetMapping("/deletefunction")
	public void deletefunction(String functionId) {
		grantService.deletefunction(functionId);
	}
	@ResponseBody
	@GetMapping("/addquanxianandtype")
	public void addquanxianandtype(String funcClassifyId,String functionName) {
		grantService.addquanxianandtype(funcClassifyId, functionName);
	}
	@ResponseBody
	@GetMapping("/deletequanxiantype")
	public void deletequanxianfenlei(String funcClassifyId) {
		grantService.deletequanxianfenlei(funcClassifyId);
	}
	@ResponseBody
	@PostMapping("/addoreditquanxiantype")
	public void addoreditquanxiantype(@RequestBody FunctionClassify functionClassify) {
		grantService.addquanxianfenlei(functionClassify);
	}
	@ResponseBody
	@GetMapping("/findroles")
	public List<Role> findRoles(){
		return grantService.findRoles();
	}
	@ResponseBody
	@GetMapping("findempsss")
	public List<EmpInformation> findempss(String roleid){
		return grantService.findempss(roleid);
	}
	
	@ResponseBody
	@GetMapping("/findfunctions")
	public List<Function> findFunctions(){
		return grantService.findFunctions();
	}
	@ResponseBody
	@PostMapping("/addoreditrole")
	public void addOrEditRole(@RequestBody Role role) {
		 grantService.addOrEditRole(role);
	}
	@ResponseBody
	@GetMapping("/deleterolebyid")
	public void deleterole(String roleid) {
		grantService.deleteRole(roleid);
	}
	@ResponseBody
	@GetMapping("/findrolebyid")
	public Role findRole(String roleid) {
		return grantService.findRole(roleid);
	}
	@ResponseBody
	@GetMapping("/findallemp")
	public List<EmpInformation> findEmpInformations(){
		return grantService.findEmpInformations();
	}
	@ResponseBody
	@GetMapping("/findempEmpInformationsbyrole")
	public List<String> findempEmpInformationsbyrole(String roleid){
		return grantService.findempEmpInformationsbyrole(roleid);
	}
	@ResponseBody
	@GetMapping("/findclassifies")
	public List<FunctionClassify> findClassifies(){
		return grantService.findClassifies();
	}
	@ResponseBody
	@GetMapping("/findrolesss")
	public List<Role> findroless(String rolePosition,int currentpage){
		rolePosition = "%"+rolePosition+"%";
		return grantService.findRoless(rolePosition, currentpage);
	}
	@ResponseBody
	@GetMapping("findcountsbyname")
	public long findcounts(String rolePosition) {
		return grantService.findcount(rolePosition);
	}
	@ResponseBody
	@GetMapping("/fenpei")
	public void fenpei(String roleid,String funcStrings) {
		grantService.fenpei(roleid, funcStrings);
	}
	@ResponseBody
	@GetMapping("/grantUserForRole")
	public void grantUserForRole(String roleid,String empids) {
		grantService.grantUserForRole(roleid, empids);
	}
	@ResponseBody
	@GetMapping("/findcountsbyname11")
	public long findcountsbyname(String rolePosition) {
		return grantService.findcountsbyname(rolePosition);
	}
	@ResponseBody
	@GetMapping("/findygxhs")
	public List<String> findempygxhs(String roleid){
		return grantService.findempygxhs(roleid);
	}
	@ResponseBody
	@GetMapping("/findepts")
	public List<Result2> finddepts(){
		return grantService.finDepts();
	}
}
