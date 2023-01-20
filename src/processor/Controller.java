package processor;

import processor.matrix.Matrix;
import processor.matrix.transposer.HorizontalCentralTransposer;
import processor.matrix.transposer.MainDiagonalTransposer;
import processor.matrix.transposer.SideDiagonalTransposer;
import processor.matrix.transposer.VerticalCentralTransposer;
import processor.view.CommandView;

public class Controller {
    private static final Controller INSTANCE = new Controller();
    private final CommandView view = new CommandView();

    private Controller() {
    }

    public static Controller getInstance() {
        return INSTANCE;
    }

    public void start() {
        int choice;
        do {
            view.showMainMenu();
            choice = view.getMenuOption();
            switch (choice) {
                case 1 -> performMatrixAddition();
                case 2 -> performMatrixScalarMultiplication();
                case 3 -> performMatrixMultiplication();
                case 4 -> performMatrixTranspose();
                case 5 -> performMatrixDetermination();
                case 6 -> performMatrixInversion();
                default -> {
                }
            }
        } while (choice != 0);
    }

    public void performMatrixAddition() {
        double[][] m1 = view.getMatrix();
        double[][] m2 = view.getMatrix();
        try {
            view.showMatrix(Matrix.add(m1, m2));
        } catch (IllegalArgumentException e) {
            view.showError();
        }
    }

    public void performMatrixScalarMultiplication() {
        double[][] m1 = view.getMatrix();
        double multiplier = view.getDoubleNumber();
        view.showMatrix(Matrix.multiply(m1, multiplier));
    }

    public void performMatrixMultiplication() {
        double[][] m1 = view.getMatrix(1);
        double[][] m2 = view.getMatrix(2);
        view.showMatrix(Matrix.multiply(m1, m2));
    }

    public void performMatrixTranspose() {
        view.showTransposeMenu();
        int choice = view.getMenuOption();

        double[][] m1 = view.getMatrix();

        double[][] result = switch (choice) {
            //singleton? static references?
            case 1 -> Matrix.transpose(m1, new MainDiagonalTransposer());
            case 2 -> Matrix.transpose(m1, new SideDiagonalTransposer());
            case 3 -> Matrix.transpose(m1, new VerticalCentralTransposer());
            case 4 -> Matrix.transpose(m1, new HorizontalCentralTransposer());
            default -> throw new RuntimeException();
        };

        view.showMatrix(result);
    }

    public void performMatrixDetermination() {
        double[][] m1 = view.getMatrix();

        view.showDoubleResult(Matrix.getDeterminant(m1));
    }

    public void performMatrixInversion(){
        double[][] m1 = view.getMatrix();

        view.showMatrix(Matrix.inverse(m1));
    }
}
