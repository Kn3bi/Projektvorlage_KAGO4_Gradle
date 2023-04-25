package KAGO_framework.model;

public final class Vector {

    private double x, y, z;

    public Vector(){
        x = 0;
        y = 0;
        z = 0;
    }

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
        z = 0;
    }

    public Vector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(Vector vector){
        x += vector.x;
        y += vector.y;
        z += vector.y;
    }

    public void multiply(double d){
        x *= d;
        y *= d;
        z *= d;
    }

    public double[] toArray(){
        return new double[]{x, y, z};
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + (z == 0 ? ")" : "," + z + ")");
    }

    @Override
    public boolean equals(Object o){
        return o instanceof Vector && x == ((Vector) o).x && y == ((Vector) o).y && z == ((Vector) o).z;
    }

    public double x(){
        return x;
    }

    public double y(){
        return y;
    }

    public double z(){
        return z;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setZ(double z){
        this.z = z;
    }
}
