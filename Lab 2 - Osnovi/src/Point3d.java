public class Point3d {

        private double xCoord;
        private double yCoord;
        private double zCoord;

    public Point3d( double x, double y, double z){
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    public Point3d() {
        this(0, 0, 0);
    }

        /**
         * Возвращение координаты X
         **/
        public double getX () {
        return xCoord;
    }

        /**
         * Возвращение координаты Y
         **/
        public double getY () {
        return yCoord;
    }

        /**
         * Возвращение координаты Z
         **/
        public double getZ () {
        return zCoord;
    }


        /**
         * Установка значения координаты X.
         **/
        public void setX ( double val){
        xCoord = val;
    }

        /**
         * Установка значения координаты Y.
         **/
        public void setY ( double val){
        yCoord = val;
    }

        /**
         * Установка значения координаты Z.
         **/
        public void setZ ( double val){
        zCoord = val;
    }

    public boolean EqualsTo(Point3d other_point){
    return(xCoord == other_point.getX() && yCoord == other_point.getY() && zCoord == other_point.getZ());
    }

    /** Корень из суммы квадратов разности координат с точностью до 2-х знаков после запятой **/
    public double DistanceTo (Point3d other_point) {
        return Math.floor(100 * Math.sqrt((xCoord - other_point.getX())*(xCoord - other_point.getX()) +
                (yCoord - other_point.getY())*(yCoord - other_point.getY()) +
                (zCoord - other_point.getZ())*(zCoord - other_point.getZ())))/100;
    }
}
