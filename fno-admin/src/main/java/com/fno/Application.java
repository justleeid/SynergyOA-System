package com.fno;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;

/**
 * 启动程序
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        org.flowable.spring.boot.FlowableSecurityAutoConfiguration.class})
@ComponentScan(value = {"com.fno.*"})
public class Application implements CommandLineRunner {
    private final static Logger LOGGER = LoggerFactory.getLogger(Application.class);
    @Value("${server.port}")
    private int serverPort;
    private static long startTime;

    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("--------------------启动成功------------------------");
        LOGGER.info("--------------------访问端口{}---------------------", serverPort);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        long endTime = System.currentTimeMillis();
        LOGGER.info("项目启动花费时间：{}秒", (endTime - startTime) / 1000);
    }
}
