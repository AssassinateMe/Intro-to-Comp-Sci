package HW2;

public class LargestOfFive {

    public static void main (String[] args) {
        int max = Integer.parseInt(args[0]);
        for (int i = 1; i < args.length; i++) {
          int input = Integer.parseInt(args[i]);
          if (input > max) {
            max = input;
          }
        }
        System.out.println(max);
    }
}