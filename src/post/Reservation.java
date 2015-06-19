package post;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Reservation extends JFrame implements ActionListener {
	private JTable table;
	String[] title = { "¿¹¾à¹øÈ£", "ÀÌ¸§", "¿¬¶ôÃ³", "³»¿ë", "½ÂÀÎ¿©ºÎ" };
	JScrollPane scroll;
	DefaultTableModel model;
	JButton reserve_bt, close_bt, update_bt;
	public Connection con;
	public PreparedStatement pstmt;
	public ResultSet rs;

	public Reservation() {
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC608\uC57D \uAD00\uB9AC");
		lblNewLabel.setFont(new Font("±¼¸²", Font.BOLD, 24));
		lblNewLabel.setBounds(300, 10, 109, 28);
		getContentPane().add(lblNewLabel);

		model = new DefaultTableModel(title, 0);
		table = new JTable(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(12, 48, 486, 398);
		getContentPane().add(scroll);

		reserve_bt = new JButton("\uC608\uC57D \uC2B9\uC778");
		reserve_bt.setFont(new Font("±¼¸²", Font.BOLD, 24));
		reserve_bt.setBounds(510, 98, 174, 81);
		getContentPane().add(reserve_bt);

		close_bt = new JButton("\uB2EB\uAE30");
		close_bt.setFont(new Font("±¼¸²", Font.BOLD, 24));
		close_bt.setBounds(510, 334, 174, 86);
		getContentPane().add(close_bt);

		update_bt = new JButton("\uBBF8\uC2B9\uC778");
		update_bt.setFont(new Font("±¼¸²", Font.BOLD, 24));
		update_bt.setBounds(510, 221, 174, 81);
		getContentPane().add(update_bt);

		reserve();
		reserve_bt.addActionListener(this);
		close_bt.addActionListener(this);
		update_bt.addActionListener(this);
		setVisible(true);
		setSize(706, 499);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int num = 0;
		String r_num = null;
		if (e.getSource() == reserve_bt || e.getSource() == update_bt) {
			num = table.getSelectedRow();
			r_num = (String) model.getValueAt(num, 0);
		}
		if (e.getSource() == reserve_bt) {
			Reserve_approve(Integer.parseInt(r_num));
		} else if (e.getSource() == update_bt) {
			update(num, Integer.parseInt(r_num));
		} else if (e.getSource() == close_bt) {
			setVisible(false);
		}
	}

	public void update(int num, int r_num) {
		String sql = "update reservation set reser_count = '½ÂÀÎ°ÅºÎ' where reservation_no = ?";
		con = UserConnection.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, r_num);
			pstmt.executeUpdate();
			int row = model.getRowCount();
			for (int i = row - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			reserve();
		} catch (Exception err) {
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {
			}
		}
	}

	public void reserve() {
		String sql = "select r.reservation_no, m.member_name, m.member_phone, r.reser_content, r.reser_count from reservation r, member m where r.member_no = m.member_no";
		con = UserConnection.getConnection();
		String reserve_data[] = new String[5];
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				reserve_data[0] = Integer.toString(rs.getInt(1));
				reserve_data[1] = rs.getString(2);
				reserve_data[2] = rs.getString(3);
				reserve_data[3] = rs.getString(4);
				reserve_data[4] = rs.getString(5);
				model.addRow(reserve_data);
			}
		} catch (Exception err) {
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {
			}
		}
	}

	public void Reserve_approve(int num) {
		String sql2 = "update reservation set reser_count = 'È®ÀÎ' where reservation_no = ? and reser_count = '½ÂÀÎ´ë±â'";
		con = UserConnection.getConnection();
		try {

			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			int row = model.getRowCount();
			for (int i = row - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			reserve();
		} catch (Exception err) {
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {
			}
		}
	}
}
