package plantenbahnen;

public class SpaceObject {
    private double x,y;
    private double xnew,ynew;
    private double mass;
    private Vector velocityVector;
    private Vector velocityVectornew;
    private int size;
    private int thickness;
    private int[] colour;

    SpaceObject(double x,double y, double mass, Vector velocityVector, int size, int thickness, int[] colour) {
        this.x=x;
        this.y=y;
        this.mass = mass;
        this.velocityVector = velocityVector;

        this.size = size; //10;
        this.thickness = thickness ;//0;
        this.colour = colour; //new int[]{0, 0, 255};
    }

    public void setVelocityVector(Vector velocityVector) {
        this.velocityVector = velocityVector;
    }

    public Vector getVelocityVector() {
        return this.velocityVector;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public int getThickness() {
        return thickness;
    }

    public void setColour(int[] colour) {
        this.colour = colour;
    }

    public int[] getColour() {
        return colour;
    }

    public double getx(){
        return this.x;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getXnew() {
        return xnew;
    }

    public double getYnew() {
        return ynew;
    }

    public Vector getVelocityVectornew() {
        return velocityVectornew;
    }

    public void setXnew(double xnew) {

        this.xnew = xnew;
    }

    public void setYnew(double ynew) {
        this.ynew = ynew;
    }

    public void setVelocityVectornew(Vector velocityVectornew) {
        this.velocityVectornew = velocityVectornew;
    }

    public void sety(double value){
        this.y=value;
    }

    public double getMass() {
        return this.mass;
    }

    public Vector getPositionVector(){
        return new Vector(this.x,this.y);
    }
}