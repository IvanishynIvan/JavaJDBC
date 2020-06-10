package com.company;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Practical work №3");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("res/icon.png")));
        primaryStage.setScene(new Scene(root, 1200, 800));
        Controller controller = loader.getController();
        SQLCarService Scanner = new SQLCarService();
        controller.inject(Scanner);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
