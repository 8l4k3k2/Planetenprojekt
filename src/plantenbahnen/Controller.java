package plantenbahnen;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
    @FXML private Pane paneControls;
    @FXML private Slider slider_sim_speed;
    @FXML private CheckBox checkBox_drawTrajectory;
    @FXML private Slider slider_trajectoryLength;
    @FXML private ChoiceBox choiceBox_scenario;

    private ObservableList scenariosToChose; //
    private ArrayList<SpaceObject> universe = new ArrayList<>();
    Berechnungen startCalc;
    Rectangle rectangleClipForPane;
    GuiElements gui;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rectangleClipForPane = new Rectangle(paneDraw.getPrefWidth(), paneDraw.getPrefHeight());
        
        int trajectoryLength = 100;
        double scaleFactor = 0.1;
        gui = new GuiElements(anchorPane, paneDraw, paneControls,
            panesSeparator, scaleFactor, slider_sim_speed, checkBox_drawTrajectory, slider_trajectoryLength, 
            trajectoryLength, choiceBox_scenario, rectangleClipForPane);
        startCalc = new Berechnungen(universe, gui);
        
        startCalc.setDeltaTime(gui.getSliderSimSpeed().getValue());
        gui.getSliderSimSpeed().valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                startCalc.setDeltaTime(gui.getSliderSimSpeed().getValue());
            }
        });
        
        gui.getSliderTrajectoryLength().setMin(100.0);
        gui.getSliderTrajectoryLength().setMax(10000.0); //10000000.0
        gui.getSliderTrajectoryLength().setValue(100.0);
        gui.getSliderTrajectoryLength().valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                for (SpaceObject so : universe) {
                    so.setTrajectorySize((int)gui.getSliderTrajectoryLength().getValue());
                }
            }
        });

        // Set the mouse events for the pane
	//MyMouseEvents.paneMouseEvents(gui.getPaneDraw(), gui.getRectangleClipForPane());
        
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
        scenariosToChose.add("Drei Planeten");
        scenariosToChose.add("Testfall: Erde um Sonne");
        scenariosToChose.add("Vier Planeten #1");
        scenariosToChose.add("Vier Planeten #2");
	choiceBox_scenario.setItems(scenariosToChose);
	choiceBox_scenario.setValue(scenariosToChose.get(0));
	choiceBox_scenario.setOnAction((event) -> {
            
            // Stop calculations if there are any
            startCalc.stop();
            
            // Remove all planets from the universe
            universe.clear();
            
            // (Re-)Set Scale factor and Simulation speed slider settings
            gui.setScaleFactor(0.1);
            gui.getSliderSimSpeed().setMin(0.00001);
            gui.getSliderSimSpeed().setMax(0.0001);
            gui.getSliderSimSpeed().setValue(0.00002);
            
            // Move pane and rectangle back to center
            gui.getPaneDraw().setLayoutX(0.0);
            gui.getPaneDraw().setLayoutY(0.0);
            gui.getRectangleClipForPane().setLayoutX(0.0);
            gui.getRectangleClipForPane().setLayoutY(0.0);
            
            if ( choiceBox_scenario.getValue() == "Drei Planeten" ) {
                SpaceObject sun = new SpaceObject("sun", 0, 0, 797000000000000000.4, new Vector(), 15, new int[]{0,0,255}, gui, universe);
                universe.add(sun);
                Vector nF = new Vector(-5,2,350);
                SpaceObject earth = new SpaceObject("earth", 450, 450, 50000000000000.0, nF, 7, new int[]{0,255,0}, gui, universe);
                universe.add(earth);
                Vector nF2 = new Vector(5,2,350);
                SpaceObject moon = new SpaceObject("moon", 350, 550, 7970000000000.4, nF2, 3, new int[]{0,0,255}, gui, universe);
                universe.add(moon);
                Draw.draw(gui, universe);
            } else if ( choiceBox_scenario.getValue() == "Testfall: Erde um Sonne" ) {
                /*
                This case simulates real conditions - including sun and earth -
                using values for mass and distance as in reality:
                mass, sun: 1.984 * 10^30 kg
                mass, earth: 5.974 * 10^24 kg
                farthest distance between sun and earth: 152 * 10^9 m
                
                Zu erwarten: Die Umlaufbahn der Erde erscheint wie ein aus dem Mittelpunkt
                verschobener Kreis. Diese Umlaufbahn weicht nur sehr leicht von einem
                exakten Kreis ab.
                Ergebnis: Mit dem bloßen Auge ist eine Kreisbahn erkennbar. Es ist aber vermutlich
                eine Ellipse.
                */
                SpaceObject sun = new SpaceObject("sun", 0, 0, 1.984 * Math.pow(10,30), new Vector(), 15, new int[]{0,0,255}, gui, universe);
                universe.add(sun);
                Vector nF = new Vector(0, -1, 30000.0);
                SpaceObject earth = new SpaceObject("earth", 152.0 * Math.pow(10,9), 0, 5.974 * Math.pow(10,24), nF, 7, new int[]{0,255,0}, gui, universe);
                universe.add(earth);
                
                // Adjust scale factor and simulation speed settings
                gui.setScaleFactor(1.0 / Math.pow(10, 9));
                gui.getSliderSimSpeed().setMin(0.01);
                gui.getSliderSimSpeed().setMax(10.0);
                gui.getSliderSimSpeed().setValue(0.5);
                Draw.draw(gui, universe);
            } else if ( choiceBox_scenario.getValue() == "Vier Planeten #1" ) {
                SpaceObject sun = new SpaceObject("sun", 0, 0, 797000000000000000.4, new Vector(), 15, new int[]{0,0,255}, gui, universe);
                universe.add(sun);
                Vector nF = new Vector(-5,2,110);
                SpaceObject earth = new SpaceObject("earth", 3000, 3000, 50000000000000000.0, nF, 7, new int[]{0,255,0}, gui, universe);
                universe.add(earth);
                Vector nF2 = new Vector(2,2,2.5);
                SpaceObject moon = new SpaceObject("moon", 2800, 2800, 797000000000.4, nF2, 3, new int[]{255,0,0}, gui, universe);
                universe.add(moon);
                Vector nF3 = new Vector(2,10,250);
                SpaceObject p2 = new SpaceObject("moon", -850, 650, 7970000000000.4, nF3, 3, new int[]{0,0,0}, gui, universe);
                universe.add(p2);
                Draw.draw(gui, universe);
            } else if ( choiceBox_scenario.getValue() == "Vier Planeten #2" ) {    
                Vector star1F = new Vector(0,2,60); //81.5
                SpaceObject star1 = new SpaceObject("star1", -2000, 0, 797000000000000000.4, star1F, 15, new int[]{0,0,255}, gui, universe);
                universe.add(star1);
                Vector star2F = new Vector(0,-2,60); //81.5
                SpaceObject star2 = new SpaceObject("star2", 2000, 0, 797000000000000000.4, star2F, 15, new int[]{0,255,0}, gui, universe);
                universe.add(star2);
                Vector nF3 = new Vector(0,2,250);
                SpaceObject obj3 = new SpaceObject("moon", -1000, 0, 7970000000000.4, nF3, 3, new int[]{0,0,0}, gui, universe);
                universe.add(obj3);
                Vector nF4 = new Vector(0,-2,250);
                SpaceObject obj4 = new SpaceObject("moon", 1000, 0, 7970000000000.4, nF4, 3, new int[]{255,0,0}, gui, universe);
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

    @FXML private void buttonZentrieren(ActionEvent event) throws InterruptedException {
        if ( ! universe.isEmpty() ) {
            boolean simulationWasRunning = false;
            if ( gui.getSimulationStatus() == 1 ) {
                simulationWasRunning = true;
                startCalc.stop();
                startCalc.getThread().join();
            }
            
            // Verschiebe das gesamte System so, dass der Massenschwerpunkt des
            // Systems in der Mitte ist. Berechnen des Massenschwerpunkts:
            double totalMass = 0.0;
            double massTimesCoordinateX = 0.0;
            double massTimesCoordinateY = 0.0;
            for (int i=0; i<universe.size(); i++) {
                totalMass += universe.get(i).getMass();
                massTimesCoordinateX += universe.get(i).getCenterX() * universe.get(i).getMass();
                massTimesCoordinateY += universe.get(i).getCenterY() * universe.get(i).getMass();
            }
            double xSP = massTimesCoordinateX / totalMass;
            double ySP = massTimesCoordinateY / totalMass;
            
            // Move the pane by the difference of the mass center and the center of the pane
            double diffToCenterX = 0.0;
            double diffToCenterY = 0.0;
            if ( gui.getPaneDraw().getLayoutX() < 0.0 ) {
                diffToCenterX = Math.abs(gui.getPaneDraw().getLayoutX()) + gui.getPaneHalfWidth() - xSP;
            } else {
                diffToCenterX = gui.getPaneDraw().getLayoutX() * -1.0 + gui.getPaneHalfWidth() - xSP;
            }
            if ( gui.getPaneDraw().getLayoutY() < 0.0 ) {
                diffToCenterY = Math.abs(gui.getPaneDraw().getLayoutY()) + gui.getPaneHalfHeight() - ySP;
            } else {
                diffToCenterY = gui.getPaneDraw().getLayoutY() * -1.0 + gui.getPaneHalfHeight() - ySP;
            }
            //diffToCenterX = gui.getPaneHalfWidth() - xSP;
            //diffToCenterY = gui.getPaneHalfHeight() - ySP;
            
            final double diffX = diffToCenterX;
            final double diffY = diffToCenterY;
            // Modify the GUI by running Platform.runlater() in a thread
            Thread thread = new Thread() {
                @Override public void run() {
                    if ( ! this.isInterrupted() ) {
                        Platform.runLater(new Runnable() {
                            @Override public void run() {
                                gui.getPaneDraw().setLayoutX(gui.getPaneDraw().getLayoutX() + diffX);
                                gui.getRectangleClipForPane().setLayoutX(gui.getRectangleClipForPane().getLayoutX() - diffX);
                                gui.getPaneDraw().setLayoutY(gui.getPaneDraw().getLayoutY() + diffY);
                                gui.getRectangleClipForPane().setLayoutY(gui.getRectangleClipForPane().getLayoutY() - diffY);
                            }
                        });
                    }
                }
            };
            /*Thread thread = new Thread() {
                @Override public void run() {
                    if ( ! this.isInterrupted() ) {
                        Platform.runLater(new Runnable() {
                            @Override public void run() {
                                for (SpaceObject so: universe) {
                                    //so.relocate(so.getCenterX()+diffX, so.getCenterY()+diffY);
                                    so.setCenterX(so.getCenterX()+diffX);
                                    so.setCenterY(so.getCenterY()+diffY);
                                    so.setX((so.getCenterX()-gui.getPaneHalfWidth()) / gui.getScaleFactor());
                                    so.setY((so.getCenterY()-gui.getPaneHalfHeight()) / gui.getScaleFactor());
                                    
                                    for (Circle c: so.getTrajectory()) {
                                        c.relocate(c.getCenterX()+diffX, c.getCenterY()+diffY);
                                        //c.setCenterX(c.getCenterX()+diffX);
                                        //c.setCenterY(c.getCenterY()+diffY);
                                    }
                                }
                            }
                        });
                    }
                }
            };*/
            thread.start();
            thread.join();
            
            // Restart the simulation if it was running
            if ( simulationWasRunning ) {
                startCalc.start();
            }
        }
    }

    @FXML private void checkBox_drawTrajectory(ActionEvent event) throws InterruptedException {
        if ( checkBox_drawTrajectory.isSelected() ) {
            for (SpaceObject planet: universe){
                planet.setDrawTrajectory(true);
            }
        } else { // remove the trajectories
            boolean simulationWasRunning = false;
            if ( gui.getSimulationStatus() == 1 ) {
                simulationWasRunning = true;
                startCalc.stop();
                startCalc.getThread().join();
            }
            for (SpaceObject planet: universe){
                planet.setDrawTrajectory(false);
                if ( planet.getTrajectory().size() > 0 ) {
                    for (Circle c: planet.getTrajectory()) {
                        paneDraw.getChildren().remove(c);
                    }
                }
                planet.getTrajectory().clear();
            }
            // Restart the simulation if it was running
            if ( simulationWasRunning ) {
                startCalc.start();
            }
        }
    }

    public void onCloseEvent(){
        startCalc.stop();
    }
}
