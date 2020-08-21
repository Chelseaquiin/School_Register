package database;

import javax.swing.*;
import java.sql.*;

public final class DataBaseHandler {
    private static DataBaseHandler handler;

    private static final String DB_URL = "jdbc:derby:database/student;create=true";
    private static Connection conn = null;
    private static Statement statement = null;

    private DataBaseHandler() {
        creatConnection();
        setupStudentTable();
        setUpTeachersTable();

    }
    public static DataBaseHandler getInstance(){
        if(handler == null){
            handler = new DataBaseHandler();
        }
        return handler;
    }

    void creatConnection(){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void setupStudentTable(){
        String TABLE_NAME = "Students";
        try{
            statement = conn.createStatement();
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            ResultSet tables = databaseMetaData.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()){
                System.out.println("Table " + TABLE_NAME + " now exist. Let's Go" );
            }else{
                statement.execute("CREATE TABLE " + TABLE_NAME.toUpperCase() + "("
                        +" id varchar(200) primary key, \n"
                        +" studentname varchar(200),\n"
                        +" studentclass varchar(200),\n"
                        +" studentemail varchar(200)"
                        + " )");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getMessage() +" --- setup Database ");
        }finally {

        }
    }



    void setUpTeachersTable(){
        String TABLENAME = "TEACHERS";
        try{
            statement = conn.createStatement();
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            ResultSet tables = databaseMetaData.getTables(null, null, TABLENAME.toUpperCase(), null);

            if (tables.next()){
                System.out.println("Table " + TABLENAME + " now exist. Let's Go" );
            }else{
                statement.execute("CREATE TABLE " + TABLENAME + "("
                        +" id varchar(200) primary key, \n"
                        +" teachersname varchar(200),\n"
                        +" teachersphone varchar(20),\n"
                        +" teachersemail varchar(100)\n"
                        + " )");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getMessage() +" --- setup Database ");
        }finally {

        }
    }

    public ResultSet execQuery(String query){
        ResultSet resultSet;
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Exception at execQuery:dataHandler" + e.getLocalizedMessage());
            return null;
        }finally {

        }
        return resultSet;
    }
    public boolean execAction(String query){
        try {
            statement = conn.createStatement();
            statement.execute(query);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(),  "Error occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + e.getLocalizedMessage());
            return false;
        }finally {

        }
//        return resultSet;
    }
}
