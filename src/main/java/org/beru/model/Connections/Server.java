package src.main.java.org.beru.model.Connections;

import org.beru.controller.PrincipalController;
import org.beru.model.Datas;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server extends Connection{

    public Server(){
        try {
            server = new ServerSocket(Datas.SERVER_PORT);
            System.out.println("Server started");

            System.out.println("Waiting for a client");

            socket = server.accept();

            System.out.println("Client connected");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void send(String message){
        try {
            byte[] arr = message.getBytes();

            out.write(arr);
            System.out.println("Send to client: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String received(){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            baos.write(buffer, 0, in.read(buffer));

            byte[] result = baos.toByteArray();


            return new String(result);
        }catch(IOException e){
            e.printStackTrace();
        }
        return "NaN";
    }
    @Override
    public void close(){
        try{
            System.out.println("Closing connection");

            socket.close();
            in.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
