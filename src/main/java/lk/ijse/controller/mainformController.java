package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class mainformController {

    @FXML
    private Pane pane;

     @FXML
     void AppoinmentsbtnOnActhion(ActionEvent event) throws IOException {
         navigate.changePane(pane,"appointment_form.fxml");
    }
    @FXML
    void ordersbtnOnActhion(ActionEvent event) throws IOException {
        navigate.changePane(pane,"orders_form.fxml");
    }

    @FXML
    void DoctorOnActhion(ActionEvent event) throws IOException {
        navigate.changePane(pane,"docter_form.fxml");
    }

    @FXML
    void CustomerOnAction(ActionEvent event) throws IOException {
        navigate.changePane(pane,"customer_form.fxml");
    }

    @FXML
    void ProductOnAction(ActionEvent event) throws IOException {
        navigate.changePane(pane,"product_form.fxml");
    }

    @FXML
    void PaymentOnAction(ActionEvent event) throws IOException {
         navigate.changePane(pane,"payments_form.fxml");
    }

    @FXML
    void ReportsOnAction(ActionEvent event) throws IOException {
        navigate.changePane(pane,"reports_form.fxml");
    }


    @FXML
    void SuppliersOnAction(ActionEvent event) throws IOException {
        navigate.changePane(pane,"suppliers_form.fxml");
    }

    @FXML
    void PetsOnAction(ActionEvent event) throws IOException {
        navigate.changePane(pane,"pets_form.fxml");
    }
    @FXML
    void SignOutOmAction(ActionEvent event) throws IOException {
        navigate.switchNavigation("login_form.fxml",event);
    }
    @FXML
    void EmployeeOnAction(ActionEvent event) throws IOException {
        navigate.changePane(pane,"employee_form.fxml");

    }

    public void MadicineOnAction(ActionEvent actionEvent) throws IOException {
         navigate.changePane(pane,"medicine_form.fxml");
    }

    public void dashboarOnAction(ActionEvent actionEvent) throws IOException {
         navigate.changePane(pane,"dashboard_page.fxml");
    }
}
