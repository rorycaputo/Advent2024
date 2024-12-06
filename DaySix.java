import java.io.*;
import java.util.*;

public class DaySix {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        try {
            File inputFile = new File("input.txt");
            input = new Scanner(inputFile);
            Integer height = 0;
            while (input.hasNextLine()) {
                height += 1;
                input.nextLine();
            }

            input = new Scanner(inputFile);
            String line = input.nextLine();
            Integer width = line.length();
            char[][] map = new char[height][width];
            Integer startX = -1;
            Integer startY = -1;

            Integer currentY = -1;
            input = new Scanner(inputFile);
            while (input.hasNextLine()) {
                line = input.nextLine();
                currentY += 1;
                for(int i=0; i<line.length(); i++){
                    map[currentY][i] = line.charAt(i);
                    if(line.charAt(i) == '^'){
                        startX = i;
                        startY = currentY;
                    }
                }
            }
            input.close();

            Integer curX = startX;
            Integer curY = startY;
            Integer xDir = 0;
            Integer yDir = -1;
            Integer sum = 1;
            while(curX >=1 && curY >=1 && curX < width-1 && curY < height-1) {
                if(map[curY + yDir][curX + xDir] != '.' && map[curY + yDir][curX + xDir] != 'X'){
                    Integer newX = yDir * -1;
                    yDir = xDir;
                    xDir = newX;
                }
                
                if(map[curY][curX] != 'X'){
                    sum += 1;
                }
                map[curY][curX] = 'X';
                curX += xDir;
                curY += yDir;
            }

            System.out.println(sum);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    }
}