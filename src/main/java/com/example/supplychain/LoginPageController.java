package com.example.supplychain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.supplychain.HelloApplication.connection;

public class LoginPageController {
    @FXML
    TextField email;
    @FXML
    TextField password;
    @FXML

    public void login(MouseEvent event)throws SQLException, IOException {
        /*System.out.println(email.getText());
        System.out.println(password.getText());*/
        String query= String.format("Select * from user where emailID='%s' AND pass='%s'",email.getText(),password.getText());
        ResultSet res=HelloApplication.connection.executeQuery(query);

        if(res.next()){
            String userType=res.getString("userType");
            HelloApplication.emailID=res.getString("emailID");
            if(userType.equals("Buyer")){
                System.out.println("Logged in as Buyer");

                ProductPage products=new ProductPage();

                Header header=new Header();
                ListView<HBox>productList=products.showProducts();

                AnchorPane productPane=new AnchorPane();
                productPane.setLayoutX(30);
                productPane.setLayoutY(70);

                productPane.getChildren().add(productList);
                HelloApplication.root.getChildren().clear();
                HelloApplication.root.getChildren().addAll(header.root,productPane);

            }
            else{
                System.out.println("Logged in as Seller");
                AnchorPane sellerPage= FXMLLoader.load(getClass().getResource("SellerPage.fxml"));
                HelloApplication.root.getChildren().add(sellerPage);
            }
        }
        else
        {
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Login Failed");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Please enter a valid ID/Password");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }
}
