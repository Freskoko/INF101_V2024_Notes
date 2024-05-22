package no.uib.inf101.tetris.model.tetromino;

import java.util.Random;

public class RandomTetrominoFactory implements TetrominoFactory {

  @Override
  public Tetromino getNext() {
    // Source for random char in string:
    // https://stackoverflow.com/questions/28512351/selecting-a-random-char-in-a-string-in-java-with-a-certain-method
    // Visited 2024-02-25

    String tetrisSymbols = "LJSZTIO";
    Random random = new Random();
    int index = random.nextInt(tetrisSymbols.length());

    return Tetromino.newTetromino(tetrisSymbols.charAt(index));
  }
}
