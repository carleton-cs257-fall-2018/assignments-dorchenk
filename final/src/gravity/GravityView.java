package gravity;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.ListView;

import java.lang.Math;
import java.util.List;
import java.util.ArrayList;


public class GravityView {

//    @FXML private AnchorPane view;

    @FXML private Label playerData;
    List dataPlayer = new ArrayList();




//USE THE VIEW TO PRESENT VELOCITIES, ACCELERATIONS, MASSES?, FORCEG, DISTANCE, DESC, ETC!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //Probably in another anchorpane.

//    Object player = new Object();

    public GravityView() { //maybe asks model for data

    }



    public void update() {

     }

}
