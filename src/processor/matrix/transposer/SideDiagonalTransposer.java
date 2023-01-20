package processor.matrix.transposer;

public class SideDiagonalTransposer implements ITransposer{

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
        return height-col-1;
    }

    @Override
    public int getResultColPos(int height, int width, int row, int col) {
        return width-row-1;
    }
}
