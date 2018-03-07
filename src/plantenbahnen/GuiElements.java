package plantenbahnen;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class GuiElements {

    private AnchorPane anchorPane;
    private Pane paneDraw;
    private Pane paneControls;
    private double scaleFactor;
    private double paneHalfWidth;
    private double paneHalfHeight;
    private Slider slider_sim_speed;
    private CheckBox checkBox_drawTail;
    private Slider slider_tailLength;
    private int tailLength;
    private ChoiceBox choiceBox_scenario;
    private Rectangle rectangleClipForPane;
    
    public GuiElements (AnchorPane anchorPane, Pane paneDraw, Pane paneControls, double scaleFactor,
            Slider slider_sim_speed, CheckBox checkBox_drawTail, Slider slider_tailLength, 
            int tailLength, ChoiceBox choiceBox_scenario, Rectangle rectangleClipForPane) {
        this.anchorPane = anchorPane;
        this.paneDraw = paneDraw;
        this.paneControls = paneControls;
        this.scaleFactor = scaleFactor;
        this.paneHalfWidth = paneDraw.getPrefWidth() / 2.0;
        this.paneHalfHeight = paneDraw.getPrefHeight() / 2.0;
        this.slider_sim_speed = slider_sim_speed;
        this.checkBox_drawTail = checkBox_drawTail;
        this.slider_tailLength = slider_tailLength;
        this.tailLength = tailLength;
        this.choiceBox_scenario = choiceBox_scenario;
        this.rectangleClipForPane = rectangleClipForPane;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public Pane getPaneDraw() {
        return this.paneDraw;
    }
    
    public double getPaneHalfWidth() {
        return paneHalfWidth;
    }

    public double getPaneHalfHeight() {
        return paneHalfHeight;
    }
    
    public int getTailLength() {
        return this.tailLength;
    }
    
    public Slider getSliderSimSpeed() {
        return this.slider_sim_speed;
    }

    public Slider getSliderTailLength() {
        return this.slider_tailLength;
    }
    
    public CheckBox getCheckBoxDrawTail() {
        return this.checkBox_drawTail;
    }
    
    public Rectangle getRectangleClipForPane() {
        return this.rectangleClipForPane;
    }
}
