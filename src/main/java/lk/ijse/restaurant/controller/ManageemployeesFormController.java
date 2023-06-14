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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.restaurant.bo.BoFactory;
import lk.ijse.restaurant.bo.custom.EmployeeBO;
import lk.ijse.restaurant.dto.EmployeeDTO;
import lk.ijse.restaurant.view.EmployeeTM;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ManageemployeesFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtContact;
    @FXML
    private TextField txtJobrole;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TableView tblemployee;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colContact;
    @FXML
    private TableColumn colJobrole;
    @FXML
    private TableColumn colUsername;
    @FXML
    private TableColumn colPassword;
    @FXML
    private Label lbldateandtime;

    private EmployeeBO employeeBO= BoFactory.getBoFactory().getBO(BoFactory.BOTypes.EMPLOYEE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd      hh:mm");
        Date date = new Date();
        lbldateandtime.setText(simpleDateFormat.format(date));

        getAll();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colJobrole.setCellValueFactory(new PropertyValueFactory<>("jobrole"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    private void getAll() {
        try {
            ObservableList<EmployeeTM> observableList = FXCollections.observableArrayList();
            List<EmployeeDTO> employeeDTOList = employeeBO.loadAllEmployees();

            for (EmployeeDTO employeeDTO : employeeDTOList) {
                observableList.add(new EmployeeTM(
                        employeeDTO.getId(),
                        employeeDTO.getName(),
                        employeeDTO.getContact(),
                        employeeDTO.getJobrole(),
                        employeeDTO.getUsername(),
                        employeeDTO.getPassword()
                ));
            }
            tblemployee.setItems(observableList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void saveOnAction(ActionEvent event) {

        try {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    txtId.getText(),
                    txtName.getText(),
                    txtContact.getText(),
                    txtJobrole.getText(),
                    txtUsername.getText(),
                    txtPassword.getText()
            );

            if (employeeBO.saveEmployee(employeeDTO) > 0) {

                new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully...!").show();
                tblemployee.refresh();
                getAll();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void searchOnAction(ActionEvent event) {
        try {
            EmployeeDTO employeeDTO = employeeBO.searchEmployee(txtId.getText());
            if (employeeDTO != null) {
                txtName.setText(employeeDTO.getName());
                txtContact.setText(employeeDTO.getContact());
                txtJobrole.setText(employeeDTO.getJobrole());
                txtUsername.setText(employeeDTO.getUsername());
                txtPassword.setText(employeeDTO.getPassword());
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void updateOnAction(ActionEvent event) {
        try {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    txtId.getText(),
                    txtName.getText(),
                    txtContact.getText(),
                    txtJobrole.getText(),
                    txtUsername.getText(),
                    txtPassword.getText()
            );

            if (employeeBO.updateEmployee(employeeDTO) > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully...!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void deleteOnAction(ActionEvent event) {
        try {
            if (employeeBO.deletEmployeee(txtId.getText()) > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted Successfully...!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void backOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/admindashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Admin Dashboard");
        stage.setResizable(false);
        stage.show();
    }
}
