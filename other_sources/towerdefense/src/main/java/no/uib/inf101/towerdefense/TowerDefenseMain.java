package no.uib.inf101.towerdefense;

import java.awt.Dimension;
import javax.swing.JFrame;
import no.uib.inf101.towerdefense.controller.GameController;
import no.uib.inf101.towerdefense.model.GameModel;
import no.uib.inf101.towerdefense.view.GameView;
import no.uib.inf101.towerdefense.view.viewUtils.GameVisualInformation;

/**
 * @author Henrik Brøgger, with assistance from chatGPT Created with assistance from ChatGPT OpenAI
 *     (2023). ChatGPT (20. april-versjon) [Stor språkmodell]. Hentet fra https://chat.openai.com.
 */
public class TowerDefenseMain {
  public static final String WINDOW_TITLE = "INF101 Language vs Machine";

  public static void main(String[] args) {

    int rows = GameVisualInformation.getBoardRows();
    int cols = GameVisualInformation.getBoardCols();

    GameModel GameModel = new GameModel(rows, cols);

    GameView view = new GameView(GameModel);

    view.setPreferredSize(
        new Dimension(
            GameVisualInformation.getWindowLength(), GameVisualInformation.getWindowHeight()));

    @SuppressWarnings("unused") // supress constant warning, controller is used
    GameController controllableGameModel = new GameController(GameModel, view);

    // boilerplate to run JFrame

    // The JFrame is the "root" application window.
    // We here set som properties of the main window,
    // and tell it to display our GameView
    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Here we set which component to view in our window
    frame.setContentPane(view);

    // Call these methods to actually display the window
    frame.pack();
    frame.setVisible(true);
  }
}
