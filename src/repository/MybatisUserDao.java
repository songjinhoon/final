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
	
	//회원가입
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
	
	//이메일 체크
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

	//회원 이메일 조회
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
	
}
