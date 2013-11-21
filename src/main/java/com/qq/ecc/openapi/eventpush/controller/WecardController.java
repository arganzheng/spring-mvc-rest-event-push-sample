package com.qq.ecc.openapi.eventpush.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qq.ecc.openapi.eventpush.common.RestResponse;
import com.qq.ecc.openapi.eventpush.model.User;

/**
 * 会员卡消息通知处理
 * 
 * @author arganzheng
 * @date 2013-11-21
 */
@Controller
@RequestMapping("/wecard")
public class WecardController {

    private static final Logger      logger = Logger.getLogger(WecardController.class);

    @Value("${secretKey}")
    private String                   secretKey;

    private static Map<String, User> users  = new HashMap<String, User>();

    static {
        users.put("jsmith", new User("Jane Smith", "jsmith"));
        users.put("djones", new User("Don Jones", "djones"));
        users.put("argan", new User("Argan Zheng", "argan"));
    }

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
    public RestResponse<User> receiveMessage(@RequestBody
    User user) {
        users.put(user.getUsername(), user);
        RestResponse<User> resp = new RestResponse<User>();
        resp.setData(user);
        return resp;
    }

}
