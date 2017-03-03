package view;

import controller.GameController;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.Civilization;

public class ResourcesMenu {

    private HBox civhbox;
    private Civilization civ;
    private Text stratText = new Text();
    private Text resourceText = new Text();
    private Text moneyText = new Text();
    private Text foodText = new Text();
    private Text happinessText = new Text();

    /**
    * creates a resource bar and display the current state of
    * your civilization's resouces
    */
    public ResourcesMenu() {

        civhbox = new HBox(10);
        civ = GameController.getCivilization();
        this.update();

        civhbox.getChildren().addAll(stratText, resourceText, moneyText,
            foodText, happinessText);

    }
    /**
    * should update all the resouce values to the current
    * state of your resource values
    */
    public void update() {
        stratText.setText("Strat Level: "
            + civ.getStrategy().getStrategyLevel());
        resourceText.setText("Resources: " + civ.getResources());
        moneyText.setText("Money: " + civ.getTreasury().getCoins());
        foodText.setText("Food: " + civ.getFood());
        happinessText.setText("Happiness: " + civ.getHappiness());

    }
    /**
    * updates the resource bar and returns the resource bar
    * @return a hbox representation of the resource bar
    */
    public HBox getRootNode() {
        return civhbox;
    }
}
