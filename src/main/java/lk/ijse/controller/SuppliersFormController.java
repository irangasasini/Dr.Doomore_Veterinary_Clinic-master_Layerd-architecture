package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.SuppliersBO;
import lk.ijse.dto.SuppliersDto;
import lk.ijse.dto.Tm.SuppliersTm;
import lk.ijse.bo.custom.impl.SuppliersBOImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SuppliersFormController {
    public TextField txtSupid;
    public TextField txtaddress;
    public TextField txtemail;
    public TextField txttelnumber;
    public TextField txte_id;
    public TableView tblSupplier;
    public TableColumn colSupid;
    public TableColumn colemail;
    public TableColumn coladdress;
    public TableColumn coltelnumber;
    public TableColumn cole_id;

    SuppliersBO suppliersBO = (SuppliersBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    public void initialize(){
        setCellValueFactory();
        loadAllItems();
    }

    private void loadAllItems() {

        ObservableList<SuppliersTm> obList = FXCollections.observableArrayList();

        try {
            List<SuppliersDto> list = suppliersBO.getAllSupplier();
            for (SuppliersDto dto: list){
                obList.add(new SuppliersTm(
                        dto.getSup_id(),
                        dto.getEmail(),
                        dto.getAddress(),
                        dto.getTel_number(),
                        dto.getE_id()
                ));
            }
            tblSupplier.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {

        colSupid.setCellValueFactory(new PropertyValueFactory<>("sup_id"));
         colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
         coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
         coltelnumber.setCellValueFactory(new PropertyValueFactory<>("tel_number"));
         cole_id.setCellValueFactory(new PropertyValueFactory<>("e_id"));

    }

    public void AddOnAction(ActionEvent actionEvent) {


        boolean isSuppliersValid = validateSuppliers();
        if (isSuppliersValid) {
            String id = txtSupid.getText();
            String email = txtemail.getText();
            String address = txtaddress.getText();
            String number = txttelnumber.getText();
            String e_id = txte_id.getText();

            SuppliersDto dto = new SuppliersDto(id, email, address ,number,e_id);

            try {
                SuppliersBOImpl suppliersModel = new SuppliersBOImpl();
                boolean isSaved = suppliersModel.addSuppliers(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " Supplier Saved!", ButtonType.OK).show();
                    clearFields();
                    loadAllItems();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Supplier Not Saved!", ButtonType.OK).show();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Supplier Details", ButtonType.OK).show();
        }
    }

    private void clearFields() {

        txtSupid.setText("");
        txtemail.setPromptText("");
        txtaddress.setText("");
        txttelnumber.setText("");
        txte_id.setText("");

    }

    private boolean validateSuppliers() {

        String supplierIdText = txtSupid.getText();
        Pattern compile = Pattern.compile("[S][0-9]{3,}");
        Matcher matcher = compile.matcher(supplierIdText);
        boolean matches = matcher.matches();

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Supplier ID").show();
            return false;
        }

        String addressText = txtaddress.getText();
        boolean isAddressValid = Pattern.compile("[A-Za-z]{3,}").matcher(addressText).matches();
        if (!isAddressValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Supplier Address").show();
            return false;
        }
        String numberText = txttelnumber.getText();
        boolean isNumberValid = Pattern.compile("[(07)]\\d{9}|[+]\\d{11}").matcher(numberText).matches();
        if (!isNumberValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Supplier Number").show();
            return false;
        }
        return true;

    }

    public void UpadateOnAction(ActionEvent actionEvent) {


        String id = txtSupid.getText();
        String email = txtemail.getText();
        String address = txtaddress.getText();
        String number = txttelnumber.getText();
        String e_id = txte_id.getText();

        try {
            boolean isUpdated = suppliersBO.updateSuppliers(new SuppliersDto(id, email,address ,number,e_id ));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated!").show();
                clearFields();
                loadAllItems();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void DeleteOnAction(ActionEvent actionEvent) {

        String id = txtSupid.getText();

        try {
            boolean isDeleted = suppliersBO.deleteSuppliers(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Shedule deleted!").show();
                clearFields();
                loadAllItems();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void ClearOnAction(ActionEvent actionEvent) {

        clearFields();
    }

    public void searchIdOnAction(ActionEvent actionEvent) {

        String code = txtSupid.getText();

        try {
            SuppliersDto dto = suppliersBO.searchSuppliers(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, " Not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(SuppliersDto dto) {

        txtSupid.setText(dto.getSup_id());
        txtemail.setText(dto.getEmail());
        txtaddress.setText(dto.getAddress());
        txttelnumber.setText(dto.getTel_number());
        txte_id.setText(dto.getE_id());

    }
}
