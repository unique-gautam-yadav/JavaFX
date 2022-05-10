package com.example.mmw;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MainView implements Initializable {
    public Button btn_reload;
    public Text text_welcome;
    @FXML
    private TableView<DatafromDatabase> data;

    @FXML
    private TableColumn<DatafromDatabase, Integer> sr;

    @FXML
    private TableColumn<DatafromDatabase, String> title;
    @FXML

    private TableColumn<DatafromDatabase, String> desc;
    @FXML
    private TableColumn<DatafromDatabase, String> amount;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_add;

    @FXML
    private Button exit;

    @FXML
    private TextField in_amout;

    @FXML
    private TextField in_decs;

    @FXML
    private TextField in_title;

    @FXML
    void addEntry(ActionEvent event) {
        String tit = in_title.getText();
        String decript = in_decs.getText();
        String amt = in_amout.getText();
        String user = LoginController.Uname;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "2580");
            Statement stmt = conn.createStatement();
            ResultSet st = stmt.executeQuery("select count(*) as n from ims.data where uname = '"+LoginController.Uname+"'");
            st.next();
            int num = st.getInt("n");
            num += 1;
            String sql = "insert into ims.data values ("+num+", '"+user+"', '"+tit+"', '"+decript+"', '"+amt+"')";
            stmt.execute(sql);
            in_title.setText("");
            in_amout.setText("");
            in_decs.setText("");
            DatafromDatabase itm = new DatafromDatabase(num, tit, decript, amt);
            data.getItems().add(itm);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    ObservableList<DatafromDatabase> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sr.setCellValueFactory(new PropertyValueFactory<DatafromDatabase, Integer>("sr"));
        title.setCellValueFactory(new PropertyValueFactory<DatafromDatabase , String>("title"));
        desc.setCellValueFactory(new PropertyValueFactory<DatafromDatabase, String>("decs"));
        amount.setCellValueFactory(new PropertyValueFactory<DatafromDatabase, String>("amount"));
    }
    public void fetchAll(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "2580");
            Statement stmt = conn.createStatement();
            String sql = "select * from ims.data where uname = '"+LoginController.Uname+"'";
            ResultSet st = stmt.executeQuery(sql);
            while (st.next()){
                Integer nn = st.getInt("sr");
                String t = st.getString("title");
                String d = st.getString("descript");
                String a = st.getString("amount");
                DatafromDatabase item = new DatafromDatabase(nn, t, d, a);
                data.setItems(null);
                data.setItems(list);
                data.getItems().add(item);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void reload(ActionEvent event) {
        fetchAll();
        String p =  text_welcome.getText();
        String af = p + ", " + LoginController.FulName;
        text_welcome.setText(af);
        btn_reload.setVisible(false);
    }
}
