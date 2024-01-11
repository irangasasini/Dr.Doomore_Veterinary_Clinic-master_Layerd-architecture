package lk.ijse.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.SalaryBO;
import lk.ijse.dto.SalaryDto;
import lk.ijse.dto.Tm.SalaryTm;
import lk.ijse.bo.custom.impl.SalaryBOImpl;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SalaryFormController {
    public TextField txtsalary_id;
    public TextField txtE_id;
    public TableView tblsalary;
    public TableColumn colsalaryid;
    public TableColumn coltime;
    public TableColumn colDate;
    public TableColumn colE_id;
    public Label lbltime;
    public Label lbldate;

    SalaryBO salaryBO = (SalaryBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SALARY);

    public void initialize(){
        setCellValueFactory();
        loadAllItems();
        loadTime();
    }

    private void loadTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd:MMM:yyyy");
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            lbltime.setText(dateFormat.format(new Date()));
            lbldate.setText(dateFormat1.format(new Date()));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();
    }
    private void loadAllItems() {
        ObservableList<SalaryTm> obList = FXCollections.observableArrayList();

        try {
            List<SalaryDto> list = salaryBO.getAllSalary();
            for (SalaryDto dto: list){
                obList.add(new SalaryTm(
                        dto.getS_id(),
                        dto.getDate(),
                        dto.getTime(),
                        dto.getE_id()
                ));
            }
            tblsalary.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colsalaryid.setCellValueFactory(new PropertyValueFactory<>("s_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        coltime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colE_id.setCellValueFactory(new PropertyValueFactory<>("e_id"));


    }


    public void AddOnAction(ActionEvent actionEvent) {

        boolean isSalaryValid = validateSalary();
        if (isSalaryValid) {
            String id = txtsalary_id.getText();
            String date = String.valueOf(LocalDate.now());
            String time = String.valueOf(LocalTime.now());
            String e_id = txtE_id.getText();


            SalaryDto dto = new SalaryDto(id, date, time ,e_id);

            try {
                SalaryBOImpl salaryModel = new SalaryBOImpl();
                boolean isSaved = salaryModel.addSalary(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " Salary Saved!", ButtonType.OK).show();
                    clearFields();
                    loadAllItems();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Salary Not Saved!", ButtonType.OK).show();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Salary Details", ButtonType.OK).show();
        }
    }

    private void clearFields() {
        txtsalary_id.setText("");
        lbldate.setText("");
        lbltime.setText("");
        txtE_id.setText("");
    }

    private boolean validateSalary() {
        String salaryIDText = txtsalary_id.getText();
        Pattern compile = Pattern.compile("[S][0-9]{3,}");
        Matcher matcher = compile.matcher(salaryIDText);
        boolean matches = matcher.matches();

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Salary ID").show();
            return false;
        }
        return true;


    }

    public void UpadateOnAction(ActionEvent actionEvent) {
        String id = txtsalary_id.getText();
        String date = lbldate.getText();
        String time = lbltime.getText();
        String e_id = txtE_id.getText();

        try {
            boolean isUpdated = salaryBO.updateSalary(new SalaryDto(id, date, time,e_id ));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Salary updated!").show();
                clearFields();
                loadAllItems();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        String id = txtsalary_id.getText();

        try {
            boolean isDeleted = salaryBO.deleteSalary(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Salary deleted!").show();
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
        String code = txtsalary_id.getText();

        try {
            SalaryDto dto = salaryBO.searchSalary(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Salary not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(SalaryDto dto) {
        txtsalary_id.setText(dto.getS_id());
        lbldate.setText(dto.getDate());
        lbltime.setText(dto.getTime());
        txtE_id.setText(dto.getE_id());
    }
}
