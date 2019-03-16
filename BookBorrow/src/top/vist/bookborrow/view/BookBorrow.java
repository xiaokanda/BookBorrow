package top.vist.bookborrow.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import top.vist.bookborrow.dao.BookDao;
import top.vist.bookborrow.dao.BookTypeDao;
import top.vist.bookborrow.dao.BorrowBookDao;
import top.vist.bookborrow.dao.ReaderDao;
import top.vist.bookborrow.dao.ReaderTypeDao;
import top.vist.bookborrow.entity.Book;
import top.vist.bookborrow.entity.Reader;

public class BookBorrow extends JFrame implements ActionListener, FocusListener {

	/**
	 * 图书借阅窗口
	 */
	private static final long serialVersionUID = 1L;

	private JPanel selectJP, conditionJP, resultJP, sexJP, updateJP, buttonJP;
	private JLabel readeridJL, readernameJL, readertypeJL, ISBNJL, typeJL, booknameJL, authorJL, pubJL, pubdateJL,
			numJL, unitJL, dateJL, dayJL, fineJL, adminJL;
	private JTextField readeridJTF, readernameJTF, readertypeJTF, ISBNJTF, typeJTF, booknameJTF, authorJTF, pubJTF,
			pubdateJTF, numJTF, unitJTF, dateJTF, dayJTF, fineJTF, adminJTF;

	private ButtonGroup buttonGroup = new ButtonGroup();

	private JScrollPane jscrollPane;

	private JTable jtable;
	private JButton borrowJB, closeJB;
	private String[][] results = null;
	private String[] readersearch = null;

