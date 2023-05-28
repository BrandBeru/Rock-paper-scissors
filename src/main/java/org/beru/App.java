package src.main.java.org.beru;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static Stage stage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        App.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/view/Principal.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setTitle("Rock-paper-Scissors");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
