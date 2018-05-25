package plantenbahnen;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;

public class Draw {

    public static void draw(GuiElements gui, ArrayList<SpaceObject> universe) {
        
        MouseGestures mg = new MouseGestures();
        
        gui.getPaneDraw().getChildren().clear();
        for (SpaceObject planet: universe){
            mg.makeDraggable(planet);
            MyMouseEvents.nodeMouseEvents(planet, universe, gui.getPaneDraw(), gui);
            planet.setCircleCoordinates();
            gui.getPaneDraw().getChildren().add(planet);
        }

        if ( gui.getCheckBoxDrawTrajectory().isSelected() ) {
            for (SpaceObject planet: universe){
                planet.setDrawTrajectory(true);
            }
        }

        gui.getPaneDraw().setClip(gui.getRectangleClipForPane());;
        
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
