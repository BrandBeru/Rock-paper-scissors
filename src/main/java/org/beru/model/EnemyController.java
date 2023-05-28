package src.main.java.org.beru.model;

import javafx.application.Platform;
import org.beru.controller.PrincipalController;
import org.beru.controller.ValuesController;
import org.beru.model.Connections.Connection;
import org.beru.model.actions.Action;

public class EnemyController extends Controller{
    public EnemyController(ValuesController valuesController) {
        super(valuesController);
    }
    boolean run = true;
    @Override
    public void run(){
        System.out.println("Running");
        update();
        while(run){
            String ac = getConnection().received();
            setAction(cast(ac));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    String message;
    @Override
    public void update(){
        new Thread(() -> {
            run = true;
            while(true){
                if(PlayerController.instance.getAction()!=null && getAction()!=null) {
                    switch (getAction()) {
                        case ROCK:
                            valuesController.rock.setImage(valuesController.rock_selected);
                            break;
                        case PAPER:
                            valuesController.paper.setImage(valuesController.paper_selected);
                            break;
                        case SCISSOR:
                            valuesController.scissors.setImage(valuesController.scissor_selected);
                            break;
                        default:
                            break;
                    }
                    if(new Validator(PlayerController.instance.getAction(), getAction()).win()) {
                        message = "You win";
                        PlayerController.instance.setPoints();
                    }
                    else{
                        message = "You lose";
                        setPoints();
                    }
                    Platform.runLater(() -> {
                        PrincipalController.instance.playerPoints.setText(""+PlayerController.instance.getPoints());
                        PrincipalController.instance.enemyPoints.setText(""+getPoints());
                        PrincipalController.instance.counter.setText(message);
                    });

                    PrincipalController.instance.start();
                    run = false;
                    Thread.currentThread().stop();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    private Action cast(String act){
        return switch (act) {
            case "rock" -> Action.ROCK;
            case "paper" -> Action.PAPER;
            case "scissor" -> Action.SCISSOR;
            default -> null;
        };
    }
}
