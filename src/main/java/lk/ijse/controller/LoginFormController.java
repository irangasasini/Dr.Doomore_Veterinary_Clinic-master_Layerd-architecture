package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.impl.UserBOImpl;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {


    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private CheckBox chkbxRememberMe;


    @FXML
    private AnchorPane Pane;

    UserBOImpl model = (UserBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.USER);


    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String username= txtUsername.getText();
        String password= txtPassword.getText();

        UserBOImpl model = new UserBOImpl();


        try {
             boolean isIn = model.searchUser(username,password);
            if (!isIn){
                new Alert(Alert.AlertType.WARNING,"Login details incorrect").show();
            }else{
                navigate.navigate(Pane,"main_form.fxml");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }


    public void btnCreateanAccountOnAction(ActionEvent actionEvent) throws IOException {
        navigate.changePane(Pane,"signup_form.fxml");

    }
}
