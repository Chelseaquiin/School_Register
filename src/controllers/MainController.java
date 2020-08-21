package controllers;

import com.jfoenix.controls.JFXTextField;
import database.DataBaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    DataBaseHandler dataBaseHandler;
    @FXML
    private ImageView addstudentimg;
    @FXML
    private ImageView addteacherimg;
    @FXML
    private JFXTextField studentinput;
    @FXML
    private Label studentname;
    @FXML
    private Label studentemail;
    @FXML
    private Label studentid;
    @FXML
    private Label studentclass;
    @FXML
    private JFXTextField teachersinput;
    @FXML
    private Label teachersname;
    @FXML
    private Label teachersemail;
    @FXML
    private Label teachersid;
    @FXML
    private Label teachersphone;
    @FXML
    private ImageView viewstudentimg;
    @FXML
    private ImageView viewteachersimg;
    @FXML
    private void loadAddStudents(MouseEvent event) {
        loadWindow("/views/addStudents.fxml", "Add Students");
    }
    @FXML
    private void loadAddTeachers(MouseEvent event) {
        loadWindow("/views/addteachers.fxml", "Add Teachers");
    }
    @FXML
    private void loadViewStudents(MouseEvent event) {
        loadWindow("/views/studentslist.fxml", "Student List");
    }
    @FXML
    private void loadViewTeachers(MouseEvent event) {
        loadWindow("/views/teacherslist.fxml", "Teachers List");
    }
    @FXML
    private void loadstudentinfo(ActionEvent event) throws SQLException {
        String id = studentinput.getText();
        if (id.isEmpty()) return;
        String qu = "SELECT * FROM STUDENTS WHERE id = '" + id + "'";
        ResultSet rs = dataBaseHandler.execQuery(qu);
        Boolean flag = false;
        while (rs.next()) {
            String adname = rs.getString("studentname");
            String sid = rs.getString("id");
            String sclass = rs.getString("studentclass");
            String email = rs.getString("studentemail");
            studentname.setText(adname);
            studentemail.setText(email);
            studentid.setText(sid);
            studentclass.setText(sclass);
            flag = true;
        }
        if (!flag) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("No student with that ID");
            alert.showAndWait();
        }
    }
    @FXML
    private void loadTeachersinfo(ActionEvent event) throws SQLException {
        String id = teachersinput.getText();
        if (id.isEmpty())return;
        String qu = "SELECT * FROM TEACHERS WHERE id = '" + id + "'";
        ResultSet rs = dataBaseHandler.execQuery(qu);
        Boolean flag = false;
        while (rs.next()) {
            String tdname = rs.getString("teachersname");
            String tid = rs.getString("id");
            String tphone = rs.getString("teachersphone");
            String email = rs.getString("teachersemail");
            teachersname.setText(tdname);
            teachersemail.setText(email);
            teachersid.setText(tid);
            teachersphone.setText(tphone);
            flag = true;
        }
        if (!flag) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("No teacher with that ID");
            alert.showAndWait();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataBaseHandler = DataBaseHandler.getInstance();
        showImage();
    }

    void loadWindow(String loc, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showImage() {
        try {
            Image image = new Image("resources/add.png");
            Image imagev = new Image("/resources/viewmode.png");
            Image images = new Image("/resources/settup.png");
            addstudentimg.setImage(image);
            addteacherimg.setImage(image);
            addstudentimg.setCache(true);
            addteacherimg.setCache(true);

            viewstudentimg.setImage(imagev);
            viewteachersimg.setImage(imagev);
            viewstudentimg.setCache(true);
            viewteachersimg.setCache(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
