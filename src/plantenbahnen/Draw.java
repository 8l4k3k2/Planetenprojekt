/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plantenbahnen;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author stefan
 */
public class Draw {

    public static void draw(GuiElements gui, ArrayList<SpaceObject> universe, Berechnungen startCalc) {
        
        gui.getPaneDraw().getChildren().clear();
        for (SpaceObject planet: universe){
            gui.getPaneDraw().getChildren().add(planet);
        }

        gui.getSliderSimSpeed().setMin(0.00001);
        gui.getSliderSimSpeed().setMax(0.0001);
        gui.getSliderSimSpeed().setValue(0.00002);
        startCalc.setDeltaTime(gui.getSliderSimSpeed().getValue());
        
        gui.getSliderSimSpeed().valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                startCalc.setDeltaTime(gui.getSliderSimSpeed().getValue());
            }
        });

        int maxInteger = 1000000000;
        gui.getTextFieldTailLength().textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                //startCalc.setDeltaTime(slider_sim_speed.getValue());
                for (SpaceObject so : universe) {
                    try {
                        so.setTailSize(Integer.parseInt(gui.getTextFieldTailLength().getText()));
                    } catch (NumberFormatException n) {
                        // Entered integer is too big, so, set the maximum manually
                        so.setTailSize(maxInteger);
                        gui.getTextFieldTailLength().setText(Integer.toString(maxInteger));
                    }
                }
            }
        });
        
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (SpaceObject so : universe) {
                    so.setCircleCoordinates();

                    //System.out.println(so.getName() + "  " +so.getCenterX() + "  " + so.getCenterY());
                }
            }
        };

        animation.start();
    }    
}
