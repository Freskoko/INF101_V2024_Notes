package no.uib.inf101.tetris;

import javax.swing.JFrame;
import no.uib.inf101.tetris.controller.TetrisController;
import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.TetrisView;

public class TetrisMain {
  public static final String WINDOW_TITLE = "INF101 Tetris";
  public static final int rows = 20;
  public static final int cols = 10;

  /**
   * @author Henrik Brøgger, with assistance from ChatGPT
   */
  public static void main(String[] args) {

    TetrisBoard tetrisBoard = new TetrisBoard(rows, cols);
    TetrominoFactory factory = new RandomTetrominoFactory();
    TetrisModel tetrisModel = new TetrisModel(tetrisBoard, factory);
    TetrisView view = new TetrisView(tetrisModel);

    // NOTE: When developing im using WSL2 (Ubuntu),
    // so this doesnt work, but it should work on windows!
    TetrisSong tetrisSong = new TetrisSong();

    @SuppressWarnings("unused") // supress constant warning, controller is used
    TetrisController controllableTetrisModel = new TetrisController(tetrisModel, view, tetrisSong);

    // The JFrame is the "root" application window.
    // We here set som properties of the main window,
    // and tell it to display our tetrisView
    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Here we set which component to view in our window
    frame.setContentPane(view);

    // Call these methods to actually display the window
    frame.pack();
    frame.setVisible(true);
  }
}
