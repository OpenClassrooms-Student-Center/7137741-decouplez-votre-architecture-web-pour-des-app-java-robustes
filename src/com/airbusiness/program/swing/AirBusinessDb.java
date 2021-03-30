package com.airbusiness.program.swing;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AirBusinessDb {
	private final static String DB_URL = "jdbc:sqlite:./db/airbusiness.db";
	private static Connection conn;

	public static void setupDb() {
        createNewDatabase("airbusiness.db");
        createNewTables();
        insertRows();
	}
	
	public static ResultSet getResultSet(String query) {
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(query);
			return stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	private static void createNewDatabase(String fileName) {
        try {
        	conn = DriverManager.getConnection(DB_URL);

            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database connection has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void createNewTables() {
    	try {
	    	createTableClients();
	    	createTableReservations();
	    	createTablePilots();
	    	createTablePlanes();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void createTableClients() throws SQLException {
    	createTable("CREATE TABLE IF NOT EXISTS clients (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    first_name text NOT NULL,\n"
                + "    last_name text NOT NULL,\n"
                + "    address text NOT NULL,\n"
                + "    telephone text NOT NULL,\n"
                + "    balance real\n"
                + ")");
	}
    
	private static void createTableReservations() throws SQLException {
		createTable("CREATE TABLE IF NOT EXISTS reservations (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    name text NOT NULL,\n"
                + "    destination text NOT NULL,\n"
                + "    departureDate text NOT NULL,\n"
                + "    returnDate text NOT NULL\n"
                + ")");
	}
	
	private static void createTablePilots() throws SQLException {
		createTable("CREATE TABLE IF NOT EXISTS pilots (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    first_name text NOT NULL,\n"
                + "    last_name text NOT NULL,\n"
                + "    address text NOT NULL,\n"
                + "    telephone text NOT NULL,\n"
                + "    aircraftRating text NOT NULL,\n"
                + "    arrivalAirport text,\n"
                + "    departureAirport text,\n"
                + "    licenseExpiration text NOT NULL,\n"
                + "    licenseNumber text NOT NULL\n"
                + ")");
	}
	
	private static void createTablePlanes() throws SQLException {
		createTable("CREATE TABLE IF NOT EXISTS planes (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    type text NOT NULL,\n"
                + "    capacity integer NOT NULL,\n"
                + "    weightLimit integer NOT NULL\n"
                + ")");
	}
	
	private static void createTable(String sql) throws SQLException {
        conn.createStatement().execute(sql);
	}

    private static void insertRows() {
    	insertRowsClients();
    	insertRowsReservations();
    	insertRowsPilots();
    	insertRowsPlanes();
    }
    
    private static void insertRowsClients() {
    	insertClientRow("Justin", "Time", generateAddress(), generateTelephone(), generateAmount());
    	insertClientRow("Jack", "Wagon", generateAddress(), generateTelephone(), generateAmount());
    	insertClientRow("Eugene", "Skinner", generateAddress(), generateTelephone(), generateAmount());
    }
    private static void insertClientRow(String firstName, String lastName, String address, String telephone, double balance) {
        String sql = "INSERT INTO clients(first_name,last_name,address,telephone,balance) VALUES(?,?,?,?,?)";
 
        try (Connection conn = DriverManager.getConnection(DB_URL);
        		PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, address);
            pstmt.setString(4, telephone);
            pstmt.setDouble(5, balance);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

	private static void insertRowsReservations() {
    	insertReservationRow("Jenkins Vacation", "Aruba", generateFutureDate(5, 10), generateFutureDate(20, 5));
    	insertReservationRow("Grizwald Vacation", "Paris", generateFutureDate(5, 10), generateFutureDate(20, 5));
    	insertReservationRow("Heme Business", "Rome", generateFutureDate(5, 10), generateFutureDate(20, 5));
    	insertReservationRow("OC Office Getaway", "Springfield", generateFutureDate(5, 10), generateFutureDate(20, 5));
	}
    private static void insertReservationRow(String name, String destination, String departureDate, String returnDate) {
        String sql = "INSERT INTO reservations(name,destination,departureDate,returnDate) VALUES(?,?,?,?)";
 
        try (Connection conn = DriverManager.getConnection(DB_URL);
        		PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, destination);
            pstmt.setString(3, departureDate);
            pstmt.setString(4, returnDate);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

	private static void insertRowsPilots() {
		insertPilotRow("Icarus", "Wright", generateAddress(), generateTelephone(), "twin-engine-piston", "EUG", "PDX", generateFutureDate(365, 365), "1");
		insertPilotRow("Otto", "Wright", generateAddress(), generateTelephone(), "twin-engine-piston", "NC", "KDH", generateFutureDate(365, 365), "2");
		insertPilotRow("Bob", "Bleriot", generateAddress(), generateTelephone(), "single-engine-piston", "FRA", "ENG", generateFutureDate(365, 365), "1909");
		insertPilotRow("Neil", "Yeager", generateAddress(), generateTelephone(), "multi-engine-jet", "", "", generateFutureDate(365, 365), "1947");
		insertPilotRow("Chuck", "Armstrong", generateAddress(), generateTelephone(), "multi-enging-jet, rocket", "ETH", "MON", generateFutureDate(365, 365), "1969");
	}
	private static void insertPilotRow(String firstName, String lastName, String address, String telephone,
										String aircraftRating, String arrivalAirport, String departureAirport,
										String licenseExpiration, String licenseNumber) {
        String sql = "INSERT INTO pilots(first_name,last_name,address,telephone,aircraftRating,arrivalAirport,departureAirport,licenseExpiration,licenseNumber) VALUES(?,?,?,?,?,?,?,?,?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
        		PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, address);
            pstmt.setString(4, telephone);
            pstmt.setString(5, aircraftRating);
            pstmt.setString(6, arrivalAirport);
            pstmt.setString(7, departureAirport);
            pstmt.setString(8, licenseExpiration);
            pstmt.setString(9, licenseNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	private static void insertRowsPlanes() {
		insertPlaneRow("Lear Jet 45", 9, 6000);
		insertPlaneRow("Cessna 182", 3, 1100);
	}
	private static void insertPlaneRow(String type, int capacity, int weightLimit) {
        String sql = "INSERT INTO planes(type,capacity,weightLimit) VALUES(?,?,?)";
 
        try (Connection conn = DriverManager.getConnection(DB_URL);
        		PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, type);
            pstmt.setInt(2, capacity);
            pstmt.setInt(3, weightLimit);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	private static Random random = new Random();
	private static String[] streetNames = { "Ash", "Cedar", "Fleur", "Maple", "Pine", "Voler" };
	private static String generateAddress() {
		return (random.nextInt(9000) + 1000) + " " + streetNames[random.nextInt(streetNames.length)];
	}
	private static String generateTelephone() {
		return random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10) + "-" + random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10);
	}
	private static double generateAmount() {
		double value = (random.nextDouble() * 300) + 50;
		return Math.round(value * 100.0) / 100.0;
	}
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static String generateFutureDate(int minDaysInFuture, int range) {
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        int daysInTheFuture = random.nextInt(range) + minDaysInFuture;
        c.add(Calendar.DAY_OF_MONTH, daysInTheFuture);
		return dateFormat.format(c.getTime());
	}
}
