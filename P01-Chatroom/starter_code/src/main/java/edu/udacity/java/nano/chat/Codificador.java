package edu.udacity.java.nano.chat;


import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import com.alibaba.fastjson.JSON;




public class Codificador implements Encoder.Text<Mensaje> {

    @Override
    public String encode(Mensaje mensaje) throws EncodeException {
        return JSON.toJSONString(mensaje);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }



}
