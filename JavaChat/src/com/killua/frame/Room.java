package com.killua.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Room {

	private JFrame frame;
	private JTextField roomName;
	private JTextField roomPass;

	/**
	 * Create the application.
	 */
	public Room() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();		
		int windowWidth = frame.getWidth(); // 获得窗口宽
		int windowHeight = frame.getHeight(); // 获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width-358; // 获取屏幕的宽
		int screenHeight = screenSize.height-225; // 获取屏幕的高
	    frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示 
		frame.setSize(358, 225);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("房间名称：");
		label.setBounds(58, 36, 74, 15);
		panel.add(label);
		
		roomName = new JTextField();
		roomName.setBounds(135, 32, 131, 21);
		panel.add(roomName);
		roomName.setColumns(10);
		
		JLabel label_1 = new JLabel("房间密码：");
		label_1.setBounds(59, 82, 73, 15);
		panel.add(label_1);
		
		roomPass = new JTextField();
		roomPass.setBounds(135, 79, 131, 21);
		panel.add(roomPass);
		roomPass.setColumns(10);
		
		JButton button = new JButton("创建");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = roomName.getText();
				String pass = roomPass.getText();
				RoomShow roomShow = new RoomShow(name, pass);
				frame.setVisible(false);
			}
		});
		button.setBounds(135, 138, 93, 23);
		panel.add(button);
	
		frame.setVisible(true);
		}

}
