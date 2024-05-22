package no.uib.inf101.tetris.model.tetromino;

public class PatternedTetrominoFactory implements TetrominoFactory {

  private String tetrisSymbols;
  private int symbolIndex = 0;

  /**
   * Constructs a new PatternedTetrominoFactory with tetrisSymbols.
   *
   * @author Henrik Brøgger
   * @param tetrisSymbols the symbols used to represent the tetrominoes
   */
  public PatternedTetrominoFactory(String tetrisSymbols) {
    this.tetrisSymbols = tetrisSymbols;
  }

  @Override
  public Tetromino getNext() {
    char symbol = tetrisSymbols.charAt(symbolIndex);
    Tetromino nextTetromino = Tetromino.newTetromino(symbol);

    if (symbolIndex == tetrisSymbols.length() - 1) {
      symbolIndex = 0;
    } else {
      symbolIndex++;
    }

    return nextTetromino;
  }
}
