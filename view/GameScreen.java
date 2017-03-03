package view;

import controller.GameController;
import model.Bandit;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Map;

/**
 * This class represents the GameScreen class
 */
public class GameScreen extends BorderPane {

    private GridFX civgrid;
    private static ResourcesMenu rMenu;
    private static VBox civvbox;
    private static StatusMenu stMenu;
    private static MilitaryMenu mMenu;
    private static WorkerMenu wMenu;
    private static BuildingMenu bMenu;
    private static RecruitMenu recruitMenu;

    /**
     * Creates a new view into the game. this layout should include
     * the rescource bar, grid map, and action menus
     *
     */
    public GameScreen() {

        civgrid = GridFX.getInstance();
        Map gridMap = civgrid.getMap();
        gridMap.putSettlement(runner.CivilizationGame.getFirstSettlement(),
            GameController.getCivilization(), 0, 0);
        gridMap.addEnemies(new Bandit(), 1);

        rMenu = new ResourcesMenu();

        stMenu = new StatusMenu();
        mMenu = new MilitaryMenu();
        wMenu = new WorkerMenu();
        bMenu = new BuildingMenu();
        recruitMenu = new RecruitMenu();

        civvbox = new VBox();

        this.setTop(rMenu.getRootNode());
        this.setCenter(civgrid);
        this.setLeft(civvbox);
        civvbox.getChildren().addAll(stMenu.getRootNode());

        this.update();

    }

    /**
     * This method should update the gridfx and the resource bar
     */
    public void update() {
        rMenu.update();
        civgrid.update();
    }
    /**
    * this method should return the resource menu
    * @return reosuce menu
    */
    public static ResourcesMenu getResources() {
        return rMenu;
    }


    /**
     * This method switches menus based on passed in game state.
     * GameController.java calls this to selectively control which
     * menus are displayed
     * @param state
     */
    public static void switchMenu(GameController.GameState state) {
        civvbox.getChildren().clear();
        switch (state) {
        case MILITARY:
            civvbox.getChildren().addAll(mMenu.getRootNode());
            break;

        case WORKER:
            civvbox.getChildren().addAll(wMenu.getRootNode());
            break;

        case BUILDING:
            civvbox.getChildren().addAll(bMenu.getRootNode());
            break;

        case RECRUITING:
            civvbox.getChildren().addAll(recruitMenu.getRootNode());
            break;

        case NEUTRAL:
            civvbox.getChildren().addAll(stMenu.getRootNode());
            break;
        default:
            return;
        }
    }
}
