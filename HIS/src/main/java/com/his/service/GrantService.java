package com.his.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IEmpInformationDao;
import com.his.dao.IFunctionClassifyDAO;
import com.his.dao.IFunctionDAO;
import com.his.dao.IRoleDao;
import com.his.dao.IUserRoleDao;
import com.his.pojo.Dept;
import com.his.pojo.EmpInformation;
import com.his.pojo.Function;
import com.his.pojo.FunctionClassify;
import com.his.pojo.Role;
import com.his.pojo.UserRole;
import com.his.pojo.UserRolePK;
import com.his.utils.Stringgood;
import com.his.utils.UUIDGenerator;

/**  
* @ClassName: GrantService  
* @Description: 权限(这里用一句话描述这个类的作用)  
* @author jack
* @date 2019年8月17日  上午8:37:13
*    
*/
@Service
@Transactional(rollbackFor=Exception.class)
public class GrantService {
	@Autowired
	private IUserRoleDao userRoleDao;
	@Autowired
	private IEmpInformationDao empInformationDao;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IFunctionDAO functionDAO;
	@Autowired
	private IFunctionClassifyDAO iFunctionClassifyDAO;
	@Autowired
	private IFunctionClassifyDAO functionClassifyDAO;
	/**
	 * 
	* @Title:findClassifies
	* @Description:查询所有的功能分类
	* @param:@return
	* @return:List<FunctionClassify>
	* @throws
	* @author:jack
	* @Date:2019年8月19日 上午9:14:21
	 */
	public List<FunctionClassify> findClassifies(){
		return (List<FunctionClassify>) iFunctionClassifyDAO.findAll();
	}
	/**
	 * 
	* @Title:findRoles
	* @Description:查询所有角色
	* @param:@return
	* @return:List<Role>
	* @throws
	* @author:jack
	* @Date:2019年8月17日 上午8:43:58
	 */
	public List<Role> findRoles(){
		return (List<Role>) roleDao.findAll();
	}
	/**
	 * 
	* @Title:findFunctions
	* @Description:查询所有的权限
	* @param:@return
	* @return:List<Function>
	* @throws
	* @author:jack
	* @Date:2019年8月19日 上午9:02:31
	 */
	public List<Function> findFunctions(){
		return (List<Function>) functionDAO.findAll();
	}
	/**
	 * 
	* @Title:addOrEditRole
	* @Description:创建或者编辑角色
	* @param:@param role
	* @return:void
	* @throws
	* @author:jack
	* @Date:2019年8月17日 上午8:45:20
	 */
	  public void addOrEditRole(Role role)throws ServiceException {
		  if(role.getRoleId().equals("")) {
			  role.setRoleId(UUIDGenerator.getUUID());
		  }
		  try {
			  roleDao.save(role);
		} catch (Exception e) {
			  e.printStackTrace();
			  throw new ServiceException("Errors occurred while creating or changing roles");
		}
	  }
	   
