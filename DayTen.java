import java.io.*;
import java.util.*;

public class DayTen {
    private static Integer height;
    private static Integer width;
    
    private static HashSet<String> searchAt(int[][] map, Integer curX, Integer curY, Integer altitude, HashSet<String> peakCoords){
        if(map[curX][curY] == 9){
            peakCoords.add(curX + "," + curY);
            return peakCoords;
        }
        if (curX > 0 && altitude - map[curX - 1][curY] == -1){
            peakCoords.addAll(searchAt(map, curX-1, curY, map[curX - 1][curY], peakCoords));
        }
        if (curY > 0 && altitude - map[curX][curY - 1] == -1){
            peakCoords.addAll(searchAt(map, curX, curY - 1, map[curX][curY - 1], peakCoords));
        }
        if (curX < width -1 && altitude - map[curX + 1][curY] == -1){
            peakCoords.addAll(searchAt(map, curX + 1, curY, map[curX + 1][curY], peakCoords));
        }
        if (curY < height - 1 && altitude - map[curX][curY + 1] == -1){
            peakCoords.addAll(searchAt(map, curX, curY + 1, map[curX][curY + 1], peakCoords));
        }
        return peakCoords;
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
                        sum += searchAt(map, curX, curY, map[curX][curY], new HashSet<>()).size();
                    }
                }
            }

            System.out.println(sum);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    }
}