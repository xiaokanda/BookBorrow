package top.vist.bookborrow.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Library extends JFrame {

	/**
	 * 图书借阅系统
	 */
	private JMenu menu1, menu2, menu3, menu4, menu5;
	private JMenuItem menuitem11, menuitem12, menuitem21, menuitem22, menuitem31, menuitem32, menuitem41, menuitem42,
			menuitem43, menuitem51, menuitem52, menuitem53;

	public static void main(String[] args) {
		new Library();
	}

	public Library() {
		setTitle("图书借阅系统");
		setBounds(200, 200, 500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		menu1 = new JMenu("读者信息管理");
		menubar.add(menu1);
		menuitem11 = new JMenuItem("读者信息添加");
		menu1.add(menuitem11);
		menuitem11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ReaderAdd();
			}
		});
		menuitem12 = new JMenuItem("读者信息查询与修改");
		menu1.add(menuitem12);
		menuitem12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ReaderSelectAndModify();
			}
		});
		menu2 = new JMenu("图书信息管理");
		menubar.add(menu2);
		menuitem21 = new JMenuItem("图书信息添加");
		menu2.add(menuitem21);
		menuitem21.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new BookAdd();
			}
		});
		menuitem22 = new JMenuItem("图书信息查询与修改");
		menu2.add(menuitem22);
		menuitem22.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new BookSelectAndUpdate();
			}
		});
		menu3 = new JMenu("图书借阅管理");
		menubar.add(menu3);
		menuitem31 = new JMenuItem("图书借阅");
		menu3.add(menuitem31);
		menuitem31.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// new BookBorrow();
			}
		});
		menuitem32 = new JMenuItem("图书归还");
		menu3.add(menuitem32);
		menuitem32.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// new BookReturn();
			}
		});
		menu4 = new JMenu("基础信息维护");
		menubar.add(menu4);
		menuitem41 = new JMenuItem("图书类型管理");
		menu4.add(menuitem41);
		menuitem41.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new BookTypeManage();
			}
		});
		menuitem42 = new JMenuItem("读者类型管理");
		menu4.add(menuitem42);
		menuitem42.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ReaderTypeManage();
			}
		});
		menuitem43 = new JMenuItem("罚金设置");
		menu4.add(menuitem43);

		menu5 = new JMenu("用户管理");
		menubar.add(menu5);
		menuitem51 = new JMenuItem("添加用户");
		menu5.add(menuitem51);
		menuitem51.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new UserAdd();
			}
		});
		menuitem52 = new JMenuItem("修改密码");
		menu5.add(menuitem52);
		menuitem52.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new UpdatePWD();
			}
		});
		menuitem53 = new JMenuItem("删除用户");
		menu5.add(menuitem53);
		menuitem53.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new UserDel();
			}
		});
		this.setVisible(true);// 设置显示窗体
		setResizable(false);// 取消最大化
	}
}