package src.main.java.org.beru.model;

import javafx.application.Platform;
import org.beru.controller.PrincipalController;
import org.beru.controller.ValuesController;

public class Counter extends Thread{
    public static final int INIT_TYPE = 1;
    public static final int WIN_TYPE = 2;
    public static final int WAITING_TYPE = -1;

    private int count;
    private Controller controller;

    public Counter(int type){
        this.type = type;
    }
    public Counter(int type, Controller controller){
        this.type = type;
        this.controller = controller;
    }
    public int type;
    public void run(){
        try {
            PrincipalController.instance.playerController.lockButtons(true);
            if (type == INIT_TYPE) {
                count = 3;
            }else if(type == WIN_TYPE){
                count = 5;
            }else if(type == WAITING_TYPE){
                count = 0;
            }
            timer();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    private void timer() throws InterruptedException {
        if(count!=0){
            Thread.sleep(1500);
            while(count>0){
                Platform.runLater(() -> {
                    PrincipalController.instance.counter.setText(""+count);
                });
                Thread.sleep(1000);
                count--;
            }
            PrincipalController.instance.playerController.lockButtons(false);
            new Thread(controller).start();
        }else{
            PrincipalController.instance.playerController.lockButtons(false);
            while(true){
                Platform.runLater(() -> {
                    PrincipalController.instance.counter.setText(""+count);
                });
                Thread.sleep(1000);
                count++;
            }
        }
    }
}
