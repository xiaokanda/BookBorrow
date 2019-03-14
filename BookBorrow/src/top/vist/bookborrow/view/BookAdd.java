package top.vist.bookborrow.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import top.vist.bookborrow.dao.BookDao;
import top.vist.bookborrow.dao.BookTypeDao;
import top.vist.bookborrow.entity.Book;

/*
 * 图书信息添加
 * */
public class BookAdd extends JFrame implements ActionListener, FocusListener {
	private static final long serialVersionUID = 1L;

	private JPanel bookAddJP, buttonJP;
	private ButtonGroup buttonGroup = new ButtonGroup();
	// private JRadioButton JRB1,JRB2;
	private JLabel ISBNJL, lbieJL, bknJL, wrtnJL, cbsnJL, dateJL, numJL, vlJL;
	private JTextField ISBNJT, lbieJT, bknJT, wrtnJT, cbsnJT, dateJT, numJT, vlJT;
	// private JComboBox readertypeJCB;
	private JComboBox lbieJCB;
	private JButton addJB, resetJB, closeJB;
	private String results[] = {};

	public BookAdd() {
		setBounds(200, 200, 500, 200);
		setTitle("图书信息添加");
		// 登录取消按钮面板
		buttonJP = new JPanel();

		// 读者信息添加面板设计
		bookAddJP = new JPanel();
		bookAddJP.setBorder(new EmptyBorder(5, 10, 5, 10));
		final GridLayout gridLayout = new GridLayout(4, 4);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		bookAddJP.setLayout(gridLayout);
		getContentPane().add(bookAddJP);

		ISBNJL = new JLabel("ISBN：");
		ISBNJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(ISBNJL);
		ISBNJT = new JTextField();
		bookAddJP.add(ISBNJT);

		lbieJL = new JLabel("类别：");
		lbieJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(lbieJL);
		// 下拉列表
		lbieJCB = new JComboBox();
		bookAddJP.add(lbieJCB);
		List<String> data = new ArrayList<String>();
		BookTypeDao bookTypeDao = new BookTypeDao();
		data = bookTypeDao.findNameAll();
		Iterator<String> it = data.iterator();
		while (it.hasNext()) {
			lbieJCB.addItem(it.next());
		}

		bknJL = new JLabel("书名：");
		bknJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(bknJL);
		bknJT = new JTextField();
		bookAddJP.add(bknJT);

		wrtnJL = new JLabel("作者：");
		wrtnJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(wrtnJL);
		wrtnJT = new JTextField();
		bookAddJP.add(wrtnJT);

		cbsnJL = new JLabel("出版社：");
		cbsnJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(cbsnJL);
		cbsnJT = new JTextField();
		bookAddJP.add(cbsnJT);

		dateJL = new JLabel("出版日期：");
		dateJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(dateJL);
		dateJT = new JTextField();
		bookAddJP.add(dateJT);

		numJL = new JLabel("印刷次数：");
		numJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(numJL);
		numJT = new JTextField();
		bookAddJP.add(numJT);

		vlJL = new JLabel("单价：");
		vlJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(vlJL);
		vlJT = new JTextField();
		bookAddJP.add(vlJT);

		// 登录取消按钮面板设计
		addJB = new JButton("添加");
		closeJB = new JButton("关闭");
		buttonJP.add(addJB);
		buttonJP.add(closeJB);

		addJB.addActionListener(this);
		ISBNJT.addFocusListener(this);
		ISBNJT.addActionListener(this);
		closeJB.addActionListener(this);
		this.add(bookAddJP, BorderLayout.CENTER);
		this.add(buttonJP, BorderLayout.SOUTH);

		setVisible(true);// 设置窗体显示，否则不显示。
		setResizable(false);// 取消最大化
	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addJB) {
			Book book = new Book();
			book.setISBN(ISBNJT.getText());
			String typename = (String) lbieJCB.getSelectedItem();
			BookTypeDao bookTypeDao = new BookTypeDao();
			book.setTypeId(bookTypeDao.findIdByName(typename));
			book.setBookName(bknJT.getText());
			book.setAuthor(wrtnJT.getText());
			book.setPublish(cbsnJT.getText());
			String strDate = dateJT.getText();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date parse = format.parse(strDate);
				book.setPublishDate(parse);
				strDate = format.format(parse);
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(this, "输入日期不合法：yyyy-MM-dd");
			}
			book.setStrPublishDate(strDate);
			try {
				book.setPublishNum(Integer.parseInt(numJT.getText()));
				book.setUnitprice(Float.valueOf(vlJT.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "最后两项请输入数字");
				e1.printStackTrace();
			}
			BookDao bookDao = new BookDao();
			int r = bookDao.save(book);
			if (r == 1) {
				JOptionPane.showMessageDialog(this, "图书添加成功");
			} else {
				JOptionPane.showMessageDialog(this, "图书添加失败");
			}
		}
		if (e.getSource() == ISBNJT) {
			JOptionPane.showMessageDialog(this, "输入完成！");
		}
		if (e.getSource() == closeJB) {
			dispose();
		}
	}
}