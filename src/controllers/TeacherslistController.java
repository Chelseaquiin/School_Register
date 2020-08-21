package controllers;


import database.DataBaseHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeacherslistController implements Initializable {

    ObservableList<Teachers> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Teachers, String> mailcol;

    @FXML
    private TableColumn<Teachers, String> phonecol;

    @FXML
    private TableColumn<Teachers, String> namecol;

    @FXML
    private TableColumn<Teachers, String> idcol;

    @FXML
    private TableView tableview;

    @FXML
    private AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        loadData();
    }
    private void initCol() {
        idcol.setCellValueFactory(new PropertyValueFactory<>("teachersid"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("teachersname"));
        phonecol.setCellValueFactory(new PropertyValueFactory<>("teachersphone"));
        mailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
    private void loadData() {
        DataBaseHandler dataBaseHandler = DataBaseHandler.getInstance();
        String query = "SELECT * FROM TEACHERS";
        ResultSet resultSet = dataBaseHandler.execQuery(query);

        try {
            while (resultSet.next()){
                String sname = resultSet.getString("teachersname");
                String sclass = resultSet.getString("teachersphone");
                String sid = resultSet.getString("id");
                String smail = resultSet.getString("teachersemail");
                System.out.println(sname);
                System.out.println(sclass);
                list.add(new Teachers(sid, sname, sclass, smail));

            }
        }catch (SQLException e){
            Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, e);
        }

        tableview.getItems().setAll(list);

    }

    public static class Teachers {

        private final SimpleStringProperty teachersid;
        private final SimpleStringProperty teachersname;
        private final SimpleStringProperty teachersphone;
        private final SimpleStringProperty email;

        public String getTeachersid() {
            return teachersid.get();
        }

        public String getTeachersname() {
            return teachersname.get();
        }

        public String getTeachersphone() {
            return teachersphone.get();
        }

        public String getEmail() {
            return email.get();
        }




        public Teachers(String teachersid, String teachersname, String teachersphone, String email) {
            this.teachersid = new SimpleStringProperty(teachersid);
            this.teachersname = new SimpleStringProperty(teachersname);
            this.teachersphone = new SimpleStringProperty(teachersphone);
            this.email = new SimpleStringProperty(email);
        }
    }
}


