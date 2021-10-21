import java.awt.geom.Rectangle2D;



public class BurningShip extends FractalGenerator{

    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2.0;
        range.y = -2.5;

        range.width =  4;
        range.height = 4;
    }


    public static final int MAX_ITERATIONS = 2000;

    public int numIterations(double x, double y) {
        ComplexNumber c = new ComplexNumber(x,y);
        ComplexNumber z = new ComplexNumber();

        int iterations = 0;

        //(x^2 + y^2)
        while (z.GetX() * z.GetX() + z.GetY() * z.GetY() <= 4 &&
                iterations < MAX_ITERATIONS){

            double tz = z.GetX(); //Saving for z.GetY

            //zX^2+cX-zY^2
            z.SetX(z.GetX() * z.GetX() + c.GetX() - z.GetY() * z.GetY());
            //|2*zY*zX|+cY
            z.SetY(Math.abs(2 * z.GetY() * tz) + c.GetY());

            iterations++;
        }
        if (iterations >= MAX_ITERATIONS) return -1;
        return iterations;
    }


    public String toString() {
        return "Burning ship";
    }
}
