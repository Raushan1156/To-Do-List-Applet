package signup;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
//import java.util.Collection;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class ToDo extends JFrame {

	private JPanel contentPane;
	private JTextField task1;
	private JTextField task2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToDo frame = new ToDo();
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
	public ToDo() {
		setBackground(Color.BLACK);
		Connection con = (Connection) DB.dbconnect();
		
//	signup_s ob=new signup_s();
//	String userid= ob.username;
//	System.out.println(userid);
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 524);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("To Do Sheet");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(90, 43, 176, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Don't Do List");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(606, 43, 154, 36);
		contentPane.add(lblNewLabel_1);
		
		task1 = new JTextField();
		task1.setBounds(128, 111, 176, 39);
		contentPane.add(task1);
		task1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Tasks");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(52, 110, 75, 36);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tasks");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(568, 111, 60, 35);
		contentPane.add(lblNewLabel_3);
		
		task2 = new JTextField();
		task2.setBounds(643, 111, 187, 39);
		contentPane.add(task2);
		task2.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(131, 221, 552, 256);
		contentPane.add(scrollPane);
		
		JButton edit = new JButton("Edit");

		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			DefaultTableModel df=(DefaultTableModel)table.getModel();
			int selected=table.getSelectedRow();
			int id=Integer.parseInt(df.getValueAt(selected, 0).toString());
			task1.setText(df.getValueAt(selected,1).toString());
			task2.setText(df.getValueAt(selected,2).toString());
            edit.setEnabled(false);
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Work to do", "work don't do"
			}
		));
		
		JButton add = 
				new JButton("Add");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String todo=task1.getText();
				String donot=task2.getText();
				try {
					PreparedStatement pst=(PreparedStatement)con.prepareStatement("insert into to_do(To_Do, Donot_do) values(?,?)");
				    pst.setString(1, todo);
				    pst.setString(2, donot);
				    pst.executeUpdate();
				    
				    JOptionPane.showMessageDialog(null,"task added");
			}
				catch(SQLException e2)
				{
          			System.out.println(e2);

				}
				
				    
				    task1.setText("");
				    task2.setText("");
				    
			int a;
			try {
				PreparedStatement pst1=(PreparedStatement)con.prepareStatement("select * from to_do");
			    ResultSet rs=pst1.executeQuery();
			    ResultSetMetaData rd=(ResultSetMetaData) rs.getMetaData();
			    a=rd.getColumnCount();
			    DefaultTableModel df=(DefaultTableModel) table.getModel();
			    df.setRowCount(0);
			    while(rs.next())
			    {
			    	Vector v2=new Vector();
			    	for(int i=1;i<=a;i++)
			    	{
			    		v2.add(rs.getString("id"));
			    		v2.add(rs.getString("To_Do"));
			    	    v2.add(rs.getString("Donot_do"));	
			    	}
			    	df.addRow(v2);
			    }
			    
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		add.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add.setBounds(391, 164, 99, 39);
		contentPane.add(add);
		
//		JButton edit = new JButton("Edit");
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel df=(DefaultTableModel)table.getModel();
				int s=table.getSelectedRow();
				int id=Integer.parseInt(df.getValueAt(s,0).toString());
				try {
					String todo=task1.getText();
					String donot=task2.getText();
					
					PreparedStatement pst=(PreparedStatement)con.prepareStatement("update to_do set To_Do=?, Donot_do=? where id=?");
					pst.setString(1,todo);
					pst.setString(2,donot);
					pst.setInt(3,id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"List Updated");
					task1.setText("");
					task2.setText("");
				}
				catch(Exception e2)
				{
					
				}
					int a;
					try {
						PreparedStatement pst1=(PreparedStatement)con.prepareStatement("select * from to_do");
					    ResultSet rs=pst1.executeQuery();
					    ResultSetMetaData rd=(ResultSetMetaData) rs.getMetaData();
					    a=rd.getColumnCount();
					    DefaultTableModel df1=(DefaultTableModel) table.getModel();
					    df1.setRowCount(0);
					    while(rs.next())
					    {
					    	Vector v2=new Vector();
					    	for(int i=1;i<=a;i++)
					    	{
					    		v2.add(rs.getString("id"));
					    		v2.add(rs.getString("To_Do"));
					    	    v2.add(rs.getString("Donot_do"));	
					    	}
					    	df1.addRow(v2);
					    }
					    
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
			}	
		});
		
		
		
		
		
		
		edit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		edit.setBounds(746, 352, 99, 36);
		contentPane.add(edit);
		
		JButton done = new JButton("Done");
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		done.setFont(new Font("Tahoma", Font.PLAIN, 16));
		done.setBounds(746, 424, 99, 36);
		contentPane.add(done);
	}
}
