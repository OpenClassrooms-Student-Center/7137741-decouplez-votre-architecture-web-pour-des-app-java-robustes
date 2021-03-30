package com.airbusiness.program.swing;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Reservation {
    private long id;
    private String name;
    private String destination;
    private String departureDate;
    private String returnDate;

    public Reservation() {}

    public Reservation(String name, String destination, String departureDate, String returnDate) {
        this.name = name;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }
    
    public static void fillTableWithData(JTable table) {
    	table.setModel(buildTableModel());
    }
    
    private static DefaultTableModel buildTableModel() {
	    DefaultTableModel tableModel = new DefaultTableModel();
    	ResultSet rs = AirBusinessDb.getResultSet("SELECT * from reservations");
    	try {
			ResultSetMetaData metaData = rs.getMetaData();
		    Vector<String> columnNames = new Vector<String>();
		    int columnCount = metaData.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(metaData.getColumnName(column));
		        System.out.println("columnName: " + metaData.getColumnName(column));
		    }

		    // data of the table
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		            System.out.println("value [" + columnIndex + "][" + rs.getObject(columnIndex) + "]");
		        }
		        data.add(vector);
		    }
		    tableModel.setDataVector(data, columnNames);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return tableModel;
	}

    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }

    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getDestination() {
    	return destination;
    }
    
    public void setDestination (String destination) {
    	this.destination = destination;
    }
    
    public String getDepartureDate () {
    	return departureDate;
    }
    
    public void setDepartureDate (String departureDate) {
    	this.departureDate = departureDate;
    }
    
    public String getReturnDate () {
    	return returnDate;
    }
    
    public void setReturnDate (String returnDate) {
    	this.returnDate = returnDate;
    }
}
