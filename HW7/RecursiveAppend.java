package HW7;

public class RecursiveAppend {
    public static String appendNTimes (String original, int n) {

        if(n == 0)
            return original;

        return original + appendNTimes(original, n-1);
    }
    
    public static void main (String[] args) {
        System.out.println(appendNTimes("cat", 10));
    }

}
