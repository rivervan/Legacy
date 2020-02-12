package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint(value="/chat/{nombreUsuario}", encoders=Codificador.class)
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */

    private final String __MSJ_CONEXION__    = "ENTRÓ AL CHAT";
    private final String __MSJ_CHAT__        = "MENSAJE RECIBIDO";
    private final String __MSJ_DESCONEXION__ = "SALIÓ DEL CHAT";

    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();


    private static void sendMessageToAll(Mensaje mensaje) {



        for (String llave: onlineSessions.keySet()){
            System.out.println(llave);
            try{
                onlineSessions.get(llave).getBasicRemote().sendObject(mensaje);
            }catch(EncodeException | IOException ex){
                ex.printStackTrace();
            }

        }
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */

    @OnOpen
    public void onOpen(Session session, @PathParam("nombreUsuario") String nombreUsuario) {
        System.out.println(__MSJ_CONEXION__ + nombreUsuario);
        onlineSessions.put(nombreUsuario,session);

        Mensaje msj = new Mensaje(Mensaje.TipoMensaje.CONEXION,nombreUsuario,__MSJ_CONEXION__,onlineSessions.size());
        sendMessageToAll(msj);
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //Asegurar desde el cliente que el mensaje Json este bien armado
        System.out.println(__MSJ_CHAT__ + jsonStr);
        Mensaje msj = JSON.parseObject(jsonStr, Mensaje.class);
        sendMessageToAll(msj);
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session, @PathParam("nombreUsuario") String nombreUsuario) {
        onlineSessions.remove(nombreUsuario,session);
        System.out.println(__MSJ_DESCONEXION__ + nombreUsuario);

        Mensaje msj = new Mensaje(Mensaje.TipoMensaje.DESCONEXION,nombreUsuario,__MSJ_DESCONEXION__,onlineSessions.size());
        sendMessageToAll(msj);

    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
