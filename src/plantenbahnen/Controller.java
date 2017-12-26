package plantenbahnen;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
//import javax.swing.event.ChangeListener;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;


public class Controller implements Initializable {
    
    @FXML private Pane paneDraw;
    @FXML private Pane paneControls;
    @FXML private Slider slider_sim_speed;
    @FXML private RadioButton radioButton_drawTail;
    @FXML private TextField textField_tailLenght;

    private ArrayList<SpaceObject> universe = new ArrayList<>();

    Berechnungen startCalc = new Berechnungen(universe);
    AnimationTimer animation;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        GuiElements gui = new GuiElements(paneDraw, 0.1);
        
        SpaceObject sun = new SpaceObject("sun", 0, 0, 797000000000000000.4, new Vector(), 15, new int[]{0,0,255}, 10, gui);
        universe.add(sun);

        Vector nF = new Vector(-5,2,350);
        SpaceObject earth = new SpaceObject("earth", 450, 450, 50000000000000.0, nF, 7, new int[]{0,255,0}, 100, gui);
        universe.add(earth);


        Vector nF2 = new Vector(5,2,350);
        SpaceObject moon = new SpaceObject("moon", 350, 550, 7970000000000.4, nF2, 3, new int[]{0,0,255}, 10, gui);
        universe.add(moon);
        
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

        for (SpaceObject planet: universe){
            paneDraw.getChildren().add(planet);
        }

        slider_sim_speed.setMin(0.000001);
        slider_sim_speed.setMax(0.0001);
        slider_sim_speed.setValue(0.000005);
        startCalc.setDeltaTime(slider_sim_speed.getValue());
        
        slider_sim_speed.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                startCalc.setDeltaTime(slider_sim_speed.getValue());
            }
        });

        animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (SpaceObject so : universe) {
                    so.setCircleCoordinates();
                    //System.out.println(so.getName() + "  " +so.getCenterX() + "  " + so.getCenterY());
                }
            }
        };

        animation.start();
    }

    @FXML private void buttonStartSimulation(ActionEvent event) throws InterruptedException {
        startCalc.start();
    }    

    @FXML private void buttonStopSimulation(ActionEvent event) throws InterruptedException {
        startCalc.stop();
    }

    @FXML private void radioButton_drawTail(ActionEvent event) throws InterruptedException {
        if ( radioButton_drawTail.isSelected() ) {
            System.out.println("RadioButton is selected");
            for (SpaceObject planet: universe){
                for (Line line: planet.getTail()) {
                    paneDraw.getChildren().add(line);
                }
            }
        } else {
            System.out.println("RadioButton is DE-selected");
            for (SpaceObject planet: universe){
                for (Line line: planet.getTail()) {
                    paneDraw.getChildren().remove(line);
                }
            }
        }
    }

    public void onCloseEvent(){
        startCalc.stop();
    }
}
