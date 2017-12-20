package plantenbahnen;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
//import javax.swing.event.ChangeListener;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class Controller implements Initializable {
    
    @FXML private Pane paneDraw;
    @FXML private Pane paneControls;
    @FXML private Slider slider_sim_speed;
    @FXML private RadioButton radioButton_drawTail;
    @FXML private TextField textField_tailLenght;
    
    private ArrayList<SpaceObject> universe = new ArrayList<>();
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.03), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Draw.drawPlanets(universe, paneDraw, radioButton_drawTail);
        }
    }));
    Berechnungen startCalc = new Berechnungen(universe);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        GuiElements gui = new GuiElements(paneDraw, 0.1);
        
        SpaceObject sun = new SpaceObject("sun", 0, 0, 7970000000000000.4, new Vector(), 50, new int[]{0,0,255}, 10, gui);
        universe.add(sun);

        Vector nF = new Vector(-5,2,25);
        SpaceObject earth = new SpaceObject("earth", 450, 450, 500000000000.0, nF, 15, new int[]{0,255,0}, 100, gui);
        universe.add(earth);
        
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
        
        Draw.drawPlanets(universe, paneDraw, radioButton_drawTail);

        slider_sim_speed.setMin(10.0);
        slider_sim_speed.setMax(1000.0);
        slider_sim_speed.setValue(100.0);
        
        slider_sim_speed.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                startCalc.setVelocityFactor(slider_sim_speed.getValue());
            }
        });
    }

    @FXML private void buttonStartSimulation(ActionEvent event) throws InterruptedException {
        
        //startCalc.start();

        //timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, new KeyValue(slider.valueProperty(), 0)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        startCalc.start();
    }    

    @FXML private void buttonStopSimulation(ActionEvent event) throws InterruptedException {
        startCalc.stop();
        timeline.stop();
    }    
}
