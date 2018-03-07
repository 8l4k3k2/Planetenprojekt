package plantenbahnen;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;


public class Controller implements Initializable {
    
    @FXML private AnchorPane anchorPane;
    @FXML private Pane paneDraw;
    @FXML private Pane paneControls;
    @FXML private Slider slider_sim_speed;
    @FXML private RadioButton radioButton_drawTail;
    @FXML private TextField textField_tailLength;
    @FXML private ChoiceBox choiceBox_scenario;

    private ObservableList scenariosToChose; //
    private ArrayList<SpaceObject> universe = new ArrayList<>();
    Berechnungen startCalc = new Berechnungen(universe);
    AnimationTimer animation;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        int tailLength = 100;
        GuiElements gui = new GuiElements(anchorPane, paneDraw, paneControls, 0.1,
            slider_sim_speed, radioButton_drawTail, textField_tailLength, tailLength, choiceBox_scenario);

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
                Draw.draw(gui, universe, startCalc);
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
                Draw.draw(gui, universe, startCalc);
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
                Draw.draw(gui, universe, startCalc);
            }
        });
        
        //printausgabe
        /*
        while (true){
            System.out.println("sonne: "+(int) universe.get(0).getX()+","+(int) universe.get(0).getY());
            System.out.println("erde: "+(int) universe.get(1).getX()+","+(int) universe.get(1).getY()+"     "+universe.get(0).getPositionVector().norm(universe.get(1).getPositionVector()));
            try{
                TimeUnit.SECONDS.sleep(1);
            }
            catch (Exception ex){
                Thread t = Thread.currentThread();
                t.getUncaughtExceptionHandler().uncaughtException(t, ex);
            }
        }
        */
        /*
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> {
                paneDraw.setPrefWidth(newValue.doubleValue() - paneDraw.getLayoutX());
                //DrawTheGraph.drawGraph(myGraph, pane);
                draw();
        });
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> {
                paneDraw.setPrefHeight(newValue.doubleValue() - paneDraw.getLayoutY());
                //DrawTheGraph.drawGraph(myGraph, pane);
                draw();
        });
        */
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

    @FXML private void radioButton_drawTail(ActionEvent event) throws InterruptedException {
        if ( radioButton_drawTail.isSelected() ) {
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
