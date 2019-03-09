package top.vist.bookborrow.view;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import top.vist.bookborrow.dao.UserDao;
import top.vist.bookborrow.entity.User;

public class Login extends JFrame implements ActionListener {
	JPanel jpanel,jpanel2,jp;
	JPasswordField jpasswordfield;
	JButton jbutton ,jbutton1;
	JTextField jtextfield;
	public static String USER_NAME;
	
	public Login() {
		setBounds(200, 200, 320, 200);
		setTitle("图书借阅系统登录界面");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		jpanel = new JPanel();
		JLabel jlabel = new JLabel("图书借阅系统");
		Font font = new Font("宋体", Font.BOLD, 30);
		jlabel.setFont(font);
		jlabel.setHorizontalAlignment(SwingConstants.CENTER);
		jpanel.setLayout(new FlowLayout());
		jpanel.add(jlabel);

		jpanel2 = new JPanel();
		jpanel2.setBorder(new EmptyBorder(5, 10, 5, 10));
		GridLayout gridLayout = new GridLayout(2, 2);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		jpanel2.setLayout(gridLayout);

		JLabel jlabel1 = new JLabel("用户名：");
		jlabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jtextfield = new JTextField();
		jpanel2.add(jlabel1);
		jpanel2.add(jtextfield);

		JLabel jlabel2 = new JLabel("密  码:");
		jlabel2.setHorizontalAlignment(SwingConstants.CENTER);
		jpasswordfield = new JPasswordField();

		jpanel2.add(jlabel2);
		jpanel2.add(jpasswordfield);

		jp = new JPanel();

		jbutton = new JButton("登录");
		jbutton1 = new JButton("重置");
		jbutton.addActionListener(this);
		jbutton1.addActionListener(this);
		jp.add(jbutton);
		jp.add(jbutton1);

		add(jpanel, BorderLayout.NORTH);
		add(jpanel2, BorderLayout.CENTER);
		add(jp, BorderLayout.SOUTH);

		this.setVisible(true);// 设置窗体显示，否则不显示。
		setResizable(false);// 取消最大化

	}
				
	public static void main(String[] args) {
		new Login();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbutton) {
			String name =null,pwd=null;
			name=jtextfield.getText();
			pwd=jpasswordfield.getText();
			UserDao userDao = new UserDao();
			User user = userDao.login(name, pwd);
			if(user==null) {
				jtextfield.setText(null);
				jpasswordfield.setText(null);
				JOptionPane.showMessageDialog(this, "用户名或密码错误！");
			}else {
				new Library();
				dispose();
			}
		}
		if(e.getSource()==jbutton1){
			jtextfield.setText(null);
			jpasswordfield.setText(null);
		}
	}
}