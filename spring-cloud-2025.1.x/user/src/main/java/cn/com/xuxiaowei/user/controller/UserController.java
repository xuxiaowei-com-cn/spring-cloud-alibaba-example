package cn.com.xuxiaowei.user.controller;

import cn.com.xuxiaowei.user.properties.UserProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class UserController {

    private UserProperties userProperties;

    @Autowired
    public void setUserProperties(UserProperties userProperties) {
        this.userProperties = userProperties;
    }

    @GetMapping("/properties")
    public Map<String, Object> getUserProperties() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", userProperties.getName());
        map.put("description", userProperties.getDescription());
        map.put("username", userProperties.getUsername());
        map.put("password", userProperties.getPassword());

        String hostName;
        try {
            // Get the local host instance
            InetAddress localHost = InetAddress.getLocalHost();
            // Get the computer name (hostname)
            hostName = localHost.getHostName();
        } catch (UnknownHostException e) {
            log.error("获取 IP 异常：", e);
            hostName = e.getMessage();
        }
        map.put("hostName", hostName);

        return map;
    }

}
