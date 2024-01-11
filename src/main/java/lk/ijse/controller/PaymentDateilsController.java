package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.PaymentsBO;
import lk.ijse.dto.PaymentDto;
import lk.ijse.dto.Tm.PaymentTm;
import lk.ijse.bo.custom.impl.PaymentsBOImpl;

import java.sql.SQLException;
import java.util.List;

public class PaymentDateilsController {

    public TableView tblpayment;
    public TableColumn colp_id;
    public TableColumn colamount;
    public TableColumn cola_id;
    public TableColumn coltime;

    public TableColumn coldate;

    PaymentsBO paymentsBO = (PaymentsBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PAYMENT);

    public void initialize(){
        setCellValueFactory();
        loadAllItems();
    }

    private void loadAllItems() {
        ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

        try {
            List<PaymentDto> list = paymentsBO.getAllPayments();
            for (PaymentDto dto: list){
                obList.add(new PaymentTm(
                        dto.getP_id(),
                        dto.getTime(),
                        dto.getDate(),
                        dto.getAmount(),
                        dto.getA_id()
                ));
            }
            tblpayment.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setCellValueFactory() {
        colp_id.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        coltime.setCellValueFactory(new PropertyValueFactory<>("time"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        cola_id.setCellValueFactory(new PropertyValueFactory<>("a_id"));

    }


}
