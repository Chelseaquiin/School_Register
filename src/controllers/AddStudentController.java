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

public class AddStudentController implements Initializable {
    DataBaseHandler handler;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handler = DataBaseHandler.getInstance() ;
    }

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField studentClass;

    @FXML
    private JFXTextField studentName;

    @FXML
    private JFXTextField studentID;

    @FXML
    private AnchorPane root;

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void save(ActionEvent event) {
        String mName = studentName.getText();
        String mID = studentID.getText();
        String mClass = studentClass.getText();
        String mEmail = email.getText();

        Boolean flag = mEmail.isEmpty() || mName.isEmpty() || mClass.isEmpty() || mID.isEmpty();
        if (flag) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter all fields");
            alert.showAndWait();
            return;
        }

        String query = "INSERT INTO STUDENTS VALUES ("+
                "'"+ mID +"',"+
                "'"+ mName +"',"+
                "'"+ mClass +"',"+
                "'"+ mEmail +"'"+
                ")";
        System.out.println(query);
        if (handler.execAction(query)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Saved");
            alert.showAndWait();
            cancel(event);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Error occured");
            alert.showAndWait();
            return;
        }

    }

}
