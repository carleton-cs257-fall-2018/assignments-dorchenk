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
import java.lang.Math;

public class GravityModel {

    @FXML private Object player;
    @FXML private Object object;

    private double distance;
    private double G = 0.0000000000667;
    private double forceG; //updates based on distance between objects and the masses of objects
    private double mass;

    public GravityModel() {

    }

    public double getDistance(Object player, Object object) {
        double distX = (this.object.getLayoutX() - this.player.getLayoutX());
        double distY = (this.object.getLayoutY() - this.player.getLayoutY());
        distance = Math.sqrt((distX * distX) + (distY * distY));
        return distance;
    }

    public double getMass() {
        double mass1 = object.getMass();
        double mass2 = player.getMass();
        mass = mass1 + mass2;
        return mass;
    }

    public double getForceG() {
        return forceG;
    }

    public double setForceG(double mass, double distance, double G) {
        forceG = (G*((mass)/(distance*distance)));
        return forceG;
    }

}
