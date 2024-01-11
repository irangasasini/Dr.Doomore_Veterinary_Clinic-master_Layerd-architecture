package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ProductBO;
import lk.ijse.dto.ProductDto;
import lk.ijse.dto.Tm.ProductTm;
import lk.ijse.bo.custom.impl.ProductBOImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductPageController {
    public TextField txti_id;
    public TextField txtdescription;
    public TextField txtqty;
    public TextField txto_id;
    public TableColumn colitem_id;
    public TableColumn colqty;
    public TableColumn coldescription;
    public TableColumn colo_id;
    public TableView tblproduct;
    ProductBO productBO = (ProductBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PRODUCT);

    public void initialize() {
        setCellValueFactory();
        loadAllItems();
    }

    private void loadAllItems() {
        ObservableList<ProductTm> obList = FXCollections.observableArrayList();

        try {
            List<ProductDto> list = productBO.getAllProduct();
            for (ProductDto dto: list){
                obList.add(new ProductTm(
                        dto.getI_id(),
                        dto.getQty(),
                        dto.getDescription(),
                        dto.getO_id()
                ));
            }
            tblproduct.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colitem_id.setCellValueFactory(new PropertyValueFactory<>("i_id"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colo_id.setCellValueFactory(new PropertyValueFactory<>("o_id"));



    }

    public void searchIdOnAction(ActionEvent actionEvent) {
        String code = txti_id.getText();

        try {
            ProductDto dto = productBO.searchProduct(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Items not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(ProductDto dto) {
        txti_id.setText(dto.getI_id());
        txtqty.setText(String.valueOf(dto.getQty()));
        txtdescription.setText(dto.getDescription());
        txto_id.setText(dto.getO_id());
    }


    public void ClearOnAction(ActionEvent actionEvent) {
        clearFields();

    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        String id = txti_id.getText();

        try {
            boolean isDeleted = productBO.deleteProduct(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Items deleted!").show();
                clearFields();
                loadAllItems();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void UpadateOnAction(ActionEvent actionEvent) {
        String id = txti_id.getText();
        int qty = Integer.parseInt(txtqty.getText());
        String des = txtdescription.getText();
        String o_id = txto_id.getText();

        try {
            boolean isUpdated = productBO.updateProduct(new ProductDto(id, qty ,des,o_id ));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Items updated!").show();
                clearFields();
                loadAllItems();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void AddOnAction(ActionEvent actionEvent) {
        boolean isProductValid = validateProduct();
        if (isProductValid) {
            String id = txti_id.getText();
            int qty = Integer.parseInt(txtqty.getText());
            String des = txtdescription.getText();
            String o_id = txto_id.getText();

            ProductDto dto = new ProductDto(id, qty, des ,o_id);

            try {
                boolean isSaved = productBO.addProduct(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, " Items Saved!", ButtonType.OK).show();
                    clearFields();
                    loadAllItems();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Items Not Saved!", ButtonType.OK).show();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Items Details", ButtonType.OK).show();
        }
    }

    private void clearFields() {
        txti_id.setText("");
        txtqty.setText("");
        txtdescription.setText("");
        txto_id.setText("");
    }

    private boolean validateProduct() {
        String itemIDText = txti_id.getText();
        Pattern compile = Pattern.compile("[I][0-9]{3,}");
        Matcher matcher = compile.matcher(itemIDText);
        boolean matches = matcher.matches();

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Item ID").show();
            return false;
        }
        return true;
    }
}
