package com.example.mmw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    public Hyperlink link;
    @FXML
    private Button btn_login;

    @FXML
    private Button exit;

    @FXML
    private PasswordField pa;

    @FXML
    private Text status;
    public static String FulName;
    public static String Uname;
    @FXML
    private TextField un;
    Stage stage;
    @FXML
    void closeWin(ActionEvent event) {
        stage = (Stage) un.getScene().getWindow();
        stage.close();
    }

    @FXML
    void logIn(ActionEvent event) throws IOException{
        System.out.println("Login Button Pressed");
        String user = un.getText();
        String password = pa.getText();
        final String url = "jdbc:mysql://localhost:3306/";
        final String u = "root";
        final String  pas = "2580";
        try {
            Connection con = DriverManager.getConnection(url, u, pas);
            Statement stmt = con.createStatement();
            String sql = "select count(*) as Name from ims.user where uname='"+user+"' && pass = '"+password+"'";
            String sql2 = "select Name, uname from ims.user where uname='"+user+"' && pass = '"+password+"'";
            ResultSet set = stmt.executeQuery(sql);
            set.next();
            int no = set.getInt("Name");
            if (no > 0){
                ResultSet set2 = stmt.executeQuery(sql2);
                set2.next();
                FulName = set2.getString("Name");
                Uname = set2.getString("uname");
                stage = (Stage) un.getScene().getWindow();
                stage.close();
                System.out.println("Display Closed");
                FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("MainView.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 700, 500);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
            }
            else{
                status.setText("* username password mismatched !!");
            }
            un.setText(null);
            pa.setText(null);
            con.close();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }
    public void openSignup(ActionEvent event) throws IOException {
        stage = (Stage) un.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("signup-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
