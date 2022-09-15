package HW3;

public class FindDuplicate {
    public static void main(String[] args) 
    {
        int[] arr = new int[args.length];
        boolean duplicate = false;

        for(int i = 0; i < args.length; i++)
        {
            arr[i] = Integer.parseInt(args[i]);
        }

        for(int i = 0; i < args.length; i++)
        {
            int num = arr[i];
            arr[i] = 0;
            for(int x = 0; x < args.length; x++)
            {
                if(num == arr[x])
                {
                    duplicate = true;
                }
            }
        }
        System.out.println(duplicate);
    }
}
