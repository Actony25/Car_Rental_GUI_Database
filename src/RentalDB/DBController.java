/************************************
 * (C) Copyright 2018 by Tim Pettis *
 ************************************/
package RentalDB;

import java.sql.SQLException;
import java.util.regex.PatternSyntaxException;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javax.swing.*;
import javax.swing.table.*;

//Controller class used for Car Rental Database
public class DBController implements DBConnection {

  @FXML
  private BorderPane borderPane;
  @FXML
  private TextArea queryTextArea;
  @FXML
  private TextField filterTextField;

  //Default query retrieves all data from rentals table
  private static final String DEFAULT_QUERY = "SELECT * FROM RENTALS";

  private ResultSetTableModel tableModel;
  private TableRowSorter<TableModel> sorter;

  public void initialize() {
    queryTextArea.setText(DEFAULT_QUERY);

    //Create ResultSetTableModel and display database table
    try {
      //Create TableModel for results of DEFAULT_QUERY
      tableModel = new ResultSetTableModel(DATABASE_URL,
          USERNAME, PASSWORD, DEFAULT_QUERY);

      //Create JTable based on the tableModel
      JTable resultTable = new JTable(tableModel);

      //Set up row sorting for JTable
      sorter = new TableRowSorter<>(tableModel);
      resultTable.setRowSorter(sorter);

      //Configure SwingNode to display JTable, then add to borderPane
      SwingNode swingNode = new SwingNode();
      swingNode.setContent(new JScrollPane(resultTable));
      borderPane.setCenter(swingNode);
    } catch (SQLException sqlException) {
      displayAlert(AlertType.ERROR, "Database Error",
          sqlException.getMessage());
      tableModel.disconnectFromDatabase(); // close connection
      System.exit(1); // terminate application
    }
  }

  //Query the database and display results in JTable
  @FXML
  void submitQueryButtonPressed(ActionEvent event) {
    //Perform a new query
    try {
      tableModel.setQuery(queryTextArea.getText());
    } catch (SQLException sqlException) {
      displayAlert(AlertType.ERROR, "Database Error",
          sqlException.getMessage());

      //Try to recover from invalid user query by executing default query
      try {
        tableModel.setQuery(DEFAULT_QUERY);
        queryTextArea.setText(DEFAULT_QUERY);
      } catch (SQLException sqlException2) {
        displayAlert(AlertType.ERROR, "Database Error",
            sqlException2.getMessage());
        tableModel.disconnectFromDatabase(); // close connection
        System.exit(1); // terminate application
      }
    }
  }

  @FXML
  void RentalsButtonPressed(ActionEvent event) {
    //Query the rentals table
    try {
      tableModel.setQuery("SELECT * FROM RENTALS");
    } catch (SQLException sqlException) {
      displayAlert(AlertType.ERROR, "Database Error",
          sqlException.getMessage());
    }
  }

  @FXML
  void EmployeeButtonPressed(ActionEvent event) {
    //Query the employees table
    try {
      tableModel.setQuery("SELECT * FROM EMPLOYEES");
    } catch (SQLException sqlException) {
      displayAlert(AlertType.ERROR, "Database Error",
          sqlException.getMessage());
    }
  }

  @FXML
  void LocationButtonPressed(ActionEvent event) {
    //Query the location table
    try {
      tableModel.setQuery("SELECT * FROM LOCATION");
    } catch (SQLException sqlException) {
      displayAlert(AlertType.ERROR, "Database Error",
          sqlException.getMessage());
    }
  }

  @FXML
  void CustomersButtonPressed(ActionEvent event) {
    //Query the customers table
    try {
      tableModel.setQuery("SELECT * FROM CUSTOMERS");
    } catch (SQLException sqlException) {
      displayAlert(AlertType.ERROR, "Database Error",
          sqlException.getMessage());
    }
  }

  @FXML
  void FleetButtonPressed(ActionEvent event) {
    //Query the fleet table
    try {
      tableModel.setQuery("SELECT * FROM FLEET");
    } catch (SQLException sqlException) {
      displayAlert(AlertType.ERROR, "Database Error",
          sqlException.getMessage());
    }
  }

  //Apply filter to results
  @FXML
  void applyFilterButtonPressed(ActionEvent event) {
    String text = filterTextField.getText();

    if (text.length() == 0) {
      sorter.setRowFilter(null);
    } else {
      try {
        sorter.setRowFilter(RowFilter.regexFilter(text));
      } catch (PatternSyntaxException pse) {
        displayAlert(AlertType.ERROR, "Regex Error",
            "Bad regex pattern");
      }
    }
  }

  //Display an Alert dialog
  private void displayAlert(
      AlertType type, String title, String message) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
