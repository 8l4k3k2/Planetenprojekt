package plantenbahnen;

import javafx.scene.layout.Pane;

public class GuiElements {

    private Pane pane;
    private double scaleFactor;
    private double paneHalfWidth;
    private double paneHalfHeight;
    
    public GuiElements (Pane pane, double scaleFactor) {
        this.pane = pane;
        this.scaleFactor = scaleFactor;
        this.paneHalfWidth = pane.getPrefWidth() / 2.0;
        this.paneHalfHeight = pane.getPrefHeight() / 2.0;
    }


    public double getScaleFactor() {
        return scaleFactor;
    }

    public double getPaneHalfWidth() {
        return paneHalfWidth;
    }

    public double getPaneHalfHeight() {
        return paneHalfHeight;
    }



}
