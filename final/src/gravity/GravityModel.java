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
    private double ecc = 0.5;

//    textfield.getText();

    public GravityModel() {

    }

    public double getDistance(double distX, double distY) {
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

    public double getTotalMass(Body player, Body object) {
        double mass1 = object.getMass();
        double mass2 = player.getMass();
        totalMass = mass1 + mass2;
        return totalMass;
    }

    public double getForceG(Body player, Body object, double distance) {
        double M = player.getMass() * object.getMass();
        this.forceG = (G*((M)/(distance*distance)));
        return this.forceG;
    }

    public double getDesc(Body player, Body object, double vesc) {
        desc = ((2*G) * (getLargerMass(player, object))) / (vesc); //consider doing for x and y directions
        desc = Math.abs(desc); //make it positive
        return desc;
    }

    public double getVesc(Body player, Body object, double distance) {
        this.vesc = ((2*G) * getLargerMass(player, object)) / (distance * distance);
        return this.vesc;
    }

    public double getGravParam(Body player, Body object) {
        gravParam = (G * player.getMass()) + (G * object.getMass());
        return gravParam;
    }

    public double getBaryCenter(Body player, Body object, double distX) {
        double Rmax = Math.abs(distX) * ((player.getMass())/getTotalMass(player, object));
        return Rmax;
    }

    public double getMajorA(Body player, Body object, double distX) {        //this will not always be the major but for now this is good.
        //major = (Math.abs(distX) * (getVesc(player, object, Math.abs(distX) * getVesc(player, object, Math.abs(distX))))) / (4 * getGravParam(player, object));
        major = 2*(getBaryCenter(player, object, distX)) /3;
        return major;
    }

    public double getMinorB(double Rmax, double major, double ecc) {
        minor = (1-ecc)*major;
        return minor;
    }

    public double getOrbitalV(double gravParam, double major, double distance) {
        orbitalV = Math.sqrt((gravParam * ((2/distance)-(1-major))));
        return orbitalV;
    }

    public void setEcc(double newEcc) {
        ecc = newEcc;
    }

    public double getEcc() {
        return ecc;
    }

    public double getPosX(double t, double major) {
        double posX = major * Math.cos(t);
        return posX;
    }

    public double getPosY(double t, double minor) {
        double posY = minor * Math.sin(-t);
        return posY;
    }


}
