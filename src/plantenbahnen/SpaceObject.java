package plantenbahnen;

import java.util.ArrayList;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class SpaceObject extends Circle {
    
    private double x,y;
    private double xNew,yNew;
    private double mass;
    private Vector velocityVector;
    private Vector velocityVectorNew;
    private int size;
    private int thickness;
    private int[] colour;
    private String name;
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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getXNew() {
        return xNew;
    }

    public double getYNew() {
        return yNew;
    }

    public void setX(double value){
        this.x=value;
    }

    public void setY(double value){
        this.y=value;
    }

    public void setXNew(double xNew) {
        this.xNew = xNew;
    }

    public void setYNew(double yNew) {
        this.yNew = yNew;
    }

    public Vector getVelocityVectorNew() {
        return velocityVectorNew;
    }

    public void setVelocityVectorNew(Vector velocityVectorNew) {
        this.velocityVectorNew = velocityVectorNew;
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

    
    public ArrayList<Line> getTail() {
        return this.tail;
    }
    
    public void addPositionVectorToCoordinates(Vector v){
        this.xNew=this.x+v.x();
        this.yNew=this.y+v.y();
    }

    public void setNewCoordinates(){
        addLineToTail();
        this.x=this.xNew;
        this.y=this.yNew;
        this.velocityVector=this.velocityVectorNew;
    }


    private void addLineToTail(){
        Line line = new Line();
        line.setStartX(this.x);
        line.setStartY(this.y);
        line.setEndX(this.xNew);
        line.setEndY(this.yNew);

        this.tail.add(line);

        if ( this.tail.size() > this.tailSize ) {
            this.tail.remove(this.tail.size()-1);
        }
    }

    private double getTailLength(){
        double length=0;
        for (Line l:this.tail){
            Vector a = new Vector(l.getStartX(),l.getStartY());
            Vector b = new Vector(l.getEndX(),l.getEndY());
            double norm = a.norm(b);
            length+=norm;
        }
        return length;
    }


}