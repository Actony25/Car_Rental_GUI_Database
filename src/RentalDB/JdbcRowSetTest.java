package RentalDB;
// Displaying the contents of the authors table using JdbcRowSet.
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;  
import javax.sql.rowset.RowSetProvider;  

public class JdbcRowSetTest 
{
   // JDBC driver name and database URL
   private static final String DATABASE_URL = "jdbc:derby:lib\\rentalcarDB";
   private static final String USERNAME = "user";
   private static final String PASSWORD = "password";
   
   public static void main(String args[])
   {
      // connect to database books and query database
      try (JdbcRowSet rowSet =
         RowSetProvider.newFactory().createJdbcRowSet())
      {
         // specify JdbcRowSet properties 
         rowSet.setUrl(DATABASE_URL);        
         rowSet.setUsername(USERNAME);          
         rowSet.setPassword(PASSWORD);          
         rowSet.setCommand("SELECT * FROM customers"); // set query
         rowSet.execute(); // execute query                        

         // process query results
         ResultSetMetaData metaData = rowSet.getMetaData();
         int numberOfColumns = metaData.getColumnCount();
         System.out.println("Customers Table of Rental Database:\n");

         // display rowset header
         for (int i = 1; i <= numberOfColumns; i++)
            System.out.printf("%-8s\t", metaData.getColumnName(i));
         System.out.println();
         
         // display each row
         while (rowSet.next()) 
         {
            for (int i = 1; i <= numberOfColumns; i++)
               System.out.printf("%-8s\t", rowSet.getObject(i));
            System.out.println();
         }
      }
      catch (SQLException sqlException) 
      {
         sqlException.printStackTrace();
         System.exit(1);
      }
   } 
} // end class JdbcRowSetTest