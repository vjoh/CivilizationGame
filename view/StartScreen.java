package view;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import java.net.URI;
import java.io.File;



/**
 * Created by Tian-Yo Yang on 11/11/2016.
 * This class represents the Start Screen for the Civ applicatios. This is the
 * layout that should be displayed upon running the Civ application.
 *
 * This class should have and display
 * 1. a background
 * 2. a list of Civilizations
 * 3. a Start button
 */
public class StartScreen extends StackPane {
    private ListView listcivs;
    private Button stbtn;

    /**
    * constuctor of the start screen. Should set the background
    * image and display a list of civilizations and a start button
    */
    public StartScreen() {
        Group root = new Group();
        VBox civvbox = new VBox();
        Text startText = new Text("Select your Civilization to Start: ");
        startText.setFill(Color.RED);

        civvbox.setAlignment(Pos.CENTER);
        civvbox.setStyle("-fx-padding: 300 0 0 0;");


        File backGroundFile = new
            File("src/main/java/view/civ_background.png");
        URI uri = backGroundFile.toURI();

        this.setStyle("-fx-background-image: url('"
            + uri.toString() + "'); "
            + "-fx-background-position: center center; "
            + "-fx-background-repeat: stretch;");


        stbtn = new Button("START");

        ObservableList<CivEnum> civs =
            FXCollections.observableArrayList();

        for (CivEnum ce: CivEnum.values()) {
            civs.add(ce);
        }

        listcivs = new ListView<CivEnum>(civs);
        listcivs.setMaxHeight(100);
        listcivs.setMaxWidth(200);
        civvbox.getChildren().addAll(startText, listcivs, stbtn);

        this.getChildren().addAll(civvbox);


    }
    /**
    * gets the start button
    * @return the start button
    */
    public Button getStartButton() {
        return stbtn;
    }
    /**
    * return a ListView of CivEnums representing the list of
    * available civilizations to choose from
    * @return listview of CivEnum
    */
    public ListView<CivEnum> getCivList() {
        return listcivs;

    }

    public ListView getListView() {
        return listcivs;
    }
}
