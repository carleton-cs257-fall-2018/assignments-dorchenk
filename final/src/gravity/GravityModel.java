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

    private double gravParam;
    private double major;
    private double minor;
    private double orbitalV;
    private double ecc;

    public GravityModel() {

    }

    public void update(GravityView view) {

    }

    public double getDistance(Body player, Body object, double distX, double distY) {
        if (distX > -1 && distX < 0) {
            distX = -0.1;
        } else if (distX >= 0 && distX < 1) {
            distX = 0.1;
        }
        if (distY > -1 && distY < 0) {
            distX = -0.1;
        } else if (distY >= 0 && distY < 1) {
            distY = 0.1;
        }
        this.distance = Math.sqrt((distX * distX) + (distY * distY));
        return this.distance;
    }

    public double getSingleMass(Body player) {
        return player.getMass();
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
            desc = ((2*G) * (object.getMass())) / (vesc);
        }
        desc = Math.abs(desc); //make it positive
        return desc;
    }

    public double getVesc(Body player, Body object, double distance) {
        if (distance > -1 && distance < 0) {
            distance = -0.01;
        } else if (distance >= 0 && distance < 1) {
            distance = 0.01;
        }
        if(player.getMass() >= object.getMass()) {
//            if ((player.getVelocityX() >= object.getVelocityX()) || (player.getVelocityY() <= object.getVelocityY())) {
            this.vesc = ((2*G) * player.getMass()) / (distance * distance);
        } else {
            this.vesc = ((2*G) * (object.getMass())) / (distance * distance);
        }
        return this.vesc;
    }

    public double getGravParam(Body player, Body object) {
        gravParam = (G * player.getMass()) + (G * object.getMass());
        return gravParam;
    }

    public double getMajorA(Body player, Body object, double distance) {        //this will not always be the major but for now this is good.
        major = distance * (object.getMass() / (player.getMass() + object.getMass()));
        return major;
    }

    public double getMinorB(double distance, double major) {
        minor = distance - major;
        return minor;
    }

    public double getOrbitalV(double gravParam, double major, double distance) {
        if (distance > -1 && distance < 0) {
            distance = -0.01;
        } else if (distance >= 0 && distance < 1) {
            distance = 0.01;
        }
        orbitalV = Math.sqrt((gravParam * ((2/distance)-(1-major))));
        return orbitalV;
    }

    public double getEcc(double major, double minor) {
        ecc = Math.sqrt((1-((minor * minor)/(major * major))));
        return ecc;
    }



}
