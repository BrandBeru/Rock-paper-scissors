package src.main.java.org.beru.model;

import org.beru.controller.ValuesController;
import org.beru.model.actions.Action;
import org.beru.model.actions.Actions;
import org.beru.view.Messages;

public class PlayerController extends Controller implements Actions{
    public static PlayerController instance;
    public PlayerController(ValuesController valuesController) {
        super(valuesController);
        setName(Messages.text("Player name"));
        instance = this;
    }

    @Override
    public void rock() {
        setAction(Action.ROCK);
        getConnection().send(getAction().name().toLowerCase());
    }

    @Override
    public void paper() {
        setAction(Action.PAPER);
        getConnection().send(getAction().name().toLowerCase());
    }

    @Override
    public void scissor() {
        setAction(Action.SCISSOR);
        getConnection().send(getAction().name().toLowerCase());
    }
    public Counter counter;
    @Override
    public void run(){
        counter = new Counter(Counter.WAITING_TYPE);
        counter.start();
    }
}
