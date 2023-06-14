package lk.ijse.restaurant.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.restaurant.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ReportsFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Label lbldateandtime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd      hh:mm");
        Date date = new Date();
        lbldateandtime.setText(simpleDateFormat.format(date));
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

    @FXML
    private void customerReportOnAction(ActionEvent event) throws SQLException, JRException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("Customer", "Hiruni");
        //map.put("Table Name",txt.getText());

        InputStream resource = this.getClass().getResourceAsStream("/reports/customerReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(resource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, DBConnection.getInstance().getConnection());
        //JasperPrintManager.printReport(jasperPrint,true);
        JasperViewer.viewReport(jasperPrint, false);
    }
}
