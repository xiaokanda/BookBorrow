package top.vist.bookborrow.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import top.vist.bookborrow.dao.BookDao;
import top.vist.bookborrow.dao.BookTypeDao;


public class BookSelectAndUpdate extends JFrame implements ActionListener {

	JTextField chaxunJTF;
	JTabbedPane jtabbedPane;
	JPanel selectPanel, updatgePanel, conditionPanel, resultPanel, buttonPanel,
			infoPanel, buttonPanel1;
	private JComboBox JComboBox;
	private JScrollPane jscrollPane;
	private JTable jtable;
	private JButton button1, button2;

	private JTextField ISBNJT, lbieJT, bknJT, wrtnJT, cbsnJT, dateJT, numJT,
			vlJT;
	private JLabel ISBNJL, lbieJL, bknJL, wrtnJL, cbsnJL, dateJL, numJL, vlJL;
	private JButton moJB, closeJB;
	private JComboBox lbieJCB;
	private String[][] results;
	private String[] readersearch;

	public  BookSelectAndUpdate() {
		setTitle("图书查询与修改");
		setBounds(200, 200, 400, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jtabbedPane = new JTabbedPane();
		selectPanel = new JPanel();
		updatgePanel = new JPanel();

		jtabbedPane.addTab("图书信息查询", selectPanel);
		jtabbedPane.addTab("图书信息修改", updatgePanel);

		conditionPanel = new JPanel();
		JComboBox = new JComboBox();
		String[] array = { "ISBN", "作者", "类型", "名称" };
		for (int i = 0; i < array.length; i++) {
			JComboBox.addItem(array[i]);
		}
		conditionPanel.add(JComboBox);

		chaxunJTF = new JTextField(20);
		conditionPanel.add(chaxunJTF);
		selectPanel.add(conditionPanel, BorderLayout.NORTH);

		resultPanel = new JPanel();
		jscrollPane = new JScrollPane();
		jscrollPane.setPreferredSize(new Dimension(400, 200));
		readersearch = new String[] { "编号", "类型", "名称", "作者", "出版社", "出版日期",
				"出版次数", "单价" };
		BookDao bookDao = new BookDao();
		results =bookDao.getArrayData(bookDao.findAll());
		jtable = new JTable(results, readersearch);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jscrollPane.setViewportView(jtable);

		resultPanel.add(jscrollPane);
		selectPanel.add(resultPanel, BorderLayout.CENTER);

		buttonPanel = new JPanel();
		button1 = new JButton("查询");
		button2 = new JButton("退出");
		buttonPanel.add(button1);
		buttonPanel.add(button2);

		selectPanel.add(buttonPanel, BorderLayout.SOUTH);

		updatgePanel.setLayout(new BorderLayout());
		infoPanel = new JPanel();
		infoPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
		final GridLayout gridLayout = new GridLayout(8, 2);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		infoPanel.setLayout(gridLayout);

		ISBNJL = new JLabel("ISBN");
		ISBNJL.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(ISBNJL);
		ISBNJT = new JTextField();
		infoPanel.add(ISBNJT);

		lbieJL = new JLabel("类别：");
		lbieJL.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(lbieJL);
		// 下拉列表
		lbieJCB = new JComboBox();
		infoPanel.add(lbieJCB);
		List<String> data = new ArrayList<String>();
		BookTypeDao bookTypeDao = new BookTypeDao();
		data = bookTypeDao.findNameAll();
		Iterator<String> it = data.iterator();
		while (it.hasNext()) {
			lbieJCB.addItem(it.next());
		}

		wrtnJL = new JLabel("作者：");
		wrtnJL.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(wrtnJL);
		wrtnJT = new JTextField();
		infoPanel.add(wrtnJT);

		cbsnJL = new JLabel("出版社：");
		cbsnJL.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(cbsnJL);
		cbsnJT = new JTextField();
		infoPanel.add(cbsnJT);

		dateJL = new JLabel("出版日期：");
		dateJL.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(dateJL);
		dateJT = new JTextField();
		infoPanel.add(dateJT);

		numJL = new JLabel("印刷次数：");
		numJL.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(numJL);
		numJT = new JTextField();
		infoPanel.add(numJT);

		vlJL = new JLabel("单价：");
		vlJL.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(vlJL);
		vlJT = new JTextField();
		infoPanel.add(vlJT);

		updatgePanel.add(infoPanel, BorderLayout.CENTER);

		buttonPanel1 = new JPanel();
		moJB = new JButton("添加");
		closeJB = new JButton("关闭");
		buttonPanel1.add(moJB);
		buttonPanel1.add(closeJB);

		updatgePanel.add(buttonPanel1, BorderLayout.SOUTH);
		add(jtabbedPane);

		button1.addActionListener(this);
		button2.addActionListener(this);
		ISBNJT.addActionListener(this);
		moJB.addActionListener(this);
		closeJB.addActionListener(this);

		this.setVisible(true);// 设置窗体显示，否则不显示。
		setResizable(false);// 取消最大化
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ISBNJT) {
			 JOptionPane.showMessageDialog(this, "输入完成！");
			/*String ISBN = null;
			Book book = null;
			ISBN = ISBNJT.getText();
			book = BookDao.getBook(ISBN);
			if (book == null) {
				JOptionPane.showMessageDialog(this, "查无此书！");
			} else {
				// System.out.println(book.getTypename());
				lbieJCB.setSelectedItem(book.getTypename());
				wrtnJT.setText(book.getAuthor());
				cbsnJT.setText(book.getPublish());
				dateJT.setText(String.valueOf(book.getPublishDate()));
				numJT.setText(book.getPublishtime() + "");
				vlJT.setText(book.getUnitprice() + "");
			}*/

		}
		if (e.getSource() == moJB) {
			/*Book book = new Book();
			book.setISBN(ISBNJT.getText());
			book.setTypename((String) lbieJCB.getSelectedItem());
			book.setTypeid(BookDao.getBookTypeId(book.getTypename()));
			book.setAuthor(wrtnJT.getText());
			book.setPublish(cbsnJT.getText());
			book.setStrPublishDate(dateJT.getText());
			book.setPublishtime(Integer.parseInt(numJT.getText()));
			book.setUnitprice(Float.valueOf(vlJT.getText()));
			int row = BookDao.updateBook(book);
			if (row == 0) {
				JOptionPane.showMessageDialog(this, "添加失败！！");
			} else {
				JOptionPane.showMessageDialog(this, "添加成功！！");
			}*/
		}
		if (e.getSource() == closeJB || e.getSource() == button2) {
			dispose();
		}
		if (e.getSource() == button1) {
/*
			String type = (String) JComboBox.getSelectedItem();
			
			if (type.equals("ISBN")) {
				List<Book> books = BookDao.selectBookISBN(chaxunJTF.getText()
						.intern());
				results = BookDao.getselect(books);
			}
			if (type.equals("类型")) {
				List<Book> books = BookDao.selectBookTypeid(chaxunJTF.getText()
						.intern());
				results = BookDao.getselect(books);
			}
			if (type.equals("名称")) {
				List<Book> books = BookDao.selectBookName(chaxunJTF.getText()
						.intern());
				results = BookDao.getselect(books);
			}
			if (type.equals("作者")) {
				List<Book> books = BookDao.selectBookAuthor(chaxunJTF.getText()
						.intern());
				results = BookDao.getselect(books);
			}
			String str=chaxunJTF.getText();
			if(str.length()==0){
				results = BookDao.getArrayData(BookDao.selectReader());
				jtable = new JTable(results, readersearch);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jscrollPane.setViewportView(jtable);
			}

			jtable = new JTable(results, readersearch);
			jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jscrollPane.setViewportView(jtable);
*/
		}
	}

}