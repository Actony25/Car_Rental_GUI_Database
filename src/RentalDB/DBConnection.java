/************************************
 * (C) Copyright 2018 by Tim Pettis *
 ************************************/
package RentalDB;

//Interface with database URL, username and password
public interface DBConnection {

  String DATABASE_URL = "jdbc:derby:lib\\rentalcarDB";
  String USERNAME = "user";
  String PASSWORD = "password";
}