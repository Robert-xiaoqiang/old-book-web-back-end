package xiaoqiang.wang.endpoint;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/api/websocket")
@Component
public class MyWebSocket {
    private static int onlineCount = 0;
    private static ConcurrentHashMap<String, Session> guySessionMap = new ConcurrentHashMap<>();
    private final static Logger log = LoggerFactory.getLogger(MyWebSocket.class);

    @OnOpen
    public void onOpen(Session session)
    {
        addOnlineCount();
        log.info("new a connection, current user online: " + getOnlineCount());
    }

    @OnClose
    public void onClose(Session session)
    {
        subOnlineCount();
        // do nothing
        // we have sent information before that
        MyWebSocket.guySessionMap.clear();
        MyWebSocket.onlineCount = 0;
        log.info("close a connection, current user online: " + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session)
    {
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> json = jsonParser.parseMap(message);
        String from = (String)json.get("from");
        String to = (String)json.get("to");
        String content = (String)json.get("content");
        if(from != null && !from.isEmpty()) {
            // update or insert
            MyWebSocket.guySessionMap.put(from, session);
//            if(from != null && !from.isEmpty() && content.equals("exit")) {
//                MyWebSocket.guySessionMap.remove(from);
//            }
            if(to != null && !to.isEmpty() && !content.isEmpty()) {
                if(guySessionMap.containsKey(to)) {
                    sendMessage(MyWebSocket.guySessionMap.get(to), message);
                } else {
                    // not log in
                    // write db
                }
            }
        }

        log.info("from: {}, to: {}, content: {}", from, to, content);
    }


    @OnError
    public void onError(Session session, Throwable error) {
        log.info("websocket server exception");
        error.printStackTrace();
    }


    private static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }


    private static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }


    private static synchronized int getOnlineCount() {
        return MyWebSocket.onlineCount;
    }

    public void sendMessage(Session session, String message)
    {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
