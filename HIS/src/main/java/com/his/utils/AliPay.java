package com.his.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.TradeFundBill;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.MonitorHeartbeatSynResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayHeartbeatSynRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradePayRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeRefundRequestBuilder;
import com.alipay.demo.trade.model.hb.EquipStatus;
import com.alipay.demo.trade.model.hb.ExceptionInfo;
import com.alipay.demo.trade.model.hb.HbStatus;
import com.alipay.demo.trade.model.hb.PosTradeInfo;
import com.alipay.demo.trade.model.hb.Product;
import com.alipay.demo.trade.model.hb.SysTradeInfo;
import com.alipay.demo.trade.model.hb.Type;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.alipay.demo.trade.service.AlipayMonitorService;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayMonitorServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeWithHBServiceImpl;
import com.alipay.demo.trade.utils.Utils;
import com.his.pojo.AliPayEntity;

/**
 * Created by liuyangkly on 15/8/9.
 * 简单main函数，用于测试当面付api
 * sdk和demo的意见和问题反馈请联系：liuyang.kly@alipay.com
 */
public class AliPay {
    private static Log log = LogFactory.getLog(AliPay.class);

    // 支付宝当面付2.0服务
    private static AlipayTradeService tradeService;

    // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
    private static AlipayTradeService   tradeWithHBService;

    // 支付宝交易保障接口服务，供测试接口api使用，请先阅读readme.txt
    private static AlipayMonitorService monitorService;

    static {
        /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
         *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
         */
        Configs.init("src/main/resources/static/zfbinfo.properties");

        /** 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

        // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
        tradeWithHBService = new AlipayTradeWithHBServiceImpl.ClientBuilder().build();

        /** 如果需要在程序中覆盖Configs提供的默认参数, 可以使用ClientBuilder类的setXXX方法修改默认参数 否则使用代码中的默认设置 */
        monitorService = new AlipayMonitorServiceImpl.ClientBuilder()
            .setGatewayUrl("http://mcloudmonitor.com/gateway.do").setCharset("GBK")
            .setFormat("json").build();
    }

    // 简单打印应答
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            log.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                log.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
                    response.getSubMsg()));
            }
            log.info("body:" + response.getBody());
        }
    }

    /**
    * @Title:pay
    * @Description:支付
    * @param:@param payEntity 支付信息
    * @param:@param goods 商品列表
    * @return:void
    * @throws
    * @author:Sbaby
    * @Date:2019年8月2日 下午8:12:18
     */
    public static String pay(AliPayEntity payEntity, List<GoodsDetail> goods) {
    	AliPay main = new AliPay();

        // 系统商商测试交易保障接口api
        //        main.test_monitor_sys();

        // POS厂商测试交易保障接口api
        //        main.test_monitor_pos();

        // 测试交易保障接口调度
        //        main.test_monitor_schedule_logic();

        // 测试当面付2.0支付（使用未集成交易保障接口的当面付2.0服务）
        //        main.test_trade_pay (tradeService);

        // 测试查询当面付2.0交易
        //        main.test_trade_query();

        // 测试当面付2.0退货
        //        main.test_trade_refund();

        // 测试当面付2.0生成支付二维码
        return main.test_trade_precreate(payEntity, goods);
    }
    
    public static boolean query(String outTradeNo) {
    	AliPay main = new AliPay();
    	// 测试查询当面付2.0交易
        return main.test_trade_query(outTradeNo);
    }

