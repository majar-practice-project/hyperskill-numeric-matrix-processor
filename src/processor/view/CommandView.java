package processor.view;

import java.util.Map;
import java.util.Scanner;

public class CommandView {
    private final Scanner scanner = new Scanner(System.in);
    private final MatrixPrinter matrixPrinter = new MatrixPrinter();
    private final Map<Integer, String> orders = Map.of(
            1, "first",
            2, "second",
            0, "" + (char) 8
    );

    public double[][] getMatrix() {
        return getMatrix(0);
    }

    public double[][] getMatrix(int num) {
        System.out.printf("Enter size of %s matrix: ", orders.get(num));

        int height = scanner.nextInt();
        int width = scanner.nextInt();

        System.out.printf("Enter %s matrix:%n", orders.get(num));

        return readMatrix(height, width);
    }

    public double[][] readMatrix(int height, int width) {
        double[][] matrix = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }

    public double getDoubleNumber() {
        return scanner.nextDouble();
    }

    public void showError() {
        System.out.println("ERROR");
    }

    public void showMatrix(double[][] matrix) {
        System.out.println("The result is: ");
        matrixPrinter.showMatrix(matrix);
    }

    public void showDoubleResult(double num) {
        System.out.println("The result is: ");
        System.out.println(num);
    }

    public void showMainMenu() {
        System.out.println();
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
    }

    public void showTransposeMenu() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
    }

    public int getMenuOption() {
        System.out.print("Your choice: ");
        return scanner.nextInt();
    }
}
