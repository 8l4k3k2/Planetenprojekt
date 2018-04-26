package plantenbahnen;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


public class SpaceObject extends Circle {
    
    private double x, y;
    private double xNew, yNew;
    private double mass;
    private Vector velocityVector;
    private Vector velocityVectorNew;
    private String name;

    private boolean drawTrajectory;
    private int trajectorySize;
    private ArrayList<Circle> trajectory;
    private ArrayList<Double[]> pastCoordinates;
    private int trajectoryIndex;
    private int trajectoryIncrement;

    //arrow
    private Line line = new Line();
    private int counter=0;

    private GuiElements gui;
    private ArrayList<SpaceObject> universe;
    private boolean hasFocus;
    private final Object lock = new Object();
    
    SpaceObject(String name, double x, double y, double mass, Vector velocityVector, 
        int radius, int[] colour, GuiElements gui, ArrayList<SpaceObject> universe) {

        this.gui = gui;
        this.universe = universe;
        this.hasFocus = false;

        this.x = x;
        this.y = y;

        this.mass = mass;
        this.velocityVector = velocityVector;

        pastCoordinates = new ArrayList<>();
        
        this.name = name;
        this.setRadius(radius);
        this.setFill(Color.rgb(colour[0],colour[1],colour[2]));

        this.drawTrajectory = false;
        this.trajectory = new ArrayList<>();
        //this.trajectorySize = trajectorySize;
        this.trajectoryIndex = 0;
        this.trajectoryIncrement = 10000;

        Platform.runLater(() -> this.gui.getPaneDraw().getChildren().add(this.line));
        /*
        this.trajectory = new Line[this.trajectorySize];
        for (int i=0; i<this.trajectorySize; i++){
            this.trajectory[i] = new Line();
        }
        */
    }

    public GuiElements getGui() {
        return this.gui;
    }
    
    public ArrayList<SpaceObject> getUniverse() {
        return this.universe;
    }
    
    public void setHasFocus(boolean hf) {
        this.hasFocus = hf;
    }
    
    public boolean getHasFocus() {
        return this.hasFocus;
    }
    
    public void setVelocityVector(Vector velocityVector) {
        this.velocityVector = velocityVector;
    }

    public Vector getVelocityVector() {
        return this.velocityVector;
    }

    public void setTrajectorySize(int trajectorySize) {
        this.trajectorySize = trajectorySize;
    }

    public int getTrajectorySize() {
        return this.trajectorySize;
    }
    
    public void setDrawTrajectory(boolean drawTrajectory) {
        this.drawTrajectory = drawTrajectory;
    }
    
    public boolean getDrawTrajectory() {
        return this.drawTrajectory;
    }
    
    public void setColour(int[] colour) {
        this.setFill(Color.color(colour[0],colour[1],colour[2]));
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

    
    public void addPositionVectorToCoordinates(Vector v){
        this.xNew = this.x+v.x();
        this.yNew = this.y+v.y();
    }

    public void setNewCoordinates(){

        /*
        this.counter++;
        if (this.counter>=10000){
            drawArrows();
            this.counter=0;
        }
        */

        /*
        if ( this.trajectoryIndex == 0 ) {
            addLineToTrajectory();
        } else if ( this.trajectoryIndex == this.trajectoryIncrement ) {
            addLineToTrajectory();
            this.trajectoryIndex = 1;
        }
        this.trajectoryIndex++;
        */
        //addToPastCoordinates();

        this.trajectoryIndex++;
        if ( this.drawTrajectory && this.trajectoryIndex % this.trajectoryIncrement == 0 ) {
            Circle c = new Circle();
            c.setCenterX(this.x * this.gui.getScaleFactor() + this.gui.getPaneHalfWidth());
            c.setCenterY(this.y * this.gui.getScaleFactor() + this.gui.getPaneHalfHeight());
            c.setRadius(this.getRadius()/4.0);
            c.setFill(this.getFill());
            this.trajectory.add(0, c);
            // We need Platform.runlater() in order to modify the JavaFX GUI thread
            Platform.runLater(() -> this.gui.getPaneDraw().getChildren().add(c));
            while ( this.trajectory.size() > (int) this.gui.getSliderTrajectoryLength().getValue() ) {
                // some circle objects remain on the pane, so, make them at least
                // fully translucent
                this.trajectory.get(this.trajectory.size()-1).setOpacity(0.0);
                // delete from pane and trajectory
                Platform.runLater(() -> this.gui.getPaneDraw().getChildren().remove(this.trajectory.get(this.trajectory.size()-1)));
                this.trajectory.remove(this.trajectory.size()-1);
            }
        }
        
        this.x = this.xNew;
        this.y = this.yNew;
        this.velocityVector = this.velocityVectorNew;
    }

    
    public ArrayList<Circle> getTrajectory() {
        return this.trajectory;
    }
    
    public double getlLength(ArrayList<Double[]> l, Double[] newest) {
        l.add(newest);
        double length = 0;
        for (int i=0; i<l.size()-1; i++) {
            length += Math.sqrt(Math.pow((l.get(i)[0] - l.get(i+1)[0]), 2) + Math.pow((l.get(i)[1] - l.get(i+1)[1]), 2));
        }
        return length;
    }

    public void setCircleCoordinates() {
        this.setCenterX(this.x * this.gui.getScaleFactor() + this.gui.getPaneHalfWidth());
        this.setCenterY(this.y * this.gui.getScaleFactor() + this.gui.getPaneHalfHeight());
    }

    public void setCircleCoordinates(double x, double y) {
        this.setCenterX(x);
        this.setCenterY(y);
    }

    public void drawArrows(){


        //Platform.runLater(() -> this.gui.getPane().getChildren().remove(line));

        this.line.setStartX(this.x * this.gui.getScaleFactor() + this.gui.getPaneHalfWidth());
        this.line.setStartY(this.y * this.gui.getScaleFactor() + this.gui.getPaneHalfWidth());
        this.line.setEndX((this.x+this.velocityVector.x()*5)*this.gui.getScaleFactor() + this.gui.getPaneHalfWidth());
        this.line.setEndY((this.y+this.velocityVector.y()*5)*this.gui.getScaleFactor() + this.gui.getPaneHalfWidth());


    }
}