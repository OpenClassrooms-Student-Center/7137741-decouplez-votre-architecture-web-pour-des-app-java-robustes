package com.airbusiness.program.swing;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AirBusinessSwing {
    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Air Business");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Air Business");
        contentPane.add(label);

        JTable table = new JTable();
        Client.fillTableWithData(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        contentPane.add(new JScrollPane(table));
 
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        
        JButton btn = new JButton("Clients");
        btn.addActionListener(new ActionListener() {
			@Override
        	public void actionPerformed(ActionEvent e) {
				Client.fillTableWithData(table);
        	}
        });
        buttonPane.add(btn);
 
        btn = new JButton("Reservations");
        btn.addActionListener(new ActionListener() {
			@Override
        	public void actionPerformed(ActionEvent e) {
				Reservation.fillTableWithData(table);
        	}
        });
        buttonPane.add(btn);
        
        btn = new JButton("Pilots");
        btn.addActionListener(new ActionListener() {
			@Override
        	public void actionPerformed(ActionEvent e) {
				Pilot.fillTableWithData(table);
        	}
        });
        buttonPane.add(btn);
        
        btn = new JButton("Planes");
        btn.addActionListener(new ActionListener() {
			@Override
        	public void actionPerformed(ActionEvent e) {
				Plane.fillTableWithData(table);
        	}
        });
        buttonPane.add(btn);
        
        contentPane.add(buttonPane);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	AirBusinessDb.setupDb();
                createAndShowGUI();
            }
        });
    } 
}

