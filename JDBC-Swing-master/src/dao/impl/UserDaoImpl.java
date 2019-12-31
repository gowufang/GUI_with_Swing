package dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.IUserDao;
import util.DBCon;
import entity.User;

public class UserDaoImpl implements IUserDao {
	DBCon util = new DBCon();

	@Override
	public boolean add(User user) {

		return util.update("insert into user(name,deptid,password) values(?,?,?)",
				user.getName(), user.getDeptid(), user.getPassword()) > 0;
	}

	@Override
	public boolean update(User user) {
		return util.update(
				"update user set name=?,deptid=?,password=? where userid=?",
				user.getName(), user.getDeptid(), user.getPassword(),
				user.getUserid()) > 0;
	}

	@Override
	public boolean delete(int userid) {
		
		return util.update("delete from user where userid=?", userid)>0;
	}

	@Override
	public User QueryById(int userid) {
		return _user(util.query("select * from user where userid=?", userid));
	}

	@Override
	public List<User> queryAll() {
		return  _list(util.query("select * from user"));
	}
	
	private User _user(ResultSet rs){
		//杩斿洖涓�釜user瀵硅薄
		User user=null;
		try {
			if(rs.next()){
				user=new User();
				user.setPassword(rs.getString("password"));
				user.setDeptid(rs.getInt("deptid"));
				user.setName(rs.getString("name"));
				user.setUserid(rs.getInt("userid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			util.closeAll();
		}
		return user;
	}
	
	private List<User> _list(ResultSet rs){
		//杩斿洖涓�釜鐢ㄦ埛鍒楄〃
		List<User> _list=new ArrayList<User>();
		try {
			while(rs.next()){
				User user=new User();
				user.setPassword(rs.getString("password"));
				user.setDeptid(rs.getInt("deptid"));
				user.setName(rs.getString("name"));
				user.setUserid(rs.getInt("userid"));
				_list.add(user);
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