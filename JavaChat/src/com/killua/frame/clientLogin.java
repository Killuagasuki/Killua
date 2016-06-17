package com.killua.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;

import cn.util.DbHelper1;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class clientLogin {

	private JFrame frame;
	private JTextField userName;
	private JPasswordField passWord;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					clientLogin window = new clientLogin();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public clientLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("登陆界面");
		frame.setResizable(false);
		int windowWidth = frame.getWidth(); // 获得窗口宽
		int windowHeight = frame.getHeight(); // 获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width - 260; // 获取屏幕的宽
		int screenHeight = screenSize.height - 200; // 获取屏幕的高
		frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// 设置窗口居中显示
		frame.setSize(260, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel userLabel = new JLabel("用户名：");
		userLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		userLabel.setBounds(37, 38, 69, 19);
		panel.add(userLabel);

		userName = new JTextField();
		userName.setBounds(99, 38, 111, 21);
		panel.add(userName);
		userName.setColumns(10);

		JLabel passLabel = new JLabel("密码：");
		passLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		passLabel.setBounds(37, 80, 69, 19);
		panel.add(passLabel);

		passWord = new JPasswordField();
		passWord.setBounds(99, 80, 111, 21);
		panel.add(passWord);

		JButton loginButton = new JButton("登录");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = userName.getText();
				char[] pass = passWord.getPassword();
				String password = String.valueOf(pass);
				try {
					Connection conn = DbHelper1.getConn();
					Statement st = conn.createStatement();
					String sql = "select * from userlogin";
					ResultSet rs = st.executeQuery(sql);
					int count = 0;
					while (rs.next()) {
						String dbName = rs.getString("userName");
						String dbPass = rs.getString("passWorld");
						if (name.equals(dbName) && dbPass.equals(password)) {
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										@SuppressWarnings("unused")
										Chat chat = new Chat();
										frame.setVisible(false);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
						} else {
							count++;
						}
					}
					 rs.last();
					 int size =rs.getRow();
					if(count==size){
						JOptionPane.showMessageDialog(null, "账号或者密码错误", "登陆错误", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					st.close();
					conn.close();
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		loginButton.setFont(new Font("宋体", Font.PLAIN, 15));
		loginButton.setBounds(93, 126, 65, 25);
		panel.add(loginButton);

		JButton exitButton = new JButton("退出");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exitButton.setFont(new Font("宋体", Font.PLAIN, 15));
		exitButton.setBounds(168, 126, 65, 25);
		panel.add(exitButton);

		JButton registerButton = new JButton("注册");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				Regest regest = new Regest();
			}
		});
		registerButton.setFont(new Font("宋体", Font.PLAIN, 15));
		registerButton.setBounds(18, 126, 65, 25);
		panel.add(registerButton);
		frame.setVisible(true);
	}
}
