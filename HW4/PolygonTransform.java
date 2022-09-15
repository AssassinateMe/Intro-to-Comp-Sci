package HW4;

public class PolygonTransform {

    // Returns a new array that is an exact copy of the given array. 
    // The given array is not mutated.
    public static double[] copy(double[] array) {
        double[] copy = new double [array.length];
        for(int i = 0;i < array.length; i++)
        {
            copy[i] = array[i];
        }
        return copy;
    }

    // Scales the given polygon by the factor alpha. 
    public static void scale(double[] x, double[] y, double alpha) {
        for(int i = 0; i < x.length; i++)
        {
            x[i] = x[i] * alpha;
            y[i] = y[i] * alpha;
        }
    }

    // Translates the given polygon by (dx, dy). 
    public static void translate(double[] x, double[] y, double dx, double dy) {
        for(int i = 0; i < x.length; i++)
        {
            x[i] = x[i] + dx;
            y[i] = y[i] + dy;
        }
    }

    /*
    *   x‘i = xi cos θ – yi sin θ
    *   y‘i = yi cos θ + xi sin θ
    */
    // Rotates the given polygon theta degrees counterclockwise, about the origin. 
    public static void rotate(double[] x, double[] y, double theta) {
        for(int i = 0; i < x.length; i++)
        {
            double xhold = x[i];
            double yhold = y[i];
            x[i] = xhold * (double)Math.cos(Math.toRadians(theta)) - yhold * (double)Math.sin(Math.toRadians(theta));
            y[i] = yhold * (double)Math.cos(Math.toRadians(theta)) + xhold * (double)Math.sin(Math.toRadians(theta));
        }
    }

    public static void main(String[] args) {

        // WRITE YOUR CODE HERE
        // Dont need anything here
    }
}
