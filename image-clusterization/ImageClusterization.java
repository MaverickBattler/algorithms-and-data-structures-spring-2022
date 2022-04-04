import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class ImageClusterization {

    private final char[][] grid;
    private final int[][] directions = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    ImageClusterization(int m, int n) {
        grid = new char[m][n];
    }

    public static void main(String[] args) {
        for (int fileId = 1; fileId <= 8; fileId++) {
            File file = new File("./image-clusterization/" + fileId + ".in");
            try (
                    Scanner myReader = new Scanner(file);
                    FileWriter writer = new FileWriter("./image-clusterization/" + fileId + "_mine.out", false)
            ) {
                int m = myReader.nextInt();
                int n = myReader.nextInt();
                ImageClusterization imageClusterization = new ImageClusterization(m, n);
                myReader.nextLine();//переводим сканер на начало строки символов
                for (int i = 0; i < m; i++) {
                    char[] line = myReader.nextLine().toCharArray();
                    imageClusterization.grid[i] = line;
                }
                writer.write(imageClusterization.countIslands() + "\n");
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void DFS(int i, int j) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length //если координата в пределах поля
                && grid[i][j] == '#') {//и если на клетке символ #
            grid[i][j] = 'X';//помечаем его
            for (int[] direction : directions) { //поиск по соседним клеткам
                DFS(i + direction[0], j + direction[1]);
            }
        }
    }

    private int countIslands() {
        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '#') {
                    DFS(i, j);
                    islands++;
                }
            }
        }
        return islands;
    }
}