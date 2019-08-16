package com.his.utils.aliPayTools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class AlipayNotify {


	private static final String HTTPS_VERIFY_URL = "https://mapi.alipaydev.com/gateway.do?service=notify_verify&";


	/**
     * 鏍规嵁鍙嶉鍥炴潵鐨勪俊鎭紝鐢熸垚绛惧悕缁撴灉
     * @param Params 閫氱煡杩斿洖鏉ョ殑鍙傛暟鏁扮粍
     * @param sign 姣斿鐨勭鍚嶇粨鏋�
     * @return 鐢熸垚鐨勭鍚嶇粨鏋�
     */
	public static boolean getSignVeryfy(Map<String, String> Params, String sign) {
		//杩囨护绌哄€笺€乻ign涓巗ign_type鍙傛暟
		Map<String, String> sParaNew = AlipayCore.paraFilter(Params);
		//鑾峰彇寰呯鍚嶅瓧绗︿覆
		String preSignStr = AlipayCore.createLinkString(sParaNew);
		//鑾峰緱绛惧悕楠岃瘉缁撴灉
		boolean isSign = false;
		if(AlipayConfig.sign_type.equals("RSA")){
			isSign = RSA.verify(preSignStr, sign, AlipayConfig.alipay_public_key, AlipayConfig.input_charset);
		}
		 return isSign;
	}


	/**
     * 获取远程服务器ATN结果,验证返回URL
     * @param notify_id 通知校验ID
     * @return 服务器ATN结果
     * 验证结果集：
     * invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 
     * true 返回正确信息
     * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
     */
	public static String verifyResponse(String notify_id) {
	//获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求


	String partner = AlipayConfig.partner;
	String veryfy_url = HTTPS_VERIFY_URL + "partner=" + partner + "&notify_id=" + notify_id;


	return checkUrl(veryfy_url);
    }


	/**
    * 获取远程服务器ATN结果
    * @param urlvalue 指定URL路径地址
    * @return 服务器ATN结果
    * 验证结果集：
    * invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 
    * true 返回正确信息
    * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
    */
	public static String checkUrl(String urlvalue) {
		String inputLine = "";


		try {
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection
					.getInputStream()));
			inputLine = in.readLine().toString();
		} catch (Exception e) {
			e.printStackTrace();
			inputLine = "";
		}

		return inputLine;
    }
}
