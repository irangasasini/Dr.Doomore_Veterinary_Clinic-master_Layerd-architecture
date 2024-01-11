package lk.ijse.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.PaymentsBO;
import lk.ijse.bo.custom.impl.*;
import lk.ijse.dto.AppointmentDto;
import lk.ijse.dto.MedicineDto;
import lk.ijse.dto.PlaceOrderDto;
import lk.ijse.dto.Tm.CartTm;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PaymentsFormController {
    private final ObservableList<CartTm> obList = FXCollections.observableArrayList();
    public TextField txtp_id;
    public TextField txtamount;
    public DatePicker txtdate;
    public TableView tblpayment;
    public TableColumn colamount;

    public TableColumn coldate;

    public Label lblTime;
    public Label lbldate;
    public Label lblTotal;
    public TextField txtv_name;
    public TextField txtp_name;
    public TextField txtu_price;
    public TextField txtqty;
    public ComboBox<String> cmb_id;
    public ComboBox<String> cmbmedicode;
    public TextField txtdescription;
    public TableColumn col_code;
    public TableColumn colDescription;
    public TableColumn colqty;
    public TableColumn colAction;
    public TableColumn colunitPrice;
    public AnchorPane PaymentPane;

    PaymentsBO paymentsBO = (PaymentsBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PAYMENT);

    public void initialize() {
        loadAppoinmentId();
        loadMediCodes();
        loadTime();
        setCellValueFactory();
        lblTotal.setText("0000.00");
    }

    private void setCellValueFactory() {
        col_code.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colunitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("button"));
    }

    private void loadMediCodes() {

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<MedicineDto> list = paymentsBO.getAllMedicine();

            for (MedicineDto dto : list) {
                obList.add(dto.getM_code());
            }
            cmbmedicode.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAppoinmentId() {

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<AppointmentDto> list =  paymentsBO.getAllAppointment();

            for (AppointmentDto dto : list) {
                if (!dto.getAppoinment_status().equals("Paid")) {
                    obList.add(dto.getA_id());
                }
            }
            cmb_id.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd:MMM:yyyy");
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            lblTime.setText(dateFormat.format(new Date()));
            lbldate.setText(dateFormat1.format(new Date()));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();
    }


    public void AddOnAction(ActionEvent actionEvent) {
        String mediCode = cmbmedicode.getValue();
        String description = txtdescription.getText();
        int qty = Integer.parseInt(txtqty.getText());
        int unitPrice = Integer.parseInt(txtu_price.getText());
        int total = qty * unitPrice;

        txtamount.setText(String.valueOf(total));

        Button btn = new Button("remove");
        btn.setCursor(Cursor.HAND);

        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int index = tblpayment.getSelectionModel().getSelectedIndex();
                obList.remove(index);
                tblpayment.refresh();

                calculateNetTotal();
            }
        });
        for (int i = 0; i < tblpayment.getItems().size(); i++) {
            if (mediCode.equals(col_code.getCellData(i))) {
                qty += (int) colqty.getCellData(i);
                total=qty*unitPrice;

                obList.get(i).setQty(qty);
                obList.get(i).setAmount(total);

                tblpayment.refresh();
                calculateNetTotal();
                return;
            }
        }
        obList.add(new CartTm(mediCode, description, unitPrice, qty,total, btn));

        tblpayment.setItems(obList);
        calculateNetTotal();
        clearMediFields();

    }

    private void calculateNetTotal() {
        int total = 0;
        for (int i = 0; i < tblpayment.getItems().size(); i++) {
            total += (int) colamount.getCellData(i);
        }

        lblTotal.setText(String.valueOf(total));
    }

    private void clearMediFields() {
        txtdescription.clear();
        txtu_price.clear();
        txtqty.clear();
        txtamount.clear();
    }

    public void PlaceOrderOnAction(ActionEvent actionEvent) {
        String orderId = cmb_id.getValue();
        java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
        Time time = Time.valueOf(LocalTime.now());
        int total=Integer.parseInt(lblTotal.getText());

        List<CartTm> tmList = new ArrayList<>();

        for (CartTm cartTm : obList) {
            tmList.add(cartTm);
        }

        var placeOrderDto = new PlaceOrderDto(orderId,date,time,total,tmList);
        try {
            boolean isSuccess =  new PlaceOrderBOImpl().placeOrder(placeOrderDto);
            if(isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "order completed!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void PaymentPayOnAction(ActionEvent actionEvent) throws IOException {

        PaymentPane.getChildren().clear();
        PaymentPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/paymentDetails.fxml")));
    }

    public void cmb_idOnAction(ActionEvent actionEvent) {

        AppointmentBOImpl model = new AppointmentBOImpl();

        String id = cmb_id.getValue();
        try {
            AppointmentDto dto = model.searchAppointment(id);
            if (dto != null) {
                txtv_name.setText(new DocterBOImpl().getDocter(dto.getV_id()));
                txtp_name.setText(new PetsBOImpl().getPetName(dto.getP_id()));
                txtp_id.setText(new PaymentsBOImpl().generateNextPaymentId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void cmbmedicodeOnAction(ActionEvent actionEvent) {

        String code = cmbmedicode.getValue();

        try {
            MedicineDto dto = new MedicineBOImpl().searchMedicine(code);
            txtdescription.setText(dto.getDescription());
            txtu_price.setText(dto.getUnitPrice());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtQtyOnAction(ActionEvent actionEvent) {
        int qty = Integer.parseInt(txtqty.getText());
        int unitPrice = Integer.parseInt(txtu_price.getText());
        int total = qty * unitPrice;

        txtamount.setText(String.valueOf(total));
    }
}
