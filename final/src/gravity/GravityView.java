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


public class GravityView {

    @FXML private Object player;
    @FXML private Object object;

    public GravityView() {
    }

    public void update(GravityModel model) {
        model.getDistance(player, object);
        }
}
