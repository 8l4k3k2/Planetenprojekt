package plantenbahnen;

import java.util.ArrayList;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Draw {

    public static void drawPlanets(ArrayList<SpaceObject> universe, Pane pane, RadioButton rb_drawTail) {
        //for (Node child: pane.getChildren()) {
        //    System.out.println(child);
        //}
        
        // Clear objects first
        /*
        System.out.println("Children: " + pane.getChildren());
        for (Object o: pane.getChildren() ) {
            if ( o.getClass().isInstance(Polyline.class) ) {
                pane.getChildren().remove(o);
            }
        }
        */
        /*for (SpaceObject so: universe) {
            
            if ( pane.getChildren().contains(so) ) {
                
                pane.getChildren().remove(so);
                System.out.println("Removing ... " + so.getName());
            }
        }
        */
        //pane.getChildren().clear();
        /*

        double paneHalfWidth = pane.getPrefWidth() / 2.0;
        double paneHalfHeight = pane.getPrefHeight() / 2.0;
        double scaleFactor = 0.1;
        int nLines, i;
        double opacity;


        for (SpaceObject so: universe) {
            
            // Draw the tail
            
            if ( rb_drawTail.isSelected() & so.getTail().size() > 0 ) {
                nLines = so.getTail().size();
                i = 0;
                System.out.println(so.getTail());
                System.out.println("Size of tail: " + so.getTail().size());
                for (Line line: so.getTail()) {
                    //Line line = so.getTail().get(5);
                    opacity = 80.0 * ( (double)(nLines - i) / (double)nLines );
                    if ( ! pane.getChildren().contains(line) ) {
                        line.setStartX(line.getStartX() * scaleFactor + paneHalfWidth);
                        line.setStartY(line.getStartY() * scaleFactor + paneHalfHeight);
                        line.setEndX(line.getEndX() * scaleFactor + paneHalfWidth);
                        line.setEndY(line.getEndY() * scaleFactor + paneHalfHeight);
                        line.setStrokeWidth(3.0);
                        pane.getChildren().add(line);
                    }
                    line.opacityProperty().set(opacity);
                    System.out.println(line);
                    i++;
                }
            }
                */
            
            /*
            if ( rb_drawTail.isSelected() & so.getTail().size() > 0 ) {
                nLines = so.getTail().size();
                System.out.println(nLines);
                i = 0;
                for (Circle c: so.getTail()) {
                    if ( ! pane.getChildren().contains(c)) {
                        c.setRadius(3.0);
                        System.out.println(c);
                        pane.getChildren().add(c);
                    }
                    opacity = 80.0 * ( (double)(nLines - i) / (double)nLines );
                    System.out.println("opacity=" + opacity);
                    c.opacityProperty().set(opacity);
                    c.setCenterX(c.getCenterX() * scaleFactor + paneHalfWidth);
                    c.setCenterY(c.getCenterY() * scaleFactor + paneHalfHeight);
                    i++;
                }
            }
            */
            /*
            if ( rb_drawTail.isSelected() & so.getTail().size() > 0 ) {
                Polyline polyline = new Polyline();
                for (Point2D point: so.getTail()) {
                    polyline.getPoints().addAll(new Double[]{point.getX(), point.getY()});
                    polyline.setStrokeWidth(3.0);
                    //if ( ! pane.getChildren().contains(polyline)) {
                        pane.getChildren().add(polyline);
                    //} else {
                    //    pane.getChildren().add(polyline);
                    //}
                }
            }
            */

            // Draw the planets
            /*
            if ( ! pane.getChildren().contains(so)) {
                so.setRadius(7.0);
                pane.getChildren().add(so);
            }
            so.setCenterX(so.getX() * scaleFactor + paneHalfWidth);
            so.setCenterY(so.getY() * scaleFactor + paneHalfHeight);

        }
        */
    }
}