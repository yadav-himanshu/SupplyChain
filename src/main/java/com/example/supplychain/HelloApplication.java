package com.example.supplychain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static DatabaseConnection connection;

    public  static Group root;
    public static String emailID;

    @Override
    public void start(Stage stage) throws Exception {
        emailID="";
        connection=new DatabaseConnection();
        root=new Group();
        Header header=new Header();

        ProductPage products=new ProductPage();
        ListView<HBox> productList=products.showProducts();

        AnchorPane productPane=new AnchorPane();
        productPane.setLayoutX(30);
        productPane.setLayoutY(70);

        productPane.getChildren().add(productList);
        /*ResultSet res=connection.executeQuery("Select * from product");
        while(res.next())
        {
            System.out.println(res.getString("ProductName"));
        }
        int res2=connection.executeUpdate("insert into product values(3,'Notebook',100,'himanshu12@gmail.com')");
        if(res2>0)
        {
            System.out.println("A new row is inserted");
        }*/
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        root.getChildren().addAll(header.root,productPane);

        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Supply Chain");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e ->{
            try{
                connection.con.close();
                System.out.println("Connection is closed");
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}