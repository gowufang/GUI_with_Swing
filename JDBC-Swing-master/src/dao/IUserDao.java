package dao;
import java.util.List;
import entity.User;
/**
 * @author zhanglj
 *���ݷ��ʽӿ�
 */
public interface IUserDao {
	/**
	 * ���
	 * @param user
	 * @return
	 */
	boolean add(User user);
	/**
	 * �޸�
	 * @param user
	 * @return
	 */

	boolean update(User user);
	/**
	 * ɾ��
	 * @param userid
	 * @return
	 */
	boolean delete(int userid);
	
	/**
	 * ͨ��Id���� һ����¼
	 * @param userid
	 * @return
	 */
	User QueryById(int userid);
	
	/**
	 * ����ȫ����¼
	 * @return
	 */
	List<User> queryAll();
}