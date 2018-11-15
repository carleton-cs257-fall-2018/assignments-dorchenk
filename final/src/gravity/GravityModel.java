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

    private double distance;
    private double G = 0.0000000000667; //take input later on!
    private double totalMass;
    private double forceG;
    private double desc;
    private double vesc;

    public GravityModel() {

    }

    public void update(GravityView view) {

    }

    public double getDistance(Body player, Body object, double distX, double distY) {
        this.distance = Math.sqrt((distX * distX) + (distY * distY));
        return this.distance;
    }

    public double getSingleMass(Body player) {
        return player.getMass();
    }

    public double getVelX(Body player) {
        return player.getVelocityX();
    }

    public double getVelY(Body player) {
        return player.getVelocityY();
    }

    public double getAccX(Body player) {
        return player.getAccelX();
    }

    public double getAccY(Body player) {
        return player.getAccelY();
    }

    public double getLargerMass(Body player, Body object) {
        if (player.getMass() >= object.getMass()) {
            return player.getMass();
        } else {
            return object.getMass();
        }
    }

    //Consider method to force an orbit. Maybe controller contains a boolean to start object orbiting player

    public double getTotalMass(Body player, Body object) {
        double mass1 = object.getMass();
        double mass2 = player.getMass();
        totalMass = mass1 + mass2;
        return totalMass;
    }

    public double getForceG() {
        return forceG;
    }

    public double getForceG(Body player, Body object, double distance) {
        double M = player.getMass() * object.getMass();
        this.forceG = (G*((M)/(distance*distance)));
        return this.forceG;
    }

    public double getDesc(Body player, Body object) {
        if(player.getMass() >= object.getMass()) {
            desc = ((2*G) * (player.getMass())) / (vesc); //consider doing for x and y directions
        }
        else {
            desc = ((2*G) * (object.getMass())) / (player.getVelocityX() + player.getVelocityY()); //slope of velocities is velocity?
        }
        desc = Math.abs(desc); //make it positive
        return desc;
    }

    public double getVesc(Body player, Body object, double distance) {
        if(player.getMass() >= object.getMass()) {
//            if ((player.getVelocityX() >= object.getVelocityX()) || (player.getVelocityY() <= object.getVelocityY())) {
            this.vesc = ((2*G) * player.getMass()) / (distance * distance);
        } else {
            this.vesc = ((2*G) * (object.getMass())) / (distance * distance);
        }
        return this.vesc;
    }

}
