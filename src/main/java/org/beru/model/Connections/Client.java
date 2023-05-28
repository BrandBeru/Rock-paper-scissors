package src.main.java.org.beru.model.Connections;

import org.beru.controller.PrincipalController;
import org.beru.model.Datas;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class Client extends Connection{
    public Client(String address){
        try {
            socket = new Socket(address, Datas.SERVER_PORT);
            if(!socket.isConnected())
                return;
            System.out.println("Connected");

            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void send(String message){
        try {
            byte[] arr = message.getBytes();

            out.write(arr);
            System.out.println("Send to server: " + message);
        }catch(IOException e){
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
        try {
            System.out.println("Closing server");
            in.close();
            out.close();
            socket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
