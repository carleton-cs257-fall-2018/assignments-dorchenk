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
    private double totalMass;
    private double forceG; //updates based on distance between objects and the masses of objects
    private double desc;
    private double vesc;

    public GravityModel() {

    }

    public double getDistance(Object player, Object object, double distX, double distY) {
        this.distance = Math.sqrt((distX * distX) + (distY * distY));
        return this.distance;
    }

    public double getSingleMass(Object player) {
        return player.getMass();
    }

    public double getTotalMass(Object player, Object object) {
        double mass1 = object.getMass();
        double mass2 = player.getMass();
        totalMass = mass1 + mass2;
        return totalMass;
    }

    public double getForceG() {
        return forceG;
    }

    public double getForceG(Object player, Object object, double distance) {
        double M = player.getMass() * object.getMass();
        this.forceG = (G*((M)/(distance*distance)));
        return this.forceG;
    }

    public double getDesc(Object player, Object object) {
        if(player.getMass() >= object.getMass()) {
            this.desc = ((2*G) * (player.getMass())) / (player.getVelocityX() + player.getVelocityY());
        }
        else {
            this.desc = ((2*G) * (object.getMass())) / (player.getVelocityX() + player.getVelocityY()); //slope of velocities is velocity?
        }
        this.desc = (this.desc * this.desc)/2; //make it positive
        return this.desc;
    }

    public double getVesc(Object player, Object object, double distance) {
        if(player.getMass() >= object.getMass()) {
//            if ((player.getVelocityX() >= object.getVelocityX()) || (player.getVelocityY() <= object.getVelocityY())) {
            this.vesc = ((2*G) * (player.getMass())) / (distance * distance);
        } else {
            this.vesc = ((2*G) * (object.getMass())) / (distance * distance);
        }

        return this.vesc;
    }

}
