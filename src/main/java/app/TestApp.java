package app;

import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import core.util.HibernateUtil;
import web.member.pojo.Member;

public class TestApp {
	public static void main(String[] args) {
//		TestApp app = new TestApp();
//		Member member = new Member();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		//Select USERNAME, NICKNAME
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
		//from Member
		Root<Member> root = criteriaQuery.from(Member.class);
		//where USENSME = ? and PASSWORD = ?
		criteriaQuery.where(criteriaBuilder.and(
				criteriaBuilder.equal(root.get("username"), "admin"),
				criteriaBuilder.equal(root.get("password"),"P@ssw0rd")
				));
		
		//select USERNAME,NICKNAME
		criteriaQuery.multiselect(root.get("username"), root.get("nickname"));
		//order by ID
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
		
		Member member = session.createQuery(criteriaQuery).uniqueResult();
		System.out.println(member.getNickname());
		
//		member.setId(1);
//		member.setRoleId(2);
//		System.out.println(app.selectById(2).getNickname());
//		for(Member member2 : app.selectAll()) {
//			System.out.println(member2.getNickname());
//			System.out.println(member2.getUsername());
//		}
//		 app.selectAll().forEach(member -> System.out.println(member.getNickname()));
		
		
	 
//		member.setUsername("使用者名稱");
//		member.setPassword("密碼");
//		member.setNickname("暱稱");
//		app.insert(member);
//		System.out.println(member.getId());
		
//		System.out.println(app.deleteById(4));
	}
	public Integer insert(Member member) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(member);
			transaction.commit();
			return member.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}
//	public int deleteById(Integer id) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//			Member member = session.get(Member.class, id);
//			session.remove(member);
//			transaction.commit();
//			return 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			return -1;
//		}
//	}
	public int updateById(Member newMember) {   //有多個參數可用VO
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			//若有傳pass 就傳此pass設定給oldMember
			Member oldMember = session.get(Member.class,newMember.getId());
			final Boolean pass = newMember.getPass();
			if (pass != null) {
				oldMember.setPass(pass);
			}
			final Integer roleId = newMember.getRoleId();
			if(roleId != null) {
				oldMember.setRoleId(roleId);
			}
			transaction.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}
	}
	
	public Member selectById(Integer id) {   //有多個參數可用VO
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member member = session.get(Member.class,id);
			transaction.commit();
			return member;
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}
	
	public List <Member> selectAll() {   
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<Member> query = session.createQuery("SELECT new web.member.pojo.Member (username, nickname) FROM Member", Member.class);
			List<Member> List = query.getResultList();
 			transaction.commit();
			return List;
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}
}

