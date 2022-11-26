package com.example.supplychain;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Header {
    public AnchorPane root;
    Header() throws IOException{
        root= FXMLLoader.load(getClass().getResource("Header.fxml"));
    }
}
