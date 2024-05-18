package com.twinternship.in;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JButton;

public class OnlineVoterSystem {

	private JFrame frame;
	private JTextField textname;
	private JTextField textphone;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OnlineVoterSystem window = new OnlineVoterSystem();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OnlineVoterSystem() {
		initialize();
		Connect();
		tableLoad();
	}
	
	private String url = "jdbc:mysql://localhost:3306/OnlineVotingSystem?autoReconnect=true&useSSL=false";
	private String user = "root";
	private String password = "root";
	private Connection con;
	private PreparedStatement pat;
	public void Connect() 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver is loaded");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Connection Established");
			
		}
		catch(ClassNotFoundException e) {
			
		}
		catch(SQLException e) {
			
		}
	}
	
	public void tableLoad() {
		try {
			pat = con.prepareStatement("select * from votingsystem");
			pat.executeQuery();
			
			}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 569, 342);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ONLINE VOTING SYSTEM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(163, 34, 273, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Cast Your Vote Here");
		lblNewLabel_1_1.setBounds(56, 171, 175, 17);
		frame.getContentPane().add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textname = new JTextField();
		textname.setBounds(205, 90, 159, 17);
		frame.getContentPane().add(textname);
		textname.setColumns(10);
		
		textphone = new JTextField();
		textphone.setBounds(205, 118, 159, 17);
		frame.getContentPane().add(textphone);
		textphone.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Phone");
		lblNewLabel_1.setBounds(106, 117, 86, 17);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBackground(new Color(255, 245, 235));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Enter Name");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(106, 92, 86, 17);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Party A");
		rdbtnNewRadioButton.setBounds(56, 199, 109, 23);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Party B");
		rdbtnNewRadioButton_1.setBounds(56, 225, 109, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_1_1 = new JRadioButton("Party C");
		rdbtnNewRadioButton_1_1.setBounds(56, 251, 109, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_1_1);
		
		JButton btnNewButton = new JButton("Submit your vote");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name,mobile;
				
				name = textname.getText();
				mobile = textphone.getText();
				
				
				try {
					pat = con.prepareStatement("insert into votingsystem(name,mobile)values(?,?)");
					pat.setString(1, name);
					pat.setString(2, mobile);
					pat.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record add Successfully!!!!");
					tableLoad();
					textname.setText("");
					textphone.setText("");
					textname.requestFocus();
				}
				
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(241, 199, 153, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCheckResult = new JButton("Check Result");
		btnCheckResult.setForeground(Color.BLACK);
		btnCheckResult.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCheckResult.setBackground(Color.WHITE);
		btnCheckResult.setBounds(241, 233, 153, 23);
		frame.getContentPane().add(btnCheckResult);
		
	}
}