	public BookBorrow() {
		setBounds(200, 200, 600, 550);
		setTitle("图书借阅");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// 读者借阅信息查询面板设计
		selectJP = new JPanel();
		selectJP.setLayout(new BorderLayout());
		selectJP.setBorder(new TitledBorder(null, "读书借阅信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		// 查询条件面板
		// 查询条件下拉列表框
		conditionJP = new JPanel();

		readeridJL = new JLabel("读者编号：");
		readeridJTF = new JTextField(8);

		readernameJL = new JLabel("读者姓名：");
		readernameJTF = new JTextField(8);
		//readernameJTF.setEnabled(false);
		readertypeJL = new JLabel("读者类别：");
		readertypeJTF = new JTextField(8);
		//readertypeJTF.setEnabled(false);
		conditionJP.add(readeridJL);
		conditionJP.add(readeridJTF);
		conditionJP.add(readernameJL);
		conditionJP.add(readernameJTF);
		conditionJP.add(readertypeJL);
		conditionJP.add(readertypeJTF);

		selectJP.add(conditionJP, BorderLayout.NORTH);

		// 查询结果面板
		resultJP = new JPanel();
		jscrollPane = new JScrollPane();
		jscrollPane.setPreferredSize(new Dimension(400, 200));

		readersearch = new String[] { "图书编号", "图书名称", "借书日期" };
		results = new String[][] {};
		jtable = new JTable(results, readersearch);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		jscrollPane.setViewportView(jtable);

		resultJP.add(jscrollPane);
		selectJP.add(resultJP, BorderLayout.CENTER);

		// 读者信息修改面板设计
		updateJP = new JPanel();
		updateJP.setBorder(new EmptyBorder(5, 10, 5, 10));
		updateJP.setBorder(new TitledBorder(null, "图书借阅", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridLayout gridLayout = new GridLayout(5, 4);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		updateJP.setLayout(gridLayout);

		ISBNJL = new JLabel("ISBN");
		ISBNJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(ISBNJL);
		ISBNJTF = new JTextField();
		updateJP.add(ISBNJTF);

		typeJL = new JLabel("类别：");
		typeJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(typeJL);
		typeJTF = new JTextField();
		updateJP.add(typeJTF);

		booknameJL = new JLabel("书名：");
		booknameJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(booknameJL);
		booknameJTF = new JTextField();
		updateJP.add(booknameJTF);

		authorJL = new JLabel("作者：");
		authorJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(authorJL);
		authorJTF = new JTextField();
		updateJP.add(authorJTF);

		pubJL = new JLabel("出版社：");
		pubJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(pubJL);
		pubJTF = new JTextField();
		updateJP.add(pubJTF);

		pubdateJL = new JLabel("出版日期：");
		pubdateJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(pubdateJL);
		pubdateJTF = new JTextField();
		updateJP.add(pubdateJTF);

		numJL = new JLabel("印刷次数：");
		numJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(numJL);
		numJTF = new JTextField();
		updateJP.add(numJTF);

		unitJL = new JLabel("单价：");
		unitJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(unitJL);
		unitJTF = new JTextField();
		updateJP.add(unitJTF);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String str = df.format(new java.util.Date());

		dateJL = new JLabel("当前日期：");
		dateJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(dateJL);
		dateJTF = new JTextField(str);
		dateJTF.setEditable(false);
		updateJP.add(dateJTF);

		adminJL = new JLabel("操作人员：");
		adminJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(adminJL);
		adminJTF = new JTextField();
		adminJTF = new JTextField("admin");
		adminJTF.setEditable(false);
		updateJP.add(adminJTF);

		// 登录取消按钮面板设计
		buttonJP = new JPanel();// 修改按钮面板
		borrowJB = new JButton("借阅");
		closeJB = new JButton("关闭");
		buttonJP.add(borrowJB);
		buttonJP.add(closeJB);

		this.add(selectJP, BorderLayout.NORTH);
		this.add(updateJP, BorderLayout.CENTER);
		this.add(buttonJP, BorderLayout.SOUTH);

		borrowJB.addActionListener(this);
		closeJB.addActionListener(this);
		ISBNJTF.addFocusListener(this);
		readeridJTF.addFocusListener(this);
		this.setVisible(true);// 设置窗体显示，否则不显示。
		setResizable(false);// 取消最大化

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BorrowBookDao borrowBookDao = new BorrowBookDao();

		if (e.getSource() == borrowJB) {
			String readerid = readeridJTF.getText().intern();
			String ISBN = ISBNJTF.getText().intern();
			if ("".equals(readerid) || "".equals(ISBN)) {
				JOptionPane.showMessageDialog(this, "请输入读者编号和ISBN");
				return;
			}
			int row = borrowBookDao.save(readerid, ISBN);
			if (row == 1) {
				results = borrowBookDao.getArrayData(borrowBookDao.findBorrowByReaderId(readeridJTF.getText().intern()));
				jtable = new JTable(results, readersearch);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
				jscrollPane.setViewportView(jtable);
				JOptionPane.showMessageDialog(this, "添加成功！");
			} else {
				JOptionPane.showMessageDialog(this, "添加失败！");
			}
		}
		if (e.getSource() == closeJB) {
			dispose();
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		ReaderDao readerDao = new ReaderDao();
		BookDao bookDao = new BookDao();
		BookTypeDao bookTypeDao = new BookTypeDao();
		ReaderTypeDao readerTypeDao = new ReaderTypeDao();
		BorrowBookDao borrowBookDao = new BorrowBookDao();
		if (e.getSource() == readeridJTF) {
			if ("".equals(readeridJTF.getText().intern()))
				return;
			Reader reader = readerDao.findReaderById(readeridJTF.getText().intern());
			if (reader == null) {
				readeridJTF.setText(null);
				JOptionPane.showMessageDialog(this, "没该用户！");
			} else {
				readernameJTF.setText(reader.getName());
				readertypeJTF.setText(readerTypeDao.getNameById(reader.getType()));
				results = borrowBookDao.getArrayData(borrowBookDao.findBorrowByReaderId(reader.getReaderId()));
				jtable = new JTable(results, readersearch);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
				jscrollPane.setViewportView(jtable);
			}

		}
		if (e.getSource() == ISBNJTF) {
			if ("".equals(ISBNJTF.getText().intern()))
				return;
			Book book = bookDao.findBookByISBN(ISBNJTF.getText().intern());
			if (book == null) {
				ISBNJTF.setText(null);
				JOptionPane.showMessageDialog(this, "查无此书");
			} else {
				typeJTF.setText(bookTypeDao.findNameById(book.getTypeId()));
				booknameJTF.setText(book.getBookName());
				authorJTF.setText(book.getAuthor());
				pubJTF.setText(book.getPublish());
				pubdateJTF.setText(book.getStrPublishDate());
				numJTF.setText(book.getPublishNum() + "");
				unitJTF.setText(book.getUnitprice() + "");
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
	}


}