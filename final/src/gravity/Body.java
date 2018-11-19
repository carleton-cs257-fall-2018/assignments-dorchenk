/**
 * Body.java
 * Kellen Dorchen, 11/18/18.
 *
 * A subclass for gravity package.
 */
package gravity;

import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class Body extends Circle {
    @FXML private double velocityX;
    @FXML private double velocityY;
    private double mass = 0;
    private double accelX = 0;
    private double accelY = 0;


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
        return this.mass;
    }

    public double getAccelX() {
        return this.accelX;
    }

    public void setAccelX(double accelX) {
        this.accelX = accelX;
    }

    public double getAccelY() {
        return this.accelY;
    }

    public void setAccelY(double accelY) {
        this.accelY = accelY;
    }

}
