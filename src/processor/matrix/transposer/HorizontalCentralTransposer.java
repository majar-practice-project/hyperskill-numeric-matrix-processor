package processor.matrix.transposer;

public class HorizontalCentralTransposer implements ITransposer{
    @Override
    public boolean isTranposable(double[][] matrix) {
        return true;
    }

    @Override
    public int getResultHeight(int height, int width) {
        return height;
    }

    @Override
    public int getResultWidth(int height, int width) {
        return width;
    }

    @Override
    public int getResultRowPos(int height, int width, int row, int col) {
        return height-row-1;
    }

    @Override
    public int getResultColPos(int height, int width, int row, int col) {
        return col;
    }
}
