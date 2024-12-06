import java.io.*;
import java.util.*;
import java.util.regex.*;

public class DayThree {
    
    private static Long sumString(String input) {
        Long sum = 0L;
        Pattern multPattern = Pattern.compile("mul\\(\\d*,\\d*\\)");
        Pattern digitPattern = Pattern.compile("\\d*[,|\\)]");
        Matcher m = multPattern.matcher(input);
        
        while(m.find()) {
            Matcher d = digitPattern.matcher(m.group());
            d.find();
            Long multOne = Long.parseLong(d.group().substring(0, d.group().length() - 1));
            d.find();
            Long multTwo = Long.parseLong(d.group().substring(0, d.group().length() - 1));
            sum += (multOne * multTwo);
        }
        return sum;
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        try {
            File inputFile = new File("input.txt");
            input = new Scanner(inputFile);
            String fullInput = "";
            while(input.hasNextLine()){
                fullInput += input.nextLine();
            }
            input.close();

            Pattern doPattern = Pattern.compile("do\\(\\)");
            Pattern dontPattern = Pattern.compile("don't\\(\\)");
            Long sum = 0L;
            // boolean active = true;

            Matcher d = doPattern.matcher(fullInput);
            Matcher dt = dontPattern.matcher(fullInput);
            dt.find();
            sum += sumString(fullInput.substring(0, dt.start()));

            int current = 0;

            //Find the don'ts, get the next nearest do, then get the next don't and send that substring
            while (dt.find(current + 1)) {
                if(d.find(dt.end())) {
                    current = d.end();
                    if(dt.find(current)) {
                        sum += sumString(fullInput.substring(d.end(), dt.start()));
                    } else {
                        sum += sumString(fullInput.substring(d.end()));
                    }
                }
            }

            System.out.println(sum);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
