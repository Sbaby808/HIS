package com.his.utils.aliPayTools;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 */


public class AlipayConfig {
	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088102177589859";

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
//	public static String private_key = "xxxxxxx";
	//沙箱环境
	 public static String private_key="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCIKsbJ7bdWZoB7+LORUpdhQx4lNlpSiYYnxB7/isQHtmpJ8loGA5wTDkLxq7xEzojUeZHhBAQGpxlV97bInam6txGGoK/sKcEag/vHcAPPW0yqFwG33x+ipaqIIr0xFExueW87ISDjkpIgdquA3dTdZMwgh7YMoYYsFEYGUylj0bOFgEIaLAp6gvk25vsW1Rhda2Ff1Kv/o4JpEZqtOlyHd7ugLPSHBG+aafdMjFQEd5jbP6YIKq+rGt5d+yPkblF6XaFPg3SGsQOioI4mLt6g9Xt4VLAEckP7qzNRqwzqfKONuYTEgaq/Qbrxl9N8nOSEJbolOfQNXUemlYfSE3ipAgMBAAECggEAbjbhjFRDD0YMPUwCXGATc4BWCBzEYwY5djqCNKjnHq8BvTTStQd67tmeSeqNisv5aLG16AHOGGxsT28fnaYv2ZeQMf4iJu9tA79MrhL2ooHtvca9d0y8WHPrYiFsLSuW6dyUsbGQ42KhsHEdRENJpqGNr87pBbQ/27HVNA1f9RB6n4v/NB1b0PQwQuBsGwBtNMzVyK4FNg5ONlIk9oNLNMP5uiVVzUe97AMKFHZ/39mJDGEb22iVN+afDCWRTqOf/cGxDmsCJgYGS1rnijIp9VuhTirYol8CCpBqYWC3W8Md6Ia1H0C3NmaKHGIpfaPZjrW+9/9jDrToF8IQ2mAtAQKBgQDFT6RQu3okXSci5fs7DNSAKgE40oKcQspIkLgJhp/V1uORGg2Z8EkLSgEnRvbWp4Bg52x0Qb78QlZyPR1r60Hy2MkiMRixJnTFxQIeDNbUaJYCDU3fqHwekAdbeEvMJP4OWiGQQI9O/EhLHgjXw/a70s5Yd2ckSl3UeeMTBTj9UQKBgQCwq0rDdG2yvE7PjfdsoReYIJpMvV6A4MkHUtKHgVm5VWGHgr045ASMV5X7xtfYd3kSflId+fDiv/5AqWZ7ACqhFpLQLQA7OEZzsqll8w5MQvGhnqAfVe5EpZGxXhnhasNsJz7l2DWe+QbZwyYYeb2RpVjmAlgmG6lK03atr90P2QKBgQC1Vh+WjuaPWwln2mONfpoh+/jJhzbAc+XC0TZCkvZ8qhTVO1N6wcnv1SDAP0kGOEUVSjtzkST8Y99c+Gv8zeb7UF9xzBt8W/J//DKY+YXLCx+qXR3PyuHfmNEaMLHAudK/z/f+wkay+ctbNgw8eH5fWjuINJyXTr1zVF3sEiWxMQKBgG/m34Ez8vPecLDoxEqrMs2qAPIQlJZfSxX3cOBhnxpUKWVy+zXYv8TBCYAjr74t1qNPHeZc1SZa48nDQuUv8tB8AtwXVOktuPo5tTCdJnZJhNGRLu0KOdSDZugIqa3tOQD9Tsq5CyW5qIwwHqYsHlXtfIScAYeJx85caPSUyxnpAoGAUL7F+T5I+NC4Gs6/3fyF2xmb6uFgipQRqzGgZeJoqPOQPe3yb+nbe5a8PGu2ikMrV/lMUZxSsQaiSQl2Y+kA6W13oxd+YRp7aUpjFML66eDBXEqUexG8eIdrOF7nb6K0lRB1MsKeRTK8ittYMZnOQvo4HojD3KOhN8IMj4QcZkA=" ;

	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = "2088102177589859" ;

	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnNd2+vLvFFzVWQHhC6LJ+XOCVYdxWc6TzijuN5WPaN39+bd4I29h8eC1YdXvGnZ7iWJDpButq4qU+HqcBFWN2pqp5BHvdzCoi8syqoJXOEpd7O82Z45Mq4RhxCH2ZhwLyKSCMh3Yh3EA1+XA0iDSQp2XhvZGFZZ8ah8BuYCb0vet7I37wdh3MuDG/QOhT9EkuMdYwBvr3rkQkP4h4FVU5RuRZqieZpYKDg3MdEDfAi1rOz7/7DoDdJc+SRIIhTQz15X3UjXFYjtlsSUnp25XbQB4QZ1/Dx8VgrsXx49iJg7av6jd13LeWELMh49wrekNa3Z+X3QVeVLjfDj5EQpk5wIDAQAB";

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\HIS\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 签名方式 不需修改
	public static String sign_type = "RSA";

	// 支付类型 ，无需修改
	public static String payment_type = "1";

	// 调用的接口名，无需修改
	public static String service = "create_direct_pay_by_user";

	public static String notify_url = "http://2wd6211182.wicp.vip:45150/notify_pay" ;
}