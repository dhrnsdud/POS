package post;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

public class Counter extends JFrame implements ActionListener {
	private JTable order_table;
	private JTextField textField;
	private JTextField order_tf;
	private JTextField discount_tf;
	private JTextField sum_tf;
	private JTextField date_tf;
	private JTextField textField_5;
	private JTextField table_num_tf;
	private JTextField restraunt_tf;
	private JTextField tf_count;
	public static int cnt;
	public static int all_sum = 0;

	JScrollPane scroll;
	String sqldata = "";

	public Connection con;
	public PreparedStatement pstmt;
	public ResultSet rs;
	String[] data = new String[2];
	String[] table_data = new String[3];
	DefaultTableModel model;
	JTabbedPane tabbedPane;
	JPanel jpButton, jpResult, kimbab, chinese, korean, western; // �г� �ʱ�ȭ
	JMenuBar jmb; // �޴��� �ʱ�ȭ
	JMenu jmEdit, jmHelp; // �޴� �ʱ�ȭ
	JMenuItem mCopy, mPaste, mHelp, mInfo; // �޴� ������ �ʱ�ȭ
	JLabel jlbResult1, jlbResult2; // ���̺� �ʱ�ȭ
	JButton[] jbButton = null; // ��ư�迭 �ʱ�ȭ
	JButton allcancle_bt, selecancle_bt, bt_close, service_bt;
	JButton[] menubutton = null; // ��ư�迭 �ʱ�ȭ
	JButton bu[];
	JButton bt_order, bt_pay, bt_count;
	JButton discount_button;
	String[] title = { "��ǰ��Ȳ", "�Ǹűݾ�", "�Ǹż���" };
	String result = "";
	String result1 = "";
	String result2 = "";
	String number[] = { " ", " ", " " };
	String[] numStr = { "��", "CE", "C", "/", "7", "8", "9", "*", "4", "5", "6",
			"-", "1", "2", "3", "+", "0", "00", ".", "=" }; // ��ư�� �� ��
	static boolean tableYN = false;
	static int menucount = 0;
	static int menunum = 0;
	static int shownum = 0;

