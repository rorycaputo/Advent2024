import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DaySaven {
    
    private static Integer solve(Long target, Long current, ArrayList<Long> n) {
        long mult = current * n.get(0);
        long add = current + n.get(0);
        long concat = Long.parseLong(current.toString() + n.get(0).toString());
        if(n.size() == 1) {
            if (mult == target || concat == target || add == target) {
                return 1;
            } else {
                return 0;
            }
        }

        ArrayList<Long> nextList = new ArrayList<Long>(n.subList(1, n.size()));

        if(mult > target && concat > target) {
            return solve(target, add, nextList);
        } else {
            if(solve(target, mult, nextList) == 1) {
                return 1;
            } else if(solve(target, concat, nextList) == 1){
                return 1;
            } else {
                return solve(target, add, nextList);
            }
        }
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        try {
            File inputFile = new File("input.txt");
            input = new Scanner(inputFile);
            String line = "";
            Long sum = 0L;
            while (input.hasNextLine()) {
                line = input.nextLine();
                Long target = Long.parseLong(line.split(":")[0]);
                ArrayList<Long> n = new ArrayList<Long>(Arrays.stream(line.split(": ")[1].split(" "))
                                .map(Long::parseLong)
                                .collect(Collectors.toList()));
                sum += (target * solve(target, n.get(0), new ArrayList<Long>(n.subList(1, n.size()))));
            }
            input.close();

            System.out.println(sum);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
