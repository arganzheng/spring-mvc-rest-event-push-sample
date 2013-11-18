package com.qq.ecc.openapi.eventpush.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qq.ecc.openapi.eventpush.common.RestResponse;
import com.qq.ecc.openapi.eventpush.exception.UnknownResourceException;
import com.qq.ecc.openapi.eventpush.model.User;
import com.qq.ecc.openapi.eventpush.util.SignUtils;

@Controller
@RequestMapping("/users")
public class UserController {

    private static Map<String, User> users = new HashMap<String, User>();

    static {
        users.put("jsmith", new User("Jane Smith", "jsmith"));
        users.put("djones", new User("Don Jones", "djones"));
        users.put("argan", new User("Argan Zheng", "argan"));
    }

    @RequestMapping(value = "/{username}", method = GET)
    @ResponseBody
    public User getUser(@PathVariable
    String username) {
        return findUser(username);
    }

    private User findUser(String username) throws UnknownResourceException {
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("Username is required.");
        }

        if (users.containsKey(username)) {
            return users.get(username);
        }

        throw new UnknownResourceException("Unable to find user with username '" + username + "'");
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RestResponse<User> addUser(@RequestBody
    User user) {
        users.put(user.getUsername(), user);
        RestResponse<User> resp = new RestResponse<User>();
        resp.setData(user);
        return resp;
    }

    @RequestMapping(value = "{username}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse<User> updateUser(@RequestBody
    User user, @RequestParam("timestamp")
    String timestamp, @RequestParam("noice")
    String noice, @RequestParam("signature")
    String signature) {
        User thisUser = findUser(user.getUsername());
        users.put(user.getUsername(), user);

        List<String> params = new ArrayList<String>();
        params.add(noice);
        params.add(timestamp);
        params.add("arganzheng");

        String sign = SignUtils.makeSign(params);

        thisUser.setUsername(sign);
        return new RestResponse<User>(thisUser);
    }

}
