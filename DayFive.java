import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DayFive {
    
    // private static Long sumString(String input) {
    //     Long sum = 0L;
    //     Pattern multPattern = Pattern.compile("mul\\(\\d*,\\d*\\)");
    //     Pattern digitPattern = Pattern.compile("\\d*[,|\\)]");
    //     Matcher m = multPattern.matcher(input);
        
    //     while(m.find()) {
    //         Matcher d = digitPattern.matcher(m.group());
    //         d.find();
    //         Long multOne = Long.parseLong(d.group().substring(0, d.group().length() - 1));
    //         d.find();
    //         Long multTwo = Long.parseLong(d.group().substring(0, d.group().length() - 1));
    //         sum += (multOne * multTwo);
    //     }
    //     return sum;
    // }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        try {
            File inputFile = new File("input.txt");
            input = new Scanner(inputFile);
            // String fullInput = "";

            HashMap<Integer, HashSet<Integer>> rules = new HashMap<>();
            String currentLine = input.nextLine();

            while(!currentLine.equals("")){
                Integer left = Integer.parseInt(currentLine.split("\\|")[0]);
                Integer right = Integer.parseInt(currentLine.split("\\|")[1]);
                Set<Integer> ruleValues = rules.get(left);
                if(ruleValues != null){
                    ruleValues.add(right);
                } else {
                    rules.put(left, new HashSet<Integer>(Arrays.asList(right)));
                }
                currentLine = input.nextLine();
            }

            LinkedList<Integer> update;
            Integer sum = 0;
            while(input.hasNextLine()){
                update = new LinkedList<Integer>(Arrays.stream(input.nextLine().split(","))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList()));
                boolean valid = true;
                for(int i=0; i<update.size(); i++) {
                    Set<Integer> rulePages = rules.get(update.get(i));
                    if(rulePages != null){
                        for(Integer rulePage : rulePages) {
                            int updatePostion = update.indexOf(rulePage); 
                            if(updatePostion >=0 && updatePostion < i){
                                valid = false;
                                if(i == update.size()-1){
                                    update.add(update.remove(updatePostion));
                                } else {
                                    update.add(i+1, update.remove(updatePostion));
                                }
                                i=0;
                            }
                        }
                    }
                }
                if(!valid){
                    update.toString();
                    sum += update.get(update.size()/2);
                }
                // if(valid){
                //     sum += update.get(update.size()/2);
                // }
            }


            input.close();

           
            System.out.println(sum);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
