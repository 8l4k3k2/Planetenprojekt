package plantenbahnen;

import javafx.scene.shape.Circle;

public class SpaceObject extends Circle {
    
    private double x,y;
    private double mass;
    private Vector velocityVector;
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
}