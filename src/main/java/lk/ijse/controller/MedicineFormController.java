package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.MedicineBO;
import lk.ijse.dto.MedicineDto;
import lk.ijse.dto.Tm.MedicineTm;
import lk.ijse.bo.custom.impl.MedicineBOImpl;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MedicineFormController {
    public TextField txtm_Code;
    public TextField txtdescription;
    public TextField txtqty;
    public TextField txta_id;
    public TableView tblmedicine;
    public TableColumn colm_code;
    public TableColumn coldes;
    public TableColumn colqty;
    public TableColumn cola_id;
    public TextField txtunitprice;

    MedicineBO medicineBO = (MedicineBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.MEDICINE);

    public void initialize(){
        setCellValueFactory();
        loadAllItems();
    }

    private void loadAllItems() {

        ObservableList<MedicineTm> obList = FXCollections.observableArrayList();

        try {
            List<MedicineDto> list = medicineBO.getAllMedicine();
            for (MedicineDto dto: list){
                obList.add(new MedicineTm(
                        dto.getM_code(),
                        dto.getDescription(),
                        dto.getUnitPrice(),
                        dto.getQty(),
                        dto.getA_id()
                ));
            }
            tblmedicine.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colm_code.setCellValueFactory(new PropertyValueFactory<>("m_code"));
        coldes.setCellValueFactory(new PropertyValueFactory<>("description"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        cola_id.setCellValueFactory(new PropertyValueFactory<>("a_id"));


    }

    public void AddBtnOnActhion(ActionEvent actionEvent) {

        boolean isMedicineValid = validateMedicine();
        if (isMedicineValid) {
            String id = txtm_Code.getText();
            String des =txtdescription.getText();
            int qty = Integer.parseInt(txtqty.getText());
            String unitprice = txtunitprice.getText();
            String a_id = txta_id.getText();

            MedicineDto dto = new MedicineDto(id, des,unitprice, qty ,a_id);

            try {
                MedicineBOImpl medicineModel = new MedicineBOImpl();
                boolean isSaved = medicineModel.addMedicine(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " Medicine Saved!", ButtonType.OK).show();
                    clearFields();
                    loadAllItems();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Medicine Not Saved!", ButtonType.OK).show();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Medicine Details", ButtonType.OK).show();
        }

    }

    private void clearFields() {
        txtm_Code.setText("");
        txtdescription.setText("");
        txtqty.setText("");
        txta_id.setText("");
        txtunitprice.setText("");

    }

    private boolean validateMedicine() {

        String MedicineIdText = txtm_Code.getText();
        Pattern compile = Pattern.compile("[M][0-9]{3,}");
        Matcher matcher = compile.matcher(MedicineIdText);
        boolean matches = matcher.matches();

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Medicine ID").show();
            return false;
        }
        return true;
    }

    public void UpdatebtnOnActhion(ActionEvent actionEvent) {
        String id = txtm_Code.getText();
        String des = txtdescription.getText();
        int qty = Integer.parseInt(txtqty.getText());
        String unitprice = txtunitprice.getText();
        String a_id = txta_id.getText();

        try {
            boolean isUpdated = medicineBO.updateMedicine(new MedicineDto(id, des,unitprice,qty,a_id  ));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Medicine updated!").show();
                clearFields();
                loadAllItems();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void DeletebtnOnActhion(ActionEvent actionEvent) {

        String id = txtm_Code.getText();

        try {
            boolean isDeleted = medicineBO.deleteMedicine(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Medicine deleted!").show();
                clearFields();
                loadAllItems();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void ClearbtnOnActhion(ActionEvent actionEvent) {
        {
            clearFields();
        }

    }
    public void searchIdOnAction(ActionEvent actionEvent) {
        String code = txtm_Code.getText();

        try {
            MedicineDto dto = medicineBO.searchMedicine(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Medicine not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(MedicineDto dto) {
        txtm_Code.setText(dto.getM_code());
        txtdescription.setText(dto.getDescription());
        txtunitprice.setText(dto.getUnitPrice());
        txtqty.setText(String.valueOf(dto.getQty()));
        txta_id.setText(dto.getA_id());

    }
}