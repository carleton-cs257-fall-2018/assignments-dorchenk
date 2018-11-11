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

import java.util.Timer;
import java.util.TimerTask;

public class Controller implements EventHandler<KeyEvent> {
    final private double FRAMES_PER_SECOND = 20.0;

    @FXML private GravityView gravityView;
    private GravityModel gravityModel;

    @FXML private Object player;
    @FXML private Object object;


    @FXML private AnchorPane gameBoard;
    @FXML private Button pauseButton;
    private boolean paused;

    public Controller() {
        this.paused = false;
    }

    private Timer timer;

    public void initialize() {
        this.startTimer();
        this.gravityModel = new GravityModel();
//        this.update();
    }

    private void update() {
        gravityView.update(gravityModel);


    }

    private void startTimer() {
        this.timer = new java.util.Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        updateAnimation();
                    }
                });
            }
        };

        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }



    private void updateAnimation() {
        double objectCenterX = this.object.getCenterX() + this.object.getLayoutX();
        double objectCenterY = this.object.getCenterY() + this.object.getLayoutY();
        double objectRadius = this.object.getRadius();
        //--------------------------------------------------------------------------
        double playerCenterX = this.player.getCenterX() + this.player.getLayoutX();
        double playerCenterY = this.player.getCenterY() + this.player.getLayoutY();
        double playerRadius = this.player.getRadius();





        // Bounce off player. NOTE: THIS IS A BAD BOUNCING ALGORITHM. The object can badly
        // overshoot the player and still "bounce" off it. See if you can come up with
        // something better.

        /*if (objectCenterX >= playerCenterX && objectCenterX < playerCenterY && this.object.getVelocityY() > 0) {
            double objectBottom = objectCenterY + objectRadius;
            if (objectBottom >= playerRadius) {
                this.object.setVelocityY(-this.object.getVelocityY());
                this.score++;
                this.scoreLabel.setText(String.format("Bounces: %d", this.score));
            }

        }*/

        // Bounce off walls
        //-------------------------------------------------Instead of bounce consider dragging on wall
        double objectVelocityX = this.object.getVelocityX();
        double objectVelocityY = this.object.getVelocityY();
       /* if (objectCenterX + objectRadius >= this.gameBoard.getWidth() && objectVelocityX > 0) {
            this.object.setVelocityX(-objectVelocityX);
        } else if (objectCenterX - objectRadius < 0 && objectVelocityX < 0) {
            this.object.setVelocityX(-objectVelocityX);
        } else if (objectCenterY + objectRadius >= this.gameBoard.getHeight() && objectVelocityY > 0) {
            this.object.setVelocityY(-objectVelocityY);
        } else if (objectCenterY - objectRadius < 0 && objectVelocityY < 0) {
            this.object.setVelocityY(-objectVelocityY);
        }*/

        // Move the sprite. ------------------------------------------------------Interesting
        this.object.step();
    }


    @Override
    public void handle(KeyEvent keyEvent) {                                     //basically update and fix me for velocity changes
        KeyCode code = keyEvent.getCode();
        //----------------------------------------------------------------------Player controls starts here
        double playerVelocityX = this.player.getVelocityX();
        double playerVelocityY = this.player.getVelocityY();

        double playerAccel = this.player.getLayoutX();
        double stepSize = 15.0;                                                 //should change acceleration
        if (code == KeyCode.LEFT || code == KeyCode.A) {
            // move player left
            if (playerAccel > stepSize) {
                this.player.setLayoutX(this.player.getLayoutX() - stepSize);
            } else {
                this.player.setLayoutX(0);
            }
            keyEvent.consume();
        } else if (code == KeyCode.RIGHT || code == KeyCode.D) {
            // move player right
            if (playerAccel + this.player.getRadius() + stepSize < this.gameBoard.getWidth()) {
                this.player.setLayoutX(this.player.getLayoutX() + stepSize);
            } else {
                this.player.setLayoutX(this.gameBoard.getWidth() - this.player.getRadius());
            }
            keyEvent.consume();
        }
    }

    public void onPauseButton(ActionEvent actionEvent) {
        if (this.paused) {
            this.pauseButton.setText("Pause");
            this.startTimer();
        } else {
            this.pauseButton.setText("Continue");
            this.timer.cancel();
        }
        this.paused = !this.paused;
    }

}
