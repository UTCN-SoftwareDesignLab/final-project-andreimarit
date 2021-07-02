package com.lab4.demo.websocket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;


@ServerEndpoint("/socket/test")
@Component
public class WsServerEndpoint {
    static ArrayList<Session> sessions = new ArrayList<Session>();

    public static void sendAlert(String alert) {
        sessions.forEach((cs)->{
            try {
                cs.getBasicRemote().sendText(alert);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("ALERT: " + alert);
    }

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        System.out.println("Conexiune reusita | " + sessions.size());
    }


    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("Conexiune inchisa | " + sessions.size());
    }




}
