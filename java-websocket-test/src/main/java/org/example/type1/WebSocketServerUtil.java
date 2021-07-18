package org.example.type1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: guaniu
 * @Description:
 * @Date: Create at 21:41 2021/7/16
 */
@Component
@ServerEndpoint(value="/websocketType1/{userId}")
public class WebSocketServerUtil {

    private static final Logger log= LoggerFactory.getLogger(WebSocketServerUtil.class);

    /**
     * 表示在线人数
     */
    private static final AtomicInteger onlineCount = new AtomicInteger();

    /**
     * 表示存在的WebSocket会话
     */
    private static final ConcurrentHashMap<String, WebSocketServerUtil> webSocketCache = new ConcurrentHashMap<>();

    /**
     * 当前socket会话
     */
    private Session session;

    /**
     * 当前socket连接用户
     */
    private String currentUser;

    /**
     * 登录时间戳
     */
    private long timestamp;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        String msg;
        //当前用户已在线
        if (webSocketCache.containsKey(userId)){
            msg = "您已在线！";
            log.info("当前用户已在线！当前在线人数为{}", onlineCount.get());
        } else {
            this.session = session;
            currentUser = userId;
            timestamp = System.currentTimeMillis();
            //将当前用户socket连接加入缓存管理
            webSocketCache.put(currentUser, this);
            msg = "连接成功！";
            //在线人数加1
            log.info("有新连接加入,userId = {}！当前在线人数为{}", userId, onlineCount.get());
            log.info("session={}|||userId={}" ,session,userId);
        }
        try {
            sendMessage(session, msg);
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (currentUser != null) {
            try {
                Session session = webSocketCache.get(currentUser).session;
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            webSocketCache.remove(currentUser);
            //在线数减1
            log.info("有一连接关闭！当前在线人数为{}", onlineCount.decrementAndGet());
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("当前收到来自客户端的消息:{}", message);
    }


    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    /**
     * 向指定用户推送消息(主要监听井盖状态发生异常)
     * @param message     //消息内容
     * @param messageUserId   //消息接收人
     */
    public static void sendToUser(String message,String messageUserId) {
        try {
            //判断消息接收人是否在线
            if (webSocketCache.containsKey(messageUserId)) {
                WebSocketServerUtil socket = webSocketCache.get(messageUserId);
                webSocketCache.get(messageUserId).sendMessage(socket.session, message);
            } else {
                log.error("当前用户不在线!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 群发在线时间
     * */
    public static void sendToAllOnlineTime() {
        for (Map.Entry<String, WebSocketServerUtil>  entry :  webSocketCache.entrySet()){
            try {
                WebSocketServerUtil socket = entry.getValue();
                long time = (System.currentTimeMillis() - socket.timestamp) / 1000;
                socket.sendMessage(socket.session, "您已在线" + time + "秒！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
