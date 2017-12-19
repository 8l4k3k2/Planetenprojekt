package plantenbahnen;



import java.util.ArrayList;
import javafx.scene.layout.Pane;

public class Zeichnen implements Runnable {

    ArrayList<SpaceObject> universe;
    Pane pane;
    
    public Zeichnen (ArrayList<SpaceObject> universe, Pane pane) {
        this.universe = universe;
        this.pane = pane;
    }
    
    //public void zeichnePlaneten(ArrayList<SpaceObject> universe) {
    @Override
    public void run() {
        
        while ( true ) {
            //if ( pane.getChildren().isEmpty() ) {
            pane.getChildren().clear();
            //}

            double paneHalfWidth = pane.getPrefWidth() / 2.0;
            double paneHalfHeight = pane.getPrefHeight() / 2.0;
            double scaleFactor = 0.1;

            for (SpaceObject so: universe) {
                so.setCenterX(so.getX() * scaleFactor + paneHalfWidth);
                so.setCenterY(so.getY() * scaleFactor + paneHalfHeight);
                so.setRadius(2);
                //so.setColour(Color.BLACK);
                pane.getChildren().add(so);
            }
        }
    }    


    
}
