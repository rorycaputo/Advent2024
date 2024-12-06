import java.io.*;
import java.util.*;

public class DayFour {

    private static Integer rowLength;
    
    private static Integer getRow(Integer index) {
       return index / rowLength;
    }

    private static boolean getUp(String input, Integer index) {
        if(getRow(index) < 3) {
            // if(index > 20){
            // System.out.println(input.substring(index-15, index));
            // }
            return false;
        }
        return ("" + input.charAt(index-rowLength) + 
            input.charAt(index-(2*rowLength)) +
            input.charAt(index-(3*rowLength))).equals("MAS");
    }

    private static boolean getDown(String input, Integer index) {
        if(getRow(index) + 1 > (input.length() / rowLength) - 3) {
            // if(index < 19500){
            //     System.out.println(input.substring(index, index+15));
            // }
            return false;
        }

        return ("" + input.charAt(index+rowLength) + 
            input.charAt(index+(2*rowLength)) +
            input.charAt(index+(3*rowLength))).equals("MAS");
    }

    private static boolean getnwDiag(String input, Integer index) {
        if(index % (rowLength) <= 2 || getRow(index) < 3) {
            return false;
        }

        return ("" + input.charAt(index-rowLength-1) + 
            input.charAt(index-(2*rowLength)-2) +
            input.charAt(index-(3*rowLength)-3)).equals("MAS");
    }

    private static boolean getneDiag(String input, Integer index) {
        if(index % (rowLength) >= rowLength - 3 || getRow(index) < 3) {
            return false;
        }

        return ("" + input.charAt(index-rowLength+1) + 
            input.charAt(index-(2*rowLength)+2) +
            input.charAt(index-(3*rowLength)+3)).equals("MAS");
    }

    private static boolean getswDiag(String input, Integer index) {
        if(index % (rowLength) <= 2 || getRow(index) + 1 > (input.length() / rowLength) - 3) {
            return false;
        }

        return ("" + input.charAt(index+rowLength-1) + 
            input.charAt(index+(2*rowLength)-2) +
            input.charAt(index+(3*rowLength)-3)).equals("MAS");
    }

    private static boolean getseDiag(String input, Integer index) {
        if(index % (rowLength) >= rowLength - 3 || getRow(index) + 1 > (input.length() / rowLength) - 3) {
            // if(index < 19500){
            //     System.out.println(input.substring(index-15, index));
            // }
            return false;
        }

        return ("" + input.charAt(index+rowLength+1) + 
            input.charAt(index+(2*rowLength)+2) +
            input.charAt(index+(3*rowLength)+3)).equals("MAS");
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
            Integer index = fullInput.indexOf("X");
            Integer maxIndex = index;
            while(index >= 0) {
                // if(index % (rowLength) >= rowLength - 3){
                //     System.out.println(fullInput.substring(index-15, index));
                // }
                if(index % (rowLength) < rowLength - 3 && fullInput.substring(index, index + 4).equals("XMAS")){
                    sum +=1;
                    System.out.println(index + " right " + sum);
                }
                // if(index % (rowLength) <= 3){
                //     System.out.println(fullInput.substring(index, index+15));
                // }
                if(index % (rowLength) > 2 &&  fullInput.substring(index - 3, index + 1).equals("SAMX")){
                    sum +=1;
                    System.out.println(index + " left " + sum);
                }
                if(getUp(fullInput, index)) {
                    sum +=1;
                    System.out.println(index + " up " + sum);
                }
                if(getDown(fullInput, index)) {
                    sum +=1;
                    System.out.println(index + " down " + sum);
                }
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
                index = fullInput.indexOf("X", index + 1);
            }

            System.out.println(sum);
            System.out.println(maxIndex);
            // System.out.println(fullInput.substring(19599 - 20, 19599));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
