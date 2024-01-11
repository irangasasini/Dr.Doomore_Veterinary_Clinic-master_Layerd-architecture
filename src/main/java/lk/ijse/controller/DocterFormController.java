package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.DoctorBO;
import lk.ijse.dto.DocterDto;
import lk.ijse.dto.Tm.DocterTm;
import lk.ijse.bo.custom.impl.DocterBOImpl;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocterFormController {
    public TextField txtv_id;
    public TextField txtaddress;
    public TextField txtname;
    public TextField txttelNumber;
    public TableColumn colv_id;
    public TableColumn colname;
    public TableColumn coladdress;
    public TableColumn coltelnumber;
    public TableView tblveterinary;
    public AnchorPane DocterPane;

    DoctorBO docterBO = (DoctorBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.DOCTOR);
    public void SheduleOnAction(ActionEvent actionEvent) throws IOException {

        DocterPane.getChildren().clear();
        DocterPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/View/shedule_form.fxml")));
    }



    public void initialize(){
        setCellValueFactory();
        loadAllItems();
    }

    private void loadAllItems() {

        ObservableList<DocterTm> obList = FXCollections.observableArrayList();

        try {
            List<DocterDto> list = docterBO.getAllDocter();
            for (DocterDto dto: list){
                obList.add(new DocterTm(
                        dto.getV_id(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getTel_number()
                ));
            }
            tblveterinary.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colv_id.setCellValueFactory(new PropertyValueFactory<>("v_id"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coltelnumber.setCellValueFactory(new PropertyValueFactory<>("tel_number"));
        
    }


    public void AddOnAction(ActionEvent actionEvent) {
        boolean isDocterValid = validateDocter();
        if (isDocterValid) {
            String id = txtv_id.getText();
            String address = txtaddress.getText();
            String name = txtname.getText();
            String number = txttelNumber.getText();

            DocterDto dto = new DocterDto(id, name,address ,number);

            try {
                DocterBOImpl docterModel = new DocterBOImpl();
                boolean isSaved = docterModel.addDocter(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " Docter Saved!", ButtonType.OK).show();
                    clearFields();
                    loadAllItems();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Docter Not Saved!", ButtonType.OK).show();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Docter Details", ButtonType.OK).show();
        }
    }

    private void clearFields() {
        txtv_id.setText("");
        txtaddress.setText("");
        txtname.setText("");
        txttelNumber.setText("");

    }

    private boolean validateDocter() {

        String veterinaryIdText = txtv_id.getText();
        Pattern compile = Pattern.compile("[V][0-9]{3,}");
        Matcher matcher = compile.matcher(veterinaryIdText);
        boolean matches = matcher.matches();

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid veterinary ID").show();
            return false;
        }
        String addressText = txtaddress.getText();
        boolean isAddressValid = Pattern.compile("[A-Za-z]{3,}").matcher(addressText).matches();
        if (!isAddressValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid veterinary Address").show();
            return false;
        }

        String nameText = txtname.getText();
        boolean isNameValid = Pattern.compile("[A-Za-z]{3,}").matcher(nameText).matches();
        if (!isNameValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid veterinary Name").show();
            return false;
        }

        String numberText = txttelNumber.getText();
        boolean isNumberValid = Pattern.compile("[(07)]\\d{9}|[+]\\d{11}").matcher(numberText).matches();
        if (!isNumberValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid veterinary Number").show();
            return false;
        }
        return true;



    }

    public void UpadateOnAction(ActionEvent actionEvent) {
        String id = txtv_id.getText();
        String address = txtaddress.getText();
        String name = txtname.getText();
        String number = txttelNumber.getText();

        try {
            boolean isUpdated = docterBO.updateDocter(new DocterDto(id, name,number,address ));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Docter updated!").show();
                clearFields();
                loadAllItems();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void DeleteOnAction(ActionEvent actionEvent) {

        String id = txtv_id.getText();

        try {
            boolean isDeleted = docterBO.deleteDocter(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Docter deleted!").show();
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
        String code = txtv_id.getText();

        try {
            DocterDto dto = docterBO.searchDocter(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Docter not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(DocterDto dto) {
        txtv_id.setText(dto.getV_id());
        txtaddress.setText(dto.getAddress());
        txtname.setText(dto.getName());
        txttelNumber.setText(String.valueOf(dto.getTel_number()));
    }


}

