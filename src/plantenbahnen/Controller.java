/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plantenbahnen;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author stefan
 */
public class Controller implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    private ArrayList<SpaceObject> universe = new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        SpaceObject sun = new SpaceObject(new double[]{600,600},59700000000000.4,new ForceVector(),50,10,new int[]{0,0,255});
        this.universe.add(sun);

        ForceVector nF = new ForceVector(new double[]{150,150},3000000);
        SpaceObject earth = new SpaceObject(new double[]{450,450}, 500000000000.0, nF, 15, 10, new int[]{0,255,0});
        
        
        //Berechnungen.berechneBahn(universe);
        Berechnungen startCalc;
        startCalc = new Berechnungen(universe);
        startCalc.start();
        
        while ( true ) {
            zeichnePlaneten(universe);
        }
        
    }    
    
    public void zeichnePlaneten(ArrayList<SpaceObject> universe) {
        // To do by Stefan
    }    
}
