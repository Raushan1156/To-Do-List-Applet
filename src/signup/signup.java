package signup;

//import java.awt.BorderLayout;
import java.lang.Runnable;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

@SuppressWarnings("serial")
public class signup extends JFrame {

	private JPanel contentPane;
	private JTextField signupname;
	private JTextField signupemail;
	private JTextField signupuserid;
	private JPasswordField signuppassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup frame = new signup();
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
	public signup() {
		setBackground(Color.MAGENTA);
		
		Connection con = (Connection) DB.dbconnect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 538);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sign up form");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(249, 42, 191, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Your Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(103, 107, 185, 39);
		contentPane.add(lblNewLabel_1);
		
		signupname = new JTextField();
		signupname.setBounds(320, 113, 285, 31);
		contentPane.add(signupname);
		signupname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(103, 190, 125, 31);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email ID");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(103, 263, 125, 31);
		contentPane.add(lblNewLabel_3);
		
		signupemail = new JTextField();
		signupemail.setBounds(320, 265, 285, 31);
		contentPane.add(signupemail);
		signupemail.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("User ID");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(103, 343, 102, 28);
		contentPane.add(lblNewLabel_4);
		
		signupuserid = new JTextField();
		signupuserid.setBounds(320, 340, 239, 31);
		contentPane.add(signupuserid);
		signupuserid.setColumns(10);
		
		JLabel label_username = new JLabel("");
		label_username.setFont(new Font("Tahoma", Font.ITALIC, 14));
		label_username.setBounds(320, 141, 278, 22);
		contentPane.add(label_username);
		
		JLabel label_password = new JLabel("");
		label_password.setFont(new Font("Tahoma", Font.ITALIC, 14));
		label_password.setBounds(320, 216, 278, 28);
		contentPane.add(label_password);
		
		JLabel label_email = new JLabel("");
		label_email.setFont(new Font("Tahoma", Font.ITALIC, 14));
		label_email.setBounds(320, 293, 261, 37);
		contentPane.add(label_email);
		
		JLabel label_user_id = new JLabel("");
		label_user_id.setFont(new Font("Tahoma", Font.ITALIC, 14));
		label_user_id.setBounds(320, 365, 217, 39);
		contentPane.add(label_user_id);
		
		
		
		JButton signupbutton = new JButton("Sign Up");
		signupbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				/* storing sign up data */
//				if(signupname.getText()==null && signuppassword.getText()==null && signupemail.getText()==null && signupuserid.getText()==null) {
//					signupname.setText("User name is empty.");
//					JOptionPane.showMessageDialog(null, "Enter data into all field.");
//				}
				if(signupname.getText().trim().isEmpty()&&signuppassword.getText().trim().isEmpty()&& signupemail.getText().trim().isEmpty()&&signupuserid.getText().trim().isEmpty())
				{
					label_username.setText("Name is empty.");
					label_password.setText("Password is empty.");
					label_email.setText("Email is empty");
					label_user_id.setText("user id is empty");

				}
				else if(signupname.getText().trim().isEmpty())
				{
					label_username.setText("Name is empty.");
					label_password.setText("");
					label_email.setText("");
					label_user_id.setText("");
				}
				
				else if(signuppassword.getText().trim().isEmpty())
				{
					label_username.setText("");
					label_password.setText("Password is empty.");
					label_email.setText("");
					label_user_id.setText("");
				}
				else if(signupemail.getText().trim().isEmpty())
				{
					label_username.setText("");
					label_password.setText("");
					label_email.setText("Email is empty");
					label_user_id.setText("");
				}
				else if(signupuserid.getText().trim().isEmpty())
				{
					label_username.setText("");
					label_password.setText("");
					label_email.setText("");
					label_user_id.setText("user id is empty");
				}
				 
				else 
				{
				try {
				String name=signupname.getText();
				@SuppressWarnings("deprecation")
				String password=signuppassword.getText();
				String email= signupemail.getText();
				String user_id=signupuserid.getText();
				 
				PreparedStatement pst=con.prepareStatement("insert into signup(Name,Password,Email,userName) values(?,?,?,?)");
				pst.setString(1, name);
				pst.setString(2, password);
				pst.setString(3, email);
				pst.setString(4, user_id);
				pst.executeUpdate();
				
				
					
				JOptionPane.showMessageDialog(null, "Data added");
				signupname.setText("");
				signuppassword.setText("");
				signupemail.setText("");
				signupuserid.setText("");
				}
				
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null,"Click ok to log in.");
				signup_s sus=new signup_s();
				sus.setVisible(true);
				dispose();
			}
			}
		});
		signupbutton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		signupbutton.setBounds(320, 432, 139, 31);
		contentPane.add(signupbutton);
		
		signuppassword = new JPasswordField();
		signuppassword.setBounds(320, 186, 285, 31);
		contentPane.add(signuppassword);
		
		

	}
}
