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
import lk.ijse.restaurant.bo.custom.SalaryBO;
import lk.ijse.restaurant.dto.SalaryDTO;
import lk.ijse.restaurant.view.SalaryTM;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ManagesalaryFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtEmployeeid;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextField txtDatetime;
    @FXML
    private TableView tblSalary;
    @FXML
    private TableColumn colCode;
    @FXML
    private TableColumn colEmployeeid;
    @FXML
    private TableColumn colAmount;
    @FXML
    private TableColumn colDatetime;
    @FXML
    private Label lbldateandtime;

    private SalaryBO salaryBO= BoFactory.getBoFactory().getBO(BoFactory.BOTypes.SALARY);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd      hh:mm");
        Date date = new Date();
        lbldateandtime.setText(simpleDateFormat.format(date));

        getAll();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colEmployeeid.setCellValueFactory(new PropertyValueFactory<>("employeeid"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDatetime.setCellValueFactory(new PropertyValueFactory<>("datetime"));
    }

    private void getAll() {
        try {
            ObservableList<SalaryTM> observableList = FXCollections.observableArrayList();
            List<SalaryDTO> salaryDTOList = salaryBO.loadAllSalary();

            for (SalaryDTO salaryDTO : salaryDTOList) {
                observableList.add(new SalaryTM(
                        salaryDTO.getCode(),
                        salaryDTO.getEmployeeid(),
                        salaryDTO.getAmount(),
                        salaryDTO.getDate().toString()
                ));
            }
            tblSalary.setItems(observableList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void saveOnAction(ActionEvent event) {
        try {
            SalaryDTO salaryDTO = new SalaryDTO(
                    txtCode.getText(),
                    txtEmployeeid.getText(),
                    Double.parseDouble(txtAmount.getText()),
                    LocalDate.parse(txtDatetime.getText())
            );

            if (salaryBO.saveSalary(salaryDTO) > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully...!").show();
                tblSalary.refresh();
                getAll();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void searchOnAction(ActionEvent event) {
        try {
            SalaryDTO salaryDTO = salaryBO.searchSalary(txtCode.getText());
            if (salaryDTO != null) {
                txtEmployeeid.setText(salaryDTO.getEmployeeid());
                txtAmount.setText(String.valueOf(salaryDTO.getAmount()));
                txtDatetime.setText(String.valueOf(salaryDTO.getDate()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void updateOnAction(ActionEvent event) {
        try {
            SalaryDTO salaryDTO = new SalaryDTO(
                    txtCode.getText(),
                    txtEmployeeid.getText(),
                    Double.parseDouble(txtAmount.getText()),
                    LocalDate.parse(txtDatetime.getText())
            );

            if (salaryBO.updateSalary(salaryDTO) > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully...!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Please try again...!").show();
        }
    }

    @FXML
    private void deleteOnAction(ActionEvent event) {
        try {
            if (salaryBO.deleteSalary(txtCode.getText()) > 0) {
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
