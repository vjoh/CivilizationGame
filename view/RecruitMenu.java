package view;

import controller.GameController;
import model.Civilization;
import model.Unit;
import model.TerrainTile;
import model.Egypt;
import model.QinDynasty;
import model.RomanEmpire;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class RecruitMenu extends AbstractMenu {

    private ListView listUnits;
    private static final String MELEE_UNIT = "Melee Unit";
    private static final String RANGED_UNIT = "Ranged Unit";
    private static final String HYBRID_UNIT = "Hybrid Unit";
    private static final String SIEGE_UNIT = "Siege Unit";
    private static final String SETTLER_UNIT = "Settlers";
    private static final String FARMER_UNIT = "Farmers";
    private static final String COALMINER_UNIT = "Coal Miners";
    private static final String ANGLER_UNIT = "Anglers";
    private static final String MASTER_BUILDER_UNIT = "Master Builders";
    private static final String RANGE_UNIT_EGYPT = "War Chariot Unit";
    private static final String SIEGE_UNIT_QD = "Black Powder Unit";
    private static final String MELEE_UNIT_RE = "Legion Unit";


    /**
    * recuit menu should have a list of worker/units
    * to choose from. There should also be a select button
    * and the function of the button should be implemented
    *here
    */
    public RecruitMenu() {

        Button recruitBTN = new Button("Recruit");
        ObservableList<String> stringUnits =
            FXCollections.observableArrayList();

        stringUnits.addAll(HYBRID_UNIT, SETTLER_UNIT, FARMER_UNIT,
            COALMINER_UNIT, ANGLER_UNIT, MASTER_BUILDER_UNIT);

        if (GameController.getCivilization() instanceof Egypt) {
            stringUnits.add(RANGE_UNIT_EGYPT);
        } else {
            stringUnits.add(RANGED_UNIT);
        }

        if (GameController.getCivilization() instanceof QinDynasty) {
            stringUnits.add(SIEGE_UNIT_QD);
        } else {
            stringUnits.add(SIEGE_UNIT);
        }

        if (GameController.getCivilization() instanceof RomanEmpire) {
            stringUnits.add(MELEE_UNIT_RE);
        } else {
            stringUnits.add(MELEE_UNIT);
        }

        listUnits = new ListView<String>(stringUnits);

        Civilization civ = GameController.getCivilization();
        recruitBTN.setOnAction(event -> {
                Unit mapObject = null;

                TerrainTileFX tTileFX = GameController.getLastClicked();
                TerrainTile tTile = tTileFX.getTile();

                switch (listUnits.getSelectionModel().getSelectedItem()
                    .toString()) {
                case HYBRID_UNIT:
                    mapObject = civ.getHybridUnit();
                    break;

                case SETTLER_UNIT:
                    TextInputDialog tidbox = new TextInputDialog();
                    tidbox.setTitle("A New Settlement");
                    tidbox.setHeaderText("You have built a settlement!");
                    tidbox.setContentText("Enter the Name of your next town:");
                    Optional<String> result = tidbox.showAndWait();
                    if (result.isPresent() && !(result.get().equals(""))) {
                        mapObject = civ.getSettlerUnit(result.get());
                        break;
                    } else {
                        Alert noNameAlert = new Alert(Alert.AlertType.WARNING);
                        noNameAlert.setTitle("Warning");
                        noNameAlert.setHeaderText("No Name Present Warning");
                        noNameAlert.setContentText("Your new Settlement must"
                            + "have a name!");
                        noNameAlert.showAndWait();
                        return;
                    }

                case FARMER_UNIT:
                    mapObject = civ.getFarmerUnit();
                    break;

                case COALMINER_UNIT:
                    mapObject = civ.getCoalMinerUnit();
                    break;

                case ANGLER_UNIT:
                    mapObject = civ.getAnglerUnit();
                    break;

                case MASTER_BUILDER_UNIT:
                    mapObject = civ.getMasterBuilderUnit();
                    break;

                case SIEGE_UNIT:
                case SIEGE_UNIT_QD:
                    mapObject = civ.getSiegeUnit();
                    break;

                case MELEE_UNIT:
                case MELEE_UNIT_RE:
                    mapObject = civ.getMeleeUnit();
                    break;

                case RANGED_UNIT:
                case RANGE_UNIT_EGYPT:
                    mapObject = civ.getRangedUnit();
                    break;
                default:
                    return;
                }

                if (mapObject.isAffordable()) {
                    tTile.setOccupant(mapObject);
                    mapObject.applyInitialCosts();
                    tTileFX.updateTileView();
                    GameController.updateResourcesBar();

                } else {
                    Alert noNameAlert = new Alert(Alert.AlertType.WARNING);
                    noNameAlert.setTitle("Warning");
                    noNameAlert.setHeaderText("Insufficient Funds");
                    noNameAlert.setContentText("This recruitment "
                        + "isn't allowed!");
                    noNameAlert.showAndWait();
                    return;
                }

            });

        this.addMenuItem(recruitBTN);
        this.addMenuItem(listUnits);

    }
}
