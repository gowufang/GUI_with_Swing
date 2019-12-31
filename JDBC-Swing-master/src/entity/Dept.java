package entity;
import java.io.Serializable;
public class Dept implements Serializable {
	private int deptid;
	private String deptname;

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public Dept(int deptid, String deptname) {
		super();
		this.deptid = deptid;
		this.deptname = deptname;
	}

	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return this.deptname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + deptid;
		result = prime * result
				+ ((deptname == null) ? 0 : deptname.hashCode());
		return result;
	}
	public Dept(int deptid){
		this.deptid=deptid;
	}
	@Override
	public boolean equals(Object obj) {
		Dept dept=(Dept) obj;
		return dept.getDeptid()==this.getDeptid();
	}
}