	public Counter(int cnt, boolean tableYN) {
		this.cnt = cnt;
		this.tableYN = tableYN;
		dbShow();
		menu_count(menunum);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 498, 122);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("\uD14C\uC774\uBE14 \uBC88\uD638");
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 13));
		lblNewLabel_2.setBounds(7, 88, 76, 15);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\uC810\uD3EC\uBA85");
		lblNewLabel_3.setFont(new Font("����", Font.BOLD, 13));
		lblNewLabel_3.setBounds(12, 42, 57, 15);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\uC8FC\uBB38\uB2F4\uB2F9");
		lblNewLabel_4.setFont(new Font("����", Font.BOLD, 13));
		lblNewLabel_4.setBounds(251, 42, 57, 15);
		panel.add(lblNewLabel_4);

		date_tf = new JTextField();
		date_tf.setEditable(false);
		date_tf.setBounds(251, 80, 235, 32);
		panel.add(date_tf);
		date_tf.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setText("������");
		textField_5.setBounds(320, 39, 166, 31);
		panel.add(textField_5);
		textField_5.setColumns(10);

		table_num_tf = new JTextField();
		table_num_tf.setEditable(false);
		table_num_tf.setBounds(88, 80, 151, 32);
		System.out.println(data[1]);
		table_num_tf.setText(data[1]);
		panel.add(table_num_tf);
		table_num_tf.setColumns(10);

		restraunt_tf = new JTextField();
		restraunt_tf.setEditable(false);
		restraunt_tf.setBounds(88, 39, 151, 31);
		restraunt_tf.setText(data[0]);
		panel.add(restraunt_tf);
		restraunt_tf.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 428, 498, 122);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		order_tf = new JTextField();
		order_tf.setBounds(70, 10, 198, 30);
		panel_1.add(order_tf);
		order_tf.setColumns(10);

		discount_tf = new JTextField();
		discount_tf.setColumns(10);
		discount_tf.setBounds(70, 45, 198, 30);
		panel_1.add(discount_tf);

		sum_tf = new JTextField();
		sum_tf.setColumns(10);
		sum_tf.setBounds(70, 80, 198, 30);
		panel_1.add(sum_tf);

		JLabel lblNewLabel_5 = new JLabel("\uC8FC\uBB38 \uAD00\uB9AC");
		lblNewLabel_5.setFont(new Font("����", Font.BOLD, 14));
		lblNewLabel_5.setBounds(205, 10, 72, 18);
		panel.add(lblNewLabel_5);

		model = new DefaultTableModel(title, 0);
		// model.setColumnIdentifiers(title);

		order_table = new JTable(model);
		table_default();
		scroll = new JScrollPane(order_table);
		scroll.setBounds(12, 142, 498, 276);
		getContentPane().add(scroll);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		panel_7.setBounds(12, 10, 50, 30);
		panel_1.add(panel_7);
		panel_7.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("\uC8FC\uBB38");
		lblNewLabel_1.setBounds(5, 2, 45, 25);
		panel_7.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 18));

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		panel_8.setBounds(12, 45, 50, 30);
		panel_1.add(panel_8);

		JLabel label = new JLabel("\uD560\uC778");
		label.setBounds(5, 2, 45, 25);
		panel_8.add(label);
		label.setFont(new Font("����", Font.BOLD, 18));

		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		panel_9.setBounds(12, 80, 50, 30);
		panel_1.add(panel_9);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(522, 428, 312, 402);
		getContentPane().add(panel_3);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel_3.setLayout(new BorderLayout()); // ��ü ���̾ƿ��� BorderLayout

		JLabel label_1 = new JLabel("\uD569\uACC4");
		label_1.setFont(new Font("����", Font.BOLD, 18));
		label_1.setBounds(5, 2, 45, 25);
		panel_9.add(label_1);

		allcancle_bt = new JButton("\uC804\uCCB4 \uCDE8\uC18C");
		allcancle_bt.setFont(new Font("����", Font.BOLD, 13));
		allcancle_bt.setBounds(280, 10, 97, 100);
		panel_1.add(allcancle_bt);

		selecancle_bt = new JButton("\uC9C0\uC815 \uCDE8\uC18C");
		selecancle_bt.setFont(new Font("����", Font.BOLD, 13));
		selecancle_bt.setBounds(389, 10, 97, 100);
		panel_1.add(selecancle_bt);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 560, 498, 153);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		bt_close = new JButton("\uB2EB\uAE30");
		bt_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bt_close.setFont(new Font("����", Font.BOLD, 13));
		bt_close.setBounds(12, 10, 239, 60);
		panel_2.add(bt_close);

		discount_button = new JButton("\uACB0\uC7AC\uD560\uC778");
		discount_button.setFont(new Font("����", Font.BOLD, 13));
		discount_button.setBounds(261, 10, 95, 60);
		panel_2.add(discount_button);

		service_bt = new JButton("\uC11C\uBE44\uC2A4");
		service_bt.setFont(new Font("����", Font.BOLD, 13));
		service_bt.setBounds(383, 10, 103, 60);
		panel_2.add(service_bt);

		textField = new JTextField();
		textField.setBounds(77, 720, 433, 31);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("\uBA54\uC2DC\uC9C0");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel.setBounds(12, 723, 57, 31);
		getContentPane().add(lblNewLabel);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(522, 10, 538, 408);
		getContentPane().add(panel_4);
		panel_4.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 49, 514, 349);
		panel_4.add(tabbedPane);

		kimbab = new JPanel();
		tabbedPane.addTab("\uBD84\uC2DD", null, kimbab, null);
		kimbab.setLayout(null);

		korean = new JPanel();
		tabbedPane.addTab("\uD55C\uC2DD", null, korean, null);
		korean.setLayout(null);

		western = new JPanel();
		tabbedPane.addTab("\uC591\uC2DD", null, western, null);
		western.setLayout(null);

		chinese = new JPanel();
		tabbedPane.addTab("\uC911\uC2DD", null, chinese, null);
		chinese.setLayout(null);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(869, 428, 210, 402);
		getContentPane().add(panel_6);
		panel_6.setLayout(null);

		tf_count = new JTextField();
		tf_count.setBounds(10, 58, 88, 57);
		panel_6.add(tf_count);
		tf_count.setColumns(10);

		bt_count = new JButton("\uC218\uB839\uC815\uC815");
		bt_count.setFont(new Font("����", Font.BOLD, 13));
		bt_count.setBounds(110, 45, 88, 82);
		panel_6.add(bt_count);

		bt_pay = new JButton("\uACB0\uC7AC");
		bt_pay.setFont(new Font("����", Font.BOLD, 13));
		bt_pay.setBounds(7, 218, 191, 82);
		panel_6.add(bt_pay);

		bt_order = new JButton("\uC8FC\uBB38\uC644\uB8CC");
		bt_order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bt_order.setFont(new Font("����", Font.BOLD, 13));
		bt_order.setBounds(7, 310, 191, 82);
		panel_6.add(bt_order);

		Date sysdate = new Date();
		SimpleDateFormat currentdate = new SimpleDateFormat(
				"yyy�� MM�� dd�� HH�� mm��");
		date_tf.setText(currentdate.format(sysdate));

		btnShow(shownum);
		setVisible(true);
		setSize(1102, 867);

		/** �޴��� ���� */
		jmb = new JMenuBar();

		// jmEdit = new JMenu("����(E)");
		// jmHelp = new JMenu("����(H)");

		// mCopy = new JMenuItem("����");
		// mPaste = new JMenuItem("�ٿ��ֱ�");
		// mHelp = new JMenuItem("���� ����");
		// mInfo = new JMenuItem("���� ����");

		// jmEdit.add(mCopy); // jmEdit��� �޴��� mCopy��� �޴������� �߰�
		// jmEdit.add(mPaste); // jmEdit��� �޴��� mPaste��� �޴������� �߰�
		// jmHelp.add(mHelp); // jmHelp��� �޴��� mHelp��� �޴������� �߰�
		// jmHelp.add(mInfo); // jmHelp��� �޴��� mInfo��� �޴������� �߰�

		// jmb.add(jmEdit); // jmb��� �޴��ٿ� jmEdit��� �޴� �߰�
		// jmb.add(jmHelp); // jmb��� �޴��ٿ� jmHelp��� �޴� �߰�

		// setJMenuBar(jmb); // jmb�޴��� �߰�
		/** �޴��� �� */

		/** ���̺� ���� */
		jpResult = new JPanel(new GridLayout(2, 1)); // jpResult��� �гο�
														// GrideLayot�� ����, 2 x 1
		jpResult.setBackground(Color.WHITE); // jpResult��� �гο� ��� �������
		jlbResult1 = new JLabel("", JLabel.RIGHT); // jlbResult1��� �� ���� ���̺�,
													// ��������
		jlbResult2 = new JLabel("0", JLabel.RIGHT); // jlbResult2��� �⺻�� 0�� ���̺�,
													// ��������
		jlbResult2.setFont(new Font("����", Font.BOLD, 20)); // jlbResult2 ���̺�
															// ��Ʈ�� ����, ���ϰ�, ũ��20
															// ����

		jpResult.add(jlbResult1);
		jpResult.add(jlbResult2); // jpResult �гο� jlbResult2 ���̺� �߰�
		/** ���̺� �� */

		/** ��ư ���� */
		jpButton = new JPanel(new GridLayout(5, 4, 5, 5)); // jpButton��� �г�,
															// GridLayout����, 5 x
															// 4, ������ ���μ��� 5��
		jpButton.setBackground(Color.WHITE); // jpButton �г� ����� ���
		jbButton = new JButton[numStr.length]; // jbButton ��ư �迭 �ʱ�ȭ

		// jbButton�� ǥ���� ���� ����
		for (int i = 0; i < numStr.length; i++) {
			jbButton[i] = new JButton(numStr[i]);
			jbButton[i].setFont(new Font("����", Font.BOLD, 20));
			jpButton.add(jbButton[i]);
			jbButton[i].addActionListener(this);
		}

		/** ��ư �� ���� */
		for (int j = 0; j < numStr.length; j++) {
			if (j < 3) {
				jbButton[j].setForeground(Color.RED);
			} else if (j == 3 || j == 7 || j == 11 || j == 15 || j == 19) {
				jbButton[j].setForeground(Color.BLUE);
			}
		}
		jbButton[0].setForeground(Color.RED);
		/** ��ư �� �� */

		/** ��ư ����Ű ���� */
		// jmEdit.setMnemonic('E');
		// jmHelp.setMnemonic('H');

		// mCopy.setAccelerator(KeyStroke.getKeyStroke('C',
		// InputEvent.CTRL_MASK));
		// mPaste.setAccelerator(KeyStroke.getKeyStroke('V',
		// InputEvent.CTRL_MASK));
		// mHelp.setAccelerator(KeyStroke.getKeyStroke('H',
		// InputEvent.CTRL_MASK));
		// mInfo.setAccelerator(KeyStroke.getKeyStroke('I',
		// InputEvent.CTRL_MASK));
		/** ��ư ����Ű �� */

		// mCopy.addActionListener(this);
		// mPaste.addActionListener(this);
		// mHelp.addActionListener(this);
		// mInfo.addActionListener(this);
		// /** ��ư �� */

		panel_3.add("North", jpResult);
		panel_3.add("Center", jpButton);
		panel_3.setBackground(Color.WHITE);

		for (int i = 0; i < bu.length; i++) {
			bu[i].addActionListener(this);
		}
		allcancle_bt.addActionListener(this);
		selecancle_bt.addActionListener(this);
		bt_close.addActionListener(this);
		service_bt.addActionListener(this);
		bt_order.addActionListener(this);
		bt_pay.addActionListener(this);
		discount_button.addActionListener(this);
		setVisible(true);
		bt_count.addActionListener(this);

		// display(Integer.parseInt(data[1]));
	}

	/*------------------------------------------------------------------------------------------*/
	public void table_default() {

		int discount = 0;
		int result_sum = all_sum - discount;
		String sql4 = "select food_name, food_price, sum from oder where table_no=? and sum > 0;";
		try {
			con = UserConnection.getConnection();

			pstmt = con.prepareStatement(sql4);
			pstmt.setString(1, data[1]);
			rs = pstmt.executeQuery();
			model = (DefaultTableModel) order_table.getModel();
			int num1 = model.getRowCount();

			while (rs.next()) {
				String[] data = new String[4];
				data[0] = rs.getString(1);
				data[1] = rs.getString(2);
				data[2] = rs.getString(3);
				all_sum += Integer.parseInt(data[1])
						* Integer.parseInt(data[2]);
				model.addRow(data);

			}

			if (tableYN == true) {

			}
			order_tf.setText(Integer.toString(all_sum));
			discount = Integer.parseInt(discount_tf.getText());
			discount_tf.setText(Integer.toString(discount));
			sum_tf.setText(Integer.toString(result_sum));

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

	/*----------------------------------------------------------------------------------------*/
	private void table_Show(String name, String price, int count) {
		int foodcount = 1;
		String f_name = "";
		int table_no = 0;
		int all = 0;
		Vector vec = new Vector();
		System.out.println("table_show �޼��� ����");
		String sql1 = "select food_name ,table_no from oder where food_name=? and table_no=?;";
		String sql2 = "insert into oder(food_name, food_price, table_no, sum) values(?,?,?,?);";
		String sql3 = "update oder set sum=sum+1 where food_name=? and table_no=?";
		String sql4 = "select food_name, food_price, sum from oder where table_no=?;";
		String sql5 = "select oder_no from oder;";
		try {

			con = UserConnection.getConnection();
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, name);
			pstmt.setInt(2, Integer.parseInt(data[1]));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				f_name = rs.getString(1);
				table_no = rs.getInt(2);
			}

			con = UserConnection.getConnection();
			pstmt = con.prepareStatement(sql5);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				all = rs.getInt(1);
			}
			System.out.println(f_name + "f_name");

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace(System.out);
		} finally {
			try {
				if (pstmt != null)
					con.close();
				pstmt.close();
				rs.close();

			} catch (Exception e) {

			}
		}

		if ((!(f_name.equals(name)) && table_no != Integer.parseInt(data[1]))) {
			// Ŭ������ �� �˻� �� ������ ���� ��
			try {
				System.out.println(" �ֹ� ������ ���� ��" + data[1]);
				con = UserConnection.getConnection();
				// insert into oder(food_name, food_price, table_no, order_pay,
				// sum) values(?,?,?,0,?);
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, name);
				pstmt.setInt(2, Integer.parseInt(price));
				pstmt.setInt(3, Integer.parseInt(data[1]));
				pstmt.setInt(4, count);
				pstmt.executeUpdate();

				// select food_name, food_price, sum from oder;
				pstmt = con.prepareStatement(sql4);
				pstmt.setString(1, data[1]);
				// pstmt.setString(1, name);
				rs = pstmt.executeQuery();

				model = (DefaultTableModel) order_table.getModel();
				int num1 = model.getRowCount();

				for (int cnt = num1 - 1; cnt >= 0; cnt--) {

					model.removeRow(cnt);
				}

				while (rs.next()) {
					String[] data = new String[4];
					data[0] = rs.getString(1);
					data[1] = rs.getString(2);
					data[2] = rs.getString(3);

					model.addRow(data);

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

		} else if (all == 0) {
			// Ŭ������ �� �˻� �� ������ ���� ��
			try {
				System.out.println(" �ֹ� ������ ���� ��" + data[1]);
				con = UserConnection.getConnection();
				// insert into oder(food_name, food_price, table_no, order_pay,
				// sum) values(?,?,?,0,?);
				System.out.println(sql2 + "sql ����");
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, name);
				System.out.println(name + "name");
				pstmt.setInt(2, Integer.parseInt(price));
				System.out.println(price + "price");
				pstmt.setInt(3, Integer.parseInt(data[1]));
				System.out.println(data[1] + "data[1]");
				pstmt.setInt(4, count);
				System.out.println(count + "count");
				pstmt.executeUpdate();
				System.out.println(" �ֹ������� ���� �� insert ���� ����");

				// select food_name, food_price, sum from oder;
				pstmt = con.prepareStatement(sql4);
				pstmt.setString(1, data[1]);
				System.out.println("�������--------");
				rs = pstmt.executeQuery();
				System.out.println("���� ����");
				System.out.println(name + ", " + price + ", " + count);
				model = (DefaultTableModel) order_table.getModel();
				int num1 = model.getRowCount();

				for (int cnt = num1 - 1; cnt >= 0; cnt--) {

					model.removeRow(cnt);
				}

				while (rs.next()) {
					String[] data = new String[4];
					data[0] = rs.getString(1);
					data[1] = rs.getString(2);
					data[2] = rs.getString(3);

					model.addRow(data);

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

		} else { // �ֹ� ���̺� ������ ���� ��
			try {
				System.out.println("db ���� �ֽ��ϴ�.!!");
				con = UserConnection.getConnection();
				// "update oder set pos=pos+1 where food_name=? and table_no=?";
				pstmt = con.prepareStatement(sql3);
				pstmt.setString(1, name);
				pstmt.setInt(2, table_no);
				pstmt.executeUpdate();

				pstmt = con.prepareStatement(sql4);
				pstmt.setString(1, data[1]);
				System.out.println("�������--------");
				rs = pstmt.executeQuery();
				System.out.println("���� ����");
				System.out.println(name + ", " + price + ", " + count);
				model = (DefaultTableModel) order_table.getModel();
				int num1 = model.getRowCount();

				for (int cnt = num1 - 1; cnt >= 0; cnt--) {

					model.removeRow(cnt);
				}

				while (rs.next()) {
					String[] data = new String[4];
					data[0] = rs.getString(1);
					data[1] = rs.getString(2);
					data[2] = rs.getString(3);

					model.addRow(data);

				}

			} catch (Exception err) {
			} finally {
				try {
					con.close();
					pstmt.close();
					rs.close();
				} catch (Exception e) {
				}
			}
		}

		/*
		 * int num1 = model.getRowCount();
		 * 
		 * for(int cnt = num1-1; cnt>=0; cnt--){
		 * 
		 * model.removeRow(cnt); }
		 */

	}

	/*---------------------------------------------------------------------------------------*/
	public int menu_count(int index) {
		String sql4 = null;
		if (index == 0) {
			sql4 = "select food_no from food_register where business_no = 1";
		} /*
		 * else if (index == 1) { sql4 =
		 * "select food_no from food where food_type = '�ѽ�'"; } else if (index
		 * == 2) { sql4 = "select food_no from food where food_type = '���'"; }
		 * else if (index == 3) { sql4 =
		 * "select food_no from food where food_type = '�߽�'"; }
		 */
		try {
			System.out.println(" �ֹ� ������ ���� ��" + data[1]);
			con = UserConnection.getConnection();

			pstmt = con.prepareStatement(sql4);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				menucount++;
			}
			System.out.println(menucount);
		} catch (Exception err) {
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {
			}
		}
		System.out.println(menucount + " count ����:�ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�");
		return menucount;
	}

	// Counter �ʱ�ȭ��
	// ���--------------------------------------------------------------------
	public void dbShow() {
		System.out.println("db �޼��� ����");
		System.out.println(cnt + "cnt��");
		String sql = "SELECT business.business_name, busi_table.table_number"
				+ " FROM busi_table"
				+ " inner join business ON busi_table.business_no=business.business_no where busi_table.table_number=?";
		try {
			con = UserConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, this.cnt);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				data[0] = rs.getString(1);
				System.out.println(data[0] + "db �޼���");
				data[1] = rs.getString(2);
			}

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace(System.out);
		} finally {
			try {
				if (pstmt != null)
					con.close();
				pstmt.close();
				rs.close();

			} catch (Exception e) {

			}
		}
	}

	/*
	 * ����
	 * ----------------------------------------------------------------------
	 * ---------------------
	 */
	public void service(int table_row) {
		String food_name = (String) order_table.getValueAt(table_row, 0);
		String sql = "update oder set sum=sum-1 where food_name = ?";
		String sql2 = "delete from oder where sum=0";
		int price = Integer.parseInt((String) order_table.getValueAt(table_row,
				1));
		int quantity = Integer.parseInt((String) order_table.getValueAt(
				table_row, 2));

		if (table_row < 0) {
			System.out.println("���õ� ���� ����.");
		} else {
			quantity = quantity - 1;
			if (quantity == 0) {
				model.removeRow(table_row);
			} else {
				order_table.setValueAt(quantity, table_row, 2);
			}

			all_sum = all_sum - price;
			System.out.println(all_sum + "�쿡����������������������������");
			order_tf.setText(Integer.toString(all_sum));
		}

		con = UserConnection.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, food_name);
			pstmt.executeUpdate();
			System.out.println("���� �߰� ����");
			if (quantity == 0) {
				pstmt = con.prepareStatement(sql2);
				pstmt.executeQuery();
				System.out.println("�����̻���");
			}
		} catch (Exception err) {
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception er) {
			}
		}
	}

	// ��ư ������ ���,
	// ����--------------------------------------------------------------------

	public void btnShow(int idx) {
		ResultSetMetaData mrs;
		System.out.println("btn db ����");
		String rest = restraunt_tf.getText();
		System.out.println(rest + " rest text�ʵ� ��");
		String sql = null;
		if (idx == 0) {
			sql = "SELECT food_register.register_no, food.food_name,food.food_price ,business.business_no"
					+ " FROM food_register"
					+ " inner join business ON food_register.business_no=business.business_no"
					+ " inner join food ON food_register.food_no = food.food_no"
					+ " where business.business_name=?";
		} /*
		 * else if (idx == 1) { sql =
		 * "SELECT food_register.register_no, food.food_name,food.food_price ,business.business_no"
		 * + " FROM food_register" +
		 * " inner join business ON food_register.business_no=business.business_no"
		 * + " inner join food ON food_register.food_no = food.food_no" +
		 * " where business.business_name=? and food.food_type='�ѽ�'"; } else if
		 * (idx == 2) { sql =
		 * "SELECT food_register.register_no, food.food_name,food.food_price ,business.business_no"
		 * + " FROM food_register" +
		 * " inner join business ON food_register.business_no=business.business_no"
		 * + " inner join food ON food_register.food_no = food.food_no" +
		 * " where business.business_name=? and food.food_type='���'"; } else if
		 * (idx == 3) { sql =
		 * "SELECT food_register.register_no, food.food_name,food.food_price ,business.business_no"
		 * + " FROM food_register" +
		 * " inner join business ON food_register.business_no=business.business_no"
		 * + " inner join food ON food_register.food_no = food.food_no" +
		 * " where business.business_name=? and food.food_type='�߽�'"; }
		 */
		try {
			con = UserConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rest);
			rs = pstmt.executeQuery();
			mrs = rs.getMetaData();
			int rowCnt = mrs.getColumnCount();
			System.out.println(rowCnt + " getROw ��");

			// ��ư �ν��Ͻ����� db�� ���޾ƿͼ� ������ŭ ��������� ����
			bu = new JButton[menucount];
			System.out.println(menucount + "���ƾƾƾƾƾǾ���");
			int i = 0;
			kimbab.setLayout(new FlowLayout());
			while (rs.next()) {
				table_data[0] = rs.getString(1);
				table_data[1] = rs.getString(2);
				table_data[2] = rs.getString(3);

				bu[i] = new JButton();
				bu[i].setFont(new Font("�������", Font.BOLD, 20));
				bu[i].setText(table_data[1] + "-" + table_data[2]);
				if (idx == 0) {
					kimbab.add(bu[i]);
				} else if (idx == 1) {
					kimbab.add(bu[i]);
				} else if (idx == 2) {
					kimbab.add(bu[i]);
				} else if (idx == 3) {
					chinese.add(bu[i]);
				}
				i++;

			}
			menucount = 0;
			System.out.println(menucount + "��� �����");
			kimbab.setLayout(new FlowLayout());

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace(System.out);
		} finally {
			try {
				if (pstmt != null)
					con.close();
				pstmt.close();
				rs.close();

			} catch (Exception e) {

			}
		}

	}

	/*----------------------------------------------------------------------------------------*/
	public void btnOrder(int price, String hidden) {
		all_sum += price;

		String result = Integer.toString(all_sum);
		order_tf.setText(result);
		// sum_tf.setText("0");
		// discount_tf.setText("0");

		/*
		 * con = UserConnection.getConnection(); String sql =
		 * "insert into oder(oder_pay) values()";
		 */

	}

	/*----------------------------------------------------------------------------------------*/
	public void btnOrderDel(int price, String hidden, int quantity) {
		all_sum -= price * quantity;

		String result = Integer.toString(all_sum);
		order_tf.setText(result);
		// sum_tf.setText("0");
		// discount_tf.setText("0");

		/*
		 * con = UserConnection.getConnection(); String sql =
		 * "insert into oder(oder_pay) values()";
		 */
	}

	/*----------------------------------------------------------------------------------------*/
	public void discount() {
		con = UserConnection.getConnection();
		int sum = Integer.parseInt(order_tf.getText());
		int discount = Integer.parseInt(discount_tf.getText());
		int lastsum = sum - discount;

		Object tabledata[] = new Object[2];
		String sql = "insert into pay() values()";
		System.out.println(lastsum);
		sum_tf.setText(Integer.toString(lastsum));
		all_sum=0;
		try {
			/*
			 * pstmt = con.prepareStatement(sql); pstmt.setInt(1, lastsum);
			 * pstmt.setInt(2, table_no); pstmt.executeUpdate();
			 */

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

	/*----------------------------------------------------------------------------------------*/
	public void order_delete() {

		String sql = "delete from oder where table_no=?";
		String sql2 = "update busi_table set table_use='0' where table_number = ?";

		try {
			con = UserConnection.getConnection();

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(data[1]));
			pstmt.executeUpdate();

			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, Integer.parseInt(data[1]));
			pstmt.executeUpdate();

			order_tf.setText("0");
			all_sum = 0;
			System.out.println("���� ���� �ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�");

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

	/*----------------------------------------------------------------------------------------*/
	/*
	 * public void display(int table_no){ con = UserConnection.getConnection();
	 * String sql =
	 * "select food_name, food_price, sum, order_pay from oder where table_no =?"
	 * ; Object tabledata[] = new Object[4]; try{ pstmt =
	 * con.prepareStatement(sql); pstmt.setInt(1, table_no); rs =
	 * pstmt.executeQuery(); if(rs!=null){ while(rs.next()){ tabledata[0] =
	 * rs.getString(1); tabledata[1] = rs.getInt(2); tabledata[2] =
	 * rs.getInt(3); tabledata[3] = rs.getInt(4); model.addRow(tabledata); }
	 * System.out.println(tabledata[3] + "����������������������������");
	 * order_tf.setText((String)tabledata[3]); }else{
	 * 
	 * } }catch(Exception err){} finally {try
	 * {rs.close();pstmt.close();con.close();} catch (Exception e) {}} }
	 */
	/* ---------------------------------------------------------------------- */
	public void order_end() {
		String sql = "update busi_table set table_use='1' where table_number = ?";
		String sql2 = "insert into pay(pay_date, pay_total, table_no) values(sysdate, ?, ?)";
		con = UserConnection.getConnection();
		System.out.println("��ٴٴٴٴٴٴٴٴٴ�");
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(data[1]));
			pstmt.executeUpdate();
			System.out.println(data[1] + "�ֹ� �Ϸ���� ¥��");
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

	/*
	 * ��ư �̺�Ʈ ����
	 * ----------------------------------------------------------------------
	 */
	public void pay_end() {
		String sql2 = "insert into pay(pay_date, pay_total, table_no) values(sysdate, ?, ?)";
		con = UserConnection.getConnection();
		System.out.println("����ߴپƾƾƾӤ�");
		try {

			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, Integer.parseInt(sum_tf.getText()));
			pstmt.setInt(2, Integer.parseInt(data[1]));
			pstmt.executeUpdate();
			System.out.println("���� �Ϸ����");
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

	/*
	 * ����
	 * ����----------------------------------------------------------------------
	 */
	public void update_count(String name) {
		String sql = "update oder set sum=? where table_no=? and food_name=?;";

		con = UserConnection.getConnection();
		try {
			int row = model.getRowCount();
			for (int i = row - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			System.out.println(name + "name ��" + data[1] + "data[1] ��"
					+ tf_count.getText() + "text ��");
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(tf_count.getText()));
			pstmt.setString(2, data[1]);
			pstmt.setString(3, name);
			pstmt.executeUpdate();
			System.out.println("update_count ����");
		} catch (Exception err) {
			System.out.println("update_count() == err ���� ");

		}
	}

	/*
	 * ��ư �̺�Ʈ ����
	 * ----------------------------------------------------------------------
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * System.out.println("�Ƴ� ����!!!!!!!!!!!!!!!!!!!!!�־ȵ�!!!!!!!!!"); if
		 * (tabbedPane.getSelectedIndex() == 0) { System.out.println("�н�");
		 * menunum = 0; shownum = 0; menu_count(menunum); btnShow(shownum);
		 * 
		 * } else if (tabbedPane.getSelectedIndex() == 1) {
		 * System.out.println("�ѽ�"); menunum = 1; shownum = 1;
		 * menu_count(menunum); btnShow(shownum);
		 * 
		 * } else if (tabbedPane.getSelectedIndex() == 2) {
		 * System.out.println("���"); menunum = 2; shownum = 2;
		 * menu_count(menunum); btnShow(shownum);
		 * 
		 * } else if (tabbedPane.getSelectedIndex() == 3) {
		 * System.out.println("�߽�"); menunum = 3; shownum = 3;
		 * menu_count(menunum); btnShow(shownum);
		 * 
		 * }
		 */

		// ���� ��ư
		String name = "";
		System.out.println(name + " amount �� Ȯ�� @@@@@@@@@@@@@@");
		if (e.getSource() == bt_count) {

			int t_r = order_table.getSelectedRow();
			name = (String) order_table.getValueAt(t_r, 0);
			int num_count = order_table.getSelectedRow();
			if (num_count < 0) {
				System.out.println("���õ� ���� ����.");
			} else {
				System.out.println();
				update_count(name);
				table_default();
				System.out.println("���� �߰�~~~");
			}

		}

		// ���� ��ư
		if (e.getSource() == service_bt) {
			int table_row = order_table.getSelectedRow();
			System.out.println("��ٴٴٴٴٴٴ�");
			service(table_row);
		}
		// ���ι�ư
		if (e.getSource() == discount_button) {
			discount();
		}
		// �ֹ��Ϸ��ư
		if (e.getSource() == bt_order) {
			tableYN = true;
			order_end();
			setVisible(false);
			new PostMain(this.cnt, tableYN, Integer.parseInt(data[1]));
		}
		// ������ư
		if (e.getSource() == bt_pay) {
			order_delete();
			pay_end();
			setVisible(false);
			tableYN = false;
			new PostMain(this.cnt, tableYN, Integer.parseInt(data[1]));
		}
		// �ݱ� ��ư
		if (e.getSource() == bt_close) {
			all_sum = 0;
			setVisible(false);
			new PostMain(this.cnt, tableYN, Integer.parseInt(data[1]));
			System.out.println(tableYN + "�ݱ��ư�̴�");

		}
		// ��ü ���
		if (e.getSource() == allcancle_bt) {

			int row = model.getRowCount();
			for (int i = row - 1; i >= 0; i--) {
				model.removeRow(i);
			}

			order_delete();

		}
		// �������
		if (e.getSource() == selecancle_bt) {
			int num = order_table.getSelectedRow();
			if (num < 0) {
				System.out.println("���õ� ���� ����.");
			} else {
				String price = (String) model.getValueAt(num, 1);
				String quantity = (String) model.getValueAt(num, 2);
				model.removeRow(num);
				btnOrderDel(Integer.parseInt(price), "N",
						Integer.parseInt(quantity));
			}
		}
		// �ֹ� ���� ��ư
		for (int i = 0; i < bu.length; i++) {
			if (e.getSource().equals(bu[i])) {
				System.out.println(bu[0].getText() + "�׼������� bu ����");
				String arr = bu[i].getText();
				StringTokenizer token = new StringTokenizer(arr, "-");
				String items[] = new String[2];
				String count[] = new String[6];

				int j = 0;

				while (token.hasMoreTokens()) {
					String str = token.nextToken();
					items[j] = str;
					j++;
				}

				table_Show(items[0], items[1], 1);

				int price = Integer.parseInt(items[1]);
				btnOrder(price, "Y");

			}
		}
		// TODO Auto-generated method stub
		// ----------------------------------------------------------------------------------------
		String txtButton = e.getActionCommand();

		if (txtButton.equals("1") || txtButton.equals("2")
				|| txtButton.equals("3") || txtButton.equals("4")
				|| txtButton.equals("5") || txtButton.equals("6")
				|| txtButton.equals("7") || txtButton.equals("8")
				|| txtButton.equals("9")) {
			if (result2.equals("0")) {
				result2 = "";
			}
			result2 += txtButton;
			jlbResult2.setText(result2);
		} else if (txtButton.equals("��")) {
			int len = jlbResult2.getText().length();
			if (len == 1) {
				result2 = "";
				jlbResult2.setText("0");
			} else {
				if (!"".equals(result2)) {
					result2 = result2.substring(0, len - 1);
					jlbResult2.setText(result2);
				}
			}
		} else if (txtButton.equals("CE")) {
			result2 = "";
			result = "";
			jlbResult2.setText("0");
			number[0] = "0";
		} else if (txtButton.equals("C")) {
			result = "";
			result1 = "";
			result2 = "";
			jlbResult1.setText("");
			jlbResult2.setText("0");
			number[0] = " ";
			number[1] = " ";
			number[2] = " ";
		} else if (txtButton.equals("0") || txtButton.equals("00")) {
			if (!("".equals(result2))) {
				if (!"0".equals(result2)) {
					result2 += txtButton;
					jlbResult2.setText(result2);
				}
			} else {
				result2 = "0";
			}
		} else if (txtButton.equals(".")) {
			if ("".equals(result2)) {
				result2 = "0" + txtButton;
			} else {
				if (result2.indexOf(".") == -1) {
					result2 += txtButton;
				}
			}
			jlbResult2.setText(result2);
		} else if (txtButton.equals("=")) {
			if (!"".equals(result2)) {
				number[2] = result2;
			}
			if ("".equals(result1)) {
				if (!number[1].equals(" ")) {
					result = Cal();
					if ("0���� ���� �� �����ϴ�.".equals(result)) {
						result = "";
						result1 = "";
						result2 = "";
						number[0] = " ";
						number[1] = " ";
						number[2] = " ";
					} else {
						number[0] = result;
						jlbResult2.setText(result);
					}
				}
				jlbResult1.setText(result1);
			} else {
				if (" ".equals(number[2])) {
					number[2] = result2;
				}
				result = number[0];

				if ("0���� ���� �� �����ϴ�.".equals(result)) {
					jlbResult2.setText(result);
					result = "";
					result1 = "";
					result2 = "";
					number[0] = " ";
					number[1] = " ";
					number[2] = " ";
				} else {
					result = Cal();

					if (!"0���� ���� �� �����ϴ�.".equals(result)) {
						result1 = "";
						number[0] = result;
						jlbResult2.setText(result);
						jlbResult1.setText(result1);
						result2 = "";
					} else {
						jlbResult2.setText("0���� ���� �� �����ϴ�.");
						jlbResult1.setText("");
						result = "";
						result1 = "";
						result2 = "";
						number[0] = " ";
						number[1] = " ";
						number[2] = " ";
					}
					System.out.println("?");
				}
			}
		} else if (txtButton.equals("/") || txtButton.equals("*")
				|| txtButton.equals("-") || txtButton.equals("+")) {
			if ("".equals(result1)) {
				if ("".equals(result2)) {
					if ("".equals(result)) {
						result1 = "0" + txtButton;
						number[0] = "0";
					} else {
						result1 = result + txtButton;
					}
				} else {
					if (!"".equals(result)) {
						result1 = result + txtButton;
					}
					result1 = result2 + txtButton;
					number[0] = result2;
				}
				number[1] = txtButton;
			} else {
				if ("".equals(result2)) {
					result1 = result1.substring(0, result1.length() - 1)
							+ txtButton;
					number[1] = txtButton;
				} else {
					result1 += result2 + txtButton;
					number[2] = result2;
					result = Cal();
					number[1] = txtButton;
					jlbResult2.setText(result);
					number[0] = result;
				}
			}
			if (number[1].equals("/")
					&& (number[2].equals(" 0") || number[2].equals("0"))) {

			} else {
				result2 = "";
				jlbResult1.setText(result1);
			}

		}
	}

	/** ��ư �̺�Ʈ �� */

	/** ��� �޼ҵ� ���� */
	public String Cal() {
		String num = "";
		String testNum1 = "";
		String testNum2 = "";

		if (number[0].indexOf(".") != -1) {
			int index1 = number[0].indexOf(".");

			testNum1 = number[0].substring(index1, number[0].length());

			if (testNum1.replaceAll("0", "").equals(".")) {
				number[0] = number[0].substring(0, index1);
			}
		}

		if (number[2].indexOf(".") != -1) {
			int index2 = number[2].indexOf(".");

			testNum2 = number[2].substring(index2, number[2].length());

			if (testNum2.replaceAll("0", "").equals(".")) {
				number[2] = number[2].substring(0, index2);
			}
		}
		if (number[1].equals("*")) {
			if (number[0].indexOf(".") != -1 || number[2].indexOf(".") != -1) {
				num = (Double.parseDouble(number[0]) * Double
						.parseDouble(number[2])) + "";
			} else {
				if ("".equals(number[2])) {
					num = result2;
				} else {
					if (!"".equals(result)) {
						number[0] = result;
					}
					num = (Long.parseLong(number[0]) * Long
							.parseLong(number[2])) + "";
					result = num;
					number[0] = result;
				}
			}
		} else if (number[1].equals("+")) {
			if (number[0].indexOf(".") != -1 || number[2].indexOf(".") != -1) {
				num = (Double.parseDouble(number[0]) + Double
						.parseDouble(number[2])) + "";
			} else {
				if ("".equals(number[2])) {
					num = result2;
				} else {
					if (!"".equals(result)) {
						number[0] = result;
					}
					num = (Long.parseLong(number[0]) + Long
							.parseLong(number[2])) + "";
					result = num;
					number[0] = result;
				}
			}
		} else if (number[1].equals("-")) {
			if (number[0].indexOf(".") != -1 || number[2].indexOf(".") != -1) {
				num = (Double.parseDouble(number[0]) - Double
						.parseDouble(number[2])) + "";
			} else {
				if ("".equals(number[2])) {
					num = result2;
				} else {
					if (!"".equals(result)) {
						number[0] = result;
					}
					num = (Long.parseLong(number[0]) - Long
							.parseLong(number[2])) + "";
					result = num;
					number[0] = result;
				}
			}
		} else if (number[1].equals("/")) {
			number[2].trim();
			if (number[0].indexOf(".") != -1 || number[2].indexOf(".") != -1) {
				num = (Double.parseDouble(number[0]) / Double
						.parseDouble(number[2])) + "";
			} else {
				if (number[2].equals("0")) {
					num = "0���� ���� �� �����ϴ�.";
					result2 = "";
				} else {
					if ("".equals(number[2])) {
						num = result2;
					} else {
						if (!"".equals(result)) {
							number[0] = result;
						}
						num = (Double.parseDouble(number[0]) / Double
								.parseDouble(number[2])) + "";

						if (num.indexOf(".") != -1) {
							int index3 = num.indexOf(".");
							String testNum3 = num.substring(index3,
									num.length());
							if (testNum3.replaceAll("0", "").equals(".")) {
								num = num.substring(0, index3);
							}
						}
						result = num;
						number[0] = result;
					}
				}
			}
		}
		return num;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Counter frame = new Counter(cnt, tableYN);
					System.out.println(tableYN + "ȣ��Ƶ�");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}