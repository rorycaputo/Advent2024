import java.io.*;
import java.security.KeyStore.Entry;
import java.util.*;
import java.util.stream.Collectors;

public class DayEight {
    
    private static Integer height;
    private static Integer width;

    private static String toLoc(Integer x, Integer y) {
        return x + "," + y;
    }

    private static Integer xLoc(String loc) {
        return Integer.parseInt(loc.split(",")[0]);
    }

    private static Integer yLoc(String loc) {
        return Integer.parseInt(loc.split(",")[1]);
    }

    //this relies on t1's y being <= t2
    private static ArrayList<String> getAntinodes(String t1, String t2) {
        ArrayList<String> antiNodes = new ArrayList<>();
        Integer xDiff = xLoc(t1) - xLoc(t2);
        Integer yDiff = yLoc(t1) - yLoc(t2);
        Integer currXLoc = xLoc(t1);
        Integer currYLoc = yLoc(t1);

        // up
        if(xDiff <= 0) {
            while(currXLoc >= 0 && currYLoc >= 0) {
                antiNodes.add(toLoc(currXLoc, currYLoc));
                currXLoc += xDiff;
                currYLoc += yDiff;
            }
        }
        if(xDiff > 0) {
            while(currXLoc < width && currYLoc >= 0) {
                antiNodes.add(toLoc(currXLoc, currYLoc));
                currXLoc += xDiff;
                currYLoc += yDiff;
            }
        }
        
        currXLoc = xLoc(t1);
        currYLoc = yLoc(t1);
        currXLoc -= xDiff;
        currYLoc -= yDiff;
        // down
        if(xDiff <= 0) {
            while(currXLoc < width && currYLoc < height) {
                antiNodes.add(toLoc(currXLoc, currYLoc));
                currXLoc -= xDiff;
                currYLoc -= yDiff;
            }
        }
        if(xDiff > 0) {
            while(currXLoc >= 0 && currYLoc < height) {
                antiNodes.add(toLoc(currXLoc, currYLoc));
                currXLoc -= xDiff;
                currYLoc -= yDiff;
            }
        }

        // // in-between
        // if(xDiff % 3 == 0 && yDiff % 2 == 0){
        //     antiNodes.add(toLoc(xLoc(t1) - (xDiff/3), xLoc(t1) - (yDiff/2)));
        //     antiNodes.add(toLoc(xLoc(t2) + (xDiff/3), xLoc(t2) + (yDiff/2)));
        // }
        return antiNodes;
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
            HashMap<Character, ArrayList<String>> dict = new HashMap<Character, ArrayList<String>>();

            input = new Scanner(inputFile);
            Integer currentY = -1;
            while (input.hasNextLine()) {
                line = input.nextLine();
                currentY += 1;
                for(int i=0; i<line.length(); i++){
                    char current = line.charAt(i);
                    if(current != '.') {
                        dict.putIfAbsent(current, new ArrayList<String>());
                        dict.get(current).add(toLoc(i, currentY));
                    }
                }
            }
            input.close();

            HashSet<String> antinodes = new HashSet<String>();

            for(char towerType : dict.keySet()) {
                ArrayList<String> towerList= dict.get(towerType);
                while(towerList.size() > 0){
                    String tower = towerList.remove(0);
                    for(String otherTower : towerList) {
                        antinodes.addAll(getAntinodes(tower, otherTower));
                    }
                }
            }

            System.out.println(antinodes.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
