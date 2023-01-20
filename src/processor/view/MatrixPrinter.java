package processor.view;

public class MatrixPrinter {
    public void showMatrix(double[][] matrix) {
        int decimalLength = 3;
        int maxLength = getMaxLength(matrix) + decimalLength;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%" + maxLength + "." + decimalLength + "f  ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    private int getMaxLength(double[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, (int) Math.ceil(Math.log10(matrix[i][j])));
            }
        }
        // extra 1 for edge cases, another extra 1 for negative sign
        return max + 2;
    }
}
