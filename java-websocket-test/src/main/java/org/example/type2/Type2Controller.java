package org.example.type2;

import org.example.type1.WebSocketServerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: guaniu
 * @Description:
 * @Date: Create at 21:29 2021/7/16
 */
@RestController
@RequestMapping("/type2")
public class Type2Controller {

    private static final Logger logger = LoggerFactory.getLogger(Type2Controller.class);

    @Autowired
    MyWebSocketServer myWebSocketServer;

    @RequestMapping("/sendToUser")
    public void sendToUser(){
        String userId = "wangwu";
        myWebSocketServer.sendToUser("你好啊！" + userId + "!", userId);
    }

    @RequestMapping("/sendToAllOnlineTime")
    public void sendToAllOnlineTime(){
        myWebSocketServer.sendToAllOnlineTime();
    }
}
