package post;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class PostMain extends JFrame implements ActionListener, Runnable {
	public static PreparedStatement pstmt;
	public static java.sql.Connection con;
	public static ResultSet rs;
	JPanel panel[];
	JPanel panel_5;
	private JPanel contentPane;
	JButton btnTable[] = new JButton[16];
	static boolean tableYN = false;
	static int b = 1;
	static int a = 0;
	int holnum[] = new int[16];
	static boolean btntableYN[] = new boolean[16];
	static Reservation reserve;
	Thread tr;
	static Vector list;
	static DefaultTableModel model;
	static int cnt = 0, count = 0, recount = 0;
	JButton reserve_bt, close_bt, casher_bt;
	private JTable table;
	String[] title = { "¿¹¾à ³»¿ë" };
	JScrollPane scroll;
	static int num = 0;
	static Thread t;

	/**
	 * Launch the application.
	 */

	public void thread() {
		PostMain frame;
		if (a == 0) {
			frame = new PostMain();
			// reser();
			Thread t = new Thread(frame); // ThreadÅ¬·¡½º·Î Æ÷Àå
			t.start();
		} else {
			frame = new PostMain();
			// reser();
			Thread t = new Thread(frame); // ThreadÅ¬·¡½º·Î Æ÷Àå
			t.start();

			count = 0;
			cnt = 0;

		}

	}

	/**
	 * Create the frame.
	 */
	public PostMain() {
	}

	public PostMain(int a, boolean tableYN, int num) {
		this.num = num;
		tableYN(num);
		thread();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1330, 869);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1372, 1052);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1303, 92);
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(210,
				105, 30), new Color(210, 105, 30), new Color(210, 105, 30),
				new Color(210, 105, 30)));
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel(
				"\uC8FC\uBB38 \uBC0F \uC815\uC0B0\uAD00\uB9AC");
		lblNewLabel.setForeground(new Color(139, 69, 19));
		lblNewLabel.setBackground(new Color(139, 69, 19));
		lblNewLabel.setFont(new Font("HY¿±¼­L", Font.PLAIN, 33));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 1142, 92);
		panel_1.add(lblNewLabel);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(1034, 102, 269, 713);
		panel_3.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(244,
				164, 96), new Color(244, 164, 96), new Color(244, 164, 96),
				new Color(244, 164, 96)));
		panel.add(panel_3);

		Button reciep_bt = new Button("\uC601\uC218\uC99D \uC870\uD68C");
		reciep_bt.setBounds(10, 443, 249, 80);
		reciep_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_3.setLayout(null);
		reciep_bt.setBackground(new Color(135, 206, 250));
		panel_3.add(reciep_bt);

		Button order_bt = new Button("\uC8FC\uBB38\uB0B4\uC5ED \uC870\uD68C");
		order_bt.setBounds(10, 351, 249, 86);
		order_bt.setBackground(new Color(135, 206, 250));
		panel_3.add(order_bt);

		casher_bt = new JButton("\uB3C8\uD1B5 \uC5F4\uAE30");
		casher_bt.setBounds(10, 529, 249, 86);
		panel_3.add(casher_bt);
		casher_bt.setBackground(Color.LIGHT_GRAY);

		reserve_bt = new JButton("\uC608\uC57D \uD655\uC778");
		reserve_bt.setBounds(10, 259, 249, 86);
		panel_3.add(reserve_bt);
		reserve_bt.setBackground(new Color(245, 245, 245));

		close_bt = new JButton("\uC5C5\uBB34 \uC885\uB8CC");
		close_bt.setBounds(10, 621, 249, 86);
		panel_3.add(close_bt);
		close_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		close_bt.setBackground(new Color(100, 149, 237));

		reserve_bt.addActionListener(this);

		model = new DefaultTableModel(title, 0);
		// model.setColumnIdentifiers(title);

		table = new JTable(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(13, 10, 246, 239);
		panel_3.add(scroll);

		panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setForeground(Color.WHITE);
		panel_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		panel_5.setBounds(10, 102, 1012, 713);
		panel.add(panel_5);
		panel_5.setLayout(null);

		if (a == 0) {
			btnTable(a, tableYN);
		} else {
			btntableYN[a - 1] = tableYN;
			btnTable(a, btntableYN[a - 1]);
		}
		setVisible(true);

		close_bt.addActionListener(this);
		casher_bt.addActionListener(this);
	}

	/*---------------------------------------------------------------------------------------------*/
	public static void count() {
		String sql = "select * from reservation";
		try {
			con = UserConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				recount++;
			}
			System.out.println(recount + "³ª³ª³ª³ª³ª³ª");
		} catch (Exception err) {
			err.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}

	// ---------------------------------------------------------------------------------------
	@Override
	public void run() {
		while (true) {
			count();
			String sql = "select * from reservation where business_no = 1 and reser_count = '½ÂÀÎ´ë±â'";
			String data[] = new String[1];
			String reserve = null;
			try {
				con = UserConnection.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				int row = model.getRowCount();
				for (int i = row - 1; i >= 0; i--) {
					model.removeRow(i);
				}
				while (rs.next()) {
					data[0] = rs.getString(2);
					cnt++;
					if (cnt <= recount) {
						model.addRow(data);
					}
				}
				Thread.sleep(5000); // 0.5ÃÊ
			} catch (Exception err) {
				err.printStackTrace();
			} finally {
				try {
					rs.close();
					pstmt.close();
					con.close();
				} catch (Exception er) {
					er.printStackTrace();
				}
			}
			recount = 0;
			cnt = 0;
		}
	}

	/*---------------------------------------------------------------------------------------------*/
	public static void tableYN(int num) {
		/*
		 * for(int i=0; i<16; i++){ btntableYN[i] = false; }
		 */
		String sql = "select table_use from busi_table where business_no = ?";
		int aa = 0;
		try {
			con = UserConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rs.getString(1);
				if (rs.getString(1).equals("1")) {
					btntableYN[aa] = true;
				} else {
					btntableYN[aa] = false;
				}
				aa++;
			}
		} catch (Exception err) {
			err.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}

	// --------------------------------------------------------------------------------------
	public void btnTable(int a, boolean tableYN) {
		/*
		 * if(a!=0){ btntableYN[a-1] = tableYN; }
		 */

		btnTable[0] = new JButton("È¦1");
		btnTable[0].setForeground(Color.BLACK);
		btnTable[0].setFont(new Font("³ª´®°íµñ", Font.BOLD, 50));
		/*
		 * if(a==0){ btnTable[0].setBackground(new Color(135, 206, 250)); } else
		 */if (btntableYN[0] == false) {
			btnTable[0].setBackground(new Color(135, 206, 250));
		} else if (btntableYN[0] == true) {
			btnTable[0].setBackground(new Color(250, 250, 250));
		}
		btnTable[0].setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE,
				Color.WHITE, Color.WHITE, Color.WHITE));
		btnTable[0].setBounds(30, 15, 234, 167);
		panel_5.add(btnTable[0]);

		btnTable[1] = new JButton("È¦2");
		btnTable[1].setForeground(Color.BLACK);
		btnTable[1].setFont(new Font("³ª´®°íµñ", Font.BOLD, 50));
		btnTable[1].setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE,
				Color.WHITE, Color.WHITE, Color.WHITE));
		if (btntableYN[1] == false) {
			btnTable[1].setBackground(new Color(135, 206, 250));
		} else if (btntableYN[1] == true) {
			btnTable[1].setBackground(new Color(250, 250, 250));
		}
		btnTable[1].setBounds(274, 15, 234, 167);
		panel_5.add(btnTable[1]);

		btnTable[2] = new JButton("È¦3");
		btnTable[2].setForeground(Color.BLACK);
		btnTable[2].setFont(new Font("³ª´®°íµñ", Font.BOLD, 50));
		btnTable[2].setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE,
				Color.WHITE, Color.WHITE, Color.WHITE));
		if (btntableYN[2] == false) {
			btnTable[2].setBackground(new Color(135, 206, 250));
		} else if (btntableYN[2] == true) {
			btnTable[2].setBackground(new Color(250, 250, 250));
		}
		btnTable[2].setBounds(525, 15, 234, 167);
		panel_5.add(btnTable[2]);

		btnTable[3] = new JButton("È¦4");
		btnTable[3].setForeground(Color.BLACK);
		btnTable[3].setFont(new Font("³ª´®°íµñ", Font.BOLD, 50));
		btnTable[3].setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE,
				Color.WHITE, Color.WHITE, Color.WHITE));
		if (btntableYN[3] == false) {
			btnTable[3].setBackground(new Color(135, 206, 250));
		} else if (btntableYN[3] == true) {
			btnTable[3].setBackground(new Color(250, 250, 250));
		}
		btnTable[3].setBounds(765, 15, 234, 167);
		panel_5.add(btnTable[3]);

		btnTable[4] = new JButton("È¦5");
		btnTable[4].setForeground(Color.BLACK);
		btnTable[4].setFont(new Font("³ª´®°íµñ", Font.BOLD, 50));
		btnTable[4].setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE,
				Color.WHITE, Color.WHITE, Color.WHITE));
		if (btntableYN[4] == false) {
			btnTable[4].setBackground(new Color(135, 206, 250));
		} else if (btntableYN[4] == true) {
			btnTable[4].setBackground(new Color(250, 250, 250));
		}
		btnTable[4].setBounds(30, 186, 234, 167);
		panel_5.add(btnTable[4]);

		btnTable[5] = new JButton("È¦6");
		btnTable[5].setForeground(Color.BLACK);
		btnTable[5].setFont(new Font("³ª´®°íµñ", Font.BOLD, 50));
		btnTable[5].setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE,
				Color.WHITE, Color.WHITE, Color.WHITE));
		if (btntableYN[5] == false) {
			btnTable[5].setBackground(new Color(135, 206, 250));
		} else if (btntableYN[5] == true) {
			btnTable[5].setBackground(new Color(250, 250, 250));
		}
		btnTable[5].setBounds(274, 186, 234, 167);
		panel_5.add(btnTable[5]);

		btnTable[6] = new JButton("È¦7");
		btnTable[6].setForeground(Color.BLACK);
		btnTable[6].setFont(new Font("³ª´®°íµñ", Font.BOLD, 50));
		btnTable[6].setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE,
				Color.WHITE, Color.WHITE, Color.WHITE));
		if (btntableYN[6] == false) {
			btnTable[6].setBackground(new Color(135, 206, 250));
		} else if (btntableYN[6] == true) {
			btnTable[6].setBackground(new Color(250, 250, 250));
		}
		btnTable[6].setBounds(525, 186, 234, 167);
		panel_5.add(btnTable[6]);

		btnTable[7] = new JButton("È¦8");
		btnTable[7].setForeground(Color.BLACK);
		btnTable[7].setFont(new Font("³ª´®°íµñ", Font.BOLD, 50));
		btnTable[7].setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE,
				Color.WHITE, Color.WHITE, Color.WHITE));
		if (btntableYN[7] == false) {
			btnTable[7].setBackground(new Color(135, 206, 250));
		} else if (btntableYN[7] == true) {
			btnTable[7].setBackground(new Color(250, 250, 250));
		}
		btnTable[7].setBounds(765, 186, 234, 167);
		panel_5.add(btnTable[7]);

		btnTable[8] = new JButton("È¦9");
		btnTable[8].setForeground(Color.BLACK);
		btnTable[8].setFont(new Font("³ª´®°íµñ", Font.BOLD, 50));
		btnTable[8].setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE,
				Color.WHITE, Color.WHITE, Color.WHITE));
		if (btntableYN[8] == false) {
			btnTable[8].setBackground(new Color(135, 206, 250));
		} else if (btntableYN[8] == true) {
			btnTable[8].setBackground(new Color(250, 250, 250));
		}
		btnTable[8].setBounds(30, 357, 234, 167);
		panel_5.add(btnTable[8]);

		btnTable[9] = new JButton("È¦10");
		btnTable[9].setForeground(Color.BLACK);
		btnTable[9].setFont(new Font("³ª´®°íµñ", Font.BOLD, 50));
		btnTable[9].setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE,
				Color.WHITE, Color.WHITE, Color.WHITE));
		if (btntableYN[9] == false) {
			btnTable[9].setBackground(new Color(135, 206, 250));
		} else if (btntableYN[9] == true) {
			btnTable[9].setBackground(new Color(250, 250, 250));
		}
		btnTable[9].setBounds(274, 357, 234, 167);
		panel_5.add(btnTable[9]);

		btnTable[10] = new JButton("È¦11");
		btnTable[10].setForeground(Color.BLACK);
		btnTable[10].setFont(new Font("³ª´®°íµñ", Font.BOLD, 50));
		btnTable[10].setBorder(new BevelBorder(BevelBorder.LOWERED,
				Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		if (btntableYN[10] == false) {
			btnTable[10].setBackground(new Color(135, 206, 250));
		} else if (btntableYN[10] == true) {
			btnTable[10].setBackground(new Color(250, 250, 250));
		}
		btnTable[10].setBounds(525, 357, 234, 167);
		panel_5.add(btnTable[10]);

		btnTable[11] = new JButton("È¦12");
		btnTable[11].setForeground(Color.BLACK);
		btnTable[11].setFont(new Font("³ª´®°íµñ", Font.BOLD, 50));
		btnTable[11].setBorder(new BevelBorder(BevelBorder.LOWERED,
				Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		if (btntableYN[11] == false) {
			btnTable[11].setBackground(new Color(135, 206, 250));
		} else if (btntableYN[11] == true) {
			btnTable[11].setBackground(new Color(250, 250, 250));
		}
		btnTable[11].setBounds(765, 357, 234, 167);
		panel_5.add(btnTable[11]);

		btnTable[12] = new JButton("È¦13");
		btnTable[12].setForeground(Color.BLACK);
		btnTable[12].setFont(new Font("³ª´®°íµñ", Font.BOLD, 50));
		btnTable[12].setBorder(new BevelBorder(BevelBorder.LOWERED,
				Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		if (btntableYN[12] == false) {
			btnTable[12].setBackground(new Color(135, 206, 250));
		} else if (btntableYN[12] == true) {
			btnTable[12].setBackground(new Color(250, 250, 250));
		}
		btnTable[12].setBounds(30, 528, 234, 167);
		panel_5.add(btnTable[12]);

		btnTable[13] = new JButton("È¦14");
		btnTable[13].setForeground(Color.BLACK);
		btnTable[13].setFont(new Font("³ª´®°íµñ", Font.BOLD, 50));
		btnTable[13].setBorder(new BevelBorder(BevelBorder.LOWERED,
				Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		if (btntableYN[13] == false) {
			btnTable[13].setBackground(new Color(135, 206, 250));
		} else if (btntableYN[13] == true) {
			btnTable[13].setBackground(new Color(250, 250, 250));
		}
		btnTable[13].setBounds(274, 528, 234, 167);
		panel_5.add(btnTable[13]);

		btnTable[14] = new JButton("È¦15");
		btnTable[14].setForeground(Color.BLACK);
		btnTable[14].setFont(new Font("³ª´®°íµñ", Font.BOLD, 50));
		btnTable[14].setBorder(new BevelBorder(BevelBorder.LOWERED,
				Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		if (btntableYN[14] == false) {
			btnTable[14].setBackground(new Color(135, 206, 250));
		} else if (btntableYN[14] == true) {
			btnTable[14].setBackground(new Color(250, 250, 250));
		}
		btnTable[14].setBounds(525, 528, 234, 167);
		panel_5.add(btnTable[14]);

		btnTable[15] = new JButton("È¦16");
		btnTable[15].setForeground(Color.BLACK);
		btnTable[15].setFont(new Font("³ª´®°íµñ", Font.BOLD, 50));
		btnTable[15].setBorder(new BevelBorder(BevelBorder.LOWERED,
				Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		if (btntableYN[15] == false) {
			btnTable[15].setBackground(new Color(135, 206, 250));
		} else if (btntableYN[15] == true) {
			btnTable[15].setBackground(new Color(250, 250, 250));
		}
		btnTable[15].setBounds(765, 528, 234, 167);
		panel_5.add(btnTable[15]);

		for (int i = 0; i < btnTable.length; i++) {
			btnTable[i].addActionListener(this);
		}
	}

	private static class __Tmp {

		private static void __tmp() {
			javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == reserve_bt) {
			new Reservation();
		} else if (e.getSource() == casher_bt) {
			System.out.println("µ·Åë ¿­¾ú´Ù.");

		} else if (e.getSource() == close_bt) {
			System.exit(0);

		} else {
			a = e.paramString().charAt(22) - 48;
			new Counter(a, btntableYN[a - 1]);
			dispose();
		}
	}

}
