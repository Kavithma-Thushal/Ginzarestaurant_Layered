package lk.ijse.restaurant.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.restaurant.bo.BoFactory;
import lk.ijse.restaurant.bo.custom.OrdersBO;
import lk.ijse.restaurant.db.DBConnection;
import lk.ijse.restaurant.dto.OrderDTO;
import lk.ijse.restaurant.dto.CustomerDTO;
import lk.ijse.restaurant.dto.ItemDTO;
import lk.ijse.restaurant.dto.Order_DetailsDTO;
import lk.ijse.restaurant.view.OrderTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class ManageordersFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtOrderId;
    @FXML
    private TextField txtOrderDate;
    @FXML
    private ComboBox<String> cmbCustomerId;
    @FXML
    private TextField txtCustomerName;
    @FXML
    private ComboBox<String> cmbItemCode;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtQtyOnHand;
    @FXML
    private TextField txtUnitPrice;
    @FXML
    private TextField txtQty;
    @FXML
    private TextField txtNetTotal;
    @FXML
    private TableView tblOrderCart;
    @FXML
    private TableColumn colCode;
    @FXML
    private TableColumn colDescription;
    @FXML
    private TableColumn colQty;
    @FXML
    private TableColumn colUnitPrice;
    @FXML
    private TableColumn colTotal;
    @FXML
    private TableColumn colAction;
    @FXML
    private Label lbldateandtime;

    private OrdersBO ordersBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ORDERS);

    private ObservableList<OrderTM> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd      hh:mm");
        Date date = new Date();
        lbldateandtime.setText(simpleDateFormat.format(date));

        setOrderDate();
        generateNextOrderId();
        setCellValueFactory();
        loadCustomerIds();
        loadItemCodes();
    }

    private void setOrderDate() {
        txtOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    private void generateNextOrderId() {
        try {
            String id = ordersBO.generateNextOrderId();
            txtOrderId.setText(id);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "please try again...!").show();
        }
    }

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void loadCustomerIds() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            List<String> customerid = ordersBO.loadCustomerIds();

            for (String id : customerid) {
                observableList.add(id);
            }
            cmbCustomerId.setItems(observableList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "please try again...!").show();
        }
    }

    private void loadItemCodes() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            List<String> itemcodes = ordersBO.loadItemCodes();

            for (String code : itemcodes) {
                observableList.add(code);
            }
            cmbItemCode.setItems(observableList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "please try again...!").show();
        }
    }

    @FXML
    private void cmbCustomerIdOnAction(ActionEvent event) {
        String id = cmbCustomerId.getValue();
        cmbCustomerId.setDisable(true);

        try {
            CustomerDTO customerDTO = ordersBO.searchByCustomerId(id);
            txtCustomerName.setText(customerDTO.getName());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "please try again...!").show();
        }
    }

    @FXML
    private void cmbItemCodeOnAction(ActionEvent event) {
        String code = cmbItemCode.getValue();

        try {
            ItemDTO itemDTO = ordersBO.searchByItemCode(code);
            fillItemFields(itemDTO);

            txtQty.requestFocus();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "please try again...!").show();
        }
    }

    private void fillItemFields(ItemDTO itemDTO) {
        txtDescription.setText(itemDTO.getDescription());
        txtUnitPrice.setText(String.valueOf(itemDTO.getUnitprice()));
        txtQtyOnHand.setText(String.valueOf(itemDTO.getQtyonhand()));
    }

    @FXML
    private void addToCartOnAction(ActionEvent event) {
        try {
            String code = cmbItemCode.getValue();
            String description = txtDescription.getText();
            int qty = Integer.parseInt(txtQty.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());

            double total = qty * unitPrice;
            Button btnRemove = new Button("Remove");
            btnRemove.setCursor(Cursor.HAND);

            setRemoveBtnOnAction(btnRemove);    //set action to the btnRemove

            if (!observableList.isEmpty()) {
                for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                    if (colCode.getCellData(i).equals(code)) {
                        qty += (int) colQty.getCellData(i);
                        total = qty * unitPrice;

                        observableList.get(i).setQty(qty);
                        observableList.get(i).setTotal(total);

                        tblOrderCart.refresh();
                        calculateNetTotal();
                        return;
                    }
                }
            }

            OrderTM tm = new OrderTM(code, description, qty, unitPrice, total, btnRemove);

            observableList.add(tm);
            tblOrderCart.setItems(observableList);
            calculateNetTotal();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "please try again...!").show();
        }
    }

    private void setRemoveBtnOnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {

                int index = tblOrderCart.getSelectionModel().getSelectedIndex();
                observableList.remove(index+1);

                tblOrderCart.refresh();
                calculateNetTotal();
            }

        });
    }

    private void calculateNetTotal() {
        double netTotal = 0.0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            double total = (double) colTotal.getCellData(i);
            netTotal += total;
        }
        txtNetTotal.setText(String.valueOf(netTotal));
    }

    private void printBills() throws JRException, SQLException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do you want a bill?", yes, no).showAndWait();

        if (buttonType.orElse(yes) == yes) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("Customer", "Hiruni");
            InputStream resource = this.getClass().getResourceAsStream("/reports/ginzaRestaurantBill.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        }
    }

    @FXML
    private void placeOrderOnAction(ActionEvent events) throws SQLException {
        String orderId = txtOrderId.getText();
        String customerId = cmbCustomerId.getValue();
        String itemCode = cmbItemCode.getValue();
        int qty = Integer.parseInt(txtQty.getText());

        List<Order_DetailsDTO> order_detailsDTOList = new ArrayList<>();
        order_detailsDTOList.add(new Order_DetailsDTO(orderId, itemCode, qty, LocalDate.now()));

        Boolean isPlaced = ordersBO.placeOrder(new OrderDTO(orderId, customerId, order_detailsDTOList));
        if (isPlaced) {
            Alert orderPlacedAlert = new Alert(Alert.AlertType.INFORMATION, "Order Placed...!");
            orderPlacedAlert.show();

            orderPlacedAlert.setOnHidden(event -> {
                try {
                    printBills();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "please try again...!").show();
                }
            });

        } else {
            new Alert(Alert.AlertType.ERROR, "Order is Not Placed...!").show();
        }
        generateNextOrderId();
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
