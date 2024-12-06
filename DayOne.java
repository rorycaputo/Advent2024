import java.io.*;
import java.util.*;


public class DayOne {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        try {
            File inputFile = new File("input.txt");
            input = new Scanner(inputFile);
            // PriorityQueue<Integer> left = new PriorityQueue<>();
            ArrayList<Integer> left = new ArrayList<Integer>();
            // PriorityQueue<Integer> right = new PriorityQueue<>();
            ArrayList<Integer> right = new ArrayList<Integer>();


            while (input.hasNextLine()) {
                String line = input.nextLine();
                left.add(Integer.parseInt(line.split("   ")[0]));
                right.add(Integer.parseInt(line.split("   ")[1]));
            }
            input.close();

            Integer sum = 0;

            for(int i=0; i<left.size(); i++) {
                Integer freq = Collections.frequency(right, left.get(i));
                sum += left.get(i) * freq;
            }

            System.out.println(sum);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    }
}