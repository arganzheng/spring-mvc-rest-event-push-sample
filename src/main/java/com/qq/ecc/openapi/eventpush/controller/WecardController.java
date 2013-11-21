package com.qq.ecc.openapi.eventpush.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qq.ecc.openapi.eventpush.common.RestResponse;
import com.qq.ecc.openapi.eventpush.model.BindCardReq;
import com.qq.ecc.openapi.eventpush.model.BindCardResp;

/**
 * 会员卡消息通知处理
 * 
 * @author arganzheng
 * @date 2013-11-21
 */
@Controller
@RequestMapping("/wecard")
public class WecardController {

    private static final Logger logger = Logger.getLogger(WecardController.class);

    @Value("${secretKey}")
    private String              secretKey;

    /**
     * <pre>
     *  处理GET callbackUrl 验证请求。
     *  
     *  开发者提交信息后，开放平台将发送一个GET请求到填写的URL上，GET请求携带timestamp,nonce,signature,echostr四个参数。
     *  若确认此次GET请求来自开放平台，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。
     *  具体参见 [主动推送文档](http://open.weigou.qq.com/doc/api-push)
     *  
     *  由于签名校验全部在AuthorizationInterceptor拦截器中处理了，所以这里只是简单返回echostr就可以了。
     * </pre>
     */
    @RequestMapping(method = GET)
    @ResponseBody
    public String verify(@RequestParam(required = false)
    String echostr) {
        Assert.isTrue(echostr != null, "echostr is required for GET verify!");

        return echostr;
    }

    /**
     * <pre>
     * 接收POST Calllabck消息通知 
     * 请求消息格式为JSON字符串，格式为：
     * {
     *     "topic": "wecard",
     *     "event": "bindCard",
     *     "uin": 商家的uin,
     *     "data": {
     *         ...
     *     }
     * }
     * 接收之后要根据[会员卡推送接口文档](http://open.weigou.qq.com/doc/wecard-push-api)进行解析。
     * </pre>
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RestResponse<Object> receiveMessage(HttpServletRequest request) throws IOException {
        // BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(),
        // Constants.CHARSET_UTF8));
        String json = getStringValueFromReader(request.getReader());

        JSONObject jsonObject = new JSONObject(json);

        RestResponse<Object> resp = new RestResponse<Object>();

        String event = jsonObject.getString("event");
        if ("bindCard".equalsIgnoreCase(event)) {
            BindCardReq bindCardReq = new BindCardReq();
            JSONObject data = jsonObject.getJSONObject("data");
            bindCardReq.setOpenId(data.getString("openId"));
            bindCardReq.setCardNo(data.getString("cardNo"));
            bindCardReq.setMobile(data.getString("mobile"));

            BindCardResp bindCardResp = doBindCard(bindCardReq);
            resp.setData(bindCardResp);
        } else {
            // TODO
        }

        return resp;
    }

    private BindCardResp doBindCard(BindCardReq bindCardReq) {
        BindCardResp bindCardResp = new BindCardResp();

        bindCardResp.setOpenId(bindCardReq.getOpenId());
        bindCardResp.setCardId(bindCardReq.getCardNo());
        bindCardResp.setCardNo(bindCardReq.getCardNo());
        bindCardResp.setExpiryDate("2013-10-31 20:18:20");
        bindCardResp.setCardLevelId(1);
        bindCardResp.setName("arganzheng");
        bindCardResp.setMobile(bindCardReq.getMobile());
        bindCardResp.setBirthday("1985-11-16");
        bindCardResp.setPoints(100);

        return bindCardResp;
    }

    private String getStringValueFromReader(BufferedReader reader) {
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null)
                sb.append(line);
        } catch (Exception e) { /* report an error */
            logger.error("get data from request failed!", e);
        }
        return sb.toString();
    }
}
