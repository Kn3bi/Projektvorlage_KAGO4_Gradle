package KAGO_framework.model;

/**
 * Object representing a three-dimensional Vector
 */
public final class Vector {

    private double x, y, z;

    /**
     * creates a new Vector with coordinate values of zero
     */
    public Vector(){
        x = 0;
        y = 0;
        z = 0;
    }

    /**
     * creates a new Vector with the given coordinates. The Z-Coordinate will be zero
     */
    public Vector(double x, double y){
        this.x = x;
        this.y = y;
        z = 0;
    }

    /**
     * creates a new Vector with the given coordinates
     */
    public Vector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * added the coordinates of the given vector to the vector
     */
    public void add(Vector vector){
        x += vector.x;
        y += vector.y;
        z += vector.y;
    }

    /**
     * multiplies the coordinates of the vector by the given factor
     */
    public void multiply(double d){
        x *= d;
        y *= d;
        z *= d;
    }

    /**
     * returns the absolute value of the vector
     */
    public double abs(){
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * returns an array with the length of three with the coordinates
     */
    public double[] toArray(){
        return new double[]{x, y, z};
    }

    /**
     * returns a String representing the value of the vector
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + (z == 0 ? ")" : "," + z + ")");
    }

    /**
     * if the given object is an instance of the Vector class the method returns whether the coordinates are equal,
     * else the return value will always be false
     */
    @Override
    public boolean equals(Object o){
        return o instanceof Vector && x == ((Vector) o).x && y == ((Vector) o).y && z == ((Vector) o).z;
    }

    /**
     * returns the x-coordinate of the vector
     */
    public double x(){
        return x;
    }

    /**
     * returns the y-coordinate of the vector
     */
    public double y(){
        return y;
    }

    /**
     * returns the z-coordinate of the vector
     */
    public double z(){
        return z;
    }

    /**
     * sets the x-coordinate of the vector to the given value
     */
    public void setX(double x){
        this.x = x;
    }

    /**
     * sets the y-coordinate of the vector to the given value
     */
    public void setY(double y){
        this.y = y;
    }

    /**
     * sets the z-coordinate of the vector to the given value
     */
    public void setZ(double z){
        this.z = z;
    }
}
