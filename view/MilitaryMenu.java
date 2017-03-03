package view;

import controller.GameController;
import javafx.scene.control.Button;

/**
 * Created by William on 11/11/2016.
 */

public class MilitaryMenu extends AbstractMenu {
    /**
    * Implement the buttons and actions associated with
    * the buttons for the military menu
    */
    public MilitaryMenu() {
        Button attackBTN = new Button("Attack");
        Button moveBTN = new Button("Move");

        attackBTN.setOnAction(event -> {
                GameController.attacking();
                GameController.updateResourcesBar();
            });

        moveBTN.setOnAction(event -> {
                GameController.moving();
            });

        this.addMenuItem(attackBTN);
        this.addMenuItem(moveBTN);

    }
}
