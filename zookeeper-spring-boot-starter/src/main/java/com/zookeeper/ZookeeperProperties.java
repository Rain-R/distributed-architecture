package com.zookeeper;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/29 21:22
 * @since JDK 1.8
 */


@ConfigurationProperties(prefix = "gp.zookeeper")
public class ZookeeperProperties {

    String connectString;
    int sessionTimeout;

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }
}
