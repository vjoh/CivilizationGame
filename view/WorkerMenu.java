package view;

import controller.GameController;
import model.Convertable;
import model.MapObject;
import model.TerrainTile;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;

/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class WorkerMenu extends AbstractMenu {
    /**
    * There should be a convert and move button
    * as well as the functions associated with those
    * buttons
    */
    public WorkerMenu() {
        Button moveBTN = new Button("Move");
        Button convertBTN = new Button("Convert");

        moveBTN.setOnAction(event -> {
                GameController.moving();
            });

        convertBTN.setOnAction(event -> {
                TerrainTileFX tTileFX = GameController.getLastClicked();
                TerrainTile tTile = tTileFX.getTile();
                MapObject tileOccupant = tTile.getOccupant();

                Convertable convertableObject = (Convertable) tileOccupant;
                if (convertableObject.canConvert(tTile.getType())) {
                    convertableObject.convert();

                    tTileFX.updateTileView();
                    GameController.updateResourcesBar();
                } else {
                    Alert noConvertAlert =
                        new Alert(Alert.AlertType.WARNING);
                    noConvertAlert.setTitle("Warning");
                    noConvertAlert.setHeaderText("Non-Convertable Tile");
                    noConvertAlert.setContentText("Tile is not converable!");
                    noConvertAlert.showAndWait();
                }
            });
        this.addMenuItem(moveBTN);
        this.addMenuItem(convertBTN);
    }
}
