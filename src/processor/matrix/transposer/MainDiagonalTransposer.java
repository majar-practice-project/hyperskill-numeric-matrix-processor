package processor.matrix.transposer;

public class MainDiagonalTransposer implements ITransposer{
    @Override
    public boolean isTranposable(double[][] matrix) {
        return matrix.length==matrix[0].length;
    }

    @Override
    public int getResultHeight(int height, int width) {
        return width;
    }

    @Override
    public int getResultWidth(int height, int width) {
        return height;
    }

    @Override
    public int getResultRowPos(int height, int width, int row, int col) {
        return col;
    }

    @Override
    public int getResultColPos(int height, int width, int row, int col) {
        return row;
    }
}
