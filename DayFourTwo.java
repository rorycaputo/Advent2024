import java.io.*;
import java.util.*;

public class DayFourTwo {

    private static Integer rowLength;
    
    private static Integer getRow(Integer index) {
       return index / rowLength;
    }

    private static boolean getnwDiag(String input, Integer index) {
        if(index % (rowLength) <= 1 || getRow(index) < 2) {
            return false;
        }

        return ("" + input.charAt(index-rowLength-1) + 
            input.charAt(index-(2*rowLength)-2)).equals("AS") &&
            ("" + input.charAt(index-(2*rowLength)) + 
            input.charAt(index-(rowLength)-1) +
            input.charAt(index-2)).equals("MAS");
    }

    private static boolean getneDiag(String input, Integer index) {
        if(index % (rowLength) >= rowLength - 2 || getRow(index) < 2) {
            return false;
        }

        return ("" + input.charAt(index-rowLength+1) + 
        input.charAt(index-(2*rowLength)+2)).equals("AS") &&
        ("" + input.charAt(index-(2*rowLength)) + 
        input.charAt(index-(rowLength)+1) +
        input.charAt(index+2)).equals("SAM");
    }

    private static boolean getswDiag(String input, Integer index) {
        if(index % (rowLength) <= 1 || getRow(index) + 1 > (input.length() / rowLength) - 2) {
            return false;
        }

        return ("" + input.charAt(index+rowLength-1) + 
            input.charAt(index+(2*rowLength)-2)).equals("AS") &&
            ("" + input.charAt(index+(2*rowLength)) + 
            input.charAt(index+(rowLength)-1) +
            input.charAt(index-2)).equals("SAM");
    }

    private static boolean getseDiag(String input, Integer index) {
        if(index % (rowLength) >= rowLength - 2 || getRow(index) + 1 > (input.length() / rowLength) - 2) {
            // if(index < 19500){
            //     System.out.println(input.substring(index-15, index));
            // }
            return false;
        }

        return ("" + input.charAt(index+rowLength+1) + 
            input.charAt(index+(2*rowLength)+2)).equals("AS") &&
            ("" + input.charAt(index+(2*rowLength)) + 
            input.charAt(index+(rowLength)+1) +
            input.charAt(index+2)).equals("MAS");
    }

    //Use 2D arrys for christ sake
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        try {
            File inputFile = new File("input.txt");
            input = new Scanner(inputFile);
            String fullInput = input.nextLine();
            rowLength = fullInput.length();
            while(input.hasNextLine()){
                fullInput += input.nextLine();
            }
            input.close();

            
            Integer sum = 0;

            // CharacterIterator forward = new StringCharacterIterator(fullInput);
            Integer index = fullInput.indexOf("M");
            Integer maxIndex = index;
            while(index >= 0) {
                if(getnwDiag(fullInput, index)) {
                    sum +=1;
                    System.out.println(index + " nw " + sum);
                }
                if(getneDiag(fullInput, index)) {
                    sum +=1;
                    System.out.println(index + " ne " + sum);
                }
                if(getswDiag(fullInput, index)) {
                    sum +=1;
                    System.out.println(index + " sw " + sum);
                }
                if(getseDiag(fullInput, index)) {
                    sum +=1;
                    System.out.println(index + " se " + sum);
                }
                maxIndex = index;
                index = fullInput.indexOf("M", index + 1);
            }

            System.out.println(sum);
            System.out.println(maxIndex);
            // System.out.println(fullInput.substring(19599 - 20, 19599));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
