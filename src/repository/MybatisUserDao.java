package repository;


import org.apache.ibatis.session.SqlSession;

import model.User;
import mybatis.AbstractRepository;

public class MybatisUserDao extends AbstractRepository{

	private final String namespace = "mybatis.UserMapper";
	private static MybatisUserDao instance = new MybatisUserDao();
	
	private MybatisUserDao() {	}
	
	public static MybatisUserDao getInstance() {
		return instance;
	}
	
	//ȸ������
	public void joinUser(User user) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		String statement = null;
		try {
			statement = namespace + ".joinUser";
			sqlSession.insert(statement, user);
			sqlSession.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
	}
	
	//�̸��� üũ
	public int getUserEmailChecked(String userId) {
		int checked = 0;
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		String statement = null;
		try {
			statement = namespace + ".getUserEmailChecked";
			checked = sqlSession.selectOne(statement, userId);
			if(checked==1) {
				checked = 1;
			}else {
				checked = 0;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return checked;
	}

	//ȸ�� �̸��� ��ȸ
	public String getUserEmail(String userId) {
		String userEmail = null;
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		String statement = null;
		
		try {
			statement = namespace + ".getUserEmail";
			userEmail = sqlSession.selectOne(statement, userId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return userEmail;
	}
	
	//�̸��� ���� Ȯ��
	public void setUserEmailChecked(String userId) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		String statement = null;
		try {
			statement = namespace + ".setUserEmailChecked";
			sqlSession.update(statement, userId);
			sqlSession.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
	}
	
	//ID�ߺ� ��ȸ
	public int getUserIdCheck(String userId) {
		int checked = 0;
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		String statement = null;
		String userIdCheck = "";
		// System.out.println(userId);
		try {
			statement = namespace + ".getUserIdCheck";
			userIdCheck = sqlSession.selectOne(statement, userId);
			
			if(userIdCheck == null) {
				userIdCheck = "";
			}
			
			System.out.println(userIdCheck);
			System.out.println(userId);
			
			
			if(!userIdCheck.equals(userId))
			{
				checked = 0;
			}else {
				checked = 1;
			}
			System.out.println(checked);
			
			return checked;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return checked;
	}
	
	//�α���
	public String Login(User user) {
		String userId = null;
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		String statement = null;
		
		try {
			statement = namespace + ".Login";
			userId = sqlSession.selectOne(statement, user);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return userId;
	}
}
