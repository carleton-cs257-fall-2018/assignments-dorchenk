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

    @FXML private Label playerVX;
    @FXML private Label playerVY;
    @FXML private Label playerAX;
    @FXML private Label playerAY;
    @FXML private Label objectVX;
    @FXML private Label objectVY;
    @FXML private Label objectAX;
    @FXML private Label objectAY;

    @FXML private Label eccLabel;

    @FXML private AnchorPane gameBoard;
    @FXML private Button pauseButton;
    @FXML private Button resetButton;
    @FXML private Button orbitButton;
    @FXML private Button bounceButton;
    private boolean paused;
    private boolean reset;
    private boolean orbiting;
    private boolean bounce;
    private boolean firstTime;

    private double distX;
    private double distY;
    private int count;
    private int maxCount;
    double playerMajor;
    double playerMinor;
    double objectMajor;
    double objectMinor;


    public Controller() {
        this.paused = false;
        this.reset = false;
        this.orbiting = false;
        this.bounce = true;
        this.firstTime = true;
    }

    private Timer timer;

    public void initialize() {
        this.startTimer();
//        this.gravityView = new GravityView();
        this.gravityModel = new GravityModel();
        this.update();
    }


    private void update() {
        //Maybe update the anchorpane labels here Hmmmmmmmmm

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

        long frameTimeInMilliseconds = (long) (1000.0 / FRAMES_PER_SECOND);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }

    private void reset() {
        player.setAccelX(0);
        player.setAccelY(0);
        player.setVelocityX(0);
        player.setVelocityY(0);
        player.setCenterX(0);
        player.setCenterY(0);
        player.setLayoutX(325);
        player.setLayoutY(350);

        object.setAccelX(0);
        object.setAccelY(0);
        object.setVelocityX(0);
        object.setVelocityY(0);
        object.setCenterX(0);
        object.setCenterY(0);
        object.setLayoutX(275);
        object.setLayoutY(300);

        //Would be better if I could figure out how to call Controller()
        this.paused = false;
        this.reset = false;
        this.orbiting = false;
        this.bounce = true;
        this.firstTime = true;

        this.pauseButton.setText("Pause");
        this.orbitButton.setText("Orbit us");
        this.bounceButton.setText("Bouncing");

        this.timer.cancel();
    }


    //Create a setter for centers and then in the model use that setter to create and elliptical orbit using the circles and eccentricty
    public double getCenterX(Body body) {
        double centerX = body.getCenterX() + body.getLayoutX();
        return centerX;
    }

    public double getCenterY(Body body) {
        double centerY = body.getCenterY() + body.getLayoutY();
        return centerY;
    }

    public double getDistX(double playerCenterX, double objectCenterX) {
        distX = (objectCenterX - playerCenterX);
        return distX;
    }

    public double getDistY(double playerCenterY, double objectCenterY) {
        distY = (objectCenterY - playerCenterY);
        return distY;
    }

    public void setPosition(Body body, double x, double y) {
        body.setCenterX(0);
        body.setCenterY(0);
        body.setLayoutX(x);
        body.setLayoutY(y);
    }

    public void counter(int max) {
        maxCount = max;
        count = 0;
    }

    public void increment() {
        if (count < maxCount) {
            count++;
        } else {
            count = 0;
        }
    }

    public double getCountercount() {
        return Math.toRadians(count);
    }


    private void updateAnimation() {
        double objectCenterX = getCenterX(this.object);
        double objectCenterY = getCenterY(this.object);
        double objectRadius = this.object.getRadius();
        //--------------------------------------------------------------------------
        double playerCenterX = getCenterX(this.player);
        double playerCenterY = getCenterY(this.player);
        double playerRadius = this.player.getRadius();

        getDistX(playerCenterX, objectCenterX);
        getDistY(playerCenterY, objectCenterY);

        double D = gravityModel.getDistance(distX, distY);
        double grav = gravityModel.getForceG(this.player, this.object, D);
        double escV = gravityModel.getVesc(this.player, this.object, D);
        double escD = gravityModel.getDesc(this.player, this.object, escV);

        //applying gravity
        if (orbiting == false) {
            if (D <= escD) {
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
                //--------------------------------------------------------------------------------------------------
                player.setVelocityX((this.player.getVelocityX() + this.player.getAccelX()));
                player.setVelocityY((this.player.getVelocityY() + this.player.getAccelY()));
            }
        }

        if (orbiting) {
            if (firstTime) {
                gravityModel.setEcc(0.5);
                escV = gravityModel.getVesc(player, object, D);
                escD = gravityModel.getDesc(player, object, escV);

                if (distX == 0 || Math.abs(distX) > escD) {              //Sets bodies on same X axis
                    setPosition(object, (escD / 2), playerCenterY);
                    objectCenterX = getCenterX(this.object);
                } else if (distY != 0) {
                    object.setCenterY(0);
                    object.setLayoutY(playerCenterY);
                    objectCenterY = getCenterY(this.object);
                }


                getDistX(playerCenterX, objectCenterX);
                getDistY(playerCenterY, objectCenterY);

                escV = gravityModel.getVesc(player, object, D);
                if (player.getVelocityX() > escV) {
                    player.setVelocityX((player.getVelocityX() / 2));
                }
                if (player.getVelocityY() > escV) {
                    player.setVelocityY((player.getVelocityY() / 2));
                }
                if (object.getVelocityX() > escV) {
                    object.setVelocityX((object.getVelocityX() / 2));
                }
                if (object.getVelocityY() > escV) {
                    object.setVelocityY((object.getVelocityY() / 2));
                }

                count = 0;
                counter(360);

                double playerRmax = gravityModel.getBaryCenter(player, object, distX);
                playerMajor = gravityModel.getMajorA(player, object, distX);
                playerMinor = gravityModel.getMinorB(playerRmax, playerMajor, gravityModel.getEcc());
                double playerOrbitCenter = Math.abs(playerRmax - playerMajor);

                double objectRmax = gravityModel.getBaryCenter(object, player, distX);
                objectMajor = gravityModel.getMajorA(object, player, distX);
                objectMinor = gravityModel.getMinorB(objectRmax, objectMajor, distX);
                double objectOrbitCenter = Math.abs(objectRmax - objectMajor);

                player.setAccelY(0);
                player.setAccelX(0);
                object.setAccelX(0);
                object.setAccelY(0);
                this.firstTime = false;
            }

            eccLabel.setText(String.format("Eccentricty: %f", gravityModel.getEcc()));
            double gravParam = gravityModel.getGravParam(player, object);

            setPosition(player, (gravityModel.getPosX(getCountercount(), playerMajor) + playerCenterX), (gravityModel.getPosY(getCountercount(), playerMinor) + playerCenterY));
            setPosition(object, (gravityModel.getPosX(getCountercount(), objectMajor) + objectCenterX), (gravityModel.getPosY(getCountercount(), objectMinor) + objectCenterY));


            increment();
            //orbiting ends here
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

    //-----------------------------------------------------------------Update labels here
        playerVX.setText(String.format("VelocityX: %f",player.getVelocityX()));
        playerVY.setText(String.format("VelocityY: %f",player.getVelocityY()));
        playerAX.setText(String.format("AccelX: %f",player.getAccelX()));
        playerAY.setText(String.format("AccelY: %f",player.getAccelY()));
        objectVX.setText(String.format("VelocityX: %f",object.getVelocityX()));
        objectVY.setText(String.format("VelocityY: %f",object.getVelocityY()));
        objectAX.setText(String.format("AccelX: %f",object.getAccelX()));
        objectAY.setText(String.format("AccelY: %f",object.getAccelY()));


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
            this.startTimer();
        } else {
            this.resetButton.setText("Begin");
            this.reset();
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
        firstTime = true;
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
