package top.vist.bookborrow.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import top.vist.bookborrow.dao.ReaderDao;
import top.vist.bookborrow.dao.ReaderTypeDao;
import top.vist.bookborrow.entity.Reader;

public class ReaderAdd extends JFrame implements ActionListener, FocusListener {
	private static final long serialVersionUID = 1L;

	private JPanel readerAddJP, sexJP, buttonJP;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton JRB1, JRB2;
	private JLabel IDJL, categoryJL, readerNameJL, sexJL, phoneJLabel, deptJLabel, ageJLabel, regJLabel;
	private JTextField IDJTF, readerNameJTF, phoneJTF, deptJTF, ageJTF, regtimeJTF;
	private JComboBox readertypeJCB;
	private JButton addJB, closeJB;

	public ReaderAdd() {
		setBounds(200, 200, 500, 200);
		setTitle("读者信息添加");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// 登录取消按钮面板
		buttonJP = new JPanel();

		// 读者信息添加面板设计
		readerAddJP = new JPanel();
		readerAddJP.setBorder(new EmptyBorder(5, 10, 5, 10));
		final GridLayout gridLayout = new GridLayout(4, 4);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		readerAddJP.setLayout(gridLayout);
		getContentPane().add(readerAddJP);

		IDJL = new JLabel("编号：");
		IDJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(IDJL);
		IDJTF = new JTextField();
		readerAddJP.add(IDJTF);

		readerNameJL = new JLabel("姓名：");
		readerNameJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(readerNameJL);
		readerNameJTF = new JTextField();
		readerAddJP.add(readerNameJTF);

		categoryJL = new JLabel("类别：");
		categoryJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(categoryJL);
		// 下拉列表
		readertypeJCB = new JComboBox();
		readerAddJP.add(readertypeJCB);

		List<String> data = new ArrayList<>();
		ReaderTypeDao readerTypeDao = new ReaderTypeDao();
		data = readerTypeDao.findNameAll();
		Iterator<String> it = data.iterator();
		while (it.hasNext()) {
			readertypeJCB.addItem(it.next());
		}

		sexJL = new JLabel("性别：");
		sexJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(sexJL);
		sexJP = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		sexJP.setLayout(flowLayout);
		readerAddJP.add(sexJP);

		JRB1 = new JRadioButton();
		sexJP.add(JRB1);
		JRB1.setSelected(true);
		buttonGroup.add(JRB1);
		JRB1.setText("男");

		JRB2 = new JRadioButton();
		sexJP.add(JRB2);
		buttonGroup.add(JRB2);
		JRB2.setText("女");

		ageJLabel = new JLabel("年龄：");
		ageJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(ageJLabel);
		ageJTF = new JTextField();
		readerAddJP.add(ageJTF);

		phoneJLabel = new JLabel("电话：");
		phoneJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(phoneJLabel);
		phoneJTF = new JTextField();
		readerAddJP.add(phoneJTF);

		deptJLabel = new JLabel("所在部门：");
		deptJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(deptJLabel);
		deptJTF = new JTextField();
		readerAddJP.add(deptJTF);

		regJLabel = new JLabel("注册日期：");
		regJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(regJLabel);
		regtimeJTF = new JTextField();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(new java.util.Date());
		regtimeJTF.setText(str);
		regtimeJTF.setEditable(false);
		readerAddJP.add(regtimeJTF);

		// 登录取消按钮面板设计
		addJB = new JButton("添加");
		closeJB = new JButton("关闭");
		buttonJP.add(addJB);
		buttonJP.add(closeJB);

		addJB.addActionListener(this);
		closeJB.addActionListener(this);
		IDJTF.addFocusListener(this);

		this.add(readerAddJP, BorderLayout.CENTER);
		this.add(buttonJP, BorderLayout.SOUTH);

		this.setVisible(true);// 设置窗体显示，否则不显示。
		setResizable(false);// 取消最大化
	}

	public static void main(String[] args) {
		new ReaderAdd();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Reader reader = new Reader();
		reader.setReaderId(IDJTF.getText());
		if (reader.getReaderId() == null || "".equals(reader.getReaderId())) {
			JOptionPane.showMessageDialog(this, "编号不能为空！");
			return;
		}
		// 读者类型编号获取过程
		String typename = (String) readertypeJCB.getSelectedItem();
		ReaderTypeDao readerTypeDao = new ReaderTypeDao();
		// 查询typeId
		int typeId = readerTypeDao.findIdByName(typename);
		reader.setType(typeId);
		reader.setName(readerNameJTF.getText());
		String sex = "";
		if (JRB1.isSelected()) {
			sex = JRB1.getText();
		} else {
			sex = JRB2.getText();
		}
		reader.setSex(sex);
		int age = 0;
		try {
			Integer.valueOf(ageJTF.getText());
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, "请输入正确的年龄！");
			return;
		}
		reader.setAge(age);
		reader.setPhone(phoneJTF.getText());
		reader.setDapt(deptJTF.getText());
		ReaderDao readerDao = new ReaderDao();
		int row = readerDao.addReader(reader);
		if (row == 1) {
			dispose();
			JOptionPane.showMessageDialog(this, "读者添加成功！");
		} else {
			IDJTF.setText(null);
			readerNameJTF.setText(null);
			ageJTF.setText(null);
			phoneJTF.setText(null);
			deptJTF.setText(null);
			JOptionPane.showMessageDialog(this, "读者添加失败！");
		}
		if (e.getSource() == closeJB) {
			dispose();
		}

	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	// 判定读着编号是否可用
	public void focusLost(FocusEvent e) {
		if (e.getSource() == IDJTF) {
			String readerId = IDJTF.getText();
			if (readerId != null) {
				ReaderDao readerDao = new ReaderDao();
				int row = readerDao.findByReaderId(readerId);
				if (row == 1)
					JOptionPane.showMessageDialog(this, "该编号已被占用！");
			}
			if (readerId.length() > 8) {
				IDJTF.setText(null);
				JOptionPane.showMessageDialog(this, "编号的长度小于等于8位！");
			}
		}
	}

}
