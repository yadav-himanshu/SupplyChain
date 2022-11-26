package com.example.supplychain;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Order {
    void placeOrder(String ProductID)throws SQLException {
        ResultSet res=HelloApplication.connection.executeQuery("Select max(OrderID) from orders");
        int OrderID=0;
        if(res.next())
        {
            OrderID=res.getInt("max(OrderID)")+1;
        }
        String query=String.format("Insert into orders values(%s,%s,'%s')",OrderID,ProductID,HelloApplication.emailID);
        int response=HelloApplication.connection.executeUpdate(query);
        if(response>0)
        {
            System.out.println("Order is Placed");
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Order Status");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Your Order is Placed");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }
}
