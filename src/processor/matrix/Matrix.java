package processor.matrix;

import processor.matrix.transposer.ITransposer;
import processor.matrix.transposer.MainDiagonalTransposer;

import java.util.stream.IntStream;

public class Matrix {
    public static double[][] add(double[][] matrix1, double[][] matrix2) {
        int height = matrix1.length;
        int width = matrix1[0].length;

        if (height != matrix2.length || width != matrix2[0].length) {
            throw new IllegalArgumentException();
        }

        double[][] result = new double[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    public static double[][] multiply(double[][] matrix1, double multiplier) {
        int height = matrix1.length;
        int width = matrix1[0].length;
        double[][] result = new double[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result[i][j] = matrix1[i][j] * multiplier;
            }
        }

        return result;
    }

    public static double[][] multiply(double[][] matrix1, double[][] matrix2) {
        int height1 = matrix1.length;
        int width1 = matrix1[0].length;
        int height2 = matrix2.length;
        int width2 = matrix2[0].length;

        if (width1 != height2) throw new IllegalArgumentException();
        double[][] matrix = new double[height1][width2];

        for (int i = 0; i < height1; i++) {
            for (int j = 0; j < width2; j++) {
                for (int k = 0; k < width1; k++) {
                    matrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return matrix;
    }

    public static double[][] transpose(double[][] matrix, ITransposer transposer) {
        int height = matrix.length;
        int width = matrix[0].length;
        if (!transposer.isTranposable(matrix)) throw new IllegalArgumentException();

        double[][] result = new double[transposer.getResultHeight(height, width)][transposer.getResultWidth(height, width)];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result[transposer.getResultRowPos(height, width, i, j)][transposer.getResultColPos(height, width, i, j)]
                        = matrix[i][j];
            }
        }

        return result;
    }

    public static double getDeterminant(double[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;

        if (height != width) throw new IllegalArgumentException();

        int[] rows = IntStream.range(0, height).toArray();
        return getDeterminant(matrix, rows, rows.clone());
    }

    private static double getDeterminant(double[][] matrix, int[] rows, int[] cols) {
        int size = rows.length;

        if (size <= 2) {
            if (size == 1) {
                return matrix[rows[0]][cols[0]];
            } else {
                // could remove this?
                return matrix[rows[0]][cols[0]] * matrix[rows[1]][cols[1]] - matrix[rows[1]][cols[0]] * matrix[rows[0]][cols[1]];
            }
        }

        double determinant = 0;
        for (int i = 0; i < size; i++) {
            // possible caching?
            double expansion = matrix[rows[0]][cols[i]]
                    * getDeterminant(matrix, takeOneElementOut(rows, 0), takeOneElementOut(cols, i));
            determinant += i % 2 == 0 ? expansion : -expansion;
        }

        return determinant;
    }

    public static double[][] inverse(double[][] matrix){
        int height = matrix.length;
        int width = matrix[0].length;

        if(height!=width) throw new IllegalArgumentException();
        double determinant = getDeterminant(matrix);
        if(determinant==0) throw new IllegalArgumentException();

        return multiply(getAdjugate(matrix), 1/determinant);
    }

    private static double[][] getAdjugate(double[][] matrix){
        int height = matrix.length;
        int width = matrix[0].length;
        double[][] adjugate = new double[height][width];

        int[] sampleDimension = IntStream.range(0, height).toArray();
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                double cofactor = getDeterminant(matrix, takeOneElementOut(sampleDimension, i), takeOneElementOut(sampleDimension, j));
                adjugate[i][j] = (i+j)%2==0 ? cofactor : -cofactor;
            }
        }

        return transpose(adjugate, new MainDiagonalTransposer());
    }

    private static int[] takeOneElementOut(int[] dimension, int removedIndex) {
        int[] newArray = new int[dimension.length - 1];

        System.arraycopy(dimension, 0, newArray, 0, removedIndex);
        if (removedIndex + 1 < dimension.length) {
            System.arraycopy(dimension, removedIndex + 1, newArray, removedIndex, newArray.length - removedIndex);
        }
        return newArray;
    }
}
