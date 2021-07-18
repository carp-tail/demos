package org.example.type1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: guaniu
 * @Description:
 * @Date: Create at 21:29 2021/7/16
 */
@RestController
@RequestMapping("/type1")
public class Type1Controller {

    private static final Logger logger = LoggerFactory.getLogger(Type1Controller.class);

    @RequestMapping("/sendToUser")
    public void sendToUser(){
        String userId = "zhangsan";
        WebSocketServerUtil.sendToUser("你好啊！" + userId + "!", userId);
    }

    @RequestMapping("/sendToAllOnlineTime")
    public void sendToAllOnlineTime(){
        WebSocketServerUtil.sendToAllOnlineTime();
    }
}
