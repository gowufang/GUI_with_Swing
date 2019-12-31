package entity;
import java.io.Serializable;
/**
 * @author zhanglj
 * 用户实体
 *
 * 对象序列化 Serializable
 */
public class User implements Serializable {
	private int userid;
	private String name;
	private int deptid;
	private String password;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDeptid() {
		return deptid;
	}
	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}