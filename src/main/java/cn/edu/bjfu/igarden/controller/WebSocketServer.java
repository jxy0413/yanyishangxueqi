package cn.edu.bjfu.igarden.controller;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import cn.edu.bjfu.igarden.dao.ExpertsMapper;
import cn.edu.bjfu.igarden.entity.chat;
import cn.edu.bjfu.igarden.service.ExpertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@ServerEndpoint("/websocket/{userid}")
@Component
public class WebSocketServer {

    private static ApplicationContext applicationContext;
    private ExpertsService expertsService;
    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketServer.applicationContext = applicationContext;
    }
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
//    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    private static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //接收userid
    private String userid ="";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("userid") String userid) throws IOException {
        this.session = session;
        this.userid=userid;
        webSocketSet.put(userid,this);     //加入set中
        addOnlineCount();           //在线数加1
        sendMessage("连接成功");
        System.out.println("有新窗口开始监听:"+userid+",当前在线人数为" + getOnlineCount());


    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(userid,this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void OnMessage(String message,Session session) throws IOException {
        expertsService=applicationContext.getBean(ExpertsService.class);
        String sendMessage = message.split("[|]")[0];
        String sendUserId = message.split("[|]")[1];
        System.out.println(sendUserId);
        System.out.println(userid+"推送消息到窗口"+sendUserId+"，推送内容:"+message);
        try {
            sendtoUser(sendMessage,sendUserId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public void sendtoUser(String message,String sendUserId) throws IOException {
        if (webSocketSet.get(sendUserId) != null) {
            if(!userid.equals(sendUserId)){
                System.out.println(!userid.equals(sendUserId));
                webSocketSet.get(sendUserId).sendMessage(userid+"|"+ message);
                chat chat=new chat();
                int useridInt=Integer.parseInt(userid);
                int toUserid=Integer.parseInt(sendUserId);
                chat.setUserid(useridInt);
                chat.setTouserid(toUserid);
                chat.setReadtime(1);
                chat.setMessage(message);
                expertsService.addChat(chat);
            }

//            else
//                webSocketSet.get(sendUserId).sendMessage(message);
        } else {
            //如果用户不在线则返回不在线信息给自己
            System.out.println("没在线");
           int useridInt=Integer.parseInt(userid);
           int toUserid=Integer.parseInt(sendUserId);
            chat chat=new chat();
            chat.setUserid(useridInt);
            chat.setTouserid(toUserid);
            chat.setReadtime(0);
            chat.setMessage(message);
            expertsService.addChat(chat);
        }
    }
    /**
     * 群发自定义消息
     * */


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

}
