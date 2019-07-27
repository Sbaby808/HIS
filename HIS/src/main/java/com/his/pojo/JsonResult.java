/**
 * 
 */
package com.his.pojo;

/**  
* @ClassName: JsonResult  
* @Description: Controller返回类型
* @author Sbaby
* @date 2019年7月27日  下午2:59:09
*    
*/
public class JsonResult {
	
	private String status = null;
	private Object result = null;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	

}
