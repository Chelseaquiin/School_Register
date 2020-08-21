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

public class StudentlistController implements Initializable {

    ObservableList<Students> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Students, String> mailcol;

    @FXML
    private TableColumn<Students, String> clascol;

    @FXML
    private TableColumn<Students, String> namecol;

    @FXML
    private TableColumn<Students, String> idcol;

    @FXML
    private TableView tableview;

    @FXML
    private AnchorPane root;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        loadData();
    }
    private void initCol() {
        idcol.setCellValueFactory(new PropertyValueFactory<>("studentid"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("studentname"));
        clascol.setCellValueFactory(new PropertyValueFactory<>("studentclass"));
        mailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
    private void loadData() {
        DataBaseHandler dataBaseHandler = DataBaseHandler.getInstance();
        String query = "SELECT * FROM STUDENTS";
        ResultSet resultSet = dataBaseHandler.execQuery(query);

        try {
            while (resultSet.next()){
                String sname = resultSet.getString("studentname");
                String sclass = resultSet.getString("studentclass");
                String sid = resultSet.getString("id");
                String smail = resultSet.getString("studentemail");
                System.out.println(sname);
                System.out.println(sclass);
                list.add(new Students(sid, sname, sclass, smail));

            }
        }catch (SQLException e){
            Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, e);
        }

        tableview.getItems().setAll(list);

    }

    public static class Students{

        private final SimpleStringProperty studentid;
        private final SimpleStringProperty studentname;
        private final SimpleStringProperty studentclass;
        private final SimpleStringProperty email;

        public String getStudentid() {
            return studentid.get();
        }

        public String getStudentname() {
            return studentname.get();
        }

        public String getStudentclass() {
            return studentclass.get();
        }

        public String getEmail() {
            return email.get();
        }




        public Students(String studentid, String studentname, String studentclass, String email) {
            this.studentid = new SimpleStringProperty(studentid);
            this.studentname = new SimpleStringProperty(studentname);
            this.studentclass = new SimpleStringProperty(studentclass);
            this.email = new SimpleStringProperty(email);
        }
    }
}


