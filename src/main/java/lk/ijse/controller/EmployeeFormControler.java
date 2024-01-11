package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.Tm.EmployeeTm;
import lk.ijse.bo.custom.impl.EmployeeBOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeFormControler {
    public TextField txtE_id;
    public TextField txtaddress;
    public TextField txtname;
    public TextField txttelNumber;
    public TableView tblemployee;
    public TableColumn colemployee;
    public TableColumn colname;
    public TableColumn coladdress;
    public TableColumn coltelnumber;
    public AnchorPane EmployeePane;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    public void SalaryOnAction(ActionEvent actionEvent) throws IOException {

        EmployeePane.getChildren().clear();
        EmployeePane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/View/salary_form.fxml")));
    }


    public void initialize(){
        setCellValueFactory();
        loadAllItems();
    }

    private void loadAllItems() {
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
            List<EmployeeDto> list = employeeBO.getAllEmployee();
            for (EmployeeDto dto: list){
                obList.add(new EmployeeTm(
                        dto.getE_id(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getTel_number()
                ));
            }
            tblemployee.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colemployee.setCellValueFactory(new PropertyValueFactory<>("e_id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        coltelnumber.setCellValueFactory(new PropertyValueFactory<>("tel_number"));
        
    }



    public void searchIdOnAction(ActionEvent actionEvent) {
        String code = txtE_id.getText();

        try {
            EmployeeDto dto = employeeBO.searchEmployee(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Employee not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(EmployeeDto dto) {
        txtE_id.setText(dto.getE_id());
        txtname.setText(dto.getName());
        txtaddress.setText(dto.getAddress());
        txttelNumber.setText(String.valueOf(dto.getTel_number()));
    }

    public void AddOnAction(ActionEvent actionEvent) {
        boolean isEmployeeValid = validateEmployee();
        if (isEmployeeValid) {
            String id = txtE_id.getText();
            String name = txtname.getText();
            String address = txtaddress.getText();
            String number =txttelNumber.getText();

            EmployeeDto dto = new EmployeeDto(id, name, address,number);

            try {
                EmployeeBOImpl employeeModel = new EmployeeBOImpl();
                boolean isSaved = employeeModel.addEmployee(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " Employee Saved!", ButtonType.OK).show();
                    clearFields();
                    loadAllItems();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Employee Not Saved!", ButtonType.OK).show();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Employee Details", ButtonType.OK).show();
        }
    }

    private void clearFields() {
        txtE_id.setText("");
        txtname.setText("");
        txtaddress.setText("");
        txttelNumber.setText("");
    }

    private boolean validateEmployee() {


        String employeeIdText = txtE_id.getText();
        Pattern compile = Pattern.compile("[E][0-9]{3,}");
        Matcher matcher = compile.matcher(employeeIdText);
        boolean matches = matcher.matches();

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid employee ID").show();
            return false;
        }
        String addressText = txtaddress.getText();
        boolean isAddressValid = Pattern.compile("[A-Za-z]{3,}").matcher(addressText).matches();
        if (!isAddressValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid employee Address").show();
            return false;
        }

        String nameText = txtname.getText();
        boolean isNameValid = Pattern.compile("[A-Za-z]{3,}").matcher(nameText).matches();
        if (!isNameValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid employee Name").show();
            return false;
        }

        String numberText = txttelNumber.getText();
        boolean isNumberValid = Pattern.compile("[(07)]\\d{9}|[+]\\d{11}").matcher(numberText).matches();
        if (!isNumberValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid employee Number").show();
            return false;
        }
        return true;

    }

    public void UpadateOnAction(ActionEvent actionEvent) {

        String id = txtE_id.getText();
        String name = txtname.getText();
        String address = txtaddress.getText();
        String number =txttelNumber.getText();

        try {
            boolean isUpdated = employeeBO.updateEmployee(new EmployeeDto(id, name,address,number ));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!").show();
                clearFields();
                loadAllItems();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        String id = txtE_id.getText();

        try {
            boolean isDeleted = employeeBO.deleteEmployee(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted!").show();
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
}
