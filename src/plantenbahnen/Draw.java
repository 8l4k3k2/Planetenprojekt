package plantenbahnen;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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

        gui.getSliderTailLength().setMin(100.0);
        gui.getSliderTailLength().setMax(10000000.0);
        gui.getSliderTailLength().setValue(100.0);
        gui.getSliderTailLength().valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                for (SpaceObject so : universe) {
                    so.setTailSize((int)gui.getSliderTailLength().getValue());
                }
            }
        });

        if ( gui.getCheckBoxDrawTail().isSelected() ) {
            for (SpaceObject planet: universe){
                planet.setDrawTail(true);
            }
        }
        
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (SpaceObject so : universe) {
                    so.setCircleCoordinates();
                }
            }
        };
        animation.start();
    }    
}
