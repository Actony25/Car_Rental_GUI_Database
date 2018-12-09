/************************************
 * (C) Copyright 2018 by Tim Pettis *
 ************************************/

package RentalDB;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Main class for Car Rental Database that loads the FXML
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("DBQuery.fxml"));
    primaryStage.setTitle("Car Rental DB");
    primaryStage.setScene(new Scene(root, 900, 650));
    primaryStage.show();
  }

  //Main method
  public static void main(String[] args) {
    launch(args);
  }
}
