/**
 * Ball.java
 * Jeff Ondich, 10/29/14.
 *
 * A sample subclass of Sprite for CS257.
 */
package gravity;

import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class Player extends Circle {
    @FXML private double velocityX;
    @FXML private double velocityY;

    public Player() {
    }

    public void step() {
        this.setCenterX(this.getCenterX() + this.velocityX);
        this.setCenterY(this.getCenterY() + this.velocityY);
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

//Somehow set acceleration get and set method and something else.
//Put in Controller

}
