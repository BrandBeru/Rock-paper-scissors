package src.main.java.org.beru.model;

import org.beru.model.actions.Action;

public class Validator {
    private Action playerAction;
    private Action enemyAction;
    public Validator(Action playerAction, Action eneyAction){
        this.playerAction = playerAction;
        this.enemyAction = eneyAction;
    }
    public boolean win(){
        if(playerAction.equals(Action.PAPER) && enemyAction.equals(Action.ROCK))
            return true;
        else if(playerAction.equals(Action.ROCK) && enemyAction.equals(Action.SCISSOR))
            return true;
        else if(playerAction.equals(Action.SCISSOR) && enemyAction.equals(Action.PAPER))
            return true;
        else if(playerAction.equals(enemyAction))
            return true;
        else
            return false;
    }
}
