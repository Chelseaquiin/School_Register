package controllers;

import com.jfoenix.controls.JFXTextField;
import database.DataBaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTeacherController implements Initializable {
    DataBaseHandler dataBaseHandler;

    @Override
    public void initialize(URL event, ResourceBundle rb) {
        dataBaseHandler = DataBaseHandler.getInstance();
    }

    @FXML
    private JFXTextField teachersID;
    @FXML
    private JFXTextField teachersName;
    @FXML
    private JFXTextField teachersphone;
    @FXML
    private JFXTextField teachersemail;
    @FXML
    private AnchorPane root;

    @FXML
    private void save(ActionEvent event) {
        String teacherid = teachersID.getText();
        String teachername = teachersName.getText();
        String teacherphone = teachersphone.getText();
        String teacheremail = teachersemail.getText();
//        System.out.println(patientID.getText().toString());
        if (teacherid.isEmpty() || teachername.isEmpty() || teacherphone.isEmpty() || teacheremail.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter all fields");
            alert.showAndWait();
            return;
        }
            String query = "INSERT INTO TEACHERS VALUES ("+
                    "'"+ teacherid +"',"+
                    "'"+ teachername +"',"+
                    "'"+ teacherphone +"',"+
                    "'"+ teacheremail +"'"+
                    ")";
            System.out.println(query);
            if(dataBaseHandler.execAction(query)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Saved");
                alert.showAndWait();
                cancel(event);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("An Error occured");
                alert.showAndWait();
                return;
            }

    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
}
