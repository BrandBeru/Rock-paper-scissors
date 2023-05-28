package src.main.java.org.beru.model.Connections;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
    public Socket socket = null;
    public ServerSocket server = null;
    public InputStream in = null;
    public OutputStream out = null;

    public void send(String message){

    }
    public String received(){
        return "";
    }
    public void close(){

    }
}
