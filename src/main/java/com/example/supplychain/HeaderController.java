package com.example.supplychain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class HeaderController {
    @FXML
    Button loginButton;

    @FXML
    Label email;

    @FXML
    TextField searchText;

    @FXML
    Button logoutButton;


    @FXML
    public void initialize(){
        if(!HelloApplication.emailID.equals("")){
            loginButton.setOpacity(0);
            email.setText(HelloApplication.emailID);
        }
    }

    @FXML
    public void login(MouseEvent event)throws IOException {
        AnchorPane loginpage=FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        HelloApplication.root.getChildren().add(loginpage);
    }

    @FXML
    public void search(MouseEvent event)throws IOException, SQLException {
        Header header=new Header();
        ProductPage product=new ProductPage();
        AnchorPane ProductPane=new AnchorPane();
        ProductPane.getChildren().add(product.showProductsbyname(searchText.getText()));
        ProductPane.setLayoutX(150);
        ProductPane.setLayoutY(100);
        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root,ProductPane);
    }
    @FXML
    public void logout(MouseEvent event)throws IOException,SQLException{
        if(logoutButton.getOpacity()==0) {
            logoutButton.setOpacity(1);
            logoutButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    HelloApplication.emailID="";
                    try {
                        Header header = new Header();
                        HelloApplication.root.getChildren().add(header.root);
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
            });
        }
        else
            logoutButton.setOpacity(0);
    }
}
