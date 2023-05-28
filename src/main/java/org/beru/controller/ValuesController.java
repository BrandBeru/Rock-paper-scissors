package src.main.java.org.beru.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.beru.model.*;
import org.beru.model.Connections.Connection;
import org.beru.view.Messages;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ValuesController implements Initializable {

    public Label name;
    public ImageView rock;
    public ImageView paper;
    public ImageView scissors;
    private boolean isPlayer;

    public Controller controller;
    public static ValuesController instance;

    public final Image rock_selected = new Image(new File(getClass().getResource("/Images/rock_selected.png").toString()).getPath());
    public final Image paper_selected = new Image(new File(getClass().getResource("/Images/paper_selected.png").toString()).getPath());
    public final Image scissor_selected = new Image(new File(getClass().getResource("/Images/scissors_selected.png").toString()).getPath());

    public final Image rock_normal = new Image(new File(getClass().getResource("/Images/rock.png").toString()).getPath());
    public final Image paper_normal = new Image(new File(getClass().getResource("/Images/paper.png").toString()).getPath());
    public final Image scissor_normal = new Image(new File(getClass().getResource("/Images/scissors.png").toString()).getPath());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        init();
        name.setText(controller.getName());
    }
    private void init(){
        isPlayer = Datas.isPlayer;
        lockButtons(true);
        if(isPlayer)
            controller = new PlayerController(this);
        else
            controller = new EnemyController(this);
        if(controller instanceof EnemyController){
            lockButtons(true);
        }
    }
    public void lockButtons(boolean lock){
        rock.setDisable(lock);
        paper.setDisable(lock);
        scissors.setDisable(lock);
    }
    public void starting(Connection con){
        controller.setConnection(con);
        new Counter(Counter.INIT_TYPE, controller).start();
    }

    @FXML
    public void rockSelected(MouseEvent event){
        rock.setImage(rock_selected);
        controller.rock();
        lockButtons(true);
    }
    @FXML
    public void paperSelected(MouseEvent event){
        paper.setImage(paper_selected);
        controller.paper();
        lockButtons(true);
    }
    @FXML
    public void scissorSelected(MouseEvent event) {
        scissors.setImage(scissor_selected);
        controller.scissor();
        lockButtons(true);
    }
    public void restart(){
        rock.setImage(rock_normal);
        paper.setImage(paper_normal);
        scissors.setImage(scissor_normal);
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }
}
