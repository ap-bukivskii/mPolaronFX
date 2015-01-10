package GUI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import polaron.Worker;

/**
 * Created by tolik_b on 1/6/15.
 */
public class Controller {
    private double W0;
    private double Ta;
    private boolean[] params = new boolean[8];

    @FXML
    private CheckBox chbSDD;

    @FXML
    private CheckBox chbDSA;

    @FXML
    private TextField txtW0;

    @FXML
    private TextField txtTa;

    @FXML
    private CheckBox chbT;

    @FXML
    private CheckBox chbD;

    @FXML
    private Button btnCalculate;

    @FXML
    private CheckBox chbEp;

    @FXML
    private CheckBox chbDfl;

    @FXML
    private CheckBox chbDex;

    @FXML
    private Button btnExit;

    @FXML
    private CheckBox chbDAS;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert chbSDD != null : "fx:id=\"chbSDD\" was not injected: check your FXML file 'style.fxml'.";
        assert chbDSA != null : "fx:id=\"chbDSA\" was not injected: check your FXML file 'style.fxml'.";
        assert txtW0 != null : "fx:id=\"txtW0\" was not injected: check your FXML file 'style.fxml'.";
        assert txtTa != null : "fx:id=\"txtTa\" was not injected: check your FXML file 'style.fxml'.";
        assert chbT != null : "fx:id=\"chbT\" was not injected: check your FXML file 'style.fxml'.";
        assert chbD != null : "fx:id=\"chbD\" was not injected: check your FXML file 'style.fxml'.";
        assert btnCalculate != null : "fx:id=\"btnCalculate\" was not injected: check your FXML file 'style.fxml'.";
        assert chbEp != null : "fx:id=\"chbEp\" was not injected: check your FXML file 'style.fxml'.";
        assert chbDfl != null : "fx:id=\"chbDfl\" was not injected: check your FXML file 'style.fxml'.";
        assert chbDex != null : "fx:id=\"chbDex\" was not injected: check your FXML file 'style.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'style.fxml'.";
        assert chbDAS != null : "fx:id=\"chbDAS\" was not injected: check your FXML file 'style.fxml'.";


        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        btnCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

//TODO-me check for empty or invalid fields
                params[0]= chbT.isSelected();
                params[1]= chbEp.isSelected();
                params[2]= chbDex.isSelected();
                params[3]= chbDfl.isSelected();
                params[4]= chbD.isSelected();
                params[5]= chbDAS.isSelected();
                params[6]= chbDSA.isSelected();
                params[7]= chbSDD.isSelected();
                W0 = Double.parseDouble(txtW0.getCharacters().toString());
                W0/= 1000;
                Ta = Double.parseDouble(txtTa.getCharacters().toString());

                Worker wrk = new Worker(params,W0,Ta);
                wrk.run();
            }
        });

    }

}