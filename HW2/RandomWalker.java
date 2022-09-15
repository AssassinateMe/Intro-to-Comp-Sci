package HW2;

public class RandomWalker{
  public static void main(String[] args) 
    {
    int x = 0;
    int y = 0;
    int z = Integer.parseInt(args[0]);
    System.out.println("(" + x + "," + y+")");
    for(int i = 0; i < z; i++)
    {
        int random = (int)(Math.random() * 4) + 1;

        if (random == 1)
        {
            x--;
        }
        else if (random == 2)
        {
            x++;
        }
        else if (random == 3)
        {
            y--;
        }
        else
        {
            y++;
        }
        System.out.println("(" + x + "," + y+")");
    }
    System.out.println("Squared distance = " + (double) (Math.pow(x,2) + Math.pow(y,2)));

    }
}
