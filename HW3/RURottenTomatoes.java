package HW3;

public class RURottenTomatoes {
    public static void main(String[] args) 
    {
        int numRows = Integer.parseInt(args[0]);
        int numColm = Integer.parseInt(args[1]);
        int[][] movRates = new int[numRows][numColm];
        int largestSum = 0; 
        int index = 0; 
        int i = 2; 

        for(int row = 0; row < numRows; row++)
        {
            for(int col = 0; col < numColm; col++)
            {
                movRates[row][col] = Integer.parseInt(args[i]);
                i++;
            }
        }

        for(int col = 0; col < numColm; col++)
        {
            int sum = 0;
            for(int row = 0; row < numRows; row++)
            {
                sum += movRates[row][col];
                if(largestSum < sum)
                {
                    largestSum = sum;
                    index = col;
                }

            }
        }
        System.out.println(index);
    }
}
