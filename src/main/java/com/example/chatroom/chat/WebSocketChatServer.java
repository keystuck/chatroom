package com.example.chatroom.chat;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/chat/{username}")
public class WebSocketChatServer {
    Session session;
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();
    private static Map<String, String> chatUsers = new HashMap<>();

    private static void sendMessageToAll(Message message){
        String jsonOutput = JSON.toJSONString(message);
        for (Map.Entry<String, Session> entry : onlineSessions.entrySet()){
            try {
                entry.getValue().getBasicRemote().sendText(jsonOutput);
            } catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username){
        this.session = session;
        onlineSessions.put(session.getId(), session);
        chatUsers.put(session.getId(), username);

        Message message = new Message(username, "joined the chat");
        message.setOnlineCount(chatUsers.size());
        sendMessageToAll(message);
    }

    @OnMessage
    public void onMessage(Session session, String jsonStr){
        Message message = JSON.parseObject(jsonStr, Message.class);
        message.setOnlineCount(chatUsers.size());
        message.setUsername(chatUsers.get(session.getId()));
        sendMessageToAll(message);
    }

    @OnClose
    public void onClose(Session session){
        Message message = new Message(chatUsers.get(session.getId()), "left the chat");
        message.setOnlineCount(chatUsers.size() - 1);
        sendMessageToAll(message);
        onlineSessions.remove(this);
        chatUsers.remove(session.getId());
    }

    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }
}
