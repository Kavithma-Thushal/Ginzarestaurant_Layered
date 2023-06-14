package lk.ijse.restaurant.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CashierloginFormController {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    public void cashierloginOnAction(ActionEvent event) throws IOException {
        if (txtUsername.getText().equals("") && txtPassword.getText().equals("")) {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/cashierdashboard_form.fxml"));
            Stage stage = (Stage) root.getScene().getWindow();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Cashier Dashboard");
        } else {
            new Alert(Alert.AlertType.ERROR, "please try again...!").show();
        }
    }

    public void createnewaccountOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/createnewaccount_form.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Create a new account");
        stage.setResizable(false);
        stage.show();
    }
}
