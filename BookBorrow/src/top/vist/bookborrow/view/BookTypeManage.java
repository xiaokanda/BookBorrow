package top.vist.bookborrow.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.xml.ws.handler.MessageContext.Scope;

import top.vist.bookborrow.dao.BookTypeDao;
import top.vist.bookborrow.entity.Book;
import top.vist.bookborrow.entity.BookType;

public class BookTypeManage extends JFrame implements MouseListener, ActionListener {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JScrollPane scrollPane, scrollPane_1;
	private JPanel panel, panel_1;
	private JLabel lblNewLabel, lblNewLabel_1, label;
	private JButton btnNewButton, button, button_1, button_3, button_4;
	private JTable j, j1, j2, j3, j4;
	private String[][] data = new String[][] {};
	private String[] readersearch = null;

	public BookTypeManage() {

		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.pink);

		panel = new JPanel();
		panel.setBounds(0, 0, 550, 400);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("图书类型");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(31, 10, 84, 40);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(125, 16, 279, 33);
		panel.add(textField);
		textField.setColumns(10);

		btnNewButton = new JButton("查询");
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 20));
		btnNewButton.setBounds(414, 14, 84, 33);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(this);

		panel_1 = new JPanel();
		panel_1.setBounds(41, 60, 457, 153);
		panel_1.setLayout(null);
		panel.add(panel_1);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 457, 153);
		readersearch = new String[] { "图书类型编号", "图书类型名称" };
		BookTypeDao bookTypeDao = new BookTypeDao();
		data = bookTypeDao.getArrayData(bookTypeDao.findAll());
		// List<Book> books = BookDao.selectBookType();
		// data = BookDao.getSelect(books);
		j = new JTable(data, readersearch);
		j.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		j.getColumnModel().getColumn(0).setPreferredWidth(229);
		j.getColumnModel().getColumn(1).setPreferredWidth(229);
		scrollPane_1.setViewportView(j);
		panel_1.add(scrollPane_1);
		j.addMouseListener(this);

		lblNewLabel_1 = new JLabel("图书类型编号：");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel_1.setBounds(51, 223, 147, 33);
		// lblNewLabel_1.setEnabled(false);//设置不可修改内容
		panel.add(lblNewLabel_1);

		label = new JLabel("图书类型名称：");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(51, 272, 147, 33);
		panel.add(label);

		textField_1 = new JTextField();
		textField_1.setBounds(227, 223, 236, 33);
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEnabled(false);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(227, 274, 236, 33);
		panel.add(textField_2);

		button_3 = new JButton("添加");
		button_3.setFont(new Font("宋体", Font.BOLD, 20));
		button_3.setBounds(64, 326, 84, 33);
		panel.add(button_3);
		button_3.addActionListener(this);

		button_4 = new JButton("修改");
		button_4.setFont(new Font("宋体", Font.BOLD, 20));
		button_4.setBounds(162, 326, 84, 33);
		panel.add(button_4);
		button_4.addActionListener(this);

		button = new JButton("删除");
		button.setFont(new Font("宋体", Font.BOLD, 20));
		button.setBounds(266, 326, 84, 33);
		panel.add(button);
		button.addActionListener(this);

		button_1 = new JButton("退出");
		button_1.setFont(new Font("宋体", Font.BOLD, 20));
		button_1.setBounds(367, 326, 84, 33);
		panel.add(button_1);
		button_1.addActionListener(this);

		setTitle("图书类型管理");
		setBounds(100, 100, 566, 438);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);// 取消最大化
	}

	/*
	 * public static void main(String[] args) { new BookTypeManage(); }
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		// 搜索图书
		if (e.getSource() == btnNewButton) {
			BookTypeDao bookTypeDao = new BookTypeDao();
			data = bookTypeDao.getArrayData(bookTypeDao.search(textField.getText().intern()));
			j = new JTable(data, readersearch);
			j.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			j.getColumnModel().getColumn(0).setPreferredWidth(229);
			j.getColumnModel().getColumn(1).setPreferredWidth(229);
			scrollPane_1.setViewportView(j);
			panel_1.add(scrollPane_1);
			j.addMouseListener(this);
		}
		// 添加图书类别
		if (e.getSource() == button_3) {

			BookType bookType = new BookType();
			bookType.setTypeName(textField_2.getText());
			if (bookType.getTypeName() == null || "".equals(bookType.getTypeName())) {
				JOptionPane.showMessageDialog(this, "图书类型名不能为空！");
			}
			BookTypeDao bookTypeDao = new BookTypeDao();
			int row = bookTypeDao.save(bookType);
			if (row == 1) {
				data = bookTypeDao.getArrayData(bookTypeDao.findAll());
				j = new JTable(data, readersearch);
				j.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				j.getColumnModel().getColumn(0).setPreferredWidth(229);
				j.getColumnModel().getColumn(1).setPreferredWidth(229);
				scrollPane_1.setViewportView(j);
				panel_1.add(scrollPane_1);
				j.addMouseListener(this);
				JOptionPane.showMessageDialog(this, "添加成功！");
			} else {
				JOptionPane.showMessageDialog(this, "添加失败！");
			}
		}
		// 修改图书类别
		if (e.getSource() == button_4) {
			BookType bookType = new BookType();
			bookType.setId(Integer.valueOf(textField_1.getText()));
			bookType.setTypeName(textField_2.getText());
			BookTypeDao bookTypeDao = new BookTypeDao();
			int row = bookTypeDao.update(bookType);
			if (row == 1) {
				data = bookTypeDao.getArrayData(bookTypeDao.findAll());
				j = new JTable(data, readersearch);
				j.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				j.getColumnModel().getColumn(0).setPreferredWidth(229);
				j.getColumnModel().getColumn(1).setPreferredWidth(229);
				scrollPane_1.setViewportView(j);
				panel_1.add(scrollPane_1);
				j.addMouseListener(this);
				JOptionPane.showMessageDialog(this, "修改成功！");
			} else {
				JOptionPane.showMessageDialog(this, "修改失败！");
			}
		}
		// 删除图书类别
		if (e.getSource() == button) {
			BookType bookType = new BookType();
			bookType.setId(Integer.valueOf(textField_1.getText()));
			BookTypeDao bookTypeDao = new BookTypeDao();
			int row = bookTypeDao.delete(bookType);
			if (row == 1) {
				data = bookTypeDao.getArrayData(bookTypeDao.findAll());
				j = new JTable(data, readersearch);
				j.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				j.getColumnModel().getColumn(0).setPreferredWidth(229);
				j.getColumnModel().getColumn(1).setPreferredWidth(229);
				scrollPane_1.setViewportView(j);
				panel_1.add(scrollPane_1);
				j.addMouseListener(this);
				textField_1.setText(null);
				textField_2.setText(null);
				JOptionPane.showMessageDialog(this, "删除成功！");
			} else {
				JOptionPane.showMessageDialog(this, "删除失败！");
			}
		}
		if(e.getSource() == button_1) {
			dispose();
		}

	}

	@Override
	// 鼠标点击事件
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == button_1) {
			dispose();
		}
		if (e.getSource() == j) {
			int row = j.getSelectedRow();
			textField_1.setText(data[row][0]);
			textField_2.setText(data[row][1]);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}