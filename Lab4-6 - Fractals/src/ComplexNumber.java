

public class ComplexNumber {
    private double x;
    private double y;

    public ComplexNumber(){
        x = 0;
        y = 0;
    }

    public ComplexNumber(double x,double y){
    this.x = x;
    this.y = y;
    }

    public double GetX(){return x;}
    public double GetY(){return y;}

    public void SetX(double x){this.x = x;}
    public void SetY(double y){this.y = y;}
}
