/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxapplication;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author lakshan
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button closeBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private PasswordField pwdTxt;

    @FXML
    private TextField unameTxt;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private int result2;

    public void adminLogin() {
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        connect = DbConnection.connectDb();

        try {
            Alert alert;

            if (unameTxt.getText().isEmpty() || pwdTxt.getText().isEmpty()) {

                //validating empty
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, unameTxt.getText());
                prepare.setString(2, pwdTxt.getText());

                result = prepare.executeQuery();

                if (result.next()) {

                    //then proceed to the dashbord
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Login successfull!");
                    alert.showAndWait();

                    //hide the login form
                    loginBtn.getScene().getWindow().hide();

                    //link dashBord form                    
                    Parent root = FXMLLoader.load(getClass().getResource("dashBord.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    //error msg show
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("error");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username or password");
                    alert.showAndWait();
                }

            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
