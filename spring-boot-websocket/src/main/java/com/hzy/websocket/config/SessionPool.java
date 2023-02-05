package com.hzy.websocket.config;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionPool {

    public static Map<String, Session> sessions = new ConcurrentHashMap<>();

    /**
     * 通过sessionId关闭会话
     *
     * @param sessionId
     * @throws IOException
     */
    public static void close(String sessionId) throws IOException {
        for (Session session : sessions.values()) {
            if (session.getId().equals(sessionId)) {
                session.close();
            }
        }
    }

    /**
     * 向指定用户发送消息
     *
     * @param sessionId
     * @param message
     */
    public static void sendMessage(String message, String sessionId) {
        sessions.get(sessionId).getAsyncRemote().sendText(message);
    }

    /**
     * 群发消息
     *
     * @param message
     */
    public static void sendMessage(String message) {
        for (Session session : sessions.values()) {
            session.getAsyncRemote().sendText(message);
        }
    }

}
