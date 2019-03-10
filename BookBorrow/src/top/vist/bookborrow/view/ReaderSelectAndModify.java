package top.vist.bookborrow.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import top.vist.bookborrow.entity.Reader;

//-------------实现接口-----------------//
public class ReaderSelectAndModify extends JFrame implements MouseListener{	
	private static final long serialVersionUID = 1L;
	
	private JPanel selectJP,select_conditionJP,select_resultJP,sexJP,updateJP,buttonJP;    
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel IDJL,typeJL,readerNameJL,sexJL,phoneJL,deptJL,ageJL,regJL;
	private JTextField select_conditionJTF,IDJTF,readerNameJTF,phoneJTF,deptJTF,ageJTF,regJTF;
	private JComboBox conditionJCB,readertypeJCB;
	private JScrollPane jscrollPane;
	
	private JRadioButton JRB1,JRB2;
	private JTable jtable;
	private JButton selectJB,updateJB,closeJB;
	
	String[][] results = null;
	
	public ReaderSelectAndModify(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 500, 500); 
		setTitle("读者信息查询与修改");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//读者信息查询面板设计
		selectJP = new JPanel();
        selectJP.setLayout(new BorderLayout());
        
        //查询条件面板
        //查询条件下拉列表框
        select_conditionJP = new JPanel();
        conditionJCB = new JComboBox();
        String[] array = { "读者编号","姓名","类型","系部"};
        for (int i = 0; i < array.length; i++) {
            conditionJCB.addItem(array[i]);
        }
        select_conditionJP.add(conditionJCB);
        //查询条件文本框
        select_conditionJTF = new JTextField();
        select_conditionJTF.setColumns(20);
        select_conditionJP.add(select_conditionJTF);
        //查询条件按钮
        selectJB = new JButton();
        selectJB.setText("查询");        
        select_conditionJP.add(selectJB);
        
        selectJP.add(select_conditionJP, BorderLayout.NORTH);
        
        //查询结果面板
        select_resultJP = new JPanel();
        jscrollPane = new JScrollPane();
        jscrollPane.setPreferredSize(new Dimension(300, 200));       
        String[] readersearch = { "编号", "类型", "姓名","年龄", "性别", "电话", "系部", "注册日期" };
        results=new String[][]{{"11301121","学生","金鑫","22","男","18632159876","信息","2011-9-8"},
    			{"11301122","教师","李福林","23","男","18642156876","信息","2011-9-8"},
    			{"11301105","学生","李媛媛","22","女","18643625476","信息","2011-9-8"}};
        jtable = new JTable(results, readersearch);
        jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);        
        jscrollPane.setViewportView(jtable);              
        
        select_resultJP.add(jscrollPane);
        selectJP.add(select_resultJP,BorderLayout.CENTER);
		
        //读者信息修改面板设计
		updateJP = new JPanel();
		updateJP.setBorder(new EmptyBorder(5, 10, 5, 10));
        GridLayout gridLayout = new GridLayout(4, 4);
        gridLayout.setVgap(10);
        gridLayout.setHgap(10);
        updateJP.setLayout(gridLayout);
               		
		IDJL=new JLabel("编号：");
		IDJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(IDJL);
		IDJTF=new JTextField();
		updateJP.add(IDJTF);	
		
		readerNameJL=new JLabel("姓名：");
		readerNameJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(readerNameJL);
		readerNameJTF=new JTextField();
		updateJP.add(readerNameJTF);	
		
		typeJL=new JLabel("类别：");
		typeJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(typeJL);
		//下拉列表
		readertypeJCB = new JComboBox();
		readertypeJCB.addItem("教师");
		readertypeJCB.addItem("学生");
		updateJP.add(readertypeJCB);			
		
		sexJL=new JLabel("性别：");
		sexJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(sexJL);
		sexJP = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(0);
        flowLayout.setVgap(0);
        sexJP.setLayout(flowLayout);       
        
        JRB1 = new JRadioButton();
        sexJP.add(JRB1);
        JRB1.setSelected(true);
        buttonGroup.add(JRB1);
        JRB1.setText("男");

        JRB2 = new JRadioButton();
        sexJP.add(JRB2);       
        buttonGroup.add(JRB2);
        JRB2.setText("女");	      
        updateJP.add(sexJP);
        
		ageJL=new JLabel("年龄：");
		ageJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(ageJL);
		ageJTF=new JTextField();
		updateJP.add(ageJTF);	
		
		phoneJL=new JLabel("电话：");
		phoneJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(phoneJL);
		phoneJTF=new JTextField();
		updateJP.add(phoneJTF);
				
		deptJL=new JLabel("所在部门：");
		deptJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(deptJL);
		deptJTF=new JTextField();
		updateJP.add(deptJTF);		
		
		regJL=new JLabel("注册日期：");
		regJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(regJL);
		regJTF=new JTextField();		
	    updateJP.add(regJTF);
		//登录取消按钮面板设计
	    buttonJP=new JPanel();//修改按钮面板
		updateJB=new JButton("修改");		
		closeJB=new JButton("关闭");
		buttonJP.add(updateJB);
		buttonJP.add(closeJB);
				
		this.add(selectJP,BorderLayout.NORTH);
		this.add(updateJP,BorderLayout.CENTER);
		this.add(buttonJP,BorderLayout.SOUTH);		
		
		this.setVisible(true);//设置窗体显示，否则不显示。
		setResizable(false);//取消最大化
		
		//-------------添加监听器---------------
		jtable.addMouseListener(this);
	}
	
	 /*public static void main(String[] args) {
		  new ReaderSelectandModify();
	  }*/

	 //------------编写事件处理代码-----------
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==jtable){
			int row = jtable.getSelectedRow();
			IDJTF.setText(results[row][0]);
			readertypeJCB.setSelectedItem(results[row][1]);
			readerNameJTF.setText(results[row][2]);
			if("男".equals(results[row][4])){
				JRB1.setSelected(true);
				JRB2.setSelected(false);
			} else{
				JRB1.setSelected(false);
				JRB2.setSelected(true);
			}
				
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
	
	public String[][] getArrayData(List<Reader> readers){
		String [][] data = new String[readers.size()][8];
		
		for(int i=0;i<readers.size();i++){
			Reader r = readers.get(i);
			data[i][0] = r.getReaderId();
			data[i][1] = r.getType()+"";
			//.....
		}
		return data;
	}
}
