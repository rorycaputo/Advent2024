import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DayEleven {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        try {
            File inputFile = new File("input.txt");
            input = new Scanner(inputFile);
            ArrayList<Long> stonesArray = new ArrayList<Long>(Arrays.stream(input.nextLine().split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList()));
            HashMap<Long, Long> stones = new HashMap<Long, Long>();
            for(Long stone: stonesArray) {
                stones.put(stone, 1L);
            }

            int blinks = 75;
            for(int i=0; i < blinks; i++) {
                System.out.println(stones.size() + " " + i);
                HashMap<Long, Long> nextStones = new HashMap<Long, Long>(stones);
                for(Long stone : stones.keySet()) {
                    if(stones.get(stone) != 0L) {
                        String stoneStr = String.valueOf(stone);
                        if(stone == 0L) {
                            Long one = nextStones.get(1L) != null ? nextStones.get(1L) : 0L;
                            nextStones.put(1L, one + stones.get(stone));
                            nextStones.put(0L, Math.max(0, nextStones.get(stone) - stones.get(stone)));
                        } else if(stoneStr.length() % 2 == 0) {
                            Long left = Long.parseLong(stoneStr.substring(0, stoneStr.length()/2));
                            Long right = Long.parseLong(stoneStr.substring(stoneStr.length()/2));
                            Long leftVal = nextStones.get(left) != null ? nextStones.get(left) : 0;
                            nextStones.put(left, leftVal + stones.get(stone));
                            Long rightVal = nextStones.get(right) != null ? nextStones.get(right) : 0;
                            nextStones.put(right, rightVal + stones.get(stone));
                            nextStones.put(stone, Math.max(0, nextStones.get(stone) - stones.get(stone)));
                        } else {
                            Long y2k = nextStones.get(stone * 2024) != null ? nextStones.get(stone * 2024) : 0;
                            nextStones.put(stone * 2024, y2k + stones.get(stone));
                            nextStones.put(stone, Math.max(0, nextStones.get(stone) - stones.get(stone)));
                        }
                    }
                }
                stones = nextStones;
            }
            
            Long sum = 0L;
            for(Long val : stones.values()) {
                sum += val;
            }
            System.out.println(sum);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
