import java.io.*;
import java.util.*;

public class DayNineTwo {

    public static int getFirstFreeSpace(int[] map, int fileSize, int current) {
        for(int i = 0; i < current; i++) {
            if(map[(i*2) + 1] >= fileSize) {
                return i;
            }
        }
        return -1;
    }

    public static int sumSubArray(int[] a, int start, int end) {
       return Arrays.stream(Arrays.copyOfRange(a, start, end)).sum();
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

            Integer current = (map.length/2 + map.length % 2 - 1);
            Long sum = 0L;
            int[] offsets = new int[(map.length/2 + map.length % 2)];

            while(current >= 0) {
                int fileSize = map[current*2];
                int realFreeIndex = (getFirstFreeSpace(map, fileSize, current)*2) + 1;
                if(realFreeIndex < 0) {
                    int indexFromMapStart = sumSubArray(map, 0, current*2);
                    int offsetSumFromStart = sumSubArray(offsets, 0, current);
                    for(int i=0; i<fileSize; i++) {
                        System.out.println(current + " " + (indexFromMapStart + offsetSumFromStart + i));
                        sum += current * (indexFromMapStart + offsetSumFromStart + i);
                    }                    
                } else {
                    int indexFromMapStart = sumSubArray(map, 0, realFreeIndex);
                    int offsetSumFromStart = sumSubArray(offsets, 0, ((realFreeIndex - 1) / 2) + 1);
                    for(int i=0; i<fileSize; i++) {
                        System.out.println(current + " " + (indexFromMapStart + offsetSumFromStart + i));
                        sum += current * (indexFromMapStart + offsetSumFromStart + i);
                    }
                    System.out.println(map[realFreeIndex] - fileSize + ".");
                    map[realFreeIndex] -= fileSize;
                    offsets[(realFreeIndex - 1) / 2] += fileSize;
                }

                current -= 1;
            }
            
            System.out.println("\n" + sum);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
