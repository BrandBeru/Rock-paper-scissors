package src.main.java.org.beru.view;

import javafx.scene.control.*;
import org.beru.App;

public class Messages {

    public static String text(String title) {
        Dialog<String> pane = new Dialog<String>();
        pane.setTitle(title);

        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        pane.getDialogPane().getButtonTypes().add(type);

        TextField txt = new TextField();
        txt.setPromptText("Text:");

        pane.getDialogPane().setContent(txt);

        pane.getDialogPane().setPrefWidth(350);
        pane.getDialogPane().setPrefHeight(100);
        pane.showAndWait();

        return txt.getText();
    }
    public static Alert alert(String title, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();

        return alert;
    }
}
