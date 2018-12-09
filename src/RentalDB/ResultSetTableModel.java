/************************************
 * (C) Copyright 2018 by Tim Pettis *
 ************************************/
package RentalDB;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

//ResultSet class
public class ResultSetTableModel extends AbstractTableModel {

  private final Connection connection;
  private final Statement statement;
  private ResultSet resultSet;
  private ResultSetMetaData metaData;
  private int numberOfRows;

  //Keep track of database connection status
  private boolean connectedToDatabase = false;

  //Constructor initializes resultSet and obtains its metadata object
  //Determines number of rows
  public ResultSetTableModel(String url, String username,
      String password, String query) throws SQLException {
    //Connect to database
    connection = DriverManager.getConnection(url, username, password);

    //Create Statement to query database
    statement = connection.createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

    //Update database connection status
    connectedToDatabase = true;

    //Set query and execute it
    setQuery(query);
  }

  //Get class that represents column type
  public Class getColumnClass(int column) throws IllegalStateException {
    //Ensure database connection is available
    if (!connectedToDatabase) {
      throw new IllegalStateException("Not Connected to Database");
    }

    //Determine Java class of column
    try {
      String className = metaData.getColumnClassName(column + 1);

      //Return Class object that represents className
      return Class.forName(className);
    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return Object.class; //If problems occur above, assume type Object
  }

  //Get number of columns in ResultSet
  public int getColumnCount() throws IllegalStateException {
    //Ensure database connection is available
    if (!connectedToDatabase) {
      throw new IllegalStateException("Not Connected to Database");
    }

    //Determine number of columns
    try {
      return metaData.getColumnCount();
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }

    return 0; //If problems occur above, return 0 for number of columns
  }

  //Get name of a particular column in ResultSet
  public String getColumnName(int column) throws IllegalStateException {
    //Ensure database connection is available
    if (!connectedToDatabase) {
      throw new IllegalStateException("Not Connected to Database");
    }

    //Determine column name
    try {
      return metaData.getColumnName(column + 1);
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }

    return ""; //If problems, return empty string for column name
  }

  //Return number of rows in ResultSet
  public int getRowCount() throws IllegalStateException {
    //Ensure database connection is available
    if (!connectedToDatabase) {
      throw new IllegalStateException("Not Connected to Database");
    }

    return numberOfRows;
  }

  //Obtain value in particular row and column
  public Object getValueAt(int row, int column)
      throws IllegalStateException {

    //Ensure database connection is available
    if (!connectedToDatabase) {
      throw new IllegalStateException("Not Connected to Database");
    }

    //Obtain a value at specified ResultSet row and column
    try {
      resultSet.absolute(row + 1);
      return resultSet.getObject(column + 1);
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }

    return ""; //If problems, return empty string object
  }

  //Set new database query string
  public void setQuery(String query)
      throws SQLException, IllegalStateException {

    //Ensure database connection is available
    if (!connectedToDatabase) {
      throw new IllegalStateException("Not Connected to Database");
    }

    //Specify query and execute it
    resultSet = statement.executeQuery(query);

    //Obtain metadata for ResultSet
    metaData = resultSet.getMetaData();

    //Determine number of rows in ResultSet
    resultSet.last(); //Move to last row
    numberOfRows = resultSet.getRow(); //Get row number

    fireTableStructureChanged(); //Notify JTable that model has changed
  }

  //Close Statement and Connection
  public void disconnectFromDatabase() {
    if (connectedToDatabase) {
      try {
        resultSet.close();
        statement.close();
        connection.close();
      } catch (SQLException sqlException) {
        sqlException.printStackTrace();
      } finally { //Update database connection status
        connectedToDatabase = false;
      }
    }
  }
}