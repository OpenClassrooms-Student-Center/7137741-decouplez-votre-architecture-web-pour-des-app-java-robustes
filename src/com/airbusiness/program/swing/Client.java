package com.airbusiness.program.swing;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Client {
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String telephone;
    private double outstandingBalance;

    public static void fillTableWithData(JTable table) {
    	table.setModel(buildTableModel());
    }
    
    private static DefaultTableModel buildTableModel() {
    	ResultSet rs = AirBusinessDb.getResultSet("SELECT * from clients");
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
		    return new DefaultTableModel(data, columnNames);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
	}
	
    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public String getFirstName() {
    	return firstName;
    }
    
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
    
    public String getLastName() {
    	return lastName;
    }
    
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
    
    public String getAddress() {
    	return address;
    }
    
    public void setAddress(String address) {
    	this.address = address;
    }
    
    public String getTelephone() {
    	return telephone;
    }
    
    public void setTelephone(String telephone) {
    	this.telephone = telephone;
    }
    
    public double getOutstandingBalance() {
    	return outstandingBalance;
    }
    
    public void setOutstandingBalance(double outstandingBalance) {
    	this.outstandingBalance = outstandingBalance;
    }
}
