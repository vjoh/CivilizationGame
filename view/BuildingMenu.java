package view;

import controller.GameController;
import model.Building;
import model.Settlement;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;


/**
 * This class should represents the bulding menu
 */
public class BuildingMenu extends AbstractMenu {
    /**
    * there should be an invest and demolish button for this menu
    * as well as functions associated with the buttons
    */
    public BuildingMenu() {
        Button investBTN = new Button("Invest");
        Button demolishBTN = new Button("Demolish");

        investBTN.setOnAction(event -> {

                TerrainTileFX tTileFX = GameController.getLastClicked();

                if (tTileFX.getTile().getOccupant() instanceof Building) {

                    if (GameController.getCivilization()
                        .getTreasury().getCoins() >= 25) {
                        GameController.getCivilization().getTreasury()
                            .spend(25);
                        Building building =
                            (Building) tTileFX.getTile().getOccupant();
                        building.invest();
                        tTileFX.updateTileView();
                        GameController.updateResourcesBar();
                    } else {
                        Alert noNameAlert =
                            new Alert(Alert.AlertType.WARNING);
                        noNameAlert.setTitle("Warning");
                        noNameAlert.setHeaderText("Insufficient Funds");
                        noNameAlert.setContentText("This investment isn't"
                            + " allowed!");
                        noNameAlert.showAndWait();
                        return;
                    }
                }
            });

        demolishBTN.setOnAction(event -> {
                TerrainTileFX tTileFX = GameController.getLastClicked();
                if (tTileFX.getTile().getOccupant() instanceof Settlement) {
                    if (GameController.getCivilization().getNumSettlements()
                        > 1) {
                        tTileFX.getTile().setOccupant(null);
                        tTileFX.updateTileView();
                        GameController.updateResourcesBar();
                    }
                } else {
                    Alert noNameAlert = new Alert(Alert.AlertType.WARNING);
                    noNameAlert.setTitle("Warning");
                    noNameAlert.setHeaderText("Demolition Restricted");
                    noNameAlert.setContentText("Your Civilization can't "
                        + "demolish now!");
                    noNameAlert.showAndWait();
                    return;
                }

            });

    }
}
