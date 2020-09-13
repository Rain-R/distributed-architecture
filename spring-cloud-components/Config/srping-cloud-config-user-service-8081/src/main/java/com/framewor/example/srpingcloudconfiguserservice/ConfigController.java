package com.framewor.example.srpingcloudconfiguserservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/7/14 14:46
 * @since JDK 1.8
 */

@RestController
@RequestMapping("/user")
@RefreshScope
public class ConfigController {

    @Value("${env}")
    String txt;

    @GetMapping("/config")
    public String config(){
        return  txt;
    }
}
