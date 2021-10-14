import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args){

        Point3d point_1 = new Point3d();
        Point3d point_2 = new Point3d();
        Point3d point_3 = new Point3d();

        Scanner scan = new Scanner(System.in);

        System.out.println("Введите координаты первой точки: ");
        point_1.setX(Double.parseDouble(scan.nextLine()));
        point_1.setY(Double.parseDouble(scan.nextLine()));
        point_1.setZ(Double.parseDouble(scan.nextLine()));

        System.out.println("Введите координаты второй точки: ");
        point_2.setX(Double.parseDouble(scan.nextLine()));
        point_2.setY(Double.parseDouble(scan.nextLine()));
        point_2.setZ(Double.parseDouble(scan.nextLine()));

        System.out.println("Введите координаты третьей точки: ");
        point_3.setX(Double.parseDouble(scan.nextLine()));
        point_3.setY(Double.parseDouble(scan.nextLine()));
        point_3.setZ(Double.parseDouble(scan.nextLine()));

        boolean f = false;

        if(point_1.EqualsTo(point_2)) {

            if(point_1.EqualsTo(point_3)){

                System.out.println("Все точки одиаковы.");

            } else{

                System.out.println("Точки 1 и 2 одинаковы.");

            }

            f = true;
        }
        else{
            if(point_1.EqualsTo(point_3)) {

            System.out.println("Точки 1 и 3 одинаковы.");
            f = true;

            }
            else  if(point_2.EqualsTo(point_3)) {

            System.out.println("Точки 2 и 3 одинаковы.");
            f = true;

            }
        }

        if(f){
            System.out.println("\nИмеются равные точки, прощадь треугольника вычислить невозможно.");
            System.exit(0);
        }


        System.out.println("Площадь треугольника = " + ComputeArea(point_1,point_2,point_3));
    }

    public static double ComputeArea(Point3d first_point, Point3d second_point, Point3d third_point){
    double a = first_point.DistanceTo(second_point),
           b = first_point.DistanceTo(third_point),
           c = second_point.DistanceTo(third_point);

    double HalfP = (a + b + c)/2;

    return Math.sqrt(HalfP*(HalfP - a)*(HalfP - b)*(HalfP - c));

    }
}
