package plantenbahnen;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Draw {

    public static void drawPlanets(ArrayList<SpaceObject> universe, Pane pane) {
        //for (Node child: pane.getChildren()) {
        //    System.out.println(child);
        //}
        
        // Clear objects first
        /*
        for (SpaceObject so: universe) {
            if ( pane.getChildren().contains(so) ) {
                pane.getChildren().remove(so);
                //System.out.println("Removing ... " + so.getName());
            }
        }
        */
        pane.getChildren().clear();

        double paneHalfWidth = pane.getPrefWidth() / 2.0;
        double paneHalfHeight = pane.getPrefHeight() / 2.0;
        double scaleFactor = 0.1;
        int nLines, i;
        double opacity;
        
        for (SpaceObject so: universe) {
            
            // Draw the tail
            nLines = so.getTail().size();
            i = 0;
            for (Line line: so.getTail()) {
                
                opacity = 100.0 * ( (double)(nLines - i) / (double)nLines );
                line.opacityProperty().set(opacity);
                line.setStrokeWidth(3.0);
                pane.getChildren().add(line);
                
                i++;
            }

            // Draw the planets
            so.setCenterX(so.getX() * scaleFactor + paneHalfWidth);
            so.setCenterY(so.getY() * scaleFactor + paneHalfHeight);
            //System.out.println("x=" + so.getCenterX() + "   y=" + so.getCenterY());
            so.setRadius(7.0);
            //so.setColour(Color.BLACK);
            pane.getChildren().add(so);
        }
    }
}