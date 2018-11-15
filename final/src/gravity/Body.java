/**
 * Ball.java
 * Jeff Ondich, 10/29/14.
 *
 * A sample subclass of Sprite for CS257.
 */
package gravity;

import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

//This Bodys velocity will be change by the players force of gravity (a constant)
//initialize as zero.

public class Body extends Circle {
    @FXML private double velocityX; //updates based on accel but this is initial ... consider making this zero // controller can update??
    @FXML private double velocityY;
    @FXML private double mass; //this will come from fxml or controller
    private double accelX = 0; //might update based on controller
    private double accelY = 0; //


    public Body() {

    }

    public void step() {
        this.setCenterX(this.getCenterX() + this.velocityX);
        this.setCenterY(this.getCenterY() + this.velocityY);
    }

    public double getVelocityX() {
        this.velocityX = velocityX;
        return this.velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        this.velocityY = velocityY;
        return this.velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getMass() {
        this.mass = mass;
        return this.mass;
    }

    public double getAccelX() {
        this.accelX = accelX;
        return this.accelX;
    }

    public void setAccelX(double accelX) {
        this.accelX = accelX;
    }

    public double getAccelY() {
        this.accelY = accelY;
        return this.accelY;
    }

    public void setAccelY(double accelY) {
        this.accelY = accelY;
    }

}
