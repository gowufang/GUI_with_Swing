package dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBCon;
import dao.IDeptDao;
import entity.Dept;

public class DeptDaoImpl implements IDeptDao{
	//util是数据库连接的实例对�?
	DBCon util=new DBCon();
	@Override
	//queryAll是部门表实现的方法，这里是具体实�?
	public List<Dept> queryAll() {
		// TODO Auto-generated method stub
		return _list(util.query("select * from dept"));
	}
	private List<Dept> _list(ResultSet rs){
		List<Dept> _list=new ArrayList<Dept>();
		//把从数据库读到的信息设置到每个对象，返回�?��_list集合，部门表的信�?
		try {
			while(rs.next()){
				Dept dept=new Dept();
				dept.setDeptid(rs.getInt("deptid"));
				dept.setDeptname(rs.getString("deptname"));
				_list.add(dept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.closeAll();
		}
		return _list;
	}

}