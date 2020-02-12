package edu.udacity.java.nano;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.Range.is;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = WebSocketChatApplication.class, secure = false)
public class WebSocketChatApplicationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper mapper;


    @Test
    public void login() throws Exception {


        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login"));

    }

    @Test
    public void joinUser() throws Exception {

        String nombreUsuario = "Refugio";

        this.mockMvc.perform(MockMvcRequestBuilders.get("/index?nombreUsuario=" + nombreUsuario))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("chat"));



    }

   @Test
    public void mandarMensaje() throws Exception{

        String nombreUsuario = "Rife";
        //String mensajeJson = "{tipoMensaje: \"MSJTEXTO\",  usuarioNombre: \"IVAN\", miMensaje: \"Soy Yo\", numConectados:0}";

          /*
       this.mockMvc.perform(MockMvcRequestBuilders.get("/index?nombreUsuario=Rife&msg=GGGGGG"))
                   .andDo(print()) .andExpect(status().isOk())
                   .andExpect(content().contentType("text/html;charset=UTF-8"))
                   .andExpect(content().string(containsString("username")))
                   .andExpect(content().string(containsString("msg")));

*/



                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))









    }




}
