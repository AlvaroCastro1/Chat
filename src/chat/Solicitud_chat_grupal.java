package chat;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alvaro
 */
public class Solicitud_chat_grupal implements Serializable{

    private String host_server;
    private String Nombre_grupo;
    private Map<String, String> Clientes;
    

    public Solicitud_chat_grupal(String host_server, String Nombre_grupo, Map<String, String> Clientes) {
        this.host_server = host_server;
        this.Nombre_grupo = Nombre_grupo;
        this.Clientes = Clientes;
    }

    @Override
    public String toString() {
        return "Solicitud_chat_grupal " + Nombre_grupo + ", Clientes=" + Clientes;
    }

    public String getHost_server() {
        return host_server;
    }

    public void setHost_server(String host_server) {
        this.host_server = host_server;
    }

    public String getNombre_grupo() {
        return Nombre_grupo;
    }

    public void setNombre_grupo(String Nombre_grupo) {
        this.Nombre_grupo = Nombre_grupo;
    }

    public Map<String, String> getClientes() {
        return Clientes;
    }

    public void setClientes(Map<String, String> Clientes) {
        this.Clientes = Clientes;
    }
    
    
    

}
