package dao;
import java.util.List;
import entity.Dept;
/**
 * @author zhanglj
 * ���ű����ݷ��ʽӿ�
 *
 */
public interface IDeptDao {
	/**
	 * ����ȫ������
	 * @return
	 */
	//�����ü��ϼ�סԪ�ص�����
	List<Dept> queryAll();
}