package plantenbahnen;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
//import javax.swing.event.ChangeListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class Controller implements Initializable {
    
    @FXML private Pane paneDraw;
    @FXML private Pane paneControls;
    @FXML private Slider slider_set_dt;

    private ArrayList<SpaceObject> universe = new ArrayList<>();
    private Thread calcThread;
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("(Re-)Drawing...");
            //Draw.drawPlanets(universe, paneDraw);
        }
    }));

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        SpaceObject sun = new SpaceObject("sun", 600, 600, 5970000000000000.4, new Vector(), 50, 10, new int[]{0,0,255});
        universe.add(sun);

        Vector nF = new Vector(75,-12,15);
        SpaceObject earth = new SpaceObject("earth", 450, 450, 500000000000.0, nF, 15, 10, new int[]{0,255,0});
        universe.add(earth);
        
        //Berechnungen.berechneBahn(universe);
        Berechnungen startCalc;
        startCalc = new Berechnungen(universe);
        startCalc.start();


        while (true){
            System.out.println((int) universe.get(1).getX()+","+(int) universe.get(1).getY()+"     "+universe.get(0).getPositionVector().norm(universe.get(1).getPositionVector()));
            try{
                TimeUnit.SECONDS.sleep(1);
            }
            catch (Exception ex){
                Thread t = Thread.currentThread();
                t.getUncaughtExceptionHandler().uncaughtException(t, ex);
            }
        }


        /*
        MyCalculations myCalc = new MyCalculations(universe);
        calcThread = new Thread(myCalc);

        Draw.drawPlanets(universe, paneDraw);

        slider_set_dt.setMin(1);
        slider_set_dt.setMax(3);
        slider_set_dt.setValue(1);
        */
        /*slider_set_dt.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                System.out.println("changed....");
                //betLabel.textProperty().setValue( String.valueOf((int) betSlider.getValue()));
                timeline.getKeyFrames().removeAll();
                System.out.println(timeline.getKeyFrames());
                timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, new KeyValue(slider.valueProperty(), 0)));
            }
        });*/
    }

    @FXML private void buttonStartSimulation(ActionEvent event) throws InterruptedException {
        calcThread.start();

        /*
        Platform.runLater(new Runnable() {
            @Override public void run() {
                int i = 0;
                while ( i < 3 ) {
                    Draw.drawPlanets(universe, paneDraw);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    i++;
                }
            }
        });
        */
        /*
        Thread updaterThread = new Thread( () -> {
            //@Override public void run () {
            public void run () {
                while ( true ) {
                    Platform.runLater( () -> Draw.drawPlanets(universe, paneDraw) );
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        });
        */
        //updaterThread.setDaemon( true );
        //updaterThread.start();

        //timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, new KeyValue(slider.valueProperty(), 0)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }    
}
