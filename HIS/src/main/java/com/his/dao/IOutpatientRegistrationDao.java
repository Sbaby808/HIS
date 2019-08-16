package com.his.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.his.pojo.OutpatientRegistration;

/**  
* @ClassName: IOutpatientRegistrationDao  
* @Description: 门诊挂号Dao
* @author Sbaby
* @date 2019年8月9日  下午4:25:13
*    
*/
public interface IOutpatientRegistrationDao extends CrudRepository<OutpatientRegistration, String> {

	/**
	* @Title:getRegsitrationCountByEmpId
	* @Description:根据医生编号查询当日挂号数量
	* @param:@param empId
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月10日 下午2:33:06
	 */
	@Query(value = "select count(*) from reg_emp re left outer join outpatient_registration r on re.reg_id = r.reg_id "
       + " where to_char(r.do_date,'yyyy-mm-dd') = to_char(sysdate, 'yyyy-mm-dd') and re.ygxh = ?1 and r.reg_status != '退号'", nativeQuery = true)
	public int getRegsitrationCountByEmpId(String empId);
	
	/**
	* @Title:checkEmp
	* @Description:查询医生已挂号数量
	* @param:@param ygxh
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月12日 下午3:48:16
	 */
	@Query(value = "select count(*) from ((emp_information e left outer join reg_emp re on e.ygxh = re.ygxh) "
       + " left outer join outpatient_registration ore on re.reg_id = ore.reg_id) "
       + " where e.ygxh = ?1 "
       + " and to_char(ore.do_date, 'yyyy-mm-dd') = to_char(sysdate, 'yyyy-mm-dd') "
       + " and re.reg_duty = '医生'", nativeQuery = true)
	public int checkEmp(String ygxh);
	
	/**
	* @Title:getAllRegsByCardNum
	* @Description:分页查询就诊卡的挂号记录
	* @param:@param cardNum
	* @param:@return
	* @return:List<OutpatientRegistration>
	* @throws
	* @author:Sbaby
	* @Date:2019年8月14日 下午3:39:24
	 */
	@Query("from OutpatientRegistration ore where ore.medicalCard.cardId = ?1")
	public List<OutpatientRegistration> getAllRegsByCardNum(String cardNum, Pageable pageable);
	
	/**
	* @Title:getAllRegsByCardNumCount
	* @Description:根据就诊卡编号查询所有挂号记录的数量
	* @param:@param cardNum
	* @param:@return
	* @return:int
	* @throws
	* @author:Sbaby
	* @Date:2019年8月14日 下午4:06:51
	 */
	@Query("select count(*) from OutpatientRegistration ore where ore.medicalCard.cardId = ?1")
	public int getAllRegsByCardNumCount(String cardNum);
}