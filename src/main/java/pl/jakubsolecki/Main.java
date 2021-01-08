package pl.jakubsolecki;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.jakubsolecki.service.EntityManager;
import pl.jakubsolecki.service.MovementManager;
import pl.jakubsolecki.service.SimulationManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        var entityManager = new EntityManager();
        var movementManager = new MovementManager();
        var simulationManager = new SimulationManager(entityManager, movementManager);

        Parent root = FXMLLoader.load(getClass().getResource("/view/setupView.fxml"));
        primaryStage.setTitle("Evolutionary Generator Remastered");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
