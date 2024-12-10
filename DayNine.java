import java.io.*;
import java.util.*;

public class DayNine {

    public static int getLastIndex(int[] map) {
        for(int i = (map.length/2 + map.length % 2 - 1); i >= 0; i-=1) {
            if(map[i*2] > 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        try {
            File inputFile = new File("input.txt");
            input = new Scanner(inputFile);
            String disc = input.nextLine();
            int[] map = new String(disc).chars()
                                    .map(c -> (char) c - 48)
                                    .toArray();

            // Integer lastData = map.length/2 + map.length % 2 - 1; (Used reaLastIndex/2 instead)
            Integer current = 0;
            Integer finalPostion = 0;
            Long sum = 0L;
            while(current < map.length/2) {
                while(map[current*2] > 0) {
                    sum += (current * finalPostion);
                    System.out.print(current);
                    map[current*2] -= 1;
                    finalPostion += 1;
                }
                for(int i = 0; i<map[(current*2) + 1]; i++) {
                    int reaLastIndex = getLastIndex(map)*2;
                    if(reaLastIndex > 0) {
                        sum += (reaLastIndex/2 * finalPostion);
                        System.out.print(reaLastIndex/2);
                        map[reaLastIndex] -= 1;
                        finalPostion += 1;
                    }
                }
                current += 1;
            }
            
            System.out.println("\n" + sum);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
