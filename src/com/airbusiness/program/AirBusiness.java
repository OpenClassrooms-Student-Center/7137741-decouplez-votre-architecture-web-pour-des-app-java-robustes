package com.airbusiness.program;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AirBusiness {
	private final static String DB_URL = "jdbc:sqlite:./db/test.db";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createNewDatabase("test.db");
        createNewTable();
        insertRows();
        findClientsWithOutstandingBalances();
    }
    
    /**
     * Connect to a sample database
     *
     * @param fileName the database file name
     */
    public static void createNewDatabase(String fileName) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createNewTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS clients (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    name text NOT NULL,\n"
                + "    balance real\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
        		Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void insertRows() {
    	insertRow("Julie", 50.0);
    	insertRow("Eddie", 175.67);
    	insertRow("Beaver", 219.53);
    }
    
    public static void insertRow(String name, double capacity) {
        String sql = "INSERT INTO clients(name,balance) VALUES(?,?)";
 
        try (Connection conn = DriverManager.getConnection(DB_URL);
        		PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void findClientsWithOutstandingBalances() {
        String sql = "SELECT id, name, balance FROM clients WHERE balance >= 0.0";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("name") + "\t" +
                                   rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
