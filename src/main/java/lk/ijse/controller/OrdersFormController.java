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
import lk.ijse.bo.custom.OrdersBO;
import lk.ijse.dto.OrdersDto;
import lk.ijse.dto.Tm.OrdersTm;
import lk.ijse.bo.custom.impl.OrdersBOImpl;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrdersFormController {
    public TextField txtO_Id;
    public TextField txtType;
    public TableView tblOrderDetails;
    public TableColumn colO_Id;
    public TableColumn colType;
    public TableColumn colDate;
    public Label lbldate;

    OrdersBO ordersBO = (OrdersBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ORDER);

    public void initialize(){
        setCellValueFactory();
        loadAllItems();
        loadDate();
    }

    private void loadDate() {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd:MMM:yyyy");
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            lbldate.setText(dateFormat1.format(new Date()));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();
    }
    private void loadAllItems() {
        ObservableList<OrdersTm> obList = FXCollections.observableArrayList();

        try {
            List<OrdersDto> list = ordersBO.getAllOrders();
            for (OrdersDto dto: list){
                obList.add(new OrdersTm(
                        dto.getO_id(),
                        dto.getDate(),
                        dto.getTyp()
                ));
            }
            tblOrderDetails.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colO_Id.setCellValueFactory(new PropertyValueFactory<>("o_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));

    }


    public void searchIdOnAction(ActionEvent actionEvent) {

        String code = txtO_Id.getText();

        try {
            OrdersDto dto = ordersBO.searchOrders(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, " Orders not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void setFields(OrdersDto dto) {
        txtO_Id.setText(dto.getO_id());
        lbldate.setText(dto.getDate());
        txtType.setText(dto.getTyp());
    }

    public void ClearOnAction(ActionEvent actionEvent) {

        clearFields();
    }

    public void DeleteOnAction(ActionEvent actionEvent) {

        String id = txtO_Id.getText();

        try {
            boolean isDeleted = ordersBO.deleteOrders(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Orders deleted!").show();
                clearFields();
                loadAllItems();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtO_Id.setText("");
        lbldate.setText("");
        txtType.setText("");
    }

    public void UpdateOnAction(ActionEvent actionEvent) {

        String id = txtO_Id.getText();
        String date = lbldate.getText();
        String type = txtType.getText();

        try {
            boolean isUpdated = ordersBO.updateOrders(new OrdersDto(id, date,type ));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Orders updated!").show();
                clearFields();
                loadAllItems();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void AddOnAction(ActionEvent actionEvent) {


            String id = txtO_Id.getText();
            String date = lbldate.getText();
            String type = txtType.getText();

            OrdersDto dto = new OrdersDto(id, date, type);

            try {
                OrdersBOImpl ordersModel = new OrdersBOImpl();
                boolean isSaved = ordersModel.addOrders(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " Orders Saved!", ButtonType.OK).show();
                    clearFields();
                    loadAllItems();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Orders Not Saved!", ButtonType.OK).show();
                }

            } catch (SQLException e) {
                System.out.println(e);


            }
//        }else {
//            new Alert(Alert.AlertType.ERROR, "Invalid Orders Details", ButtonType.OK).show();
//        }
    }

    private boolean validateOrders() {

        String ordersIDText = txtO_Id.getText();
        Pattern compile = Pattern.compile("[O][0-9]{3,}");
        Matcher matcher = compile.matcher(ordersIDText);
        boolean matches = matcher.matches();

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Orders ID").show();
        }
        return false;
        }
    }

