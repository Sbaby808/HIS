package com.his;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alipay.demo.trade.model.GoodsDetail;
import com.his.pojo.AliPayEntity;
import com.his.pojo.EmpInformation;
import com.his.service.EmpInformationService;
import com.his.service.MedicalCardService;
import com.his.utils.AliPay;
import com.his.utils.MD5Tools;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HisApplicationTests {

	@Autowired
	private EmpInformationService empInformationService;
	@Autowired
	private MedicalCardService medicalCardService;
	
	@Test
	public void contextLoads() {
	}
	
	/**
	 * 
	* @Title:addTestEmp
	* @Description:添加测试员工信息
	* @param:
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年7月31日 下午2:03:37
	 */
	@Test
	public void addTestEmp() {
		EmpInformation admin = new EmpInformation();
		admin.setYgxh("ADMINTST");
		admin.setYgName("王富贵");
		admin.setYgSex("男");
		admin.setYgPassword(MD5Tools.KL(MD5Tools.Md5("admin123")));
		empInformationService.addTestEmp(admin);
	}
		
	/**
	 * 
	* @Title:testMD5Tools
	* @Description:测试MD5加密工具
	* @param:
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年7月31日 下午2:24:56
	 */
	@Test
	public void testMD5Tools() {
		String passwd = new String("admin123");
		String md5pwd = MD5Tools.Md5(passwd);
		if(!"".equals(md5pwd)) {
			System.out.println("md5:\t" + md5pwd);
			String md5spwd = MD5Tools.KL(md5pwd);
			System.out.println("md5s:\t" + md5spwd);
			String md5dpwd = MD5Tools.JM(md5spwd);
			System.out.println("md5d:\t" + md5dpwd);
		}
	}
	
	/**
	* @Title:testCheckCardTimes
	* @Description:测试检查就诊卡
	* @param:
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月2日 上午10:04:10
	 */
	@Test
	public void testCheckCardTimes() {
		String pid = "430922199808088518";
		System.out.println(medicalCardService.checkCardTimes(pid));
	}
	
	/**
	* @Title:testUUID
	* @Description:测试UUID
	* @param:
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月2日 上午11:00:54
	 */
	@Test
	public void testUUID() {
		String id = UUID.randomUUID().toString();
		System.out.println(id);
	}
	
	/**
	* @Title:testAliPay
	* @Description:测试支付宝生成预订单
	* @param:
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月2日 下午4:16:12
	 */
	@Test
	public void testAliPay() {
		AliPayEntity payEntity = new AliPayEntity();
		payEntity.setOutTradeNo("8a0b782638f14f43aa3046b2a0370253");
		payEntity.setSubject("HIS补办就诊卡费用");
		payEntity.setTotalAmount("5.0");
		payEntity.setBody("中南大学湘雅医院HIS-补办就诊卡工本费用-￥5.0元");
		List<GoodsDetail> goods = new ArrayList<GoodsDetail>();
		GoodsDetail good = GoodsDetail.newInstance("111", "就诊卡工本费", 5, 1);
		goods.add(good);
		String code = AliPay.pay(payEntity, goods);
		System.out.println(code);
	}
	
	/**
	* @Title:testAliPayQuery
	* @Description:测试支付查询
	* @param:
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月2日 下午11:05:34
	 */
	@Test
	public void testAliPayQuery() {
//		AliPay.query("8a0b782638f14f43aa3046b2a0370253");
	}

}
