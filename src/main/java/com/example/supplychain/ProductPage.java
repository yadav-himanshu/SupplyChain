package com.example.supplychain;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductPage {
    ListView<HBox> products;


    ListView<HBox>showProductsbyname(String search)throws SQLException {

        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res=HelloApplication.connection.executeQuery("Select * from product");
        products=new ListView<>();


        Label Name=new Label();
        Label Price=new Label();
        Label ID=new Label();

        HBox Details=new HBox();
        Name.setMinWidth(80);
        ID.setMinWidth(40);
        Price.setMinWidth(50);

        Name.setText(" Name ");
        Price.setText(" Price ");
        ID.setText("ProductID ");

        Details.getChildren().addAll(ID,Name,Price);
        productList.add(Details);
        while(res.next()){
            if(res.getString("ProductName").toLowerCase().contains(search.toLowerCase())) {
                Label ProductName = new Label();
                Label ProductPrice = new Label();
                Label ProductID = new Label();
                Button buy = new Button();
                HBox productDetails = new HBox();
                ProductName.setMinWidth(80);
                ProductID.setMinWidth(65);
                ProductPrice.setMinWidth(50);
                buy.setText("Buy");

                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (HelloApplication.emailID.equals("")) {
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("You are not logged in");
                            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            dialog.setContentText("Login first before buying");
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.showAndWait();
                        } else {


                            try {
                                Order place = new Order();
                                place.placeOrder(ProductID.getText());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("You Clicked on Buy button");
                    }
                });

                ProductName.setText(res.getString("ProductName"));
                ProductPrice.setText(res.getString("Price"));
                ProductID.setText("" + res.getInt("ProductID"));
                productDetails.getChildren().addAll(ProductID, ProductName, ProductPrice, buy);
                productList.add(productDetails);
            }
        }
        if(productList.size()==1)
        {
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Search Result");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("No Product is Available for this search");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
        products.setItems(productList);
        return products;
    }


    ListView<HBox>showProducts()throws SQLException {

        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res=HelloApplication.connection.executeQuery("Select * from product");
        products=new ListView<>();


        Label Name=new Label();
        Label Price=new Label();
        Label ID=new Label();

        HBox Details=new HBox();
        Name.setMinWidth(80);
        ID.setMinWidth(40);
        Price.setMinWidth(50);

        Name.setText(" Name ");
        Price.setText(" Price ");
        ID.setText("ProductID ");

        Details.getChildren().addAll(ID,Name,Price);
        productList.add(Details);
        while(res.next()){
            Label ProductName=new Label();
            Label ProductPrice=new Label();
            Label ProductID=new Label();
            Button buy=new Button();
            HBox productDetails=new HBox();
            ProductName.setMinWidth(80);
            ProductID.setMinWidth(65);
            ProductPrice.setMinWidth(50);
            buy.setText("Buy");

            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent){
                    if(HelloApplication.emailID.equals(""))
                    {
                        Dialog<String> dialog=new Dialog<>();
                        dialog.setTitle("You are not logged in");
                        ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        dialog.setContentText("Login first before buying");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                    }
                    else {


                        try {
                            Order place=new Order();
                            place.placeOrder(ProductID.getText());
                        }
                        catch (SQLException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("You Clicked on Buy button");
                }
            });

            ProductName.setText(res.getString("ProductName"));
            ProductPrice.setText(res.getString("Price"));
            ProductID.setText(""+res.getInt("ProductID"));
            productDetails.getChildren().addAll(ProductID,ProductName,ProductPrice,buy);
            productList.add(productDetails);
        }
        products.setItems(productList);
        return products;
    }
}
