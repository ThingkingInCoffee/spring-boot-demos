package com.hzy.websocket.config;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * 真正处理消息的节点
 */
@Component
@ServerEndpoint(value = "/websocket/{user}") // 外部暴露地址 访问 ws://localhost:9099/websocket/userA
public class WebSocketEndpoint {

    // 与客户端的连接会话，通过session向客户端发送数据
    private Session session;

    @OnOpen
    public void onOpen(Session session, @PathParam("user") String user) {
        // 存储会话
        SessionPool.sessions.put(user, session);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        SessionPool.close(session.getId());
    }

    /**
     * 收到客户端消息后调用此方法处理
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        SessionPool.sendMessage("all:" + message);
        SessionPool.sendMessage("single:" + message, session.getId());
    }

}
