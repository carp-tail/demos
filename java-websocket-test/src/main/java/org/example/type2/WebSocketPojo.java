package org.example.type2;

import org.java_websocket.WebSocket;

/**
 * @Author: guaniu
 * @Description:
 * @Date: Create at 21:50 2021/7/17
 */
public class WebSocketPojo {

    private WebSocket conn;

    private long timestamp;

    public WebSocket getConn() {
        return conn;
    }

    public void setConn(WebSocket conn) {
        this.conn = conn;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