	  /**
	   * 
	  * @Title:deleteRole
	  * @Description:通过id删除角色
	  * @param:@param id
	  * @return:void
	  * @throws
	  * @author:jack
	  * @Date:2019年8月17日 上午8:49:32
	   */
	  public void deleteRole(String id) {
		  //删除角色前解除和用户的关系
		  //1.找到该角色
		  Role role = roleDao.findById(id).get();
		  //2.通过该角色找到角色用户中间表
		  List<UserRole> userRoles=role.getUserRoles();
		  //3.删除角色和用户的关系
		  for (UserRole userRole : userRoles) {
			userRoleDao.delete(userRole);
		}
		  //删除角色的所有功能
		  role.getFunctions().clear();
		  //最终删除角色
		  roleDao.delete(role);
	  }
	  /**
	   * 
	  * @Title:findRole
	  * @Description:根据id查询角色
	  * @param:@return
	  * @return:Role
	  * @throws
	  * @author:jack
	  * @Date:2019年8月17日 上午10:09:00
	   */
	  public Role findRole(String id) {
		  return roleDao.findById(id).get();
	  }
	  /**
	   * 
	  * @Title:findEmpInformations
	  * @Description:所有员工
	  * @param:@return
	  * @return:List<EmpInformation>
	  * @throws
	  * @author:jack
	  * @Date:2019年8月17日 上午10:10:51
	   */
	 public List<EmpInformation> findEmpInformations(){
		return (List<EmpInformation>) empInformationDao.findAll();
	 }
	 /**
	  * 
	 * @Title:findempEmpInformationsbyrole
	 * @Description:查找该职位的所有员工
	 * @param:@param roleid
	 * @param:@return
	 * @return:List<EmpInformation>
	 * @throws
	 * @author:jack
	 * @Date:2019年8月17日 下午2:59:19
	  */
	  public List<String> findempEmpInformationsbyrole(String roleid){
		  return roleDao.findEmpInformationsbyroleid(roleid);
	  }
	  /**
	   * 
	  * @Title:addusers
	  * @Description:给职位添加员工
	  * @param:@param roleid
	  * @param:@param emp
	  * @return:void
	  * @throws
	  * @author:jack
	  * @Date:2019年8月17日 下午3:34:57
	   */
	  public void addusers(String roleid,List<String> ygxh) {
		  
	  }
	  /**
	   * 
	  * @Title:findRoles
	  * @Description:通过名字模糊分页查询职位
	  * @param:@param rolePosition
	  * @param:@param currentpage
	  * @param:@return
	  * @return:List<Role>
	  * @throws
	  * @author:jack
	  * @Date:2019年8月19日 上午11:22:32
	   */
	  public List<Role> findRoless(String rolePosition,int currentpage){
		  rolePosition = "%"+rolePosition+"%";
			PageRequest pageRequest = PageRequest.of(currentpage-1, 5);
			return roleDao.findroless(rolePosition,pageRequest);
		}
	/**
	 * 
	* @Title:findcount
	* @Description:通过名字模糊查询有多少职位
	* @param:@param rolePosition
	* @param:@return
	* @return:long
	* @throws
	* @author:jack
	* @Date:2019年8月19日 上午11:45:36
	 */
	public long findcount(String rolePosition) {
		  rolePosition = "%"+rolePosition+"%";
		  return roleDao.countnames(rolePosition);
	}
	/**
	 * 
	* @Title:fenpei
	* @Description:通过roleid和functionid数组 给当前role添加权限
	* @param:@param roleid
	* @return:void
	* @throws
	* @author:jack
	* @Date:2019年8月19日 下午2:43:32
	 */
	public void fenpei(String roleid,String funcStrings) {
		//通过roleid找到role
		Role role=roleDao.findById(roleid).get();
		//先去掉该职位所有的权限
		role.getFunctions().clear();
		roleDao.save(role);
		//字符串处理
		String a[]=funcStrings.split(",");
		List<Function> functions = new ArrayList<>();
		for (String string : a) {
			if(!"".equals(string)) {
				Function function=functionDAO.findById(string).get();
				functions.add(function);
			}
			
		}
		role.setFunctions(functions);
		roleDao.save(role);
	}
	
	public void grantUserForRole(String roleid,String empids) {
		String emps[] = empids.split(",");
		//获得empid的数组
		//清空roleuser中所有roleid对应的关系
		Role role = roleDao.findById(roleid).get();
		for (UserRole userRole : role.getUserRoles()) {
			userRoleDao.delete(userRole);
		}
		//给 roleuser的roleid分配
		for (String empid : emps) {
			if(!"".equals(empid)) {
				UserRole userRole = new UserRole();
				UserRolePK PK = new UserRolePK();
				PK.setRoleId(roleid);
				PK.setYgxh(empid);
				userRole.setId(PK);
				userRoleDao.save(userRole);
			}
		}
	}
	public long findcountsbyname(String rolename) {
		return roleDao.countnamesss(rolename);
	}
	
