/**
 * Ball.java
 * Jeff Ondich, 10/29/14.
 *
 * A sample subclass of Sprite for CS257.
 */
package gravity;

import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

//This objects velocity will be change by the players force of gravity (a constant)
//initialize as zero.

public class Object extends Circle {
    @FXML private double velocityX; //updates based on accel but this is initial ... consider making this zero // controller can update??
    @FXML private double velocityY;
    @FXML private double mass; //this will come from fxml or controller
    private double accelX = 0.001; //might update based on controller
    private double accelY = 0.001; //


    public Object() {

    }

    public void step() {
        this.setCenterX(this.getCenterX() + this.velocityX);
        this.setCenterY(this.getCenterY() + this.velocityY);
    }

    public double getVelocityX() {
        this.velocityX = velocityX + accelX;
        return this.velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        this.velocityY = velocityY + accelY;
        return this.velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getMass() {
        return mass;
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
