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
	private DefaultTableModel model;// 用于存储表格数据
	private IDeptDao deptDao;
	private IUserDao userDao;
	private String oldValue = "";// 保存单元格编辑 前的值

	/**
	 * Create the panel.
	 */
	public QueryPanel() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();

		scrollPane.setColumnHeaderView(table);
		// 初始化存储表格数据的对象
		model = new DefaultTableModel(new Object[][] {}, new String[] { "用户编号",
				"用户名", "所属部门", "注册密码" });
		// 将数据绑定到对象中
		table.setModel(model);
		table.setRowHeight(30);

		scrollPane.setViewportView(table);

		deptDao = new DeptDaoImpl();
		userDao = new UserDaoImpl();
		loadData();
		// 为表格绑定修改值后的事件
		model.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getColumn() < 0)
					return;
				String nVal = table.getValueAt(e.getLastRow(), e.getColumn())
						.toString();
				// 如果旧的值 和新的值一样，直接 返回
				if (nVal.equals(oldValue)) {
					return;
				}
				// 判断当前编辑的单元格是否是主键列
				if (e.getColumn() == 0) {
					// 还原旧的值
					table.setValueAt(oldValue, e.getLastRow(), e.getColumn());
					return;
				}
				// 更新数据
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
		// 清除旧的数据
		model.getDataVector().clear();
		// 查询部门数据
		List<Dept> deptList = deptDao.queryAll();

		JComboBox cob = new JComboBox(deptList.toArray());
		// 创建一个使用下拉框代替编辑框的单元格对象
		DefaultCellEditor dept = new DefaultCellEditor(cob);
		// 获取表格的列model对象
		TableColumnModel col = table.getColumnModel();
		// 获取部门的列，设置这个列为下拉框列类型
		col.getColumn(2).setCellEditor(dept);
		// 查询所有的用户信息
		List<User> list = userDao.queryAll();
		// 遍历每一条数据，添加到model中
		int i = 0;
		for (User user : list) {
			// 添加 行数据
			cob.setSelectedItem(new Dept(user.getDeptid()));
			model.addRow(new Object[] { user.getUserid(), user.getName(),
					cob.getSelectedItem(), user.getPassword()});
		}
	}

	public void del() {
		if (table.getSelectedRowCount() <= 0) {
			JOptionPane.showMessageDialog(null, "请选择要删除的数据行");
			return;
		}
		int result = JOptionPane.showConfirmDialog(null, "是否确定要删除");
		// 判断用户是否点击
		if (result == JOptionPane.OK_OPTION) {
			int userid = Integer.valueOf(table.getValueAt(
					table.getSelectedRow(), 0).toString());
			userDao.delete(userid);
			loadData();
		}
	}

}