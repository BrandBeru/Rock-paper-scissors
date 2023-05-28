package src.main.java.org.beru.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.beru.model.Connections.Client;
import org.beru.model.Connections.Connection;
import org.beru.model.Connections.Server;
import org.beru.model.Counter;
import org.beru.model.Datas;
import org.beru.model.PlayerController;
import org.beru.view.Messages;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    public VBox playerContent;
    public VBox enemyContent;
    public Label counter;
    public ImageView fight;

    public ValuesController playerController;
    public ValuesController enemyController;
    public Label playerPoints;
    public Label enemyPoints;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        instance = this;
    }
    public void init(){
        try {
            FXMLLoader player = new FXMLLoader(getClass().getResource("/view/Values.fxml"));
            //Player init
            Datas.isPlayer = true;
            playerContent.getChildren().add(player.load());
            playerController = player.getController();
            //Enemy init
            Datas.isPlayer = false;
            FXMLLoader enemy = new FXMLLoader(getClass().getResource("/view/Values.fxml"));
            enemyContent.getChildren().add(enemy.load());
            enemyController = enemy.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static PrincipalController instance;

    public void fight(MouseEvent event) {
        new Thread(() -> {
            Counter c = new Counter(Counter.WAITING_TYPE);
            c.start();
            con = new Server();
            con.send(playerController.name.getText());
            Platform.runLater(() -> {
                enemyController.name.setText(con.received());
            });
            c.stop();
            start();
        }).start();
    }
    public void start(){
        restart();
        playerController.starting(con);
        enemyController.starting(con);
    }
    public Connection con;

    public void restart(){
        playerController.restart();
        enemyController.restart();
    }
    public void connect(ActionEvent actionEvent) {
        con = new Client("127.0.0.1");
        con.send(playerController.name.getText());
        enemyController.name.setText(con.received());
        start();
    }
}
