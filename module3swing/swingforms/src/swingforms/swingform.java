package swingforms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class swingform implements ActionListener {

	JLabel l1, l2, l3, l4, l5;

	JTextField t1, t2, t3, t4;
	JRadioButton r1, r2;
	JButton b1, b2, b3, b4, b5;

	public swingform() {
		JFrame fr = new JFrame("MY DATA");
		fr.setVisible(true);
		fr.setSize(700, 500);
		fr.setLayout(null);

		l1 = new JLabel("ID");
		l1.setBounds(100, 100, 120, 20);
		fr.add(l1);

		l2 = new JLabel("username");
		l2.setBounds(100, 130, 120, 20);
		fr.add(l2);

		l3 = new JLabel("Gender");
		l3.setBounds(100, 160, 120, 20);
		fr.add(l3);

		r1 = new JRadioButton("MALE");
		r1.setBounds(200, 160, 140, 20);
		fr.add(r1);

		r2 = new JRadioButton("Female");
		r2.setBounds(300, 160, 140, 20);
		fr.add(r2);

		l4 = new JLabel("Address");
		l4.setBounds(100, 190, 120, 20);
		fr.add(l4);

		l5 = new JLabel("Contact");
		l5.setBounds(100, 220, 120, 20);
		fr.add(l5);

		t1 = new JTextField();
		t1.setBounds(200, 100, 120, 20);
		fr.add(t1);

		t2 = new JTextField();
		t2.setBounds(200, 130, 120, 20);
		fr.add(t2);

		t3 = new JTextField();
		t3.setBounds(200, 190, 120, 20);
		fr.add(t3);

		t4 = new JTextField();
		t4.setBounds(200, 220, 120, 20);
		fr.add(t4);

		b1 = new JButton("EXIT");
		b1.setBounds(100, 250, 100, 20);
		fr.add(b1);

		b2 = new JButton("REGISTER");
		b2.setBounds(250, 250, 100, 20);
		fr.add(b2);

		b3 = new JButton("DELETE");
		b3.setBounds(100, 280, 100, 20);
		fr.add(b3);

		b4 = new JButton("UPDATE");
		b4.setBounds(250, 280, 100, 20);
		fr.add(b4);

		b5 = new JButton("RESET");
		b5.setBounds(180, 310, 100, 20);
		fr.add(b5);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);

	}

	public static void main(String[] args) {
		new swingform();
	}

	public static Connection createConnection() {
		Connection conn = null;
		try {
			System.out.println("connection satrted");
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata", "root", "Whisperoftheheart");
			System.out.println("connected");
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return conn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b2) {
			System.out.println("register button clicked");
			int Id = Integer.parseInt(t1.getText());
			String username = t2.getText();
			String Gender = r1.getText();
			String Address = t3.getText();
			long Contact = Long.parseLong(t4.getText());
			System.out.println(username + Gender + Address + Contact);
			try {
				Connection conn = swingform.createConnection();
				System.out.println("connected");
				String sql = "insert into userdatas(Id,username,Gender,Address,Contact) values(?,?,?,?,?)";
				System.out.println("query");
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, Id);
				pst.setString(2, username);
				pst.setString(3, Gender);
				pst.setString(4, Address);
				pst.setLong(5, Contact);
				pst.executeUpdate();
				System.out.println("data registered");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				r1.setText("");
				r2.setText("");

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		} else if (e.getSource() == b4) {
			System.out.println("update button clicked");
			int Id = Integer.parseInt(t1.getText());
			String username = t2.getText();
			String Gender = r1.getText();
			long Contact = Long.parseLong(t3.getText());
			String Address = t4.getText();
			try {
				Connection conn = swingform.createConnection();
				String sql = "update userdatas set Id = ?,username = ?, Gender =?,Address = ?,contact = ?)";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, Id);
				pst.setString(2, username);
				pst.setString(3, Gender);
				pst.setString(4, Address);
				pst.setLong(5, Contact);

				pst.executeUpdate();
				System.out.println("data registered");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (e.getSource() == b3) {
			System.out.println("delete button clicked");
			int Id = Integer.parseInt(t1.getText());

			try {
				Connection conn = swingform.createConnection();
				String sql = "delete from userdatas where ID = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, Id);
				pst.executeUpdate();
				System.out.println("data deleted");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		else if (e.getSource() == b5) {
			System.out.println("RESET BUTTON CLICKED");
			int ID = Integer.parseInt(t1.getText());
			String username = t2.getText();
			String Gender = r1.getText();
			long Contact = Long.parseLong(t3.getText());
			String Address = t4.getText();
			try {
				Connection conn = swingform.createConnection();
				System.out.println("data has been reset");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		else if (e.getSource() == b1) {
			System.out.println("EXIT BUTTON CLICKED");
			int Id = Integer.parseInt(t1.getText());
			String username = t2.getText();
			String Gender = r1.getText();
			long Contact = Long.parseLong(t3.getText());
			String Address = t4.getText();
			try {
				Connection conn = swingform.createConnection();
				String sql = "EXIT_form";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, Id);
				pst.setString(2, username);
				pst.setString(3, Gender);
				pst.setString(4, Address);
				pst.setLong(5, Contact);

				pst.executeUpdate();
				System.out.println("EXIT FORM");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				
				t4.setText("");
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}
}
