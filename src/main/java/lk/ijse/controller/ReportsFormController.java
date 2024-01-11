package lk.ijse.controller;

import javafx.event.ActionEvent;
import lk.ijse.db.DbConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;


public class ReportsFormController {
    public void ReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {

        JasperDesign load = JRXmlLoader.load(getClass().getResourceAsStream("/Report/Medi.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                null,
                DbConnection.getInstance().getConnection()
        );
        JasperViewer.viewReport(jasperPrint,false);
    }

    public void PaymnetOnAction(ActionEvent actionEvent) throws JRException, SQLException {

        JasperDesign load = JRXmlLoader.load(getClass().getResourceAsStream("/Report/payment.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                null,
                DbConnection.getInstance().getConnection()
        );
        JasperViewer.viewReport(jasperPrint,false);
    }
    }


