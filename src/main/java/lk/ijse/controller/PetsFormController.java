package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.PetsBO;
import lk.ijse.dto.PetDto;
import lk.ijse.dto.Tm.PetTm;
import lk.ijse.bo.custom.impl.PetsBOImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetsFormController {
    public TextField txtpet_id;
    public TextField txtbreed;
    public TextField txtage;
    public TextField txtname;
    public TableView tblpets;
    public TableColumn colpet_id;
    public TableColumn colname;
    public TableColumn colage;
    public TableColumn cokbreed;
    public TextField txtc_id;
    public TableColumn colc_id;

    PetsBO petsBO = (PetsBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PET);

    public void initialize(){
        setCellValueFactory();
        loadAllItems();
    }

    private void loadAllItems() {
        ObservableList<PetTm> obList = FXCollections.observableArrayList();

        try {
            List<PetDto> list = petsBO.getAllPets();
            for (PetDto dto: list){
                obList.add(new PetTm(
                        dto.getP_id(),
                        dto.getName(),
                        dto.getAge(),
                        dto.getBreed(),
                        dto.getC_id()
                ));
            }
            tblpets.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colpet_id.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colage.setCellValueFactory(new PropertyValueFactory<>("age"));
        cokbreed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        colc_id.setCellValueFactory(new PropertyValueFactory<>("c_id"));






    }

    public void AddOnAction(ActionEvent actionEvent) {
        boolean isPetsValid = validatePets();
        if (isPetsValid) {
            String id = txtpet_id.getText();
            String name = txtname.getText();
            String age = txtage.getText();
            String breed = txtbreed.getText();
            String C_id = txtc_id.getText();

            PetDto dto = new PetDto(id, name, age ,breed,C_id);

            try {
                PetsBOImpl petsModel = new PetsBOImpl();
                boolean isSaved = petsModel.addPets(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " Pets Saved!", ButtonType.OK).show();
                    clearFields();
                    loadAllItems();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Pets Not Saved!", ButtonType.OK).show();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Pets Details", ButtonType.OK).show();
        }
    }

    private void clearFields() {
        txtpet_id.setText("");
        txtname.setText("");
        txtage.setText("");
        txtbreed.setText("");
        txtc_id.setText("");
    }

    private boolean validatePets() {

        String petsIdText = txtpet_id.getText();
        Pattern compile = Pattern.compile("[P][0-9]{3,}");
        Matcher matcher = compile.matcher(petsIdText);
        boolean matches = matcher.matches();

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Pets ID").show();
            return false;
        }
        String nameText = txtname.getText();
        boolean isNameValid = Pattern.compile("[A-Za-z]{2,}").matcher(nameText).matches();
        if (!isNameValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Pets Name").show();
            return false;
        }

        String ageText = txtage.getText();
        boolean isAgeValid = Pattern.compile("\\d{2}").matcher(ageText).matches();
        if (!isAgeValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Pets Age").show();
            return false;
        }

        String breedText = txtbreed.getText();
        boolean isBreedValid = Pattern.compile("[A-Za-z]{3,}").matcher(breedText).matches();
        if (!isBreedValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid pets Breed").show();
            return false;
        }
        return true;
    }

    public void UpadateOnAction(ActionEvent actionEvent) {
        String id =txtpet_id.getText();
        String name = txtname.getText();
        String age = txtage.getText();
        String breed  = txtbreed.getText();
        String c_id = txtc_id.getText();

        try {
            boolean isUpdated = petsBO.updatePets(new PetDto(id, name,age,breed,c_id ));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Pets updated!").show();
                clearFields();
                loadAllItems();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void DeleteOnAction(ActionEvent actionEvent) {

        String id = txtpet_id.getText();

        try {
            boolean isDeleted = petsBO.deletePets(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Pets deleted!").show();
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
        String code = txtpet_id.getText();

        try {
            PetDto dto = petsBO.searchPets(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Pets not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(PetDto dto) {
        txtpet_id.setText(dto.getP_id());
        txtname.setText(dto.getName());
        txtage.setText(dto.getAge());
        txtbreed.setText(dto.getBreed());
        txtc_id.setText(dto.getC_id());
    }
}
