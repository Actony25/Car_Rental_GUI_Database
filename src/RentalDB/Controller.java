package RentalDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {

  @FXML
  void connect(ActionEvent event) {

    final String DATABASE_URL = "jdbc:derby:lib\\rentalcarDB";
    final String SELECT_QUERY =
        "SELECT customer_id, firstName, lastName, country_of_residence FROM customers";

    // use try-with-resources to connect to and query the database
    try (
        Connection connection = DriverManager.getConnection(
            DATABASE_URL, "user", "password");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_QUERY)) {
      resultSet.next();
    }
    // AutoCloseable objects' close methods are called now
    catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
  }
}