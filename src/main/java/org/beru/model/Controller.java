package src.main.java.org.beru.model;

import javafx.application.Platform;
import org.beru.controller.PrincipalController;
import org.beru.controller.ValuesController;
import org.beru.model.Connections.Connection;
import org.beru.model.actions.Action;
import org.beru.model.actions.Actions;

public class Controller implements Actions, Runnable {
    private String name;
    private Action action;
    private int points;
    private Connection connection;
    public ValuesController valuesController;

    public Controller(ValuesController valuesController){
        this.valuesController = valuesController;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints() {
        this.points++;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void rock() {
        return;
    }

    @Override
    public void paper() {
        return;
    }

    @Override
    public void scissor() {
        return;
    }

    @Override
    public void run() {return;}
    public void update(){
        return;
    }
}
