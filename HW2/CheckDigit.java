package HW2;

public class CheckDigit{
  public static void main(String[] args){
    long n = Long.parseLong(args [0]);

    int a = 0;
    int b = 0;
    for ( int i = 0; i < 13; i ++ ){
      if ( i % 2 == 0 )a += n%10;
      else b += n%10;
      n /= 10;
    }

    int c = (a + 3*b)%10;
    System.out.println(c);
  }
}