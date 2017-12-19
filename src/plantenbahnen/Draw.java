package plantenbahnen;

/*
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Draw {

    public static void drawPlanets(ArrayList<SpaceObject> universe, Pane pane) {
        for (Node child: pane.getChildren()) {
            System.out.println(child);
        }
        
        // Clear objects first
        for (SpaceObject so: universe) {
            if ( pane.getChildren().contains(so) ) {
                pane.getChildren().remove(so);
                //System.out.println("Removing ... " + so.getName());
            }
        }

        double paneHalfWidth = pane.getPrefWidth() / 2.0;
        double paneHalfHeight = pane.getPrefHeight() / 2.0;
        double scaleFactor = 0.1;

        for (SpaceObject so: universe) {
            so.setCenterX(so.getx() * scaleFactor + paneHalfWidth);
            so.setCenterY(so.gety() * scaleFactor + paneHalfHeight);
            //System.out.println("x=" + so.getCenterX() + "   y=" + so.getCenterY());
            so.setRadius(2);
            //so.setColour(Color.BLACK);
            pane.getChildren().add(so);
        }
    }
}
*/