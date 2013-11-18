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

/**
 * Example Spring MVC Controller that will throw exceptions for specific URLs to show exception handling.
 */
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
        // simulate Manager/DAO call:
        return findUser(username);
    }

    /**
     * Simulates a Manager or DAO lookup for a stored user account.
     * 
     * @param username the username for the user to lookup. Supports only 'jsmith' and 'djones' for testing.
     * @return the associated user
     * @throws UnknownResourceException if there is no user with the specified username.
     */
    private User findUser(String username) throws UnknownResourceException {
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("Username is required.");
        }

        // simulate a successful lookup for 2 users:
        if (users.containsKey(username)) {
            return users.get(username);
        }

        // any other lookup throws an exception (not found):
        throw new UnknownResourceException("Unable to find user with username '" + username + "'");
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RestResponse<User> addUser(@RequestBody
    User user) {
        users.put(user.getUsername(), user);
        RestResponse<User> resp = new RestResponse<User>();
        resp.setData(user);
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resp;
    }

    @RequestMapping(value = "{username}", method = RequestMethod.POST)
    @ResponseBody
    public User updateUser(@RequestBody
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
        return thisUser;
    }
}
