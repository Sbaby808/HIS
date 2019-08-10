package com.his.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.pojo.JsonResult;
import com.his.pojo.OtherProject;
import com.his.service.OtherProjectService;

/**  
* @ClassName: OtherProjectController  
* @Description: 其他收费项控制器
* @author Sbaby
* @date 2019年8月3日  上午9:10:24
*    
*/
@Controller
public class OtherProjectController {

	@Autowired
	private OtherProjectService otherProjectService;
	
	/**
	* @Title:addOtherProject
	* @Description:添加其他收费项
	* @param:@param otherProject
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月3日 上午9:13:11
	 */
	@PostMapping("/add_other_project")
	@ResponseBody
	public JsonResult addOtherProject(@RequestBody OtherProject otherProject) {
//		System.out.println(otherProject.getProjectName());
		JsonResult result = new JsonResult();
		try {
			otherProjectService.addOtherProject(otherProject);
			result.setResult(otherProject);
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(otherProject);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	 * 
	* @Title:getHosBedProject
	* @Description:查询住院床位的收费项
	* @param:@return
	* @return:List<OtherProject>
	* @throws
	* @author:Hamster
	* @Date:2019年8月4日 下午9:47:30
	 */
	@ResponseBody
	@GetMapping("/get_hosBed_project")
	public List <OtherProject> getHosBedProject(){
		return otherProjectService.getHosBedProject();
	}
	
	/**
	* @Title:getAllOtherProject
	* @Description:查询所有其他收费项
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 上午9:50:11
	 */
	@GetMapping("/get_all_other_project")
	@ResponseBody
	public JsonResult getAllOtherProject() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(otherProjectService.getAll());
			result.setStatus("ok");
		} catch (Exception e) {
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getOtherProjectByPage
	* @Description:分页查询其他收费项
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 上午10:25:38
	 */
	@GetMapping("/get_other_project_by_page")
	@ResponseBody
	public JsonResult getOtherProjectByPage(int pageNum, int pageSize) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(otherProjectService.getByPage(pageNum, pageSize));
			result.setStatus("ok");
		} catch (Exception e) {
			result.setResult(pageNum);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:getAllCount
	* @Description:查询其他收费项总记录条数
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 上午10:36:37
	 */
	@GetMapping("/get_other_project_count")
	@ResponseBody
	public JsonResult getAllCount() {
		JsonResult result = new JsonResult();
		try {
			result.setResult(otherProjectService.getCount());
			result.setStatus("ok");
		} catch (Exception e) {
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:updateOtherProject
	* @Description:更新其他收费项信息
	* @param:@param otherProject
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 下午2:41:10
	 */
	@PostMapping("update_other_project")
	@ResponseBody
	public JsonResult updateOtherProject(@RequestBody OtherProject otherProject) {
		JsonResult result = new JsonResult();
		try {
			otherProjectService.addOtherProject(otherProject);
			result.setResult(otherProject);
			result.setStatus("ok");
		} catch (Exception e) {
			result.setResult(otherProject);
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:searchOtherProject
	* @Description:按条件查询其他划价项目
	* @param:@param searchkey
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 下午3:42:08
	 */
	@GetMapping("/search_other_project")
	@ResponseBody
	public JsonResult searchOtherProject(String searchKey, BigDecimal minPrice, BigDecimal maxPrice, int pageNum, int pageSize) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(otherProjectService.searchByKey(searchKey, minPrice, maxPrice, pageNum, pageSize));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
	
	/**
	* @Title:searchCount
	* @Description:根据条件搜索的记录条数
	* @param:@param searchKey
	* @param:@param minPrice
	* @param:@param maxPrice
	* @param:@return
	* @return:JsonResult
	* @throws
	* @author:Sbaby
	* @Date:2019年8月6日 下午4:17:18
	 */
	@GetMapping("/get_search_other_project_count")
	@ResponseBody
	public JsonResult searchCount(String searchKey, BigDecimal minPrice, BigDecimal maxPrice) {
		JsonResult result = new JsonResult();
		try {
			result.setResult(otherProjectService.getSearchCount(searchKey, minPrice, maxPrice));
			result.setStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("error");
		}
		return result;
	}
}
