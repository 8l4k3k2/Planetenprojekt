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

        if ( gui.getCheckBoxDrawTail().isSelected() ) {
            for (SpaceObject planet: universe){
                planet.setDrawTail(true);
            }
        }

        gui.getPaneDraw().setClip(gui.getRectangleClipForPane());;
        //System.out.println(gui.getRectangleClipForPane().getWidth() + " " + gui.getRectangleClipForPane().getHeight());
        
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
