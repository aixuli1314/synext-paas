package co.synext.module.demo.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ImageUtil;
import com.egzosn.pay.ali.api.AliPayService;
import com.egzosn.pay.ali.bean.AliTransactionType;
import com.egzosn.pay.common.bean.MethodType;
import com.egzosn.pay.common.bean.PayOrder;
import com.egzosn.pay.wx.api.WxPayService;
import com.egzosn.pay.wx.bean.WxTransactionType;
import co.synext.common.enums.Enums;
import co.synext.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author xu.ran
 * @date 2020/11/30 20:16
 * @description: TODO
 */
@Controller
@RequestMapping("/uc/pay")
public class PayController {

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private WxPayService wxPayService;


    @RequestMapping(value = "toAliHtmlPay", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String toAliHtmlPay(@RequestParam("orderId") String orderId) {
        if ("".equals(Enums.PayTypeEnum.微信扫码支付.getCode()))
            throw new BizException("此方式只支持支付宝！");
        PayOrder payOrder = new PayOrder("", "", new BigDecimal(100),"", AliTransactionType.PAGE);
        Map requestParams = aliPayService.orderInfo(payOrder);
        return aliPayService.buildRequest(requestParams, MethodType.POST);


    }

    @RequestMapping(value = "toAliQrPay")
    @ResponseBody
    public String toAliQrPay(@RequestParam("orderId") String orderId) throws IOException {
        PayOrder payOrder = new PayOrder("", "", new BigDecimal("1"), "");
        payOrder.setTransactionType(AliTransactionType.SWEEPPAY);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedImage bufferedImage = aliPayService.genQrPay(payOrder);
        ImageUtil.write(bufferedImage, ImageUtil.IMAGE_TYPE_JPEG, outputStream);
        String base64Str = Base64.encode(outputStream.toByteArray());
        return "data:image/jpeg;base64," + base64Str;

    }

    @RequestMapping(value = "toWxQrPay")
    @ResponseBody
    public String toWxQrPay(@RequestParam("orderId") String orderId){
        PayOrder payOrder = new PayOrder("", "", new BigDecimal("1"), "");
        payOrder.setTransactionType(WxTransactionType.NATIVE);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedImage bufferedImage = wxPayService.genQrPay(payOrder);
        ImageUtil.write(bufferedImage, ImageUtil.IMAGE_TYPE_JPEG, outputStream);
        String base64Str = Base64.encode(outputStream.toByteArray());
        return "data:image/jpeg;base64," + base64Str;

    }


    @RequestMapping(value = "/alipay/notify")
    @ResponseBody
    public String alipayCallback(HttpServletRequest request) throws IOException {
        Map<String, Object> params = aliPayService.getParameter2Map(request.getParameterMap(), request.getInputStream());
        if (null != params) {
            if (aliPayService.verify(params)) {

                return aliPayService.getPayOutMessage("success", "成功").toMessage();
            }
        }
        return aliPayService.getPayOutMessage("fail", "失败").toMessage();

    }

    @RequestMapping(value = "/wxpay/notify")
    @ResponseBody
    public String payBack(HttpServletRequest request) throws IOException {
        Map<String, Object> params = wxPayService.getParameter2Map(request.getParameterMap(), request.getInputStream());
        if (null != params) {
            if (wxPayService.verify(params)) {

                return wxPayService.getPayOutMessage("success", "成功").toMessage();
            }
        }
        return wxPayService.getPayOutMessage("fail", "失败").toMessage();
    }
}
