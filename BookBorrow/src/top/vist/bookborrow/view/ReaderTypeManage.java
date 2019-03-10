package top.vist.bookborrow.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import top.vist.bookborrow.dao.ReaderTypeDao;
import top.vist.bookborrow.entity.Reader;
import top.vist.bookborrow.entity.ReaderType;

/*
 * 读者类型管理
 * */
public class ReaderTypeManage extends JFrame implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;

	private JPanel selectJP, select_conditionJP, select_resultJP, sexJP, updateJP, buttonJP;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel IDJL, typeJL, readerNameJL, sexJL, phoneJL, deptJL, ageJL, regJL, jlabel;
	private JTextField select_conditionJTF, IDJTF, readerNameJTF, phoneJTF, deptJTF, ageJTF, regJTF;
	private JComboBox conditionJCB, readertypeJCB;
	private JScrollPane jscrollPane;

	private JRadioButton JRB1, JRB2;
	private JTable jtable, jtable1;
	private JButton selectJB, updateJB, closeJB, insertbutton, delbutton;
	private String[][] results = null, date;
	String[] readersearch;

	public ReaderTypeManage() {
		setBounds(200, 200, 500, 400);
		setTitle("读者类型管理");

		// 读者信息查询面板设计
		selectJP = new JPanel();
		selectJP.setLayout(new BorderLayout());

		// 查询读者类型面板
		select_conditionJP = new JPanel();
		jlabel = new JLabel("读者类型");
		select_conditionJP.add(jlabel);

		// 查询条件文本框
		select_conditionJTF = new JTextField();
		select_conditionJTF.setColumns(20);
		select_conditionJP.add(select_conditionJTF);

		// 查询条件按钮
		selectJB = new JButton();
		selectJB.setText("查询");
		selectJB.addActionListener(this);
		select_conditionJP.add(selectJB);

		selectJP.add(select_conditionJP, BorderLayout.NORTH);

		// 查询结果面板
		select_resultJP = new JPanel();
		jscrollPane = new JScrollPane();
		jscrollPane.setPreferredSize(new Dimension(400, 200));
		readersearch = new String[] { "读者类型编号", "读者类型名称", "可借图书数量", "可借图书期限" };
		ReaderTypeDao readerTypeDao = new ReaderTypeDao();
		results = readerTypeDao.getArrayData(readerTypeDao.findAll());
		// results=new String[][]{{"1","学生","3","10"},{"2","教师","6","30"}};
		jtable = new JTable(results, readersearch);
		// jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jtable.setAutoResizeMode(2);
		jtable.addMouseListener(this);// 对表格添加鼠标监听事件
		jscrollPane.setViewportView(jtable);
		select_resultJP.add(jscrollPane);
		selectJP.add(select_resultJP, BorderLayout.CENTER);

		// 读者信息修改面板设计
		updateJP = new JPanel();
		updateJP.setBorder(new EmptyBorder(5, 10, 5, 10));
		GridLayout gridLayout = new GridLayout(2, 2);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		updateJP.setLayout(gridLayout);

		IDJL = new JLabel("读者类型编号：");
		IDJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(IDJL);
		IDJTF = new JTextField();
		IDJTF.setEditable(false);

		updateJP.add(IDJTF);

		readerNameJL = new JLabel("读者类型名称：");
		readerNameJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(readerNameJL);
		readerNameJTF = new JTextField();

		updateJP.add(readerNameJTF);

		ageJL = new JLabel("可借图书数量：");
		ageJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(ageJL);
		ageJTF = new JTextField();

		updateJP.add(ageJTF);

		phoneJL = new JLabel("可借图书期限：");
		phoneJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(phoneJL);
		phoneJTF = new JTextField();

		updateJP.add(phoneJTF);

		// 登录取消按钮面板设计
		buttonJP = new JPanel();// 修改按钮面板
		updateJB = new JButton("修改");
		updateJB.addActionListener(this);
		closeJB = new JButton("退出");
		closeJB.addActionListener(this);
		insertbutton = new JButton("添加");
		insertbutton.addActionListener(this);
		delbutton = new JButton("删除");
		delbutton.addActionListener(this);
		buttonJP.add(insertbutton);
		buttonJP.add(updateJB);
		buttonJP.add(delbutton);
		buttonJP.add(closeJB);

		this.add(selectJP, BorderLayout.NORTH);
		this.add(updateJP, BorderLayout.CENTER);
		this.add(buttonJP, BorderLayout.SOUTH);

		this.setVisible(true);// 设置窗体显示，否则不显示。
		setResizable(false);// 取消最大化
	}

	// 搜索读者类型
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == selectJB) {
			ReaderTypeDao readerTypeDao = new ReaderTypeDao();
			results = readerTypeDao.getArrayData(readerTypeDao.search(select_conditionJTF.getText().intern()));

			jtable = new JTable(results, readersearch);
			jtable.setAutoResizeMode(2);
			jtable.addMouseListener(this);// 对表格添加鼠标监听事件
			jscrollPane.setViewportView(jtable);
			select_resultJP.add(jscrollPane);
			// JOptionPane.showMessageDialog(this, "查询");
		}
		// 插入读者类型
		if (e.getSource() == insertbutton) {
			ReaderType readerType = new ReaderType();
			readerType.setTypeName(readerNameJTF.getText());
			if (readerType.getTypeName() == null || "".equals(readerType.getTypeName())) {
				JOptionPane.showMessageDialog(this, "类别名不能为空");
				return;
			}
			try {
				readerType.setMaxBorrowNum(Integer.parseInt(ageJTF.getText()));
				readerType.setLimit(Integer.parseInt(phoneJTF.getText()));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "请正确的借阅数和借阅时间！");
				return;
			}
			ReaderTypeDao readerTypeDao = new ReaderTypeDao();
			int row = readerTypeDao.save(readerType);
			if (row == 1) {
				results = readerTypeDao.getArrayData(readerTypeDao.findAll());
				jtable = new JTable(results, readersearch);
				jtable.setAutoResizeMode(2);
				jtable.addMouseListener(this);// 对表格添加鼠标监听事件
				jscrollPane.setViewportView(jtable);
				select_resultJP.add(jscrollPane);
				readerNameJTF.setText(null);
				ageJTF.setText(null);
				phoneJTF.setText(null);
				JOptionPane.showMessageDialog(this, "读者类别添加成功！");
			} else {
				JOptionPane.showMessageDialog(this, "读者类别添加失败！");
			}

		}
		// 更新读者类型信息
		if (e.getSource() == updateJB) {
			ReaderType readerType = new ReaderType();
			readerType.setId(Integer.valueOf(IDJTF.getText()));
			readerType.setTypeName(readerNameJTF.getText());
			if (readerType.getTypeName() == null || "".equals(readerType.getTypeName())) {
				JOptionPane.showMessageDialog(this, "类别名不能为空");
				return;
			}
			try {
				readerType.setMaxBorrowNum(Integer.parseInt(ageJTF.getText()));
				readerType.setLimit(Integer.parseInt(phoneJTF.getText()));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "请正确的借阅数和借阅时间！");
				return;
			}
			ReaderTypeDao readerTypeDao = new ReaderTypeDao();
			int row = readerTypeDao.update(readerType);
			if (row == 1) {
				results = readerTypeDao.getArrayData(readerTypeDao.findAll());
				jtable = new JTable(results, readersearch);
				jtable.setAutoResizeMode(2);
				jtable.addMouseListener(this);// 对表格添加鼠标监听事件
				jscrollPane.setViewportView(jtable);
				select_resultJP.add(jscrollPane);
				readerNameJTF.setText(null);
				ageJTF.setText(null);
				phoneJTF.setText(null);
				IDJTF.setText(null);
				JOptionPane.showMessageDialog(this, "读者类别修改成功！");
			} else {
				JOptionPane.showMessageDialog(this, "读者类别修改失败！");
			}
		}
		// 删除读者类型
		if (e.getSource() == delbutton) {
			ReaderType readerType = new ReaderType();
			readerType.setId(Integer.valueOf(IDJTF.getText()));
			ReaderTypeDao readerTypeDao = new ReaderTypeDao();
			int row = readerTypeDao.delete(readerType);
			if (row == 1) {
				results = readerTypeDao.getArrayData(readerTypeDao.findAll());
				jtable = new JTable(results, readersearch);
				jtable.setAutoResizeMode(2);
				jtable.addMouseListener(this);// 对表格添加鼠标监听事件
				jscrollPane.setViewportView(jtable);
				select_resultJP.add(jscrollPane);
				readerNameJTF.setText(null);
				ageJTF.setText(null);
				phoneJTF.setText(null);
				IDJTF.setText(null);
				JOptionPane.showMessageDialog(this, "读者类别删除成功！");
			} else {
				JOptionPane.showMessageDialog(this, "读者类别删除失败！");
			}
		}
		// 关闭窗口
		if (e.getSource() == closeJB) {
			dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == jtable) {
			int row = jtable.getSelectedRow();
			IDJTF.setText(results[row][0]);
			readerNameJTF.setText(results[row][1]);
			ageJTF.setText(results[row][2]);
			phoneJTF.setText(results[row][3]);
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
