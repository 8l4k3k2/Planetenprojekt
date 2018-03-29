package plantenbahnen;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class MyMouseEvents {

    public static void nodeMouseEvents(SpaceObject planet, ArrayList<SpaceObject> universe, Pane pane, GuiElements gui) {
        
        // Context menu popping up on right click with mouse
        ContextMenu contextMenuForNodes = new ContextMenu();

        // Define items of the context menu
        MenuItem itemFocus = new MenuItem("Set focus on this space object");
        MenuItem itemDelete = new MenuItem("Delete this space object");

        // Define an event handler for an item
        itemFocus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Focusing on " + planet.getName());
                

                //pane.setLayoutX(planet.getGui().getPaneHalfWidth() - planet.getCenterX());
                //pane.setLayoutY(planet.getGui().getPaneHalfHeight() - planet.getCenterY());
                
                planet.centerXProperty().addListener((ov, oldValue, newValue) -> {
                    pane.setLayoutX(planet.getGui().getPaneHalfWidth() - newValue.doubleValue());
                });
                planet.centerYProperty().addListener((ov, oldValue, newValue) -> {
                    pane.setLayoutY(planet.getGui().getPaneHalfHeight() - newValue.doubleValue());
                });
                
                
            }
        });

        // Define an event handler for an item
        itemDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if ( gui.getSimulationStatus() == 0 ) {
                    universe.remove(planet);
                    planet.getGui().getPaneDraw().getChildren().remove(planet);
                    for (Circle c: planet.getTail()) {
                        planet.getGui().getPaneDraw().getChildren().remove(c);
                    }
                }
            }
        });

        // Add item(s) to the context menu
        contextMenuForNodes.getItems().add(itemFocus);
        contextMenuForNodes.getItems().add(itemDelete);

        // Define context menu for a node
        planet.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    contextMenuForNodes.show(planet, t.getScreenX(), t.getScreenY());
                } else {
                    contextMenuForNodes.hide();
                }
            }
        });
    } // nodeMouseEvents

    public static void paneMouseEvents(Pane pane) {

        DragContext sceneDragContext = new DragContext();

        pane.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sceneDragContext.mouseAnchorX = event.getSceneX();
                sceneDragContext.mouseAnchorY = event.getSceneY();
                sceneDragContext.translateAnchorX = pane.getTranslateX();
                sceneDragContext.translateAnchorY = pane.getTranslateY();
            }
        });

        pane.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    pane.setTranslateX(sceneDragContext.translateAnchorX + event.getSceneX() - sceneDragContext.mouseAnchorX);
                    pane.setTranslateY(sceneDragContext.translateAnchorY + event.getSceneY() - sceneDragContext.mouseAnchorY);
                }
                event.consume();
            }
        });

        /*
        // Zoom in and out focusing on the point where the mouse is
        pane.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                final double delta = 1.2;
                final double delta2 = 0.90;
                double scale = pane.getScaleX();	// pane.getScaleX() = pane.getScaleY()
                //System.out.println("MyMouseEvents: pane.scaleX und pane.ScaleY:   "+pane.getScaleX()+"  "+pane.getScaleY());
                double oldScale = scale;
                final double minScale = 0.7;
                final double maxScale = 7.0;
                double zoomFactor;

                //System.out.println("event.getDeltax="+event.getDeltaX()+"  event.getDeltaY()="+event.getDeltaY());
                if (event.getDeltaY() < 0) {	// Zoom out
                    scale /= delta;
                } else {	// Zoom in
                    scale *= delta;
                }

                if (scale > minScale && scale < maxScale) {
                    if (event.getDeltaY() < 0) {	// Zoom out
                        zoomFactor = 2.0 - delta2;
                    } else {	// Zoom in
                        zoomFactor = delta2;
                    }
                } else {
                    zoomFactor = 1.0;
                }
                //System.out.println("scale= "+scale+"  zoomFactor="+zoomFactor);

                // Restrict the scale to a certain range and the set it
                scale = clamp(minScale, scale, maxScale);
                pane.setScaleX(scale);
                pane.setScaleY(scale);

                for (Node node : graph.getNodeList()) {
                    node.setRadius(node.getRadius() * zoomFactor);
                    //System.out.println("MyMouseEvents: "+node.getNameOfNode()+"   nodeRadius()= "+node.getRadius());
                    node.getNodeName().relocate(node.getCenterX() + node.getRadius(), node.getCenterY() - (node.getRadius() / 2.0));
                    //node.getNodeName().relocate(node.getCenterX(), node.getCenterY());
                    //System.out.println("MyMouseEvents: "+node.getNameOfNode()+"  "+node.getCenterX()+" "+node.getCenterY());
                    //System.out.println("MyMouseEvents: "+node.getNameOfNode()+"  "+node.getNodeName().getLayoutX()+" "+node.getNodeName().getLayoutY());
                    node.getNodeName().setFont(Font.font("Verdana", node.getNodeName().getFont().getSize() * zoomFactor));
                }
                for (Edge edge : graph.getEdgeList()) {
                    edge.setStrokeWidth(edge.getStrokeWidth() * zoomFactor);
                }

                // The pivot point must be untransformed, i. e. without scaling
                double f = (scale / oldScale) - 1.0;
                double dx = (event.getSceneX() - (pane.getBoundsInParent().getWidth() / 2.0 + pane.getBoundsInParent().getMinX()));
                double dy = (event.getSceneY() - (pane.getBoundsInParent().getHeight() / 2.0 + pane.getBoundsInParent().getMinY()));
                pane.setTranslateX(pane.getTranslateX() - f * dx);
                pane.setTranslateY(pane.getTranslateY() - f * dy);

                event.consume();
            }
        });
         */
    } // paneMouseEvent
}
