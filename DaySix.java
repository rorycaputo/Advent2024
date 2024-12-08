import java.io.*;
import java.util.*;

public class DaySix {

    private static Integer START_X;
    private static Integer START_Y;
    private static Integer height;
    private static Integer width;
    
    private static char getDirection(Integer xDir, Integer yDir){
        if(yDir == 0) {
            return xDir > 0 ? '>' : '<';
        } else {
            return yDir > 0 ? 'V' : '^';
        }
    }
    
    private static Integer checkLoop(char[][] map, Integer curX, Integer curY, Integer xDir, Integer yDir){
        char[][] testMap = Arrays.stream(map).map(char[]::clone).toArray(char[][]::new);;
        int xTry = curX+xDir;
        int yTry = curY+yDir;
        if(map[curY + yDir][curX + xDir] == '.'){
            testMap[curY + yDir][curX+xDir] = '#';
            // System.out.println("y: " + (curY + yDir) +" x: " + (curX+xDir));
        } else {
            return 0;
        }

        while(curX >=1 && curY >=1 && curX < width-1 && curY < height-1) {
            char dirChar = getDirection(xDir, yDir);
            if(testMap[curY + yDir][curX + xDir] == dirChar){
                System.out.println("y: " + (yTry) +" x: " + (xTry));
                return 1;
            }
            testMap[curY][curX] = dirChar;

            int turns = 0;
            while(testMap[curY + yDir][curX + xDir] == '#'){
                turns += 1;
                if(turns >= 3){
                    return 1;
                }
                Integer newX = yDir * -1;
                yDir = xDir;
                xDir = newX;
            }
            
            curX += xDir;
            curY += yDir;
        }
        return 0;
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
            char[][] map = new char[height][width];
            START_X = -1;
            START_Y = -1;

            Integer currentY = -1;
            input = new Scanner(inputFile);
            while (input.hasNextLine()) {
                line = input.nextLine();
                currentY += 1;
                for(int i=0; i<line.length(); i++){
                    map[currentY][i] = line.charAt(i);
                    if(line.charAt(i) == '^'){
                        START_X = i;
                        START_Y = currentY;
                    }
                }
            }
            input.close();

            Integer curX = START_X;
            Integer curY = START_Y;
            Integer xDir = 0;
            Integer yDir = -1;
            Integer sum = 0;
            while(curX >=1 && curY >=1 && curX < width-1 && curY < height-1) {
                char dirChar = getDirection(xDir, yDir);
                map[curY][curX] = dirChar;
                while(map[curY + yDir][curX + xDir] == '#'){
                    Integer newX = yDir * -1;
                    yDir = xDir;
                    xDir = newX;
                }
                
                // if(map[curY][curX] != 'X'){
                //         sum += 1;
                //     }
                // if(map[curY][curX] != 'X'){
                //     map[curY][curX] = 'X';
                // }
                sum += checkLoop(map, curX, curY, xDir, yDir);
                curX += xDir;
                curY += yDir;
            }

            System.out.println(sum);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    }
}