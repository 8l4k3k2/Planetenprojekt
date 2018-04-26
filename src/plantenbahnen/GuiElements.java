package plantenbahnen;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.codefx.libfx.listener.handle.ListenerHandle;

public class GuiElements {

    private AnchorPane anchorPane;
    private Pane paneDraw;
    private Pane paneControls;
    private Separator panesSeparator;
    private double scaleFactor;
    private double paneHalfWidth;
    private double paneHalfHeight;
    private Slider slider_sim_speed;
    private CheckBox checkBox_drawTrajectory;
    private Slider slider_trajectoryLength;
    private int trajectoryLength;
    private ChoiceBox choiceBox_scenario;
    private Rectangle rectangleClipForPane;
    private int simulationStatus;
    private ListenerHandle listenerHandleCenterX;
    private ListenerHandle listenerHandleCenterY;
    
    public GuiElements (AnchorPane anchorPane, Pane paneDraw, Pane paneControls, 
            Separator panesSeparator, double scaleFactor,
            Slider slider_sim_speed, CheckBox checkBox_drawTrajectory, Slider slider_trajectoryLength, 
            int trajectoryLength, ChoiceBox choiceBox_scenario, Rectangle rectangleClipForPane) {
        this.anchorPane = anchorPane;
        this.paneDraw = paneDraw;
        this.paneControls = paneControls;
        this.panesSeparator = panesSeparator;
        this.scaleFactor = scaleFactor;
        this.paneHalfWidth = paneDraw.getPrefWidth() / 2.0;
        this.paneHalfHeight = paneDraw.getPrefHeight() / 2.0;
        this.slider_sim_speed = slider_sim_speed;
        this.checkBox_drawTrajectory = checkBox_drawTrajectory;
        this.slider_trajectoryLength = slider_trajectoryLength;
        this.trajectoryLength = trajectoryLength;
        this.choiceBox_scenario = choiceBox_scenario;
        this.rectangleClipForPane = rectangleClipForPane;
        this.simulationStatus = 0;
    }

    public double getScaleFactor() {
        return this.scaleFactor;
    }

    public void setScaleFactor(double sf) {
        this.scaleFactor = sf;
    }

    public int getSimulationStatus() {
        return this.simulationStatus;
    }

    public void setSimulationStatus(int status) {
        this.simulationStatus = status;
    }

    public Pane getPaneDraw() {
        return this.paneDraw;
    }
    
    public double getPaneHalfWidth() {
        return paneHalfWidth;
    }

    public void setPaneHalfWidth() {
        this.paneHalfWidth = this.paneDraw.getPrefWidth() / 2.0;
    }

    public double getPaneHalfHeight() {
        return paneHalfHeight;
    }

    public void setPaneHalfHeight() {
        this.paneHalfHeight = this.paneDraw.getPrefHeight() / 2.0;
    }
    
    public int getTrajectoryLength() {
        return this.trajectoryLength;
    }
    
    public Slider getSliderSimSpeed() {
        return this.slider_sim_speed;
    }

    public Slider getSliderTrajectoryLength() {
        return this.slider_trajectoryLength;
    }
    
    public CheckBox getCheckBoxDrawTrajectory() {
        return this.checkBox_drawTrajectory;
    }
    
    public Rectangle getRectangleClipForPane() {
        return this.rectangleClipForPane;
    }
    
    public void setListenerHandleCenterX(ListenerHandle lh) {
        this.listenerHandleCenterX = lh;
    }
    
    public ListenerHandle getListenerHandleCenterX() {
        return this.listenerHandleCenterX;
    }

    public void setListenerHandleCenterY(ListenerHandle lh) {
        this.listenerHandleCenterY = lh;
    }
    
    public ListenerHandle getListenerHandleCenterY() {
        return this.listenerHandleCenterY;
    }
}