//    // 测试系统商交易保障调度
//    public void test_monitor_schedule_logic() {
//        // 启动交易保障线程
//        DemoHbRunner demoRunner = new DemoHbRunner(monitorService);
//        demoRunner.setDelay(5); // 设置启动后延迟5秒开始调度，不设置则默认3秒
//        demoRunner.setDuration(10); // 设置间隔10秒进行调度，不设置则默认15 * 60秒
//        demoRunner.schedule();
//
//        // 启动当面付，此处每隔5秒调用一次支付接口，并且当随机数为0时交易保障线程退出
//        while (Math.random() != 0) {
//            test_trade_pay(tradeWithHBService);
//            Utils.sleep(5 * 1000);
//        }
//
//        // 满足退出条件后可以调用shutdown优雅安全退出
//        demoRunner.shutdown();
//    }

    // 系统商的调用样例，填写了所有系统商商需要填写的字段
    public void test_monitor_sys() {
        // 系统商使用的交易信息格式，json字符串类型
        List<SysTradeInfo> sysTradeInfoList = new ArrayList<SysTradeInfo>();
        sysTradeInfoList.add(SysTradeInfo.newInstance("00000001", 5.2, HbStatus.S));
        sysTradeInfoList.add(SysTradeInfo.newInstance("00000002", 4.4, HbStatus.F));
        sysTradeInfoList.add(SysTradeInfo.newInstance("00000003", 11.3, HbStatus.P));
        sysTradeInfoList.add(SysTradeInfo.newInstance("00000004", 3.2, HbStatus.X));
        sysTradeInfoList.add(SysTradeInfo.newInstance("00000005", 4.1, HbStatus.X));

        // 填写异常信息，如果有的话
        List<ExceptionInfo> exceptionInfoList = new ArrayList<ExceptionInfo>();
        exceptionInfoList.add(ExceptionInfo.HE_SCANER);
        //        exceptionInfoList.add(ExceptionInfo.HE_PRINTER);
        //        exceptionInfoList.add(ExceptionInfo.HE_OTHER);

        // 填写扩展参数，如果有的话
        Map<String, Object> extendInfo = new HashMap<String, Object>();
        //        extendInfo.put("SHOP_ID", "BJ_ZZ_001");
        //        extendInfo.put("TERMINAL_ID", "1234");

        String appAuthToken = "应用授权令牌";//根据真实值填写

        AlipayHeartbeatSynRequestBuilder builder = new AlipayHeartbeatSynRequestBuilder()
            .setAppAuthToken(appAuthToken).setProduct(Product.FP).setType(Type.CR)
            .setEquipmentId("cr1000001").setEquipmentStatus(EquipStatus.NORMAL)
            .setTime(Utils.toDate(new Date())).setStoreId("store10001").setMac("0a:00:27:00:00:00")
            .setNetworkType("LAN").setProviderId("2088911212323549") // 设置系统商pid
            .setSysTradeInfoList(sysTradeInfoList) // 系统商同步trade_info信息
            //                .setExceptionInfoList(exceptionInfoList)  // 填写异常信息，如果有的话
            .setExtendInfo(extendInfo) // 填写扩展信息，如果有的话
        ;

        MonitorHeartbeatSynResponse response = monitorService.heartbeatSyn(builder);
        dumpResponse(response);
    }

    // POS厂商的调用样例，填写了所有pos厂商需要填写的字段
    public void test_monitor_pos() {
        // POS厂商使用的交易信息格式，字符串类型
        List<PosTradeInfo> posTradeInfoList = new ArrayList<PosTradeInfo>();
        posTradeInfoList.add(PosTradeInfo.newInstance(HbStatus.S, "1324", 7));
        posTradeInfoList.add(PosTradeInfo.newInstance(HbStatus.X, "1326", 15));
        posTradeInfoList.add(PosTradeInfo.newInstance(HbStatus.S, "1401", 8));
        posTradeInfoList.add(PosTradeInfo.newInstance(HbStatus.F, "1405", 3));

        // 填写异常信息，如果有的话
        List<ExceptionInfo> exceptionInfoList = new ArrayList<ExceptionInfo>();
        exceptionInfoList.add(ExceptionInfo.HE_PRINTER);

        // 填写扩展参数，如果有的话
        Map<String, Object> extendInfo = new HashMap<String, Object>();
        //        extendInfo.put("SHOP_ID", "BJ_ZZ_001");
        //        extendInfo.put("TERMINAL_ID", "1234");

        AlipayHeartbeatSynRequestBuilder builder = new AlipayHeartbeatSynRequestBuilder()
            .setProduct(Product.FP)
            .setType(Type.SOFT_POS)
            .setEquipmentId("soft100001")
            .setEquipmentStatus(EquipStatus.NORMAL)
            .setTime("2015-09-28 11:14:49")
            .setManufacturerPid("2088000000000009")
            // 填写机具商的支付宝pid
            .setStoreId("store200001").setEquipmentPosition("31.2433190000,121.5090750000")
            .setBbsPosition("2869719733-065|2896507033-091").setNetworkStatus("gggbbbgggnnn")
            .setNetworkType("3G").setBattery("98").setWifiMac("0a:00:27:00:00:00")
            .setWifiName("test_wifi_name").setIp("192.168.1.188")
            .setPosTradeInfoList(posTradeInfoList) // POS厂商同步trade_info信息
            //                .setExceptionInfoList(exceptionInfoList) // 填写异常信息，如果有的话
            .setExtendInfo(extendInfo) // 填写扩展信息，如果有的话
        ;

        MonitorHeartbeatSynResponse response = monitorService.heartbeatSyn(builder);
        dumpResponse(response);
    }

    // 测试当面付2.0支付
    public void test_trade_pay(AlipayTradeService service) {
        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        String outTradeNo = "tradepay" + System.currentTimeMillis()
                            + (long) (Math.random() * 10000000L);

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店消费”
        String subject = "xxx品牌xxx门店当面付消费";

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = "0.01";

        // (必填) 付款条码，用户支付宝钱包手机app点击“付款”产生的付款条码
        String authCode = "用户自己的支付宝付款码"; // 条码示例，286648048691290423
        // (可选，根据需要决定是否使用) 订单可打折金额，可以配合商家平台配置折扣活动，如果订单部分商品参与打折，可以将部分商品总价填写至此字段，默认全部商品可打折
        // 如果该值未传入,但传入了【订单总金额】,【不可打折金额】 则该值默认为【订单总金额】- 【不可打折金额】
        //        String discountableAmount = "1.00"; //

        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0.0";

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品3件共20.00元"
        String body = "购买商品3件共20.00元";

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "test_operator_id";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "test_store_id";

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        String providerId = "2088100200300400500";
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId(providerId);

        // 支付超时，线下扫码交易定义为5分钟
        String timeoutExpress = "5m";

        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
        // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
        GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "xxx面包", 1000, 1);
        // 创建好一个商品后添加至商品明细列表
        goodsDetailList.add(goods1);

        // 继续创建并添加第一条商品信息，用户购买的产品为“黑人牙刷”，单价为5.00元，购买了两件
        GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "xxx牙刷", 500, 2);
        goodsDetailList.add(goods2);

        String appAuthToken = "应用授权令牌";//根据真实值填写

        // 创建条码支付请求builder，设置请求参数
        AlipayTradePayRequestBuilder builder = new AlipayTradePayRequestBuilder()
            //            .setAppAuthToken(appAuthToken)
            .setOutTradeNo(outTradeNo).setSubject(subject).setAuthCode(authCode)
            .setTotalAmount(totalAmount).setStoreId(storeId)
            .setUndiscountableAmount(undiscountableAmount).setBody(body).setOperatorId(operatorId)
            .setExtendParams(extendParams).setSellerId(sellerId)
            .setGoodsDetailList(goodsDetailList).setTimeoutExpress(timeoutExpress);

        // 调用tradePay方法获取当面付应答
        AlipayF2FPayResult result = service.tradePay(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("支付宝支付成功: )");
                break;

            case FAILED:
                log.error("支付宝支付失败!!!");
                break;

            case UNKNOWN:
                log.error("系统异常，订单状态未知!!!");
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                break;
        }
    }

    // 测试当面付2.0查询订单
    public boolean test_trade_query(String outTradeNo) {
    	boolean flag = false;
    	
        // (必填) 商户订单号，通过此商户订单号查询当面付的交易状态
//        String outTradeNo = "tradepay14817938139942440181";

        // 创建查询请求builder，设置请求参数
        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
            .setOutTradeNo(outTradeNo);

        AlipayF2FQueryResult result = tradeService.queryTradeResult(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("查询返回该订单支付成功: )");
                AlipayTradeQueryResponse response = result.getResponse();
                dumpResponse(response);
                flag = true;
                log.info(response.getTradeStatus());
                if (Utils.isListNotEmpty(response.getFundBillList())) {
                    for (TradeFundBill bill : response.getFundBillList()) {
                        log.info(bill.getFundChannel() + ":" + bill.getAmount());
                    }
                }
                break;

            case FAILED:
                log.error("查询返回该订单支付失败或被关闭!!!");
                break;

            case UNKNOWN:
                log.error("系统异常，订单支付状态未知!!!");
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                break;
        }
        return flag;
    }

    // 测试当面付2.0退款
    public void test_trade_refund() {
        // (必填) 外部订单号，需要退款交易的商户外部订单号
        String outTradeNo = "tradepay14817938139942440181";

        // (必填) 退款金额，该金额必须小于等于订单的支付金额，单位为元
        String refundAmount = "0.01";

        // (可选，需要支持重复退货时必填) 商户退款请求号，相同支付宝交易号下的不同退款请求号对应同一笔交易的不同退款申请，
        // 对于相同支付宝交易号下多笔相同商户退款请求号的退款交易，支付宝只会进行一次退款
        String outRequestNo = "";

        // (必填) 退款原因，可以说明用户退款原因，方便为商家后台提供统计
        String refundReason = "正常退款，用户买多了";

        // (必填) 商户门店编号，退款情况下可以为商家后台提供退款权限判定和统计等作用，详询支付宝技术支持
        String storeId = "test_store_id";

        // 创建退款请求builder，设置请求参数
        AlipayTradeRefundRequestBuilder builder = new AlipayTradeRefundRequestBuilder()
            .setOutTradeNo(outTradeNo).setRefundAmount(refundAmount).setRefundReason(refundReason)
            .setOutRequestNo(outRequestNo).setStoreId(storeId);

        AlipayF2FRefundResult result = tradeService.tradeRefund(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("支付宝退款成功: )");
                break;

            case FAILED:
                log.error("支付宝退款失败!!!");
                break;

            case UNKNOWN:
                log.error("系统异常，订单退款状态未知!!!");
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                break;
        }
    }

    // 测试当面付2.0生成支付二维码
    public String test_trade_precreate(AliPayEntity payEntity, List<GoodsDetail> goods) {
    	String code = "";
        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        String outTradeNo = payEntity.getOutTradeNo();

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        String subject = payEntity.getSubject();

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = payEntity.getTotalAmount();

        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = payEntity.getUndiscountableAmount();

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = payEntity.getSellerId();

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = payEntity.getBody();

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = payEntity.getOperatorId();

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = payEntity.getStoreId();

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");

        // 支付超时，定义为120分钟
        String timeoutExpress = "180m";

        // 商品明细列表，需填写购买商品详细信息，
//        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
        // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
//        GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "xxx小面包", 1000, 1);
        // 创建好一个商品后添加至商品明细列表
//        goodsDetailList.add(goods1);

        // 继续创建并添加第一条商品信息，用户购买的产品为“黑人牙刷”，单价为5.00元，购买了两件
//        GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "xxx牙刷", 500, 2);
//        goodsDetailList.add(goods2);

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
            .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
            .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
            .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
            .setTimeoutExpress(timeoutExpress)
            .setNotifyUrl("http://2wd6211182.wicp.vip:45150/notify_pay")//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
            .setGoodsDetailList(goods);

        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);

                // 需要修改为运行机器上的路径
