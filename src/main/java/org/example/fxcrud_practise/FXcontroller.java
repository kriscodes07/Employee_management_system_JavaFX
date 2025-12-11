package org.example.fxcrud_practise;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

import DAO.DAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee_model;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FXcontroller implements Initializable {


    DAO dao=new DAO();




    @FXML
    private Button add;

    @FXML
    private Button delete;

    @FXML
    private TextField dept;

    @FXML
    private TextField emcode;

    @FXML
    private TextField mail;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<Employee_model, String> sl;


    @FXML
    private TableView<Employee_model> tabelview;

    @FXML
    private TableColumn<Employee_model, String> column_dept;

    @FXML
    private TableColumn<Employee_model, String> column_emcode;

    @FXML
    private TableColumn<Employee_model, String> column_mail;
    @FXML
    private TableColumn<Employee_model, String> name_column;



    @FXML
    private Button update;

    @FXML
    void ADD(ActionEvent event) {


        String ename= name.getText().trim();
        String edepart= dept.getText().trim();
        String email= mail.getText().trim();
        String em_code= emcode.getText().trim();

        if(ename.isEmpty()||edepart.isEmpty()||email.isEmpty()||em_code.isEmpty()){


            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Fields cannot be empty");
            alert.show();

            return;
        }


        Employee_model em=new Employee_model(ename,edepart,email,em_code);

         try {
             dao.adddata(em);
             Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setContentText("Data inseted successfully");
             alert.show();
table();

         } catch (SQLException e) {
             Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.setContentText(" Error ");
             alert.show();
             throw new RuntimeException(e);
         }



         name.setText(" ");
         dept.setText(" ");
         mail.setText(" ");
         emcode.setText(" ");





    }

    @FXML
    void DELETE(ActionEvent event) {

        String em_code= emcode.getText().trim();


        try {

            if (dao.delete_data(em_code)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Data Deleted successfully");
                table();
                alert.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error in deleting data  ");
                alert.show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        name.setText(" ");
        dept.setText(" ");
        mail.setText(" ");
        emcode.setText(" ");


    }

    @FXML
    void UPDATE(ActionEvent event) {
        String ename= name.getText().trim();
        String edepart= dept.getText().trim();
        String email= mail.getText().trim();
        String em_code= emcode.getText().trim();


        try {
            dao.update(ename,edepart,em_code);
            table();

        }catch (Exception e){
            e.printStackTrace();
        }




        name.setText(" ");
        dept.setText(" ");
        mail.setText(" ");
        emcode.setText(" ");

    }


    public void table() {
        ObservableList<Employee_model> employeeModels = dao.table();
        tabelview.setItems(employeeModels);

        sl.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        column_dept.setCellValueFactory(new PropertyValueFactory<>("department"));
        column_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        column_emcode.setCellValueFactory(new PropertyValueFactory<>("emcode"));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table();   // ← Initialize table at the beginning
        selectRow();
    }




    public void selectRow() {
        tabelview.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selected) -> {
            if (selected != null) {
                name.setText(selected.getName());
                dept.setText(selected.getDepartment());
                mail.setText(selected.getMail());
                emcode.setText(selected.getEmcode());
            }
        });
    }

}
