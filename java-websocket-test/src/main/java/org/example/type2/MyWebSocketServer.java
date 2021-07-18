package org.example.type2;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: guaniu
 * @Description: 默认构造函数80端口
 * @Date: Create at 21:54 2021/7/17
 */
public class MyWebSocketServer extends WebSocketServer {

    private static final Logger log= LoggerFactory.getLogger(MyWebSocketServer.class);

    /**
     * 表示在线人数
     */
    private final AtomicInteger onlineCount = new AtomicInteger();

    /**
     * 表示存在的WebSocket会话
     */
    private final ConcurrentHashMap<String, WebSocketPojo> webSocketCache = new ConcurrentHashMap<>();


    public MyWebSocketServer(int port){
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        String userId = handshake.getResourceDescriptor().replace("/","");
        String msg;
        //当前用户已在线
        if (webSocketCache.containsKey(userId)){
            msg = "您已在线！";
            log.info("当前用户已在线！当前在线人数为", onlineCount.get());
        } else {
            WebSocketPojo webSocketPojo = new WebSocketPojo();
            webSocketPojo.setConn(conn);
            webSocketPojo.setTimestamp(System.currentTimeMillis());
            //将当前用户socket连接加入缓存管理
            webSocketCache.put(userId, webSocketPojo);
            msg = "连接成功！";
            //在线人数加1
            log.info("有新连接加入,userId = {}！当前在线人数为", userId, onlineCount.incrementAndGet());
            log.info("userId={}" , userId);
        }
        sendMessage(conn, msg);
        log.info("当前用户已在线！当前在线人数为{}", onlineCount.get());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        String userId = conn.getResourceDescriptor().replace("/","");
        webSocketCache.get(userId).getConn().close();
        conn.close();
        webSocketCache.remove(userId);
        log.info("{}连接关闭！当前在线人数为{}", userId, onlineCount.decrementAndGet());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        log.info("当前收到来自客户端的消息:{}", message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        log.error("发生错误");
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        log.info("type2 Java-WebSocket 已启动！");
    }

    public void  sendMessage(WebSocket conn, String message){
        if (conn != null){
            conn.send(message);
        }
    }

    /**
     * 向指定用户推送消息(主要监听井盖状态发生异常)
     * @param msg     //消息内容
     * @param msgUserId   //消息接收人
     */
    public void sendToUser(String msg, String msgUserId) {
        if (webSocketCache.containsKey(msgUserId)) {
            WebSocket conn = webSocketCache.get(msgUserId).getConn();
            this.sendMessage(conn, msg);
        } else {
            log.error("当前用户不在线!");
        }
    }


    /**
     * 群发在线时间
     * */
    public void sendToAllOnlineTime() {
        for (Map.Entry<String, WebSocketPojo> entry :  webSocketCache.entrySet()){
            WebSocketPojo webSocketPojo = entry.getValue();
            long time = (System.currentTimeMillis() - webSocketPojo.getTimestamp()) / 1000;
            this.sendMessage(webSocketPojo.getConn(), "您已在线" + time + "秒！");
        }
    }

}
