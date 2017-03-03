package runner;

import controller.GameController;
import view.CivEnum;
import view.GameScreen;
import view.StartScreen;
import model.Egypt;
import model.QinDynasty;
import model.RomanEmpire;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.Optional;
import javafx.scene.media.AudioClip;

//To compile: javac -cp src/main/java src/main/java/runner/*.java src/main/java/model/*.java src/main/java/view/*.java src/main/java/controller/*.java

//To run: java -cp src/main/java runner.CivilizationGame

/**
 * Created by Tian-Yo Yang on 11/11/2016.
 */
public class CivilizationGame extends Application {
    private static String firstSettlement;
    /**
     * this method is called upon running/launching the
     * application
     * this method should display a scene on the stage
     */
    @Override
    public void start(Stage primaryStage) {
        StartScreen stScreen = new StartScreen();
        Scene primaryScene = new Scene(stScreen, 1000, 800);
        primaryStage.setTitle("Queen Victoria's Civilization Game");
        primaryStage.setScene(primaryScene);
        primaryStage.sizeToScene();
        primaryStage.show();

        stScreen.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (stScreen.getCivList().getSelectionModel().getSelectedItem()
                    != null) {

                    CivEnum civE =
                        stScreen.getCivList().getSelectionModel().
                        getSelectedItem();

                    switch (civE) {
                        case ANCIENT_EGYPT:
                        GameController.setCivilization(new Egypt());
                        break;
                        case QIN_DYNASTY:
                        GameController.setCivilization(new QinDynasty());
                        break;
                        case ROMAN_EMPIRE:
                        GameController.setCivilization(new RomanEmpire());
                        break;

                        default:
                        return;
                    }
                    TextInputDialog tidbox = new TextInputDialog();
                    tidbox.setTitle("A New Settlement");
                    tidbox.setHeaderText("You have built a settlement!");
                    tidbox.setContentText("Enter the Name of your first town:");
                    Optional<String> result = tidbox.showAndWait();
                    if (result.isPresent() && !(result.get().equals(""))) {
                        firstSettlement = result.get();
                        primaryStage.setScene(startGame());
                        AudioClip plonkSound = new AudioClip(
                            "File:./src/main/java/view/startChime.wav");
                        plonkSound.play();
                    } else {
                        Alert noNameAlert = new Alert(Alert.AlertType.WARNING);
                        noNameAlert.setTitle("Warning");
                        noNameAlert.setHeaderText("No Name Present Warning");
                        noNameAlert.setContentText("New Settlement needs "
                            + "a name!");
                        noNameAlert.showAndWait();
                    }

                }
            }
        });
    }

    /**
     * This is the main method that launches the javafx
     * application.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
    * This method is responsible for setting the scene to
    * the corresponding
    * layout.
    * and returning the scene.
    * @return Scene
    */

    public Scene startGame() {
        return new Scene(new GameScreen());
    }

    public static String getFirstSettlement() {
        return firstSettlement;
    }


}
