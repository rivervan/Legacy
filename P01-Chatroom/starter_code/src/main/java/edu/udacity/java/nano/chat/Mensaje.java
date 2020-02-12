package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * WebSocket message model
 */
public class Mensaje {


    public enum TipoMensaje{
        CONEXION,
        DESCONEXION,
        MSJTEXTO
    }


    @JSONField(name = "tipoMensaje")
    private TipoMensaje tipoMensaje;

    @JSONField(name = "usuarioNombre")
    private String usuarioNombre;

    @JSONField(name="miMensaje")
    private String miMensaje;

    @JSONField(name="numConectados")
    private int numConectados;


    public Mensaje(){}

    public Mensaje(TipoMensaje tipoMensaje, String usuarioNombre, String miMensaje, int numConectados) {
        this.tipoMensaje = tipoMensaje;
        this.usuarioNombre = usuarioNombre;
        this.miMensaje = miMensaje;
        this.numConectados = numConectados;
    }

    public TipoMensaje getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(TipoMensaje tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getMiMensaje() {
        return miMensaje;
    }

    public void setMiMensaje(String miMensaje) {
        this.miMensaje = miMensaje;
    }

    public int getNumConectados() {
        return numConectados;
    }

    public void setNumConectados(int numConectados) {
        this.numConectados = numConectados;
    }
}
