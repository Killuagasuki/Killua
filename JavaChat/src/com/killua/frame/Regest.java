package com.killua.frame;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Regest {

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	private JPasswordField cpassword;
	private JTextField qqnum;

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public Regest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("注册页面");
		int windowWidth = frame.getWidth(); // 获得窗口宽
		int windowHeight = frame.getHeight(); // 获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width - 268; // 获取屏幕的宽
		int screenHeight = screenSize.height - 294; // 获取屏幕的高
		frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// 设置窗口居中显示
		frame.setSize(268, 294);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel label = new JLabel("用户名：");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(10, 27, 69, 24);
		panel.add(label);

		JLabel label_1 = new JLabel("密码：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 15));
		label_1.setBounds(10, 72, 69, 24);
		panel.add(label_1);

		JLabel lblQq = new JLabel("QQ号码：");
		lblQq.setFont(new Font("宋体", Font.PLAIN, 15));
		lblQq.setBounds(10, 167, 69, 24);
		panel.add(lblQq);

		username = new JTextField();
		username.setBounds(102, 29, 116, 21);
		panel.add(username);
		username.setColumns(10);

		password = new JPasswordField();
		password.setBounds(102, 74, 116, 21);
		panel.add(password);

		JButton clearn = new JButton("清空");
		clearn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username.setText(null);
				password.setText(null);
				cpassword.setText(null);
				qqnum.setText(null);
			}
		});
		clearn.setFont(new Font("宋体", Font.PLAIN, 15));
		clearn.setBounds(149, 215, 69, 23);
		panel.add(clearn);

		cpassword = new JPasswordField();
		cpassword.setBounds(102, 122, 116, 21);
		panel.add(cpassword);

		JLabel label_2 = new JLabel("确认密码：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 15));
		label_2.setBounds(10, 120, 90, 24);
		panel.add(label_2);

		qqnum = new JTextField();
		qqnum.setColumns(10);
		qqnum.setBounds(102, 169, 116, 21);
		panel.add(qqnum);

		JButton regest = new JButton("注册");
		regest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = username.getText();
				String p1 = String.valueOf(password.getPassword());
				String p2 = String.valueOf(cpassword.getPassword());
				String qqn = qqnum.getText();
				@SuppressWarnings("unused")
				int qq;
				UserDate userDate = new UserDate(name, p1, p2, qqn);
				if (name != null && p1 != null && qqn != null) {
					if (p1.equals(p2)) {
						try {
							qq = Integer.valueOf(qqn);
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "QQ格式错误", "QQ格式错误", JOptionPane.ERROR_MESSAGE);
							qqnum.setText(null);
							e1.printStackTrace();
						}
						try {
							Connection conn = DbHelper1.getConn();
							Statement st = conn.createStatement();
							ResultSet rs = st.executeQuery("select * from userlogin");
							while (rs.next()) {
								String dbName = rs.getString("userName");
								if (dbName.equals(name)) {
									JOptionPane.showMessageDialog(null, "账号已经存在", "账号错误", JOptionPane.ERROR_MESSAGE);
									username.setText(null);
									password.setText(null);
									cpassword.setText(null);
									qqnum.setText(null);
									break;
								} else {
									 String sql="insert into userlogin(userName,passWorld,qq) values(?,?,?)";
									 PreparedStatement ps=conn.prepareStatement(sql);
					                 ps.setString(1, userDate.getName());
					                 ps.setString(2, userDate.getPassword());
					                 ps.setString(3, userDate.getQq());
					                 int result=ps.executeUpdate();
					                 if(result>0){
					                	 JOptionPane.showMessageDialog(null, "注册成功", "成功", JOptionPane.INFORMATION_MESSAGE);
											frame.setVisible(false);
					                 }else{
					                	 JOptionPane.showMessageDialog(null, "注册失败", "位置错误", JOptionPane.ERROR_MESSAGE);
											 
					                 }
								}
							}

							rs.close();
							st.close();
							conn.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "两次密码不一致", "密码错误", JOptionPane.ERROR_MESSAGE);
						cpassword.setText(null);
						password.setText(null);
					}
				}
			}
		});
		regest.setFont(new Font("宋体", Font.PLAIN, 15));
		regest.setBounds(35, 215, 69, 23);
		panel.add(regest);
		frame.setVisible(true);
	}

}
