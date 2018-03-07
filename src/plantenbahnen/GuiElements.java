package plantenbahnen;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class GuiElements {

    private AnchorPane anchorPane;
    private Pane paneDraw;
    private Pane paneControls;
    private double scaleFactor;
    private double paneHalfWidth;
    private double paneHalfHeight;
    private Slider slider_sim_speed;
    private RadioButton radioButton_drawTail;
    private TextField textField_tailLength;
    private int tailLength;
    private ChoiceBox choiceBox_scenario;

    
    public GuiElements (AnchorPane anchorPane, Pane paneDraw, Pane paneControls, double scaleFactor,
            Slider slider_sim_speed, RadioButton radioButton_drawTail, TextField textField_tailLength, 
            int tailLength, ChoiceBox choiceBox_scenario) {
        this.anchorPane = anchorPane;
        this.paneDraw = paneDraw;
        this.paneControls = paneControls;
        this.scaleFactor = scaleFactor;
        this.paneHalfWidth = paneDraw.getPrefWidth() / 2.0;
        this.paneHalfHeight = paneDraw.getPrefHeight() / 2.0;
        this.slider_sim_speed = slider_sim_speed;
        this.radioButton_drawTail = radioButton_drawTail;
        this.textField_tailLength = textField_tailLength;
        this.tailLength = tailLength;
        this.textField_tailLength.setText(Integer.toString(tailLength));
        this.choiceBox_scenario = choiceBox_scenario;
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
    
    public TextField getTextFieldTailLength() {
        return this.textField_tailLength;
    }
    
    public Slider getSliderSimSpeed() {
        return this.slider_sim_speed;
    }
}
