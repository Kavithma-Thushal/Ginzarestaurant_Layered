package lk.ijse.restaurant.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.restaurant.dto.Customer;
import lk.ijse.restaurant.view.CustomerTM;
import lk.ijse.restaurant.model.CustomerModel;
import lk.ijse.restaurant.util.Validation;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ManagecustomersFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtNic;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtContact;
    @FXML
    private TextField txtAddress;
    @FXML
    private TableView tblCustomer;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colNic;
    @FXML
    private TableColumn colEmail;
    @FXML
    private TableColumn colContact;
    @FXML
    private TableColumn colAddress;
    @FXML
    private Label lbldateandtime;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    private LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern name = Pattern.compile("^([A-Z a-z]{4,40})$");
    Pattern nic = Pattern.compile("^([0-9]{12}|[0-9V]{10})$");
    Pattern email = Pattern.compile("^[a-z A-Z 0-9 ._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$");
    Pattern contact = Pattern.compile("^(07(0|1|2|4|5|6|7|8)[0-9]{7})$");
    Pattern address = Pattern.compile("^([A-Z a-z]{4,40})$");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd      hh:mm");
        Date date = new Date();
        lbldateandtime.setText(simpleDateFormat.format(date));

        getAll();
        setCellValueFactory();
        disableButtons();
        storeValidations();
    }

    private void disableButtons() {
        btnSave.setDisable(true);

    }

    private void clearAllTxt() {
        txtId.clear();
        txtName.clear();
        txtNic.clear();
        txtEmail.clear();
        txtContact.clear();
        txtAddress.clear();

        disableButtons();
        txtName.requestFocus();
        setBorders(txtId, txtName, txtNic, txtEmail, txtContact, txtAddress);
    }

    private void storeValidations() {
        map.put(txtName, name);
        map.put(txtNic, nic);
        map.put(txtEmail, email);
        map.put(txtContact, contact);
        map.put(txtAddress, address);
    }

    public void setBorders(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.setStyle("-fx-border-color: transparent");
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void getAll() {
        try {
            ObservableList<CustomerTM> observableList = FXCollections.observableArrayList();
            List<Customer> customerList = CustomerModel.getAll();

            for (Customer customer : customerList) {
                observableList.add(new CustomerTM(
                        customer.getId(),
                        customer.getName(),
                        customer.getNic(),
                        customer.getEmail(),
                        customer.getContact(),
                        customer.getAddress()
                ));
            }
            tblCustomer.setItems(observableList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void txtKeyRelease(KeyEvent keyEvent) {
        Object response = Validation.validate(map, btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField txtnext = (TextField) response;
                txtnext.requestFocus();
            }
        }
    }

    @FXML
    private void saveOnAction(ActionEvent event) {

        try {
            Customer customer = new Customer(
                    txtId.getText(),
                    txtName.getText(),
                    txtNic.getText(),
                    txtEmail.getText(),
                    txtContact.getText(),
                    txtAddress.getText()
            );

            if (CustomerModel.save(customer) > 0) {
                //setCellValueFactory();

                /*ObservableList<CustomerTM> observableList = FXCollections.observableArrayList();
                observableList.add(new CustomerTM(
                        customer.getId(),
                        customer.getName(),
                        customer.getNic(),
                        customer.getEmail(),
                        customer.getContact(),
                        customer.getAddress()
                ));
                tblCustomer.setItems(observableList);*/

                new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully...!").show();
                tblCustomer.refresh();
                getAll();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
        clearAllTxt();
        txtName.requestFocus();
    }

    @FXML
    private void searchOnAction(ActionEvent event) {

        try {
            Customer customer = CustomerModel.search(txtId.getText());
            if (customer != null) {
                txtName.setText(customer.getName());
                txtNic.setText(customer.getNic());
                txtEmail.setText(customer.getEmail());
                txtContact.setText(customer.getContact());
                txtAddress.setText(customer.getAddress());
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void updateOnAction(ActionEvent event) {

        try {
            Customer customer = new Customer(
                    txtId.getText(),
                    txtName.getText(),
                    txtNic.getText(),
                    txtEmail.getText(),
                    txtContact.getText(),
                    txtAddress.getText()
            );

            if (CustomerModel.update(customer) > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully...!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
        clearAllTxt();
    }

    @FXML
    private void deleteOnAction(ActionEvent event) {
        try {
            if (CustomerModel.delete(txtId.getText()) > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted Successfully...!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
        clearAllTxt();
    }

    @FXML
    private void backOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/cashierdashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Cashier Dashboard");
        stage.setResizable(false);
        stage.show();
    }
}
