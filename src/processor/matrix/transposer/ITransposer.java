package processor.matrix.transposer;

public interface ITransposer {
    boolean isTranposable(double[][] matrix);
    int getResultHeight(int height, int width);
    int getResultWidth(int height, int width);
    int getResultRowPos(int height, int width, int row, int col);
    int getResultColPos(int height, int width, int row, int col);
}