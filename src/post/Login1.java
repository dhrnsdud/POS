package post;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Login1 extends JFrame implements ActionListener {

	public JPanel contentPane;
	public JTextField Id_TextField;
	public JPasswordField Pw_TextField;

	public JButton login;

	String name = new String();

	// 데이터베이스 객체 관련 정의

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	Statement stmt = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login1 frame = new Login1();
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
	public Login1() {

		setBackground(new Color(250, 235, 215));
		setTitle("\uB85C\uADF8\uC778");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 317);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("POS\uAE30 \uB85C\uADF8\uC778");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 40));
		lblNewLabel.setBounds(184, 10, 276, 68);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uC544\uC774\uB514 :");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1.setBounds(104, 132, 78, 21);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\uBE44\uBC00\uBC88\uD638 :");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_2.setBounds(104, 213, 107, 21);
		contentPane.add(lblNewLabel_2);

		Id_TextField = new JTextField();
		Id_TextField.setFont(new Font("굴림", Font.BOLD, 18));
		Id_TextField.setBounds(217, 129, 156, 27);
		contentPane.add(Id_TextField);
		Id_TextField.setColumns(10);

		Pw_TextField = new JPasswordField();
		Pw_TextField.setFont(new Font("굴림", Font.BOLD, 18));
		Pw_TextField.setBounds(217, 210, 156, 27);
		contentPane.add(Pw_TextField);
		Pw_TextField.setColumns(10);

		login = new JButton("\uB85C\uADF8\uC778");
		login.setFont(new Font("굴림", Font.BOLD, 18));
		login.setBounds(417, 209, 125, 29);
		contentPane.add(login);

		login.addActionListener(this);
		this.setVisible(true);
	}

	public void loginCheck() {
		if (Id_TextField.getText().length() == 0) {
			showMsg("아이디를 입력해주세요");
			Id_TextField.requestFocus();
			return;
		}

		char[] ch = Pw_TextField.getPassword();
		String passStr = new String(ch);
		if (passStr.length() == 0) {
			showMsg("비밀번호를 정확히 입력해주세요");
			Pw_TextField.requestFocus();
			return;
		}
		int business = 0;
		String sql = "";
		try {
			sql = "select member_id, member_pass, member_grade from member where member_id=? and member_pass=?";
			System.out.println(Id_TextField.getText());
			System.out.println(passStr);
			con = UserConnection.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Id_TextField.getText());
			pstmt.setString(2, passStr);

			rs = pstmt.executeQuery();// 쿼리수행

			if (rs.next() == true) {
				Show();
				showMsg("로그인되었습니다.");
				String i = rs.getString("member_grade");
				System.out.println(i + " i 값 !!!!!!!!");
				sql = "select business_no from business where business_name=?";
				PreparedStatement ppstmt = con.prepareStatement(sql);
				ppstmt.setString(1, i);
				ResultSet rs1 = ppstmt.executeQuery();
				if (rs1.next()) {
					System.out.println("기업명 가져오기");
					business = rs1.getInt(1);
					System.out.println(business + " business 값 !!!!!!!!!");
				}
				this.setVisible(false);
				new PostMain(0, false, business);

			} else if (rs.next() == false) {
				showMsg("로그인 정보가 올바르지 않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void Show() {
		name = Id_TextField.getText();
		System.out.println(name);
	}

	private void showMsg(String msg) {
		JOptionPane.showMessageDialog(getParent(), msg);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == login)
			loginCheck();

	}

}