	public List<String> findempygxhs(String roleid){
		return roleDao.findempygxhs(roleid);
	}
	public List<EmpInformation> findempss(String roleid){
		return roleDao.findempssss(roleid);
	}
	/**
	 * 
	* @Title:addquanxianfenlei
	* @Description:添加一个权限分类
	* @param:@param functionClassify
	* @return:void
	* @throws
	* @author:jack
	* @Date:2019年8月22日 下午5:32:46
	 */
	public void addquanxianfenlei(FunctionClassify functionClassify) {
		if(functionClassify.getFuncClassifyId().equals("")) {
			functionClassify.setFuncClassifyId(UUIDGenerator.getUUID());
		}
		functionClassifyDAO.save(functionClassify);
	}
	/**
	 * 
	* @Title:deletequanxianfenlei
	* @Description:删除一个权限分类,级联删除该分类所有权限
	* @param:@param funcClassifyId
	* @return:void
	* @throws
	* @author:jack
	* @Date:2019年8月22日 下午5:35:43
	 */
	public void deletequanxianfenlei(String funcClassifyId) {
		functionClassifyDAO.deleteById(funcClassifyId);
	}
	/**
	 * 
	* @Title:addquanxian
	* @Description:添加一个权限 并给这个权限分类
	* @param:@param funcClassifyId
	* @param:@param functionName
	* @return:void
	* @throws
	* @author:jack
	* @Date:2019年8月22日 下午8:27:35
	 */
	public void addquanxianandtype(String funcClassifyId,String functionName) {
		//自动生成uuid 增加一个权限
		Function function = new Function();
		function.setFunctionName(functionName);
		function.setFunctionId(UUIDGenerator.getUUID());
		functionDAO.save(function);
		//给这个权限分类
		//找到这个权限分类
		FunctionClassify functionClassify = functionClassifyDAO.findById(funcClassifyId).get();
		//主控方添加
		function.setFunctionClassify(functionClassify);
		functionDAO.save(function);
	}
	/**
	 * 
	* @Title:deletefunction
	* @Description:删除权限
	* @param:@param functionid
	* @return:void
	* @throws
	* @author:jack
	* @Date:2019年8月23日 上午9:09:59
	 */
	public void deletefunction(String functionid) {
		//删除权限前，先要解除和所有职位的关系
		Function function = functionDAO.findById(functionid).get();
		//先找到所有有该权限的职位
		List<Role> roles=function.getRoles();
		for (Role role : roles) {
			role.getFunctions().remove(function);
			roleDao.save(role);
		}
		functionDAO.deleteById(functionid);
	}
	/**
	 * 
	* @Title:updatefunction
	* @Description:修改权限名字
	* @param:@param function
	* @return:void
	* @throws
	* @author:jack
	* @Date:2019年8月23日 上午9:13:15
	 */
	public void updatefunction(Function function) {
		String id=function.getFunctionId();
		Function function2=functionDAO.findById(id).get();
		function2.setFunctionName(function.getFunctionName());
		/* function.setFunctionClassify(function2.getFunctionClassify()); */
		functionDAO.save(function2);
	}
	/**
	 * 
	* @Title:findcountquanxian
	* @Description:查找权限分类的名字的 数目
	* @param:@param funcClassifyName
	* @param:@return
	* @return:long
	* @throws
	* @author:jack
	* @Date:2019年8月23日 下午3:13:49
	 */
	public long findcountquanxian(String funcClassifyName) {
		return functionClassifyDAO.ffindnamecount(funcClassifyName);
	}
	public long findcountqx(String functionName) {
		return functionDAO.findcountquanxian(functionName);
	}
	public Set<String> findfunctionsid(String ygxh){
		//找到所有的职位的id
		Set<String> roleidSet=userRoleDao.findroleidsStrings(ygxh);
		Set<String> functionsid = new HashSet<String>();
		//遍历每个职位
		for (String roleid : roleidSet) {
			//找到员工
			Role role = roleDao.findById(roleid).get();
			//找到每个员工对应的所有功能
			List<Function> functions1=role.getFunctions();
			//拿到每个功能的id放到set集合里面去
			for (Function function : functions1) {
				functionsid.add(function.getFunctionId());
			}
		}
			return functionsid;
	}
}







