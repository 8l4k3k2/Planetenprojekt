package plantenbahnen;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseGestures {

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    public void makeDraggable(SpaceObject node) {
        node.setOnMousePressed(circleOnMousePressedEventHandler);
        node.setOnMouseDragged(circleOnMouseDraggedEventHandler);
    }

    EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            SpaceObject p = (SpaceObject) t.getSource();
            orgTranslateX = p.getCenterX();
            orgTranslateY = p.getCenterY();
        }
    };

    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
            SpaceObject so = (SpaceObject) t.getSource();
            so.setX((newTranslateX - so.getGui().getPaneHalfWidth()) / so.getGui().getScaleFactor());
            so.setY((newTranslateY - so.getGui().getPaneHalfHeight()) / so.getGui().getScaleFactor());
        }
    };
}
