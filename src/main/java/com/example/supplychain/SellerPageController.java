package com.example.supplychain;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerPageController {
    @FXML
    TextField name;
    @FXML
    TextField price;
    @FXML
    TextField email;
    @FXML
    public void Add(MouseEvent event)throws SQLException {
        ResultSet res=HelloApplication.connection.executeQuery("Select max(ProductID) from product");
        int PruductID=0;
        if(res.next())
        {
            PruductID=res.getInt("max(ProductID)")+1;
        }
        String query= String.format("Insert into product values(%s,'%s','%s','%s')",PruductID,name.getText(),price.getText(),email.getText());
        int response=HelloApplication.connection.executeUpdate(query);
        Dialog<String>dialog=new Dialog<>();
        dialog.setTitle("Product Add");
        ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if(response>0)
        {
            dialog.setContentText("A new Product is Added");

        }
        else{
            dialog.setContentText("A new Product is Not Added");
        }
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
}