//                String filePath = String.format("src/main/resources/static/imgs/qr-%s.png",
//                    response.getOutTradeNo());
//                log.info("filePath:" + filePath);
//                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
                code =  response.getQrCode();
                break;

            case FAILED:
                log.error("支付宝预下单失败!!!");
                break;

            case UNKNOWN:
                log.error("系统异常，预下单状态未知!!!");
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                break;
        }
        return code;
    }
    
    /**
    * @Title:cancelTrade
    * @Description:关闭交易订单
    * @param:@param outTradeNo
    * @param:@return
    * @param:@throws AlipayApiException
    * @return:boolean
    * @throws
    * @author:Sbaby
    * @Date:2019年8月14日 下午2:33:11
     */
    public static boolean cancelTrade(String outTradeNo) throws AlipayApiException {
    	AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do","2016092800612632","MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCIKsbJ7bdWZoB7+LORUpdhQx4lNlpSiYYnxB7/isQHtmpJ8loGA5wTDkLxq7xEzojUeZHhBAQGpxlV97bInam6txGGoK/sKcEag/vHcAPPW0yqFwG33x+ipaqIIr0xFExueW87ISDjkpIgdquA3dTdZMwgh7YMoYYsFEYGUylj0bOFgEIaLAp6gvk25vsW1Rhda2Ff1Kv/o4JpEZqtOlyHd7ugLPSHBG+aafdMjFQEd5jbP6YIKq+rGt5d+yPkblF6XaFPg3SGsQOioI4mLt6g9Xt4VLAEckP7qzNRqwzqfKONuYTEgaq/Qbrxl9N8nOSEJbolOfQNXUemlYfSE3ipAgMBAAECggEAbjbhjFRDD0YMPUwCXGATc4BWCBzEYwY5djqCNKjnHq8BvTTStQd67tmeSeqNisv5aLG16AHOGGxsT28fnaYv2ZeQMf4iJu9tA79MrhL2ooHtvca9d0y8WHPrYiFsLSuW6dyUsbGQ42KhsHEdRENJpqGNr87pBbQ/27HVNA1f9RB6n4v/NB1b0PQwQuBsGwBtNMzVyK4FNg5ONlIk9oNLNMP5uiVVzUe97AMKFHZ/39mJDGEb22iVN+afDCWRTqOf/cGxDmsCJgYGS1rnijIp9VuhTirYol8CCpBqYWC3W8Md6Ia1H0C3NmaKHGIpfaPZjrW+9/9jDrToF8IQ2mAtAQKBgQDFT6RQu3okXSci5fs7DNSAKgE40oKcQspIkLgJhp/V1uORGg2Z8EkLSgEnRvbWp4Bg52x0Qb78QlZyPR1r60Hy2MkiMRixJnTFxQIeDNbUaJYCDU3fqHwekAdbeEvMJP4OWiGQQI9O/EhLHgjXw/a70s5Yd2ckSl3UeeMTBTj9UQKBgQCwq0rDdG2yvE7PjfdsoReYIJpMvV6A4MkHUtKHgVm5VWGHgr045ASMV5X7xtfYd3kSflId+fDiv/5AqWZ7ACqhFpLQLQA7OEZzsqll8w5MQvGhnqAfVe5EpZGxXhnhasNsJz7l2DWe+QbZwyYYeb2RpVjmAlgmG6lK03atr90P2QKBgQC1Vh+WjuaPWwln2mONfpoh+/jJhzbAc+XC0TZCkvZ8qhTVO1N6wcnv1SDAP0kGOEUVSjtzkST8Y99c+Gv8zeb7UF9xzBt8W/J//DKY+YXLCx+qXR3PyuHfmNEaMLHAudK/z/f+wkay+ctbNgw8eH5fWjuINJyXTr1zVF3sEiWxMQKBgG/m34Ez8vPecLDoxEqrMs2qAPIQlJZfSxX3cOBhnxpUKWVy+zXYv8TBCYAjr74t1qNPHeZc1SZa48nDQuUv8tB8AtwXVOktuPo5tTCdJnZJhNGRLu0KOdSDZugIqa3tOQD9Tsq5CyW5qIwwHqYsHlXtfIScAYeJx85caPSUyxnpAoGAUL7F+T5I+NC4Gs6/3fyF2xmb6uFgipQRqzGgZeJoqPOQPe3yb+nbe5a8PGu2ikMrV/lMUZxSsQaiSQl2Y+kA6W13oxd+YRp7aUpjFML66eDBXEqUexG8eIdrOF7nb6K0lRB1MsKeRTK8ittYMZnOQvo4HojD3KOhN8IMj4QcZkA=",
				"json","GBK","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnNd2+vLvFFzVWQHhC6LJ+XOCVYdxWc6TzijuN5WPaN39+bd4I29h8eC1YdXvGnZ7iWJDpButq4qU+HqcBFWN2pqp5BHvdzCoi8syqoJXOEpd7O82Z45Mq4RhxCH2ZhwLyKSCMh3Yh3EA1+XA0iDSQp2XhvZGFZZ8ah8BuYCb0vet7I37wdh3MuDG/QOhT9EkuMdYwBvr3rkQkP4h4FVU5RuRZqieZpYKDg3MdEDfAi1rOz7/7DoDdJc+SRIIhTQz15X3UjXFYjtlsSUnp25XbQB4QZ1/Dx8VgrsXx49iJg7av6jd13LeWELMh49wrekNa3Z+X3QVeVLjfDj5EQpk5wIDAQAB","RSA2");
		AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
		request.setBizContent("{" +
		"\"out_trade_no\":\""+ outTradeNo + "\"," +
		"\"trade_no\":\"\"" +
		"  }");
		AlipayTradeCancelResponse response = alipayClient.execute(request);
		return response.isSuccess();
    }
}
