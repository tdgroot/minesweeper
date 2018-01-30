import java.util.Random;

public class MineSweeper {
    private int mines;
    private int width;
    private int height;
    private int[] field;
    private final Random random = new Random();
    private static final int MINE_VALUE = 9;

    private MineSweeper(int width, int height, int mines) {
        this.width = width;
        this.height = height;
        this.mines = mines;

        field = new int[width * height];
    }

    private void generateMines() {
        for (int i = 0; i < mines; i++) {
            int index;
            do {
                index = random.nextInt(field.length);
            } while (field[index] == MINE_VALUE);

            field[index] = MINE_VALUE;
        }
    }

    private void generateField() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = x + y * width;
                if (field[index] == MINE_VALUE) {
                    continue;
                }

                int minesAround = 0;
                for (int ny = -1; ny <= 1; ny++) {
                    int yy = y + ny;
                    if (yy < 0 || yy >= height) {
                        continue;
                    }
                    for (int nx = -1; nx <= 1; nx++) {
                        int xx = x + nx;
                        if (xx < 0 || xx >= width) {
                            continue;
                        }
                        if (field[xx + yy * width] == MINE_VALUE) {
                            minesAround++;
                        }
                    }
                }

                field[index] = minesAround;
            }
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result.append("[").append(field[width * y + x]).append("]");
            }
            result.append("\n");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        MineSweeper mineSweeper = new MineSweeper(9, 9, 15);
        mineSweeper.generateMines();
        mineSweeper.generateField();
        System.out.println(mineSweeper);
    }
}
