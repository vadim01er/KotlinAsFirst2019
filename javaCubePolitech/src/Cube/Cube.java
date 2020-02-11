package Cube;

import java.util.Arrays;

public final class Cube {

    private final String[] color = {"white", "red", "green", "orange", "blue", "yellow"};

    private final int sizeCube;

    private final String[][][] cubeNow;

    public Cube(int size, CubeName name){ // Конструктор
        this.sizeCube = size;
        this.cubeNow = new String[6][size][size];
        int m = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < size; j++) {
                cubeNow[i][j] = new String[size];
                for (int k = 0; k < size; k++) {
                    cubeNow[i][j][k] = color[m];
                }

            }
            m++;
        }
    }

    static private void rotateLeft(String[][] cube){ // Поворот квадратной матрицы в лево
        int size = cube.length;
        for (int j = 0; j < size / 2; j++) {
            for (int k = j; k < size - j - 1; k++) {
                String x =cube[j][k];
                cube[j][k] = cube[k][size - j - 1];
                cube[k][size - j - 1] = cube[size - j - 1][size - k -1];
                cube[size - j - 1][size - k - 1] = cube[size - k - 1][j];
                cube[size - k - 1][j] = x;
            }
        }
    }

    static private void rotateRight(String[][] cube){ // Поворот квадратной матрицы в право
        int size = cube.length;
        for (int j = 0; j < size / 2; j++) {
            for (int k = j; k < size - j - 1; k++) {
                String x = cube[j][k];
                cube[j][k] = cube[size - k - 1][j];
                cube[size - k - 1][j] = cube[size - j - 1][size - k - 1];
                cube[size - j - 1][size - k - 1] = cube[k][size - j - 1];
                cube[k][size - j - 1] = x;
            }
        }
    }

    public enum ClockWise{
        TRUE,
        FALSE;
    }

    public enum CubeName {
        FRONT(0),
        DOWN(1),
        LEFT(2),
        UP(3),
        RIGHT(4),
        BACK(5),
        DEFAULT(-1);

        private int num;

        CubeName(int i) { num = i; }
    }

    public void rotateBrink(CubeName name, int number, ClockWise clock){  // Поворот грани или центров
        if (number < 1 || number > sizeCube) throw new IllegalArgumentException("WRONG NUMBER :)");
        switch (name) {
            case FRONT: {
                if (clock == ClockWise.TRUE) {

                    if (number == 1) rotateRight(this.cubeNow[CubeName.FRONT.num]);// Поворот самой грани
                     String[] x = this.cubeNow[CubeName.DOWN.num][number - 1];// Изменения изза поворота грани
                    this.cubeNow[CubeName.DOWN.num][number - 1] = new String[sizeCube];
                    for (int i = 0; i < sizeCube; i++) {
                        this.cubeNow[CubeName.DOWN.num][number - 1][i] = this.cubeNow[CubeName.RIGHT.num][i][number - 1];
                        this.cubeNow[CubeName.RIGHT.num][i][number - 1] = this.cubeNow[CubeName.UP.num][sizeCube - number][i];
                        this.cubeNow[CubeName.UP.num][sizeCube - number][i] = this.cubeNow[CubeName.LEFT.num][i][sizeCube - number];
                        this.cubeNow[CubeName.LEFT.num][i][sizeCube - number] = x[i];
                    }

                } else {

                    //for (int i = 0; i < 3; i++) rotateBrink(CubeName.FRONT, number, ClockWise.TRUE);
                    // УБРАТЬ (//) ЕСЛИ МОЖНО БУДЕТ ТАК СДЕЛАТЬ БЕЗ ПОТЕРИ СКОРОСТИ!!!

                    if (number == 1) rotateLeft(this.cubeNow[CubeName.FRONT.num]); // Поворот самой грани
                    String[] x = this.cubeNow[CubeName.DOWN.num][number - 1];
                    this.cubeNow[CubeName.DOWN.num][number - 1] = new String[sizeCube];
                    for (int i = 0; i < sizeCube; i++) {
                        this.cubeNow[CubeName.DOWN.num][number - 1][i] = this.cubeNow[CubeName.LEFT.num][i][sizeCube - number];
                        this.cubeNow[CubeName.LEFT.num][i][sizeCube - number] = this.cubeNow[CubeName.UP.num][sizeCube - number][i];
                        this.cubeNow[CubeName.UP.num][sizeCube - number][i] = this.cubeNow[CubeName.RIGHT.num][i][number - 1];
                        this.cubeNow[CubeName.RIGHT.num][i][number - 1] = x[i];
                    }
                }
                break;
            }

            case RIGHT: {
                if (clock == ClockWise.TRUE) {
                    if (number == 1) rotateRight(this.cubeNow[CubeName.RIGHT.num]);
                    String[] x = new String[sizeCube];
                    for (int i = 0; i < sizeCube; i++){
                        x[i] = this.cubeNow[CubeName.DOWN.num][i][number - 1];
                        this.cubeNow[CubeName.DOWN.num][i][sizeCube - number] = this.cubeNow[CubeName.BACK.num][i][number - 1];
                        this.cubeNow[CubeName.BACK.num][i][number - 1] = this.cubeNow[CubeName.UP.num][i][sizeCube - number];
                        this.cubeNow[CubeName.UP.num][i][sizeCube - number] = cubeNow[CubeName.FRONT.num][i][sizeCube - number];
                        cubeNow[CubeName.FRONT.num][i][sizeCube - number] = x[i];
                    }
                } else {
                    //for (int i = 0; i < 3; i++) rotateBrink(CubeName.RIGHT, number, ClockWise.TRUE);
                    // УБРАТЬ (//) ЕСЛИ МОЖНО БУДЕТ ТАК СДЕЛАТЬ БЕЗ ПОТЕРИ СКОРОСТИ!!!
                    String[] x = new String[sizeCube];
                    for (int i = 0; i < sizeCube; i++){
                        x[i] = cubeNow[CubeName.FRONT.num][i][sizeCube - number];
                        cubeNow[CubeName.FRONT.num][i][sizeCube - number] = this.cubeNow[CubeName.UP.num][i][sizeCube - number];
                        this.cubeNow[CubeName.UP.num][i][sizeCube - number] = this.cubeNow[CubeName.BACK.num][i][number - 1];
                        this.cubeNow[CubeName.BACK.num][i][number - 1] = this.cubeNow[CubeName.DOWN.num][i][sizeCube - number];
                        this.cubeNow[CubeName.DOWN.num][i][sizeCube - number] = x[i];
                    }
                }
                break;
            }
            case LEFT:{
                if (clock == ClockWise.TRUE) {

                } else {

                }
            }
        }
    }

    public void rotateCubeTo(CubeName move) {  // Поворот куба ()
        switch (move) {
            case DOWN:{
                String[][] x = this.cubeNow[CubeName.FRONT.num];
                this.cubeNow[CubeName.FRONT.num] = this.cubeNow[CubeName.DOWN.num];
                this.cubeNow[CubeName.DOWN.num] = this.cubeNow[CubeName.BACK.num];
                this.cubeNow[CubeName.BACK.num] = this.cubeNow[CubeName.UP.num];
                this.cubeNow[CubeName.UP.num] = x;

                rotateLeft(this.cubeNow[CubeName.LEFT.num]); //90

                rotateRight(this.cubeNow[CubeName.RIGHT.num]); //90

                rotateRight(this.cubeNow[CubeName.DOWN.num]); //180
                rotateRight(this.cubeNow[CubeName.DOWN.num]);

                rotateRight(this.cubeNow[CubeName.BACK.num]); //180
                rotateRight(this.cubeNow[CubeName.BACK.num]);

                System.out.println(" TO DOWN");
                break;
            }
            case RIGHT:{
                String[][] x = this.cubeNow[CubeName.FRONT.num];
                this.cubeNow[CubeName.FRONT.num] = this.cubeNow[CubeName.RIGHT.num];
                this.cubeNow[CubeName.RIGHT.num] = this.cubeNow[CubeName.BACK.num];
                this.cubeNow[CubeName.BACK.num] = this.cubeNow[CubeName.LEFT.num];
                this.cubeNow[CubeName.LEFT.num] = x;

                rotateRight(this.cubeNow[CubeName.UP.num]); //90

                rotateLeft(this.cubeNow[CubeName.DOWN.num]); //90
                System.out.println(" TO RIGHT");
                break;
            }
            case LEFT:{
                String[][] x = this.cubeNow[CubeName.FRONT.num];
                this.cubeNow[CubeName.FRONT.num] = this.cubeNow[CubeName.LEFT.num];
                this.cubeNow[CubeName.LEFT.num] = this.cubeNow[CubeName.BACK.num];
                this.cubeNow[CubeName.BACK.num] = this.cubeNow[CubeName.RIGHT.num];
                this.cubeNow[CubeName.RIGHT.num] = x;

                rotateLeft(this.cubeNow[CubeName.UP.num]); //90

                rotateRight(this.cubeNow[CubeName.DOWN.num]); //90
                System.out.println(" TO LEFT");
                break;
            }
            case UP:{
                String[][] x = this.cubeNow[CubeName.FRONT.num];
                this.cubeNow[CubeName.FRONT.num] = this.cubeNow[CubeName.UP.num];
                this.cubeNow[CubeName.UP.num] = this.cubeNow[CubeName.BACK.num];
                this.cubeNow[CubeName.BACK.num] = this.cubeNow[CubeName.DOWN.num];
                this.cubeNow[CubeName.DOWN.num] = x;

                rotateRight(this.cubeNow[CubeName.LEFT.num]); //90

                rotateLeft(this.cubeNow[CubeName.RIGHT.num]); //90

                rotateRight(this.cubeNow[CubeName.UP.num]); //180
                rotateRight(this.cubeNow[CubeName.UP.num]);

                rotateRight(this.cubeNow[CubeName.BACK.num]); //180
                rotateRight(this.cubeNow[CubeName.BACK.num]);

                System.out.println(" TO UP");
                break;
            }
            case BACK:{
                rotateCubeTo(CubeName.RIGHT);
                rotateCubeTo(CubeName.RIGHT);
                System.out.println(" TO BACK");
                break;
            }
            case FRONT:{
                System.out.println(" TO FRONT");
                break;
            }
        }
    }

    public int getSizeCube() {  // Выводит размер
        return sizeCube;
    }

    public String[][][] getCubeNow() {  // Выводит сам куб
        return cubeNow;
    }

    @Override
    public String toString(){  // Просмотр всего куба в целом
        int size = sizeCube;

        String s1 = " ".repeat(2 * size + size + 3) + "—".repeat(2 * size + size + 1);
        StringBuilder text = new StringBuilder(s1 + "\n");
        for (int j = 0; j < sizeCube; j++) {
            text.append(" ".repeat(2 * size + size + 2)).append("|");
            for ( int k = 0; k < sizeCube; k++){
               text.append(" ").append(cubeNow[CubeName.UP.num][j][k].substring(0,2));
            }
            text.append(" |\n");
        }
        String s2 = " " + "—".repeat(2 * size + size + 1) +
                " " + "—".repeat(2 * size + size + 1) +
                " " + "—".repeat(2 * size + size + 1) +
                " " + "—".repeat(2 * size + size + 1);
        text.append(s2).append("\n");
        for (int j = 0; j < sizeCube; j++) {
            text.append("|");
            for ( int k = 0; k < sizeCube; k++){
                text.append(" ").append(cubeNow[CubeName.LEFT.num][j][k].substring(0, 2));
            }
            text.append(" |");
            for ( int k = 0; k < sizeCube; k++){
                text.append(" ").append(cubeNow[CubeName.FRONT.num][j][k].substring(0, 2));
            }
            text.append(" |");
            for ( int k = 0; k < sizeCube; k++){
                text.append(" ").append(cubeNow[CubeName.RIGHT.num][j][k].substring(0, 2));
            }
            text.append(" |");
            for ( int k = 0; k < sizeCube; k++){
                text.append(" ").append(cubeNow[CubeName.BACK.num][j][k].substring(0, 2));
            }
            text.append(" |").append("\n");
        }
        text.append(s2).append("\n");
        for (int j = 0; j < sizeCube; j++) {
            text.append(" ".repeat(2 * size + size + 2)).append("|");
            for ( int k = 0; k < sizeCube; k++){
               text.append(" ").append(cubeNow[CubeName.DOWN.num][j][k].substring(0, 2));
            }
            text.append(" |").append("\n");
        }
        text.append(s1).append("\n");
        return text.toString();
    }

    public void toStringOneBrink(CubeName name) {  // Просмотр одной стороны
        StringBuilder text = new StringBuilder(" ".repeat(sizeCube + 1)+ name).append("\n");
        for (int j = 0; j < sizeCube; j++) {
            for (int k = 0; k < sizeCube; k++) {
                text.append(" ").append(cubeNow[name.num][j][k].substring(0, 2));
            }
            text.append("\n");
        }
        System.out.println(text);
    }
}
