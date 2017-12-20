package plantenbahnen;

import java.util.ArrayList;
import javafx.geometry.Point2D;
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
    //private ArrayList<Circle> tail;
    //private ArrayList<Point2D> tail;
    private int tailSize;
    private int tailIndex;
    private int tailIncrement;

    SpaceObject(String name, double x, double y, double mass, Vector velocityVector, 
        int size, int[] colour, int tailSize, GuiElements gui) {
        
        //this.x = x;
        //this.y = y;
        this.setCenterX(x);
        this.setCenterY(y);
        this.mass = mass;
        this.velocityVector = velocityVector;

        this.name = name;
        //this.size = size; //10;
        setRadius(size);
        //this.thickness = thickness ;//0;
        this.colour = colour; //new int[]{0, 0, 255};
        this.tail = new ArrayList<>();
        this.tailSize = tailSize;
        this.tailIndex = 0;
        this.tailIncrement = 100000;
        
        this.pane = gui.getPane();
        this.scaleFactor = gui.getScaleFactor();
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
        //public ArrayList<Circle> getTail() {
        //public ArrayList<Point2D> getTail() {
        return this.tail;
    }
    
    public void addPositionVectorToCoordinates(Vector v){
        this.xNew=this.x+v.x();
        this.yNew=this.y+v.y();
    }

    public void setNewCoordinates(){
        if ( this.tailIndex == 0 ) {
            addLineToTail();
        } else if ( this.tailIndex == this.tailIncrement ) {
            addLineToTail();
            this.tailIndex = 1;
        }
        this.tailIndex++;
        
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
        this.tail.add(0, line);
        System.out.println("Added new line: ");
        System.out.println(line);
        System.out.println(this.tail);
        
        /*
        Circle circle = new Circle();
        circle.setCenterX(this.xNew);
        circle.setCenterY(this.yNew);
        //this.tail.add(0, circle);
        */
        
        //Point2D point = new Point2D(this.x, this.y);
        //this.tail.add(point);

        if ( this.tail.size() > this.tailSize ) {
            this.tail.remove(this.tail.get(this.tail.size()-1));
        }
    }
    
    private void setCircleProperties(){
        double scaleFactor=0.1, paneHalfWidth=0,paneHalfHeight=0;
        
        this.setCenterX(this.getX() * scaleFactor + paneHalfWidth);
        this.setCenterY(this.getY() * scaleFactor + paneHalfHeight);
    }
    /*
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
    */

}