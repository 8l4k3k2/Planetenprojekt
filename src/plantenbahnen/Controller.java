package plantenbahnen;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class Controller implements Initializable {
    
    @FXML private AnchorPane anchorPane;
    @FXML private Pane paneDraw;
    @FXML private Separator panesSeparator;
    @FXML private Rectangle rectangleForClipping;
    @FXML private Pane paneControls;
    @FXML private Slider slider_sim_speed;
    @FXML private CheckBox checkBox_drawTail;
    @FXML private Slider slider_tailLength;
    @FXML private ChoiceBox choiceBox_scenario;

    private ObservableList scenariosToChose; //
    private ArrayList<SpaceObject> universe = new ArrayList<>();
    Berechnungen startCalc = new Berechnungen(universe);
    Rectangle rectangleClipForPane;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rectangleClipForPane = new Rectangle(paneDraw.getPrefWidth(), paneDraw.getPrefHeight());
        
        int tailLength = 100;
        GuiElements gui = new GuiElements(anchorPane, paneDraw, paneControls,
            panesSeparator, 0.1, slider_sim_speed, checkBox_drawTail, slider_tailLength, 
            tailLength, choiceBox_scenario, rectangleClipForPane);

        
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

        // Set the mouse events for the pane
	//MyMouseEvents.paneMouseEvents(gui.getPaneDraw());
        
        anchorPane.widthProperty().addListener((ov, oldValue, newValue) -> {

            paneDraw.setPrefWidth(newValue.doubleValue() - paneDraw.getLayoutX() - paneControls.getPrefWidth());
            paneControls.setLayoutX(newValue.doubleValue() - paneControls.getPrefWidth());
            panesSeparator.setLayoutX(newValue.doubleValue() - paneControls.getPrefWidth());
            
            gui.getRectangleClipForPane().setWidth(newValue.doubleValue() - paneDraw.getLayoutX() - paneControls.getPrefWidth());
            paneDraw.setClip(gui.getRectangleClipForPane());
            
            gui.setPaneHalfWidth();
            
            Draw.draw(gui, universe);
        });
        anchorPane.heightProperty().addListener((ov, oldValue, newValue) -> {

            paneDraw.setPrefHeight(newValue.doubleValue() - paneDraw.getLayoutY());
            paneControls.setPrefHeight(newValue.doubleValue() - paneDraw.getLayoutY());
            panesSeparator.setPrefHeight(newValue.doubleValue() - paneDraw.getLayoutY());

            gui.getRectangleClipForPane().setHeight(newValue.doubleValue() - paneDraw.getLayoutY());
            paneDraw.setClip(gui.getRectangleClipForPane());

            gui.setPaneHalfHeight();
            
            Draw.draw(gui, universe);
        });

        scenariosToChose = FXCollections.observableArrayList();
	scenariosToChose.add(" ");
        scenariosToChose.add("Sonne, Erde, Mond");
        scenariosToChose.add("Vier Planeten");
        scenariosToChose.add("Vier Planeten 2");
	choiceBox_scenario.setItems(scenariosToChose);
	choiceBox_scenario.setValue(scenariosToChose.get(0));
	choiceBox_scenario.setOnAction((event) -> {
            universe.clear();
            if ( choiceBox_scenario.getValue() == "Sonne, Erde, Mond" ) {
                SpaceObject sun = new SpaceObject("sun", 0, 0, 797000000000000000.4, new Vector(), 15, new int[]{0,0,255}, gui);
                universe.add(sun);
                Vector nF = new Vector(-5,2,350);
                SpaceObject earth = new SpaceObject("earth", 450, 450, 50000000000000.0, nF, 7, new int[]{0,255,0}, gui);
                universe.add(earth);
                Vector nF2 = new Vector(5,2,350);
                SpaceObject moon = new SpaceObject("moon", 350, 550, 7970000000000.4, nF2, 3, new int[]{0,0,255}, gui);
                universe.add(moon);
                Draw.draw(gui, universe);
            } else if ( choiceBox_scenario.getValue() == "Vier Planeten" ) {
                SpaceObject sun = new SpaceObject("sun", 0, 0, 797000000000000000.4, new Vector(), 15, new int[]{0,0,255}, gui);
                universe.add(sun);
                Vector nF = new Vector(-5,2,110);
                SpaceObject earth = new SpaceObject("earth", 3000, 3000, 50000000000000000.0, nF, 7, new int[]{0,255,0}, gui);
                universe.add(earth);
                Vector nF2 = new Vector(2,2,2.5);
                SpaceObject moon = new SpaceObject("moon", 2800, 2800, 797000000000.4, nF2, 3, new int[]{255,0,0}, gui);
                universe.add(moon);
                Vector nF3 = new Vector(2,10,250);
                SpaceObject p2 = new SpaceObject("moon", -850, 650, 7970000000000.4, nF3, 3, new int[]{0,0,0}, gui);
                universe.add(p2);
                Draw.draw(gui, universe);
            } else if ( choiceBox_scenario.getValue() == "Vier Planeten 2" ) {    
                Vector star1F = new Vector(0,2,60); //81.5
                SpaceObject star1 = new SpaceObject("star1", -2000, 0, 797000000000000000.4, star1F, 15, new int[]{0,0,255}, gui);
                universe.add(star1);
                Vector star2F = new Vector(0,-2,60); //81.5
                SpaceObject star2 = new SpaceObject("star2", 2000, 0, 797000000000000000.4, star2F, 15, new int[]{0,255,0}, gui);
                universe.add(star2);
                Vector nF3 = new Vector(0,2,250);
                SpaceObject obj3 = new SpaceObject("moon", -1000, 0, 7970000000000.4, nF3, 3, new int[]{0,0,0}, gui);
                universe.add(obj3);
                Vector nF4 = new Vector(0,-2,250);
                SpaceObject obj4 = new SpaceObject("moon", 1000, 0, 7970000000000.4, nF4, 3, new int[]{255,0,0}, gui);
                universe.add(obj4);
                Draw.draw(gui, universe);
            }
        });
    }

    @FXML private void buttonStartSimulation(ActionEvent event) throws InterruptedException {
        if ( ! universe.isEmpty() ) {
            startCalc.start();
        }
    }    

    @FXML private void buttonStopSimulation(ActionEvent event) throws InterruptedException {
        if ( ! universe.isEmpty() ) {
            startCalc.stop();
        }
    }

    @FXML private void checkBox_drawTail(ActionEvent event) throws InterruptedException {
        if ( checkBox_drawTail.isSelected() ) {
            for (SpaceObject planet: universe){
                planet.setDrawTail(true);
            }
        } else {
            for (SpaceObject planet: universe){
                planet.setDrawTail(false);
                if ( planet.getTail().size() > 0 ) {
                    for (Circle c: planet.getTail()) {
                        paneDraw.getChildren().remove(c);
                    }
                }
            }
        }
    }

    public void onCloseEvent(){
        startCalc.stop();
    }
}
