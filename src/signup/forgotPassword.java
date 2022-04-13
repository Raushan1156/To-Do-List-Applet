package signup;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class forgotPassword extends JFrame {

	private JPanel contentPane;
	private JTextField user_id;
	private JTextField user_email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					forgotPassword frame = new forgotPassword();
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
	public forgotPassword() {
		con=(Connection) DB.dbconnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Password Recovery");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(246, 32, 204, 55);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter User ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(121, 135, 204, 44);
		contentPane.add(lblNewLabel_1);
		
		user_id = new JTextField();
		user_id.setBounds(291, 135, 258, 39);
		contentPane.add(user_id);
		user_id.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Email ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(121, 223, 165, 36);
		contentPane.add(lblNewLabel_2);
		
		user_email = new JTextField();
		user_email.setBounds(291, 224, 258, 39);
		contentPane.add(user_email);
		user_email.setColumns(10);
		
		JLabel label_user_id = new JLabel("");
		label_user_id.setFont(new Font("Tahoma", Font.ITALIC, 10));
		label_user_id.setBounds(291, 166, 258, 24);
		contentPane.add(label_user_id);
		
		JLabel label_email = new JLabel("");
		label_email.setFont(new Font("Tahoma", Font.ITALIC, 10));
		label_email.setBounds(291, 260, 258, 32);
		contentPane.add(label_email); 
		

		JTextArea recover_password = new JTextArea();
		recover_password.setBounds(291, 385, 255, 30);
		contentPane.add(recover_password);
		
		// get password handler
		JButton btnNewButton = new JButton("Get Password");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user_id.getText().trim().isEmpty()&& user_email.getText().trim().isEmpty())
				{
					label_user_id.setText("Empty");
					label_email.setText("Empty");
					JOptionPane.showMessageDialog(null, "Please Enter any one field");
					
				}
				// password need to fetched from database
				// need to be checked. Start-1
				else
				{
					try {
					String id=user_id.getText();
					String mail=user_email.getText();
					label_user_id.setText("");
					label_email.setText("");
					
					PreparedStatement pst= con.prepareStatement("select Password from signup where userName=? or Email=?");
					pst.setString(1, id);
					pst.setString(2, mail);
					
					ResultSet r=pst.executeQuery();
					if(r.next())
					{
						//recover_password.setText(pst);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "entered data is incorrect");
					}
				}
					catch(Exception e2)
					{
						
					}
				}
				// End-1
			}
		});
		
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(291, 302, 138, 39);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(121, 375, 151, 40);
		contentPane.add(lblNewLabel_3);
		
		
		// login handler
		JButton login = new JButton("Log in");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signup_s sus=new signup_s();
				sus.setVisible(true);
				dispose();
			}
		});
		login.setFont(new Font("Tahoma", Font.PLAIN, 16));
		login.setBounds(291, 444, 107, 31);
		contentPane.add(login);
		
		JLabel lblNewLabel_4 = new JLabel("or");
		lblNewLabel_4.setBounds(391, 195, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		
	}
}
