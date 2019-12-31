package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame implements ActionListener {

	 JPanel contentPane;
	 JPanel panelBottom;
	private JButton btnDel;
	private JButton btnAdd;
	private AddPanel add;
	private QueryPanel query;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 546, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnLoadData = new JButton("��ȡ����");
		btnLoadData.setActionCommand("loaddata");
		panel.add(btnLoadData);
		
		btnDel = new JButton("ɾ����ѡ");
		panel.add(btnDel);
		btnDel.setActionCommand("del");
		
		btnAdd = new JButton("����û�");
		panel.add(btnAdd);
		btnAdd.setActionCommand("add");
		
		panelBottom = new JPanel();
		panelBottom.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panelBottom, BorderLayout.CENTER);
		panelBottom.setLayout(new BorderLayout(0,0));
		
		btnDel.addActionListener(this);
		btnLoadData.addActionListener(this);
		btnAdd.addActionListener(this);

		query=new QueryPanel();
		panelBottom.add(query,BorderLayout.CENTER);
		panelBottom.updateUI();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		System.out.println(command);
		if(command.equals("add")){
			panelBottom.removeAll();
			add=new AddPanel();
			panelBottom.add(add,BorderLayout.CENTER);
			panelBottom.updateUI();
		}else if(command.equals("loaddata")){
			panelBottom.removeAll();
			query=new QueryPanel();
			panelBottom.add(query,BorderLayout.CENTER);
			panelBottom.updateUI();
		}else if(command.equals("del")){
			if(query==null){
				JOptionPane.showMessageDialog(null, "û������");
				return;
			}
			query.del();
		}
		
	}

}