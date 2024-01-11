package lk.ijse.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.Tm.CustomerTm;
import lk.ijse.bo.custom.impl.CustomerBOImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerFormController {

    public TextField txtcustomerId;
    public TextField txtemployeeId;
    public TextField txtemail;
    public TextField txtTelNum;
    public TableView tblCustomer;
    public TableColumn colcustomer_id;
    public TableColumn colE_id;
    public TableColumn colEmail;
    public TableColumn coltel;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize(){
        setCellValueFactory();
        loadAllItems();
    }

    private void loadAllItems() {

        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<CustomerDto> list = customerBO.getAllCustomer();
            for (CustomerDto dto: list){
                obList.add(new CustomerTm(
                        dto.getC_id(),
                        dto.getEmail(),
                        dto.getTel_number(),
                        dto.getE_id()
                ));
            }
            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colcustomer_id.setCellValueFactory(new PropertyValueFactory<>("c_id"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("tel_number"));
        colE_id.setCellValueFactory(new PropertyValueFactory<>("e_id"));
    }

    public void AddbtnOnAction(ActionEvent actionEvent) {

        boolean isCustomerValid = validatecustomer();
        if (isCustomerValid) {
            String id = txtcustomerId.getText();
            String email =txtemail.getText();
            String num = txtTelNum.getText();
            String e_id = txtemployeeId.getText();

            CustomerDto dto = new CustomerDto(id, email, num ,e_id);

            try {
                CustomerBOImpl customerModel = new CustomerBOImpl();
                boolean isSaved=customerModel.addCustomer(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " Customer Saved!", ButtonType.OK).show();
                    clearFields();
                    loadAllItems();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Customer Not Saved!", ButtonType.OK).show();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Details", ButtonType.OK).show();
        }
    }

    private boolean validatecustomer() {

        String customerIdText = txtcustomerId.getText();
        Pattern compile = Pattern.compile("[C][0-9]{3,}");
        Matcher matcher = compile.matcher(customerIdText);
        boolean matches = matcher.matches();

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer ID").show();
            return false;
        }

        String numberText = txtTelNum.getText();
        boolean isNumberValid = Pattern.compile("[(07)]\\d{9}|[+]\\d{11}").matcher(numberText).matches();
        if (!isNumberValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Patient Number").show();
            return false;
        }
        return true;
    }

    private void clearFields() {
        txtcustomerId.setText("");
        txtemail.setText("");
        txtTelNum.setText("");
        txtemployeeId.setText("");

    }

    public void UpdatebtnOnAction(ActionEvent actionEvent) {

        String id = txtcustomerId.getText();
        String email = txtemail.getText();
        String num =(txtTelNum.getText());
        String e_id = txtemployeeId.getText();

        try {
            boolean isUpdated = customerBO.updateCustomer(new CustomerDto(id, email,num ,e_id ));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer updated!").show();
                clearFields();
                loadAllItems();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void DeletebtnOnAction(ActionEvent actionEvent) {

        String id = txtcustomerId.getText();

        try {
            boolean isDeleted = customerBO.deleteCustomer(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted!").show();
                clearFields();
                loadAllItems();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void ClearbtnOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void searchIdOnAction(ActionEvent actionEvent) {
        String code = txtcustomerId.getText();

        try {
            CustomerDto dto = customerBO.searchCustomer(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(CustomerDto dto) {
        txtcustomerId.setText(dto.getC_id());
        txtemail.setText(dto.getEmail());
        txtTelNum.setText(String.valueOf(dto.getTel_number()));
        txtemployeeId.setText(dto.getE_id());

    }
}
