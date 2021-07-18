package org.example.config;

import org.example.type2.MyWebSocketServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author: guaniu
 * @Description:
 * @Date: Create at 22:07 2021/7/16
 */
@Configuration
public class WebSocketConfig {
    /** type1
     * 使用SpringBoot的内置容器需要注册这个bean
     * 使用SpringMVC模式不需要这个
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean(initMethod = "start")
    public MyWebSocketServer myWebSocketServer(){
        int port = 8087;
        return new MyWebSocketServer(port);
    }
}
