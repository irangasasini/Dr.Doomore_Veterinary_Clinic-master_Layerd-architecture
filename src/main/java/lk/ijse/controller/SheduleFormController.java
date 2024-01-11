package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ScheduleBO;
import lk.ijse.dto.SheduleDto;
import lk.ijse.dto.Tm.SheduleTm;
import lk.ijse.bo.custom.impl.SheduleBOImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SheduleFormController {
    public TextField txtshedule;
    public TextField txttime;
    public TextField txtv_id;
    public DatePicker txtdate;
    public TableView tblshedule;
    public TableColumn colsh_id;
    public TableColumn coltime;
    public TableColumn colv_id;
    public TableColumn coldate;

    ScheduleBO sheduleBO = (ScheduleBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SCHEDULE);

    public void initialize(){
        setCellValueFactory();
        loadAllItems();
    }

    private void loadAllItems() {

        ObservableList<SheduleTm> obList = FXCollections.observableArrayList();

        try {
            List<SheduleDto> list = sheduleBO.getAllShedule();
            for (SheduleDto dto: list){
                obList.add(new SheduleTm(
                        dto.getSh_id(),
                        dto.getDate(),
                        dto.getTime(),
                        dto.getV_id()
                ));
            }
            tblshedule.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colsh_id.setCellValueFactory(new PropertyValueFactory<>("sh_id"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        coltime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colv_id.setCellValueFactory(new PropertyValueFactory<>("v_id"));

    }

    public void searchIdOnAction(ActionEvent actionEvent) {
        String code = txtshedule.getText();

        try {
            SheduleDto dto = sheduleBO.searchShedule(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, " not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void setFields(SheduleDto dto) {
        txtshedule.setText(dto.getSh_id());
        txtdate.setValue(LocalDate.parse(dto.getDate()));
        txttime.setText(dto.getTime());
        txtv_id.setText(dto.getV_id());
    }

    public void ClearbtnOnActhion(ActionEvent actionEvent) {
        clearFields();
    }

    public void DeletebtnOnActhion(ActionEvent actionEvent) {
        String id = txtshedule.getText();

        try {
            boolean isDeleted = sheduleBO.deleteShedule(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Shedule deleted!").show();
                clearFields();
                loadAllItems();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void UpdatebtnOnActhion(ActionEvent actionEvent) {


        String id = txtshedule.getText();
        String date = String.valueOf(txtdate.getValue());
        String time = txttime.getText();
        String v_id = txtv_id.getText();

        try {
            boolean isUpdated = sheduleBO.updateShedule(new SheduleDto(id, date,time ,v_id ));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Shedule updated!").show();
                clearFields();
                loadAllItems();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void AddBtnOnActhion(ActionEvent actionEvent) {

        boolean isSheduleValid = validateshedule();
        if (isSheduleValid) {
            String id = txtshedule.getText();
            String date = String.valueOf(txtdate.getValue());
            String e_id = txttime.getText();
            String v_id = txtv_id.getText();

            SheduleDto dto = new SheduleDto(id, date, e_id ,v_id);

            try {
                SheduleBOImpl sheduleModel = new SheduleBOImpl();
                boolean isSaved = sheduleModel.addShedule(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " Shedule Saved!", ButtonType.OK).show();
                    clearFields();
                    loadAllItems();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Shedule Not Saved!", ButtonType.OK).show();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Shedule Details", ButtonType.OK).show();
        }
    }

    private void clearFields() {
        txtshedule.setText("");
        txtdate.setPromptText("");
        txttime.setText("");
        txtv_id.setText("");

    }

    private boolean validateshedule() {

        String sheduleIDText = txtshedule.getText();
        Pattern compile = Pattern.compile("[S][0-9]{3,}");
        Matcher matcher = compile.matcher(sheduleIDText);
        boolean matches = matcher.matches();

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Shedule ID").show();
        }
        return false;


    }
}
