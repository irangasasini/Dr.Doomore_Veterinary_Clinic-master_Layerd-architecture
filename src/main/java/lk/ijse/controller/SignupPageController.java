package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.UserDto;
import lk.ijse.bo.custom.impl.UserBOImpl;

import java.io.IOException;
import java.sql.SQLException;

public class SignupPageController {
    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    private AnchorPane Pane;


    public void initialize() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Doctor");
        obList.add("Employee");
        cmbRole.setItems(obList);
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        String username=txtUsername.getText();
        String password=txtPassword.getText();
        String confirmPassword=txtConfirmPassword.getText();
        String role=cmbRole.getValue();

        var model = new UserBOImpl();
        if (password.equals(confirmPassword)) {
            try {
                String id = model.generateId();
                boolean isSaved = model.saveUser(new UserDto(id, username, password,role));
                if (isSaved){
                    navigate.navigate(Pane,"main_form.fxml");
                    new Alert(Alert.AlertType.CONFIRMATION, "User Saved Successfully").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else new Alert(Alert.AlertType.ERROR,"Password Not Matched").show();
    }
}
