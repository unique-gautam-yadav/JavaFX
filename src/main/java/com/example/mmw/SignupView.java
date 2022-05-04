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

public class SignupView {

    @FXML
    private Button btn_signup;

    @FXML
    private Button exit;

    @FXML
    private TextField fullName;
    Stage stage;
    @FXML
    private Hyperlink link;

    @FXML
    private PasswordField pa;

    @FXML
    private PasswordField rePa;

    @FXML
    private Text status;

    @FXML
    private TextField un;

    @FXML
    void closeSignup(ActionEvent event) {
        closeSign();
    }
    void  closeSign(){
        stage = (Stage) btn_signup.getScene().getWindow();
        stage.close();
    }
    void loginLaunch() throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void openLogin(ActionEvent event) throws IOException {
        closeSign();
        loginLaunch();
    }

    @FXML
    void signup(ActionEvent event) {
        String fullNa = fullName.getText();
        String uname = un.getText();
        String Pass = pa.getText();
        String rePass = rePa.getText();
        String URL = "jdbc:mysql://localhost:3306/"; String user = "root"; String dbPass = "2580";
        try {
            Connection conn = DriverManager.getConnection(URL, user, dbPass);
            Statement stmt = conn.createStatement();
            String sql = "select count(*) as User from ims.user where uname ='"+uname+"'";
            ResultSet set = stmt.executeQuery(sql);
            set.next();
            int Num = set.getInt("User");
            if (Num > 0){
                status.setText("* username already taken !!");
            } else
            {
                sql = "insert into ims.user values ('"+uname+"', '"+rePass+"', '"+fullNa+"')";
                stmt.executeUpdate(sql);
                closeSign();
                loginLaunch();
            }
        } catch (SQLException | IOException ee){
            ee.printStackTrace();
        }
    }

}
