package plantenbahnen;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Pair;

public class SpaceObject extends Circle {
    
    private double x,y;
    private double x1,y1;
    private double mass;
    private Vector velocityVector;
    private Vector velocityVector1;
    private int size;
    private int thickness;
    private int[] colour;
    private String name;
    //private ArrayList<double[]> tail;  // Schweif
    private ArrayList<Line> tail;
    private int tailSize;

    SpaceObject(String name, double x, double y, double mass, Vector velocityVector, 
        int size, int thickness, int[] colour, int tailSize) {
        
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.velocityVector = velocityVector;

        this.name = name;
        this.size = size; //10;
        this.thickness = thickness ;//0;
        this.colour = colour; //new int[]{0, 0, 255};
        //this.tail = new Line[tailSize];
        this.tail = new ArrayList<>();
        this.tailSize = tailSize;
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
    
    public Vector getPositionVector(){
        return new Vector(this.x,this.y);
    }
    
    public void addToTail(double x, double y) {
        double[] pair = {x, y};
        double[] startValues = {0.0, 0.0};
        Line line = new Line();
        
        try {
            startValues[0] = this.tail.get(0).getEndX();
            startValues[1] = this.tail.get(0).getEndY();
        } catch (IndexOutOfBoundsException e) {
            startValues[0] = pair[0];
            startValues[1] = pair[1];
        }
        line.setStartX(startValues[0]);
        line.setStartY(startValues[1]);
        line.setEndX(pair[0]);
        line.setEndY(pair[1]);
        
        this.tail.add(line);
        
        if ( this.tail.size() > this.tailSize ) {
            this.tail.remove(this.tail.size()-1);
        }
    }
    
    public ArrayList<Line> getTail() {
        return this.tail;
    }
}