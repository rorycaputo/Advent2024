import java.io.*;
import java.util.*;


public class DayTwo {

    private static boolean isLineSafe(ArrayList<String> digits) {
        boolean safe = true;
        Integer sign = 0;
        if(digits.size() >= 2){
            for(int i=0; i<digits.size() - 1; i++) {
                Integer diff = Integer.parseInt(digits.get(i)) - Integer.parseInt(digits.get(i+1));
                if(i==0) {
                    sign = (diff > 0) ? 1 : -1;
                }
                Integer height = Math.abs(diff);
                if(!(height > 0 && height <= 3)){
                    safe = false;
                }
                if(Math.abs(diff + sign) < Math.abs(diff)) {
                    safe = false;
                }
            }
        }
        return safe;
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        try {
            File inputFile = new File("input.txt");
            input = new Scanner(inputFile);

            Integer totalSafe=0;
            while (input.hasNextLine()) {
                ArrayList<String> digits = new ArrayList<String>(Arrays.asList(input.nextLine().split(" ")));
                boolean safe = isLineSafe(digits);
                if (safe) {
                    totalSafe+=1;
                }
                else {
                    for(int i=0; i<digits.size(); i++){
                        ArrayList<String> temp = new ArrayList<String>(digits);
                        temp.remove(i);
                        if(isLineSafe(temp)) {
                            totalSafe+=1;
                            break;
                        }
                    }
                };
            }
            System.out.println(totalSafe);
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}