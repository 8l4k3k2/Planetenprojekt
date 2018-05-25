package plantenbahnen;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
        this.trajectoryIndex = 0;
        this.trajectoryIncrement = 10000;
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
        return this.x;
    }

    public double getY() {
        return this.y;
    }
    
    public double getXNew() {
        return xNew;
    }

    public double getYNew() {
        return yNew;
    }
    
    public void setX(double value){
        this.x = value;
    }

    public void setY(double value){
        this.y = value;
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
        return new Vector(this.x, this.y);
    }

    
    public void addPositionVectorToCoordinates(Vector v){
        this.xNew = this.x + v.getX();
        this.yNew = this.y + v.getY();
    }

    public void setNewCoordinates(){
        this.trajectoryIndex++;
        if ( this.drawTrajectory && this.trajectoryIndex % this.trajectoryIncrement == 0 ) {
            Circle c = new Circle();
            c.setCenterX(this.x * this.gui.getScaleFactor() + this.gui.getPaneHalfWidth());
            c.setCenterY(this.y * this.gui.getScaleFactor() + this.gui.getPaneHalfHeight());
            c.setRadius(this.getRadius()/4.0);
            c.setFill(this.getFill());
            this.trajectory.add(0, c);
            // We need Platform.runlater() in order to modify the JavaFX GUI thread
            /*Platform.runLater(() -> this.gui.getPaneDraw().getChildren().add(c));
            while ( this.trajectory.size() > (int) this.gui.getSliderTrajectoryLength().getValue() ) {
                // some circle objects remain on the pane, so, make them at least
                // fully translucent
                this.trajectory.get(this.trajectory.size()-1).setOpacity(0.0);
                // delete from pane and trajectory
                Platform.runLater(() -> this.gui.getPaneDraw().getChildren().remove(this.trajectory.get(this.trajectory.size()-1)));
                this.trajectory.remove(this.trajectory.size()-1);
            }*/
            Thread thread = new Thread() {
                @Override public void run() {
                    Platform.runLater(() -> gui.getPaneDraw().getChildren().add(c));
                    while ( trajectory.size() > (int) gui.getSliderTrajectoryLength().getValue() ) {
                        // some circle objects remain on the pane, so, make them at least
                        // fully translucent
                        trajectory.get(trajectory.size()-1).setOpacity(0.0);
                        // delete from pane and trajectory
                        Platform.runLater(() -> gui.getPaneDraw().getChildren().remove(trajectory.get(trajectory.size()-1)));
                        trajectory.remove(trajectory.size()-1);
                    }
                }
            };
            thread.start();
        }
        this.x = this.xNew;
        this.y = this.yNew;
        this.velocityVector = this.velocityVectorNew;
    }

    
    public ArrayList<Circle> getTrajectory() {
        return this.trajectory;
    }
    
    public void setCircleCoordinates() {
        this.setCenterX(this.x * this.gui.getScaleFactor() + this.gui.getPaneHalfWidth());
        this.setCenterY(this.y * this.gui.getScaleFactor() + this.gui.getPaneHalfHeight());
    }

    public void setCircleCoordinates(double x, double y) {
        this.setCenterX(x);
        this.setCenterY(y);
    }
}