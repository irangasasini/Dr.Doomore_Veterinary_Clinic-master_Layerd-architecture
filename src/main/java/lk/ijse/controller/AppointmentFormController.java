package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.AppointmentBO;
import lk.ijse.dto.AppointmentDto;
import lk.ijse.dto.Tm.AppointmentTm;
import lk.ijse.bo.custom.impl.AppointmentBOImpl;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppointmentFormController {
    public TextField txtApponmentId;
    public TextField txtStatus;
    public TextField txtVetrnaryId;

    public DatePicker txtdate;
    public TableView tblappoinment;
    public TableColumn colappoinment;

    public TableColumn coldate;
    public TableColumn colAstatus;
    public TableColumn colv_id;
    public TextField txtpetsId;
    public TableColumn colp_Id;
    AppointmentBO appointmentBO = (AppointmentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.APPOINTMENT);

    public void initialize(){
        setCellValueFactory();
        loadAllItems();
    }

    private void loadAllItems() {

        ObservableList<AppointmentTm> obList = FXCollections.observableArrayList();

        try {
            List<AppointmentDto> list = appointmentBO.getAllAppointment();
            for (AppointmentDto dto: list){
                obList.add(new AppointmentTm(
                        dto.getA_id(),
                        dto.getAppoinment_status(),
                        dto.getDate(),
                        dto.getP_id(),
                        dto.getV_id()
                ));
            }
            tblappoinment.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
         colappoinment.setCellValueFactory(new PropertyValueFactory<>("a_id"));
         colAstatus.setCellValueFactory(new PropertyValueFactory<>("appoinment_status"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colp_Id.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        colv_id.setCellValueFactory(new PropertyValueFactory<>("v_id"));

    }

    public void searchIdOnAction(ActionEvent actionEvent) {
        String code = txtApponmentId.getText();

        try {
            AppointmentDto dto = appointmentBO.searchAppointment(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Appointment not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(AppointmentDto dto) {
        txtApponmentId.setText(dto.getAppoinment_status());
        txtStatus.setText(dto.getAppoinment_status());
        txtdate.setValue(LocalDate.parse(dto.getDate()));
        txtpetsId.setText(dto.getP_id());
        txtVetrnaryId.setText(dto.getV_id());
    }

    public void ClearbtnOnActhion(ActionEvent actionEvent) {
        clearFields();
    }

    public void DeletebtnOnActhion(ActionEvent actionEvent) {

        String id = txtApponmentId.getText();

        try {
            boolean isDeleted = appointmentBO.deleteAppointment(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Appointment deleted!").show();
                clearFields();
                loadAllItems();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void UpdatebtnOnActhion(ActionEvent actionEvent) {

        String id =txtApponmentId.getText();
        String aStatus = txtStatus.getText();
        String date = String.valueOf(txtdate.getValue());
        String p_id =txtpetsId.getText();
        String v_id  =txtVetrnaryId.getText();



        try {
            boolean isUpdated = appointmentBO.updateAppointment(new AppointmentDto(id, aStatus,date , v_id,p_id ));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Appointment updated!").show();
                clearFields();
                loadAllItems();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void AddBtnOnActhion(ActionEvent actionEvent) {


        boolean isAppointmentValid = validateAppoinment();
        if (isAppointmentValid) {
            String id = txtApponmentId.getText();
            String aStatus = txtStatus.getText();
            String date = String.valueOf(txtdate.getValue());
            String p_id = txtpetsId.getText();
            String v_id =txtVetrnaryId.getText();

            AppointmentDto dto = new AppointmentDto(id, aStatus, date ,p_id, v_id);

            try {


                boolean isSaved = appointmentBO.addAppoinment(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " Appointment Saved!", ButtonType.OK).show();
                    clearFields();
                   loadAllItems();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Appointment Not Saved!", ButtonType.OK).show();
                }

            } catch (SQLException  e) {
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Appointment Details", ButtonType.OK).show();
        }
    }

    private boolean validateAppoinment() {

        String AppoimentIdText = txtApponmentId.getText();
        Pattern compile = Pattern.compile("[A][0-9]{3,}");
        Matcher matcher = compile.matcher(AppoimentIdText);
        boolean matches = matcher.matches();

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Appointment ID").show();
            return false;
        }
        String nameText = txtStatus.getText();
        boolean isNameValid = Pattern.compile("[A-Za-z]{2,}").matcher(nameText).matches();
        if (!isNameValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Appointment Status").show();
            return false;

        }
        return true;
    }


   private  void clearFields() {
        txtApponmentId.setText("");
        txtStatus.setText("");
        txtdate.setValue(null);
        txtVetrnaryId.setText("");
    }
}
