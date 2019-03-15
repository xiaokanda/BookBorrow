package top.vist.bookborrow.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

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


public class BookBorrow extends JFrame implements ActionListener {

	/**
	 * 图书借阅窗口
	 */
	private static final long serialVersionUID = 1L;

	private JPanel selectJP, conditionJP, resultJP, sexJP, updateJP, buttonJP;
	private JLabel readeridJL, readernameJL, readertypeJL, ISBNJL, typeJL,
			booknameJL, authorJL, pubJL, pubdateJL, numJL, unitJL, dateJL,
			dayJL, fineJL, adminJL;
	private JTextField readeridJTF, readernameJTF, readertypeJTF, ISBNJTF,
			typeJTF, booknameJTF, authorJTF, pubJTF, pubdateJTF, numJTF,
			unitJTF, dateJTF, dayJTF, fineJTF, adminJTF;

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
		selectJP.setBorder(new TitledBorder(null, "读书借阅信息",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		// 查询条件面板
		// 查询条件下拉列表框
		conditionJP = new JPanel();

		readeridJL = new JLabel("读者编号：");
		readeridJTF = new JTextField(8);

		readernameJL = new JLabel("读者姓名：");
		readernameJTF = new JTextField(8);
		readertypeJL = new JLabel("读者类别：");
		readertypeJTF = new JTextField(8);

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
		updateJP.setBorder(new TitledBorder(null, "图书借阅", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
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
		readeridJTF.addActionListener(this);
		ISBNJTF.addActionListener(this);
		this.setVisible(true);// 设置窗体显示，否则不显示。
		setResizable(false);// 取消最大化

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == borrowJB) {
			/*// JOptionPane.showMessageDialog(this, "借阅按钮被点击！");
			String readerid = null, ISBN = null, date = null;
			readerid = readeridJTF.getText();
			ISBN = ISBNJTF.getText();
			date = dateJTF.getText();
			if (readerid.equals("") || ISBN.equals("")) {
				JOptionPane.showMessageDialog(this, "读者编号或者ISBN为空");
			} else {
				int rows = BorrowBookDao.borrowBook(readerid, ISBN, date);
				if (rows != 0) {
					JOptionPane.showMessageDialog(this, "借阅添加成功");
					List<BorrowBook> bbks = BorrowBookDao
							.selectBorrowByReaderId(readerid);
					results = getArrayData(bbks);
					jtable = new JTable(results, readersearch);
					jtable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
					jscrollPane.setViewportView(jtable);

					resultJP.add(jscrollPane);
				} else {
					JOptionPane.showMessageDialog(this, "借阅添加失败");
				}
			}*/
		}
		if (e.getSource() == closeJB) {
			dispose();
		}
		if (e.getSource() == readeridJTF) {
			/*String readerid = null;
			readerid = readeridJTF.getText();
			if (BorrowBookDao.getRaderName(readerid) == null) {
				JOptionPane.showMessageDialog(this, "没该用户！");
				readeridJTF.setText(null);
			} else {
				readernameJTF.setText(BorrowBookDao.getRaderName(readerid));
				readertypeJTF.setText(BorrowBookDao.getRaderType(readerid));
				List<BorrowBook> bbks = BorrowBookDao
						.selectBorrowByReaderId(readerid);
				results = getArrayData(bbks);
				jtable = new JTable(results, readersearch);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
				jscrollPane.setViewportView(jtable);

				resultJP.add(jscrollPane);
			}*/
		}
		if (e.getSource() == ISBNJTF) {
			/*String ISBN = null;
			ISBN = ISBNJTF.getText();
			Book book = new Book();
			book = BorrowBookDao.getBook(ISBN);
			if (book == null) {
				JOptionPane.showMessageDialog(this, "查无此书！");
			} else {
				typeJTF.setText(book.getTypename());
				booknameJTF.setText(book.getBookName());
				authorJTF.setText(book.getAuthor());
				pubJTF.setText(book.getPublish());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				pubdateJTF.setText(df.format(book.getPublishDate()));
				numJTF.setText(book.getPublishtime() + "");
				unitJTF.setText(book.getUnitprice() + "");
			}*/

		}
	}

	/*// 将borrowbook 的list转化成一个字符串数组
	public String[][] getArrayData(List<BorrowBook> borrowbooks) {
		String[][] data = new String[borrowbooks.size()][3];

		for (int i = 0; i < borrowbooks.size(); i++) {
			BorrowBook bbk = borrowbooks.get(i);
			data[i][0] = bbk.getISBN();
			data[i][1] = bbk.getName();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			data[i][2] = format.format(bbk.getBorrowDate());
		}
		return data;
	}*/

}