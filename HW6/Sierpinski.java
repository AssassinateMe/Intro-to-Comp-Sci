package HW6;

public class Sierpinski {

    // Height of an equilateral triangle whose sides are of the specified length. 
    public static double height(double length) {
        double h = 0;

        h = (Math.sqrt(3) * length) / 2;
        return h;
    }

    // Draws a filled equilateral triangle whose bottom vertex is (x, y) 
    // of the specified side length. 
    public static void filledTriangle(double x, double y, double length) {

        double[] xcords = new double [3];
        double[] ycords = new double [3];
        double height = height(length);

        xcords[0] = x- length/2;
        xcords[1] = x+ length/2;
        xcords[2] = x;

        ycords[0] = y + height;
        ycords[1] = y + height;
        ycords[2] = y;

        StdDraw.filledPolygon(xcords, ycords);
    }

    // Draws a Sierpinski triangle of order n, such that the largest filled 
    // triangle has bottom vertex (x, y) and sides of the specified length. 
    public static void sierpinski(int n, double x, double y, double length) {

        if(n == 0)
            return;

        sierpinski(n-1, x-length/2, y, length/2);
        sierpinski(n-1, x+length/2, y, length/2);
        sierpinski(n-1, x, y + height(length), length/2);
        filledTriangle(x,y,length);


    }

    // Takes an integer command-line argument n; 
    // draws the outline of an equilateral triangle (pointed upwards) of length 1; 
    // whose bottom-left vertex is (0, 0) and bottom-right vertex is (1, 0); and 
    // draws a Sierpinski triangle of order n that fits snugly inside the outline. 
    public static void main(String[] args) {

        StdDraw.line(0, 0, .5, height(1));
        StdDraw.line(.5, height(1), 1, 0);
        StdDraw.line(0, 0, 1, 0);

        int n = Integer.parseInt(args[0]);

        //sierpinski(n,0.5,0,1);
        sierpinski(n,0.5,0.0,0.5);

    }
}