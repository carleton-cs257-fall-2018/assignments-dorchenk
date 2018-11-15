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
import javafx.scene.control.Slider;
import java.lang.Math;

import java.util.List;
import java.util.ArrayList;

import java.util.Timer;
import java.util.TimerTask;

public class Controller implements EventHandler<KeyEvent> {
    final private double FRAMES_PER_SECOND = 20.0;

//    @FXML private GravityView gravityView;
    private GravityView gravityView;
    private GravityModel gravityModel;

    @FXML private Body player;
    @FXML private Body object;

    @FXML private Label playerData;


    @FXML private AnchorPane gameBoard;
    @FXML private Button pauseButton;
    @FXML private Button resetButton;
    @FXML private Button orbitButton;
    @FXML private Button bounceButton;
    private boolean paused;
    private boolean reset;
    private boolean orbiting;
    private boolean bounce;


    public Controller() {
        this.paused = false;
        this.reset = false;
        this.orbiting = false;
        this.bounce = true;
    }

    private Timer timer;

    public void initialize() {
        this.startTimer();
        this.gravityView = new GravityView();
        this.gravityModel = new GravityModel();
      this.update();
    }


    private void update() {
//        gravityView.update(gravityModel, player, object); //-----------Could probably use form to have model update the view
//        playerData.setText(String.format("Mass: %d",  player.getMass()));     //Fix this. Idk why it won't work.
//        gravityModel.update(gravityView(player, object));
        double massPlayer = this.player.getMass();
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


//        private double massPlayer = gravityModel.getSingleMass(player);

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


        //preparation for attempt to apply gravity
        double distX = (objectCenterX - playerCenterX);
        double distY = (objectCenterY - playerCenterY);

        double D = gravityModel.getDistance(this.player, this.object, distX, distY);
        double grav = gravityModel.getForceG(this.player, this.object, D);
        double escV = gravityModel.getVesc(this.player, this.object, D);
        double ecsD = gravityModel.getDesc(this.player, this.object);

        double vpx = player.getVelocityX();
        double vpy = player.getVelocityY();

        double vox = object.getVelocityX();
        double voy = object.getVelocityY();


        //attempt to apply gravity
//        if (true == true) {
        if (D <= ecsD) {
//        if ((vp <= escV) || (vo <= escV)) {
            if (distX < 0) { //then object is to the left and needs to be pulled to the right
                object.setAccelX(grav);
                player.setAccelX((-1) * (grav));
                if (distY < 0) { //then according to this model object is above and needs to be pulled down
                    object.setAccelY((grav));
                    player.setAccelY((-grav));
                } else { //pull object up
                    object.setAccelY((-1) * (grav));
                    player.setAccelY((grav));
                }
         /*   } else if (distX == 0 && distY == 0) {      //Merger happens?? Woah... //consider smaller Body's accel becoming = to the larger Bodys accel
                object.setAccelY((0));
                player.setAccelY((0));
                double bMass = gravityModel.getLargerMass(player, object); //Just take larger velocity
              */
            } else { //for when the object is to the right of player and needs to be pulled left
                object.setAccelX((-1) * (grav));
                player.setAccelX((grav));
                if (distY < 0) { //then according to this model object is above and needs to be pulled down
                    object.setAccelY((grav));
                    player.setAccelY((-1) * (grav));
                } else { //pull object up
                    object.setAccelY((-1) * (grav));
                    player.setAccelY((grav));
                }
            }
            object.setVelocityX((this.object.getVelocityX() + this.object.getAccelX()));
            object.setVelocityY((this.object.getVelocityY() + this.object.getAccelY()));
            double nvox = object.getVelocityX();
            double nvoy = object.getVelocityY();

            player.setVelocityX((this.player.getVelocityX() + this.player.getAccelX()));
            player.setVelocityY((this.player.getVelocityY() + this.player.getAccelY()));
            double nvpx = player.getVelocityX();
            double nvpy = player.getVelocityY();

        }

        //bounce for object
        if(bounce) {
            double objectVelocityX = this.object.getVelocityX();
            double objectVelocityY = this.object.getVelocityY();

            if (objectCenterX + objectRadius >= this.gameBoard.getWidth() && objectVelocityX > 0) {
                this.object.setVelocityX(-objectVelocityX);
            } else if (objectCenterX - objectRadius < 0 && objectVelocityX < 0) {
                this.object.setVelocityX(-objectVelocityX);
            } else if (objectCenterY + objectRadius >= this.gameBoard.getHeight() && objectVelocityY > 0) {
                this.object.setVelocityY(-objectVelocityY);
            } else if (objectCenterY - objectRadius < 0 && objectVelocityY < 0) {
                this.object.setVelocityY(-objectVelocityY);
            }
            //end bounce for object

            //bounce for player
            double playerVelocityX = this.player.getVelocityX();
            double playerVelocityY = this.player.getVelocityY();

            if (playerCenterX + playerRadius >= this.gameBoard.getWidth() && playerVelocityX > 0) {
                this.player.setVelocityX(-playerVelocityX);
            } else if (playerCenterX - playerRadius < 0 && playerVelocityX < 0) {
                this.player.setVelocityX(-playerVelocityX);
            } else if (playerCenterY + playerRadius >= this.gameBoard.getHeight() && playerVelocityY > 0) {
                this.player.setVelocityY(-playerVelocityY);
            } else if (playerCenterY - playerRadius < 0 && playerVelocityY < 0) {
                this.player.setVelocityY(-playerVelocityY);
            }
            //end bounce for player
        }

        // Move the sprite. ------------------------------------------------------Interesting

        this.object.step();
        this.player.step();
    }


