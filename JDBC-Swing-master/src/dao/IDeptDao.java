package dao;
import java.util.List;
import entity.Dept;
/**
 * @author zhanglj
 * 部门表数据访问接口
 *
 */
public interface IDeptDao {
	/**
	 * 查找全部部门
	 * @return
	 */
	//泛型让集合记住元素的类型
	List<Dept> queryAll();
}