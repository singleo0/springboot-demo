package com.singleo.springboot.common.config;

import com.alibaba.cloud.nacos.registry.NacosAutoServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import java.lang.management.ManagementFactory;
import java.util.Set;

/**
 * springboot打war包,nacos注册配置
 */
@Component
public class NacosServiceRegistryConfig implements ApplicationRunner, ApplicationContextAware {
    Logger logger= LoggerFactory.getLogger(NacosServiceRegistryConfig.class);

    @Value("${server.port}")
    Integer port;

    @Value("${spring.cloud.nacos.discovery.enabled:true}")
    boolean nacosDiscoveryEnabled;

    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(!nacosDiscoveryEnabled){
            logger.info("nacos注册服务已关闭");
            return;
        }
        logger.info("==========NacosServiceRegistryConfig==================run===================");
        logger.info("==========NacosServiceRegistryConfig==================run===================");
        logger.info("==========NacosServiceRegistryConfig==================run===================");
        logger.info("==========NacosServiceRegistryConfig==================run===================");
        logger.info("==========NacosServiceRegistryConfig==================run===================");
        logger.info("==========NacosServiceRegistryConfig==================run===================");
        NacosAutoServiceRegistration registration=(NacosAutoServiceRegistration)applicationContext.getBean("nacosAutoServiceRegistration");
        if (registration != null && port != null) {
            Integer tomcatPort = port;
            try {
                tomcatPort = new Integer(getTomcatPort());
            } catch (Exception e) {
                e.printStackTrace();
            }

            registration.setPort(tomcatPort);
            registration.start();
        }

    }

    /**
     * 获取外部tomcat端口
     */
    public String getTomcatPort() throws Exception {
        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"), Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
        String port = objectNames.iterator().next().getKeyProperty("port");
        return port;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