    @Override
    public void handle(KeyEvent keyEvent) {                                     //basically update and fix me for velocity changes
        KeyCode code = keyEvent.getCode();
        //----------------------------------------------------------------------Player controls starts here

        double playerAccelX = this.player.getAccelX();
        double playerAccelY = this.player.getAccelY();


        if (code == KeyCode.LEFT || code == KeyCode.A) {
            // move player left
            double newAccel = playerAccelX - 2;
            player.setAccelX(newAccel);
            keyEvent.consume();
        } else if (code == KeyCode.RIGHT || code == KeyCode.D) {
            // move player right
            double newAccel = playerAccelX + 2;
            player.setAccelX(newAccel);
            keyEvent.consume();
        } else if (code == KeyCode.DOWN || code == KeyCode.S) {
            // move player down
            double newAccel = playerAccelY + 2;
            player.setAccelY(newAccel);
            keyEvent.consume();
        } else if (code == KeyCode.UP || code == KeyCode.W) {
            //move player up
            double newAccel = playerAccelY - 2;
            player.setAccelY(newAccel);
            keyEvent.consume();
        } else if (code == KeyCode.F) {
            //stop player motion!
            player.setVelocityX(0);
            player.setVelocityY(0);
            player.setAccelX(0);
            player.setAccelY(0);
            keyEvent.consume();
        } else if (code == KeyCode.G) {
            //stop player motion!
            object.setVelocityX(0);
            object.setVelocityY(0);
            object.setAccelX(0);
            object.setAccelY(0);
            keyEvent.consume();
        }
        player.setVelocityX((this.player.getVelocityX() + this.player.getAccelX()));
        player.setVelocityY((this.player.getVelocityY() + this.player.getAccelY()));
        this.player.step();

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

    public void onResetButton(ActionEvent actionEvent) {
        if (this.reset) {
            this.resetButton.setText("Reset");
        } else {
            this.resetButton.setText("reset");
        }
        this.reset = !this.reset;
    }

    public void onOrbitButton(ActionEvent actionEvent) {
        if (this.orbiting) {
            this.orbitButton.setText("Orbit us");
        } else {
            this.orbitButton.setText("Un-Orbit us");
        }
        this.orbiting = !this.orbiting;
    }

    public void onBounceButton(ActionEvent actionEvent) {
        if (this.bounce) {
            this.bounceButton.setText("Not Bouncing");
        } else {
            this.bounceButton.setText("Bouncing");
        }
        this.bounce = !this.bounce;
    }


    /*public void onPlayerMass(ActionEvent actionEvent) {
        if (this.massPlayer != player.getMass()) {
            player.setMass(this.massPlayer);
        }
    }
*/
}
