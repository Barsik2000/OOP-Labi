import java.awt.geom.Rectangle2D;



public class Tricorn extends FractalGenerator{

    public void getInitialRange(Rectangle2D.Double range){
       range.x = -2;
       range.y = -1.5;

       range.width = 3;
       range.height = 3;
    }

    public static final int MAX_ITERATIONS = 2000;

    public int numIterations(double x, double y){
        ComplexNumber c = new ComplexNumber(x,-y);
        ComplexNumber z = new ComplexNumber();

       int iterations = 0;

        //(x^2 + y^2)
      while (z.GetX() * z.GetX() + z.GetY() * z.GetY() <= 4 &&
                iterations < MAX_ITERATIONS){

           double tz = z.GetX(); //Saving for z.GetY

          //zX^2 + cX - zY^2
            z.SetX(z.GetX() * z.GetX() + c.GetX() - z.GetY() * z.GetY());
            //-2*zX*zY+cY
            z.SetY(-2 * tz * z.GetY() + c.GetY());

            iterations++;
        }
        if (iterations >= MAX_ITERATIONS) return -1;
        return iterations;
    }

    @Override
    public String toString() {
        return "Tricorn";
    }
}
