package plantenbahnen;

public class SpaceObject {
    private double x,y;
    private double x1,y1;
    private double mass;
    private Vector velocityVector;
    private Vector velocityVector1;
    private int size;
    private int thickness;
    private int[] colour;
    private String name;

    SpaceObject(String name, double x, double y, double mass, Vector velocityVector, int size, int thickness, int[] colour) {
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.velocityVector = velocityVector;

        this.name = name;
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

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public Vector getVelocityVector1() {
        return velocityVector1;
    }

    public void setXnew(double x1) {

        this.x1 = x1;
    }

    public void setYnew(double y1) {
        this.y1 = y1;
    }

    public void setVelocityVector1(Vector velocityVector1) {
        this.velocityVector1 = velocityVector1;
    }

    public void setx(double value){
        this.x=value;
    }
    public double gety(){
        return this.y;
    }

    public void sety(double value){
        this.y=value;
    }

    public double getMass() {
        return this.mass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Vector getPositionVector(){
        return new Vector(this.x,this.y);
    }
}