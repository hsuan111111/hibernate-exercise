package core.dao;

import java.util.List;

import org.hibernate.Session;

import static core.util.HibernateUtil.getSessionFactory;;

public interface CoreDao<P, I> {
	int insert(P pojo);

	int deleteById(I id);

	int update(P pojo);

	P selectById(I id);

	List<P> selectAll();
	
	default Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
}
