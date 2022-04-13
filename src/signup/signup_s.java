package signup;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class signup_s extends JFrame {

	private JPanel contentPane;
	private JTextField usernameinput;
	private JTextField passwordinput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup_s frame = new signup_s();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection con=null;
	//public String username;
 signup_s() {
 	setBackground(Color.YELLOW);
		con=(Connection) DB.dbconnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 546);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("To Do Applet");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(246, 47, 174, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter User Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(127, 181, 190, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(127, 271, 150, 31);
		contentPane.add(lblNewLabel_2);
		
		usernameinput = new JTextField();
		usernameinput.setBounds(339, 181, 201, 31);
		contentPane.add(usernameinput);
		usernameinput.setColumns(10);
		
		passwordinput = new JTextField();
		passwordinput.setBounds(339, 271, 201, 28);
		contentPane.add(passwordinput);
		passwordinput.setColumns(10);
		
		JLabel label_user_name = new JLabel("");
		label_user_name.setFont(new Font("Tahoma", Font.ITALIC, 14));
		label_user_name.setBounds(339, 207, 201, 31);
		contentPane.add(label_user_name);
		
		JLabel label_user_password = new JLabel("");
		label_user_password.setFont(new Font("Tahoma", Font.ITALIC, 14));
		label_user_password.setBounds(339, 296, 201, 29);
		contentPane.add(label_user_password);
		
		
		JButton login = new JButton("Log in");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(usernameinput.getText().trim().isEmpty()&& passwordinput.getText().trim().isEmpty())
				{
					label_user_name.setText("user name is empty");
					label_user_password.setText("Password is empty");
				}
				else if(usernameinput.getText().trim().isEmpty())
				{
					label_user_password.setText("");
					label_user_name.setText("user name is empty");	
				}
				else if(passwordinput.getText().trim().isEmpty())
				{
					label_user_name.setText("");
					label_user_password.setText("Password is empty");	
				} 
				else { 
				try {
					String username=usernameinput.getText();
//					System.out.println(username);
					String userpassword=passwordinput.getText();
					
					PreparedStatement pst= con.prepareStatement("select * from signup where userName=? and Password=?");
					pst.setString(1, username);
					pst.setString(2, userpassword);
					
					ResultSet r=pst.executeQuery();
					if(r.next())
					{
						JOptionPane.showMessageDialog(null,"login completed.");
						ToDo td=new ToDo();
						td.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "userid or password incorrect.");
						usernameinput.setText("");
						passwordinput.setText("");
					}

				}
				
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				}
			}
		});
		
		login.setFont(new Font("Tahoma", Font.PLAIN, 16));
		login.setBounds(513, 405, 99, 31);
		contentPane.add(login);
		
		
		JButton dosignup = new JButton("Sign up");
		dosignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Proceeding to sign up");
				signup su=new signup();
				su.setVisible(true);
				dispose();
			}
		});
		dosignup.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dosignup.setBounds(310, 405, 99, 31);
		contentPane.add(dosignup);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Remember ");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxNewCheckBox.setBounds(337, 331, 93, 21);
		contentPane.add(chckbxNewCheckBox);
		
	}
}
