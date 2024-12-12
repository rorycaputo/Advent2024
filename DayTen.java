import java.io.*;
import java.util.*;

public class DayTen {
    private static Integer height;
    private static Integer width;
    
    private static Integer searchAt(int[][] map, Integer curX, Integer curY, Integer altitude, Integer rating){
        if (curX > 0 && altitude - map[curX - 1][curY] == -1){
            rating = (searchAt(map, curX-1, curY, map[curX - 1][curY], rating));
        }
        if (curY > 0 && altitude - map[curX][curY - 1] == -1){
            rating = (searchAt(map, curX, curY - 1, map[curX][curY - 1], rating));
        }
        if (curX < width -1 && altitude - map[curX + 1][curY] == -1){
            rating = (searchAt(map, curX + 1, curY, map[curX + 1][curY], rating));
        }
        if (curY < height - 1 && altitude - map[curX][curY + 1] == -1){
            rating = (searchAt(map, curX, curY + 1, map[curX][curY + 1], rating));
        }
        if(map[curX][curY] == 9){
            return rating += 1;
        } else{
            return rating;
        }
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        try {
            File inputFile = new File("input.txt");
            input = new Scanner(inputFile);
            height = 0;
            while (input.hasNextLine()) {
                height += 1;
                input.nextLine();
            }

            input = new Scanner(inputFile);
            String line = input.nextLine();
            width = line.length();
            int[][] map = new int[width][height];

            Integer currentY = -1;
            input = new Scanner(inputFile);
            while (input.hasNextLine()) {
                line = input.nextLine();
                currentY += 1;
                for(int i=0; i<line.length(); i++){
                    map[currentY][i] = Integer.parseInt("" + line.charAt(i));
                }
            }
            input.close();

            Integer sum = 0;

            for(int curX = 0; curX < width; curX++) {
                for(int curY = 0; curY < height; curY++){
                    if (map[curX][curY] == 0) {
                        sum += searchAt(map, curX, curY, map[curX][curY], 0);
                    }
                }
            }

            System.out.println(sum);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    }
}