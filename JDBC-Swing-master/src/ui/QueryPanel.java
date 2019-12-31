package ui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import dao.IDeptDao;
import dao.IUserDao;
import dao.impl.DeptDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Dept;
import entity.User;

public class QueryPanel extends JPanel {
	private JTable table;
	private DefaultTableModel model;// ���ڴ洢�������
	private IDeptDao deptDao;
	private IUserDao userDao;
	private String oldValue = "";// ���浥Ԫ��༭ ǰ��ֵ

	/**
	 * Create the panel.
	 */
	public QueryPanel() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();

		scrollPane.setColumnHeaderView(table);
		// ��ʼ���洢������ݵĶ���
		model = new DefaultTableModel(new Object[][] {}, new String[] { "�û����",
				"�û���", "��������", "ע������" });
		// �����ݰ󶨵�������
		table.setModel(model);
		table.setRowHeight(30);

		scrollPane.setViewportView(table);

		deptDao = new DeptDaoImpl();
		userDao = new UserDaoImpl();
		loadData();
		// Ϊ�����޸�ֵ����¼�
		model.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getColumn() < 0)
					return;
				String nVal = table.getValueAt(e.getLastRow(), e.getColumn())
						.toString();
				// ����ɵ�ֵ ���µ�ֵһ����ֱ�� ����
				if (nVal.equals(oldValue)) {
					return;
				}
				// �жϵ�ǰ�༭�ĵ�Ԫ���Ƿ���������
				if (e.getColumn() == 0) {
					// ��ԭ�ɵ�ֵ
					table.setValueAt(oldValue, e.getLastRow(), e.getColumn());
					return;
				}
				// ��������
				User user = new User();
				user.setUserid(Integer.valueOf(table.getValueAt(e.getLastRow(),
						0).toString()));
				user.setName(table.getValueAt(e.getLastRow(), 1).toString());
				user.setPassword(table.getValueAt(e.getLastRow(), 3).toString());
				Dept dept = (Dept) table.getValueAt(e.getLastRow(), 2);
				user.setDeptid(dept.getDeptid());

				userDao.update(user);

				loadData();
			}
		});
	}

	public void loadData() {
		// ����ɵ�����
		model.getDataVector().clear();
		// ��ѯ��������
		List<Dept> deptList = deptDao.queryAll();

		JComboBox cob = new JComboBox(deptList.toArray());
		// ����һ��ʹ�����������༭��ĵ�Ԫ�����
		DefaultCellEditor dept = new DefaultCellEditor(cob);
		// ��ȡ������model����
		TableColumnModel col = table.getColumnModel();
		// ��ȡ���ŵ��У����������Ϊ������������
		col.getColumn(2).setCellEditor(dept);
		// ��ѯ���е��û���Ϣ
		List<User> list = userDao.queryAll();
		// ����ÿһ�����ݣ���ӵ�model��
		int i = 0;
		for (User user : list) {
			// ��� ������
			cob.setSelectedItem(new Dept(user.getDeptid()));
			model.addRow(new Object[] { user.getUserid(), user.getName(),
					cob.getSelectedItem(), user.getPassword()});
		}
	}

	public void del() {
		if (table.getSelectedRowCount() <= 0) {
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ����������");
			return;
		}
		int result = JOptionPane.showConfirmDialog(null, "�Ƿ�ȷ��Ҫɾ��");
		// �ж��û��Ƿ���
		if (result == JOptionPane.OK_OPTION) {
			int userid = Integer.valueOf(table.getValueAt(
					table.getSelectedRow(), 0).toString());
			userDao.delete(userid);
			loadData();
		}
	}

